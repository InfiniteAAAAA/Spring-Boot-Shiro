package com.infinite.service.impl;

import com.infinite.pojo.CreateCustomerReq;
import com.infinite.pojo.CreateCustomerRes;
import com.infinite.pojo.OpenCustomerReq;
import com.infinite.pojo.OpenCustomerRes;
import com.infinite.service.CustomerService;
import com.infinite.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Value("${createCustomer.path}")
    private String filePathParent;
    @Value("${createCustomer.fileName}")
    private String fileName;
    @Value("${createCustomer.passwordFile}")
    private String passwordFile;
    @Override
    public CreateCustomerRes createCustomer(CreateCustomerReq createCustomerReq) {
        CreateCustomerRes createCustomerRes = new CreateCustomerRes();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        String filePath = filePathParent + File.separator + date +fileName;
        String passwordFilePath = filePathParent + File.separator + date +passwordFile;
        String agentIdStr = createCustomerReq.getAgentIdStr();
        String userIdStr = createCustomerReq.getUserIdStr();
        //获取信息
        String[] userIdArray = userIdStr.split("\r\n");
        String[] agentIdArray = agentIdStr.split("\r\n");
        //校验数据是否数目是否相同
        if (userIdArray.length == 0 || agentIdArray.length == 0) {
            createCustomerRes.setCode("500");
            createCustomerRes.setMsg("数据有误,请重新检查");
            return createCustomerRes;
        }
        else if (userIdArray.length != userIdArray.length) {
            createCustomerRes.setCode("500");
            createCustomerRes.setMsg("数据有误,请重新检查");
            return createCustomerRes;
        }
        StringBuilder createCustomerSQL = new StringBuilder();
        StringBuilder createCustomerHistoryPasswordSQL = new StringBuilder();
        StringBuilder customerPassword = new StringBuilder();
        for (int i = 0;i < userIdArray.length;i++) {
            String userId = userIdArray[i];
            String agentId = agentIdArray[i];
            Map<String, String> passwordMap = null;
            try {
                passwordMap = PasswordUtils.encrypt();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            String password = passwordMap.get("password");
            String pwdSalt = passwordMap.get("pwdSalt");
            String sha256Password = passwordMap.get("sha256Password");
            createCustomerSQL.append(
                    String.format("INSERT INTO ECS.USERPROFILE (USERGUID,NAME,ORGUNITGUID,PWDSALT,SHA256PASSWORD,PASSWORD,ORIGINAL,STATUS,FLAG,LASTUPDATE,ATTRIBUTE5) VALUES('%s','%s','%s','%s','%s',' ','A','A','A',SYSDATE,'B2B_PRO');\n",
                    userId,userId,agentId,pwdSalt,sha256Password));
            createCustomerHistoryPasswordSQL.append(
                    String.format("INSERT INTO ECS.userpasswordhistory(ID,HISTORYSALT,HISTORYPWD,USERGUID,UPDATETIME,FLAG)VALUES(pwdhistory_sequence.nextval,'%s' ,'%s' ,'%s',SYSDATE,'1');\n",
                    pwdSalt,sha256Password,userId));
            customerPassword.append(
                    String.format("用户名: %s, 密码: %s\n",userId,password));
        }

        File file = new File(filePathParent);
        if(!file.exists()){
            file.mkdirs();
        }

        try {
            FileWriter fileWriter1 = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
            bufferedWriter1.append(createCustomerSQL.toString());
            bufferedWriter1.append(createCustomerHistoryPasswordSQL.toString());
            bufferedWriter1.close();
            fileWriter1.close();
            FileWriter fileWriter2 = new FileWriter(passwordFilePath, true);
            BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
            bufferedWriter2.append(customerPassword.toString());
            bufferedWriter2.close();
            fileWriter2.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OpenCustomerRes openCustomer(OpenCustomerReq openCustomerReq) {
        String userIdStr = openCustomerReq.getUserIdStr();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        String filePath = filePathParent + File.separator + date + "开通权限.txt";
        //获取信息
        String[] userIdArray = userIdStr.split("\r\n");
        String[] permissionArray = openCustomerReq.getPermission();
        StringBuilder openCustomerSQL = new StringBuilder();
        for (String permission : permissionArray) {
            for (String userId : userIdArray) {
                openCustomerSQL.append(String.format("insert into ECS.userrolesmapping (roleid,userguid)values(%s,'%s');\n",permission,userId));
            }
        }
        File file = new File(filePathParent);
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            FileWriter fileWriter1 = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
            bufferedWriter1.append(openCustomerSQL.toString());
            bufferedWriter1.close();
            fileWriter1.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

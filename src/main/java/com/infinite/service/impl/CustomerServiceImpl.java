package com.infinite.service.impl;

import com.infinite.pojo.CreateCustomerReq;
import com.infinite.pojo.CreateCustomerRes;
import com.infinite.service.CustomerService;
import com.infinite.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {
    @Value("${createCustomer.path}")
    private String filePathParent;
    @Value("${createCustomer.fileName}")
    private String fileName;
    @Override
    public CreateCustomerRes createCustomer(CreateCustomerReq createCustomerReq) throws NoSuchAlgorithmException {
        CreateCustomerRes createCustomerRes = new CreateCustomerRes();
        String filePath = filePathParent + File.separator + fileName;
        String agentIdStr = createCustomerReq.getAgentIdStr();
        String userIdStr = createCustomerReq.getUserIdStr();
        //获取信息
        String[] userIdArray = userIdStr.split("\n");
        String[] agentIdArray = agentIdStr.split("\n");
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
        StringBuilder crateCustomerSQL = new StringBuilder();
        for (int i = 0;i < userIdArray.length;i++) {
            String userId = userIdArray[i];
            String agentId = agentIdArray[i];
            Map<String, String> passwordMap = PasswordUtils.encrypt();
            String password = passwordMap.get("password");
            String pwdSalt = passwordMap.get("pwdSalt");
            String sha256Password = passwordMap.get("sha256Password");
            crateCustomerSQL.append(String.format("INSERT INTO ECS.USERPROFILE (USERGUID,NAME,ORGUNITGUID,PWDSALT,SHA256PASSWORD,PASSWORD,ORIGINAL,STATUS,FLAG,LASTUPDATE,ATTRIBUTE5) VALUES('%s','%s','%s','%s','%s',' ','A','A','A',SYSDATE,'B2B_PRO');\n",
                    userId,userId,agentId,pwdSalt,sha256Password));
        }

        File file = new File(filePathParent);
        if(!file.exists()){
            file.mkdirs();
        }
        File desFile = new File(filePath);
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(crateCustomerSQL.toString());
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

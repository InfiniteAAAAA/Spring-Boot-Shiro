package com.infinite;

import com.infinite.dao.UserMapper;
import com.infinite.pojo.Permission;
import com.infinite.pojo.User;
import com.infinite.service.UserPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShiroApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShiroApplicationTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPermissionService userPermissionService;

    @Test
    public void findUserByNameTest() {
        User kobe = userMapper.findByUserName("Kobe");
        System.out.println(kobe.toString());
    }

    @Test
    public void getPermissionTest() {
        List<Permission> permissionList = userPermissionService.findByUserName("Ricky");
        for (Permission permission : permissionList) {
            System.out.println(permission.getName());
        }
    }
}

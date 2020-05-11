package com.example.todo;

import com.example.todo.entity.Admin;
import com.example.todo.entity.Catalog;
import com.example.todo.entity.User;
import com.example.todo.repository.AdminRepository;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class AdminTest {

    @Resource
    private AdminRepository adminRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private TodoRepository todoRepository;

    @Test
    void t1() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123");
        adminRepository.save(admin);
    }

    @Test
    void t2() {
        User user = userRepository.findById("oUL22wjSGB2Fdd1B5S4YLjQlU2Ls").get();
        System.out.println(user.getNickName());
        for (Catalog item : user.getCatalogs()) {
            System.out.println(item.getText());
        }
    }

    @Test
    void t3() {
        todoRepository.deleteById("1589191640489");
    }
}

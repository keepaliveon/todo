package com.example.todo.service;

import com.example.todo.entity.Admin;
import com.example.todo.repository.AdminRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AdminService {

    @Resource
    private AdminRepository adminRepository;

    public Admin login(Admin admin) {
        Optional<Admin> optional = adminRepository.findById(admin.getUsername());
        return optional.orElse(null);
    }
}

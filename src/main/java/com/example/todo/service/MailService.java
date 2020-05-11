package com.example.todo.service;

import com.example.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(Todo todo) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("vit125@163.com");
            message.setTo(todo.getCatalog().getUser().getEmail());
            message.setSubject("提醒你有待办事项未完成");
            message.setText(todo.getCatalog().getText() + ":" + todo.getText() + ":" + todo.getDesc());
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送简单文本文件-发生异常");
        }
    }

}

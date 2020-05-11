package com.example.todo.web;

import com.example.todo.entity.Admin;
import com.example.todo.entity.Catalog;
import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.AdminService;
import com.example.todo.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private TodoRepository todoRepository;

    @Resource
    private MailService mailService;

    @PostMapping("sign_in")
    public String signIn(Admin login, HttpSession session) {
        Admin admin = adminService.login(login);
        if (admin != null) {
            session.setAttribute("current", admin);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("sign_out")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("password")
    public String password() {
        return "password";
    }

    @RequestMapping("home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("users", userRepository.findAll());
        return modelAndView;
    }

    @RequestMapping("todo")
    public ModelAndView todo(@RequestParam String openid) {
        User user = userRepository.findById(openid).get();
        List<Todo> todos = new ArrayList<>();
        for (Catalog item : user.getCatalogs()) {
            for (Todo todo : item.getTodoList()) {
                todo.setCategory(item.getText());
                todos.add(todo);
            }
        }
        ModelAndView modelAndView = new ModelAndView("todo");
        modelAndView.addObject("todoList", todos);
        return modelAndView;
    }

    @GetMapping("send")
    public ModelAndView send(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("send");
        Todo todo = todoRepository.findById(id).get();
        mailService.sendSimpleEmail(todo);
        modelAndView.addObject("todo", todo);
        return modelAndView;
    }
}

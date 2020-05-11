package com.example.todo.service;

import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class TodoSevice {

    @Resource
    private TodoRepository todoRepository;

    public void delete(String id) {
        todoRepository.deleteById(id);
    }
}

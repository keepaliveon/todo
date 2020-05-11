package com.example.todo.web;

import com.example.todo.entity.Catalog;
import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.repository.CatalogRepository;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.MailService;
import com.example.todo.service.TodoSevice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
@Transactional(rollbackOn = Exception.class)
public class TodoController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private CatalogRepository catalogRepository;

    @Resource
    private TodoRepository todoRepository;

    @PostMapping("{openid}")
    public ResponseEntity<?> create(@PathVariable("openid") String openid, @RequestBody Todo todo) {
        User user = userRepository.findById(openid).get();
        Catalog catalog = user.getCatalogs().get(0);
        todo.setCatalog(catalog);
        todoRepository.save(todo);
        return new ResponseEntity<>(catalog.getText(), HttpStatus.OK);
    }

    @GetMapping("all/{openid}")
    public ResponseEntity<List<Todo>> all(@PathVariable("openid") String openid) {
        User user = userRepository.findById(openid).get();
        List<Todo> todos = new ArrayList<>();
        for (Catalog item : user.getCatalogs()) {
            for (Todo todo : item.getTodoList()) {
                todo.setCategory(item.getText());
                todos.add(todo);
            }
        }
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("catalog/{id}")
    public ResponseEntity<List<Todo>> catalog(@PathVariable Integer id) {
        return new ResponseEntity<>(catalogRepository.findById(id).get().getTodoList(), HttpStatus.OK);
    }

    @GetMapping("todo/{id}")
    public ResponseEntity<Todo> todo(@PathVariable String id) {
        return new ResponseEntity<>(todoRepository.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("{openid}")
    public ResponseEntity<?> update(@PathVariable("openid") String openid, @RequestBody Todo todo) {
        User user = userRepository.findById(openid).get();
        Catalog catalog = catalogRepository.findCatalogByUserAndText(user, todo.getCategory());
        todo.setCatalog(catalog);
        todoRepository.save(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> del(@PathVariable String id) {
        todoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

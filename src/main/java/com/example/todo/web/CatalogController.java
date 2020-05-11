package com.example.todo.web;

import com.example.todo.entity.Catalog;
import com.example.todo.entity.User;
import com.example.todo.repository.CatalogRepository;
import com.example.todo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private CatalogRepository catalogRepository;

    @PostMapping("{openid}")
    public ResponseEntity<?> create(@PathVariable("openid") String openid, @RequestBody Catalog catalog) {
        User user = userRepository.findById(openid).get();
        catalog.setUser(user);
        Catalog save = catalogRepository.save(catalog);
        return new ResponseEntity<>(save.getId(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        catalogRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{openid}")
    public ResponseEntity<List<Catalog>> list(@PathVariable("openid") String openid) {
        User user = userRepository.findById(openid).get();
        return new ResponseEntity<>(catalogRepository.findCatalogsByUser(user), HttpStatus.OK);
    }

    @PutMapping("{openid}")
    public ResponseEntity<?> update(@PathVariable("openid") String openid, @RequestBody Catalog catalog) {
        catalog.setUser(userRepository.findById(openid).get());
        catalogRepository.save(catalog);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

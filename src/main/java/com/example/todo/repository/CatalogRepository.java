package com.example.todo.repository;

import com.example.todo.entity.Catalog;
import com.example.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
    List<Catalog> findCatalogsByUser(User user);

    Catalog findCatalogByUserAndText(User user, String text);
}

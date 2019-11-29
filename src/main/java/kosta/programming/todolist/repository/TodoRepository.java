package kosta.programming.todolist.repository;

import kosta.programming.todolist.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TodoRepository extends JpaRepository<TodoItem, Integer> {
    Optional<TodoItem> findByTitle(String title);
}

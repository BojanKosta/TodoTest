package kosta.programming.todolist.service;

import kosta.programming.todolist.TodoItem;
import kosta.programming.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;

    public List<TodoItem> getAll() {
        return (List<TodoItem>) repo.findAll();
    }

    public TodoItem save(TodoItem todo) {
        return repo.save(todo);
    }

    public Optional<TodoItem> get(Integer id) {
        return repo.findById(id);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }
}

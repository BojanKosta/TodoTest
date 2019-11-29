package kosta.programming.todolist.service;

import kosta.programming.todolist.TodoItem;
import kosta.programming.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;

    public List<TodoItem> getAll() {
        return repo.findAll();
    }

    public TodoItem save(TodoItem todo) {
        return repo.save(todo);
    }

    public TodoItem get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }
}

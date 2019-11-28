package kosta.programming.todolist.repository;

import kosta.programming.todolist.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository <TodoItem, Long> {


}

package kosta.programming.todolist;

import kosta.programming.todolist.repository.TodoRepository;
import kosta.programming.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
class TodoListApplicationTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void contextLoads() {

        TodoItem item = new TodoItem();
        item.setDescription("test");
        item.setDeadline("12.12.1212");
        item.setTitle("test");

        Mockito.when(todoRepository.save(item)).thenReturn(item);
        assertThat(todoService.save(item)).isEqualTo(item);
    }

    @Test
    public void getById() {

        TodoItem item = new TodoItem();
        item.setId(1l);
        item.setDescription("test");
        item.setDeadline("12.12.1212");
        item.setTitle("test");

        Mockito.when(todoRepository.findById(1l)).thenReturn(java.util.Optional.of(item));
        assertThat(todoService.get(1l)).isEqualTo(item);
    }

    @Test
    public void testGetAll() {
        TodoItem item1 = new TodoItem();
        item1.setId(1l);
        item1.setDescription("test");
        item1.setDeadline("12.12.1212");
        item1.setTitle("test");

        TodoItem item2 = new TodoItem();
        item2.setId(2l);
        item2.setDescription("test2");
        item2.setDeadline("12.12.1212");
        item2.setTitle("test2");

        List<TodoItem> todoList = new ArrayList<>();
        todoList.add(item1);
        todoList.add(item2);

        Mockito.when(todoRepository.findAll()).thenReturn(todoList);
        assertThat(todoService.getAll()).isEqualTo(todoList);
    }

    @Test
    public void testDeleteTodo() {
        TodoItem item = new TodoItem();
        item.setId(1l);
        item.setDescription("test");
        item.setDeadline("12.12.1212");
        item.setTitle("test");

        Mockito.when(todoRepository.findById(1l)).thenReturn(java.util.Optional.of(item));
        Mockito.when(todoRepository.existsById(item.getId())).thenReturn(false);
        assertFalse(todoRepository.existsById(item.getId()));
    }


    @Test
    public void testUpdateTodo() {
        TodoItem item = new TodoItem();
        item.setId(1l);
        item.setDescription("test");
        item.setDeadline("12.12.1212");
        item.setTitle("test");

        Mockito.when(todoRepository.findById(1l)).thenReturn(java.util.Optional.of(item));
        item.setTitle("test updated");
        Mockito.when(todoService.save(item)).thenReturn(item);
        assertThat(todoService.save(item)).isEqualTo(item);

    }

}
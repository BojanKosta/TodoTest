package kosta.programming.todolist;

import kosta.programming.todolist.repository.TodoRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoListApplicationTestRepository {

        @Autowired
        private TestEntityManager testEntityManager;

        @Autowired
        private TodoRepository repo;


    @Before
    public void setUp(){
        // given
        TodoItem item = new TodoItem();
        item.setDescription("test");
        item.setDeadline("12.12.1212");
        item.setTitle("test");

        testEntityManager.persist(item);
    }

    @Test
    public void whenFindByName_thenReturnProduct() {
        // when
        TodoItem product = repo.findByTitle("test").get();

        // then
        assertThat(product.getDescription()).isEqualTo("test");
    }

    @Test
    public void whenFindAll_thenReturnProductList() {
        // when
        List<TodoItem> item = (List<TodoItem>) repo.findAll();

        // then
        assertThat(item).hasSize(1);


    }
}

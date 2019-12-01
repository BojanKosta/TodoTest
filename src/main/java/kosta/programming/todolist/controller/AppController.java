package kosta.programming.todolist.controller;

import kosta.programming.todolist.TodoItem;
import kosta.programming.todolist.service.TodoService;
import kosta.programming.todolist.util.AttributeNames;
import kosta.programming.todolist.util.Mappings;
import kosta.programming.todolist.util.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(Mappings.HOME)
    public String viewHome(Model model) {
        List<TodoItem> list = todoService.getAll();
        model.addAttribute(AttributeNames.PRODUCT_LIST, list);
        return Views.INDEX;
    }

    @RequestMapping(Mappings.NEW_ITEM)
    public String newTodo(Model model) {
        TodoItem item = new TodoItem();
        model.addAttribute(AttributeNames.TODO_ITEM, item);
        return Views.NEW;
    }

    @RequestMapping(value = Mappings.SAVE, method = RequestMethod.POST)
    public String saveTodo(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoitem) {
        todoService.save(todoitem);
        return Views.REDIRECT;
    }

    @RequestMapping(Mappings.EDIT)
    public ModelAndView editTodo(@PathVariable(name = AttributeNames.PATH_ID) Integer id) {
        ModelAndView mav = new ModelAndView(Views.EDIT);
        Optional<TodoItem> todoItem = todoService.get(id);
        mav.addObject(AttributeNames.TODO_ITEM, todoItem);
        return mav;
    }

    @RequestMapping(Mappings.DELETE)
    public String deleteTodo(@PathVariable(name = AttributeNames.PATH_ID) Integer id) {
        todoService.delete(id);
        return Views.REDIRECT;
    }

    @RequestMapping(Mappings.DELETE_ALL)
    public String deleteTodo() {
        todoService.deleteAll();
        return Views.REDIRECT;
    }

}

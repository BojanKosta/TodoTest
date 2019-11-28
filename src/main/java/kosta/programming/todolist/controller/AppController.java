package kosta.programming.todolist.controller;

import kosta.programming.todolist.TodoItem;
import kosta.programming.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private TodoService todoService;

    @RequestMapping("/")
    public String viewHome(Model model) {
        List<TodoItem> list = todoService.getAll();
        model.addAttribute("listProducts", list);
        return "index";
    }

    @RequestMapping("/new-todo")
    public String newTodo(Model model) {
        TodoItem item = new TodoItem();
        model.addAttribute("todoitem", item);
        return "new-todo";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTodo(@ModelAttribute("todoitem") TodoItem todoitem) {
        todoService.save(todoitem);
        return "redirect:/";
    }

    @RequestMapping("/edit-todo/{id}")
    public ModelAndView editTodo(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit-todo");
        TodoItem todoItem = todoService.get(id);
        mav.addObject("todoitem", todoItem);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteTodo(@PathVariable(name = "id") Long id) {
        todoService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/deleteAll/")
    public String deleteTodo() {
        todoService.deleteAll();
        return "redirect:/";
    }

}

package com.in28minuutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class ToDoController {

    @Autowired
    private ToDoService todoService   ;

    public ToDoController(ToDoService todoService) {
        super();
        this.todoService = todoService;
    }

    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap model){
        List<ToDo> todos= todoService.findByUsername("rupesh");
        model.addAttribute("todos", todos);
        return "listTodos";
    }
    @RequestMapping(value="/add-ToDo", method = RequestMethod.GET)
    public String showNewToDoPage(ModelMap model){
        String username=(String)model.get("name");
        ToDo todo= new ToDo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "ToDo";
    }
    @RequestMapping(value="/add-ToDo", method = RequestMethod.POST)
    public String AddNewToDo(ModelMap model,@Valid ToDo todo, BindingResult result){
        if(result.hasErrors()){
            return "ToDo";
        }
        todoService.addTodo((String)model.get("name"),todo.getDescription(),LocalDate.now().plusYears(1), false);
        return "redirect:list-todos";
    }
    @RequestMapping(value = "/delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:list-todos";
    }
    @RequestMapping(value="update-todo",method = RequestMethod.GET)
    public String updateTodo(@RequestParam int id,ModelMap model){
        ToDo todo=todoService.findById(id);
        model.addAttribute("todo", todo);
        return "ToDo";
    }
    @RequestMapping(value="/update-todo", method = RequestMethod.POST)
    public String updateToDoPage(ModelMap model,@Valid ToDo todo, BindingResult result){
        if(result.hasErrors()){
            return "ToDo";
        }
        String username=(String) model.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

}

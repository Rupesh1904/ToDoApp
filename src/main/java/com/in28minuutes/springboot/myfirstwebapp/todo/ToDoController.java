package com.in28minuutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class ToDoController {

    @Autowired
    private ToDoService todoService   ;

    public ToDoController(ToDoService todoService) {
        super();
        this.todoService = todoService;
    }

    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap model){
        String username= getLoggedInUsername(model);
        List<ToDo> todos= todoService.findByUsername(username);
        model.addAttribute("todos", todos);
        return "listTodos";
    }
    @RequestMapping(value="/add-ToDo", method = RequestMethod.GET)
    public String showNewToDoPage(ModelMap model){
        String username= getLoggedInUsername(model);

        ToDo todo= new ToDo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "ToDo";
    }
    @RequestMapping(value="/add-ToDo", method = RequestMethod.POST)
    public String AddNewToDo(ModelMap model,@Valid ToDo todo, BindingResult result){
        if(result.hasErrors()){
            return "ToDo";
        }
        String username= getLoggedInUsername(model);
        todoService.addTodo(username,todo.getDescription(),LocalDate.now().plusYears(1), false);
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
        String username= getLoggedInUsername(model);
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }
    private String getLoggedInUsername(ModelMap model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
         return authentication.getName();
        }

}

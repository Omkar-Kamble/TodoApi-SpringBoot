package com.omkar.TodoApiSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


    private TodoService todoService;

    private TodoService todoService1;

    private static List<Todo> todoList;

    private static final String TODO_NOT_FOUND = "Todo not found";

    public TodoController(@Qualifier("fakeTodoService") TodoService todoService, @Qualifier("anotherTodoService") TodoService todoService1 ){
        this.todoService = todoService;
        this.todoService1 = todoService1;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean isCompleted) {
        System.out.println("Incoming query params: " + isCompleted + " " + this.todoService.doSomething());
        return ResponseEntity.ok(todoList);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
        /**
         * we can use this annotation to set the status code @ResponseStatus(HttpStatus.CREATED)
         *
         * */
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable Long todoId){
        for (Todo todo : todoList){
            if(todo.getId() == todoId){
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }
}

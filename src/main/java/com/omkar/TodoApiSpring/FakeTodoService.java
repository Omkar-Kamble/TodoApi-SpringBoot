package com.omkar.TodoApiSpring;

import org.springframework.stereotype.Component;

@Component("fakeTodoService")
public class FakeTodoService implements TodoService {
    @TimeMonitor
    @Override
    public String doSomething(){
        return "Something...";
    }
}

package com.example.todo.service;

import com.example.todo.dto.TodoDTO;

import java.util.List;

public interface TodoService {  // Todo 등록작업 (2.Mapper와 Controller 사이의 Service계층 설계)

    void register(TodoDTO todoDTO); // 여러개의 파라미터 대신에 TodoDTO로 묶어서 전달받도록 한다.

    // Todo 목록 기능
    List<TodoDTO> getAll();
}

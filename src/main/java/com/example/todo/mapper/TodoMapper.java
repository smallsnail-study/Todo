package com.example.todo.mapper;

import com.example.todo.domain.TodoVO;

import java.util.List;

public interface TodoMapper { //MyBatis와 스프링을 연동(2. Mapper인터페이스의 개발)

    String getTime();

    // Todo등록기능(TodoVO를 파라미터로 입력받는 insert()추가)
    void insert(TodoVO todoVO);

    // Todo 목록기능 개발(가장 최근에 등록된 TodoVO가 우선적으로 나올수 있도록 selectAll()을 추가한다)
    List<TodoVO> selectAll();
}

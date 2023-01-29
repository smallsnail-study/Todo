package com.example.todo.mapper;

import com.example.todo.domain.TodoVO;

public interface TodoMapper { //MyBatis와 스프링을 연동(2. Mapper인터페이스의 개발)

    String getTime();

    // Todo등록기능(TodoVO를 파라미터로 입력받는 insert()추가)
    void insert(TodoVO todoVO);
}

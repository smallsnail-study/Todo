package com.example.todo.service;

import com.example.todo.domain.TodoVO;
import com.example.todo.dto.TodoDTO;
import com.example.todo.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor    // 의존성주입 방법 중 생성자를 자동생성
public class TodoServiceImpl implements TodoService {

    // 의존성 주입 (주입이 필요한 객체의 타입을 final로 고정하고 @RequiredArgsConstructor를 이용해서 생성자를 생성한다)
    private final TodoMapper todoMapper; // 데이터베이스 처리를 하는 TodoMapper를 주입한다.

    private final ModelMapper modelMapper;  // DTO, VO의 변환을 처리하는 ModelMapper를 주입한다.

    @Override
    public void register(TodoDTO todoDTO) {

        log.info(modelMapper);

        // 주입된 ModelMapper를 이용해서 TodoDTO를 TodoVO로 변환한다.
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        log.info(todoVO);

        // 변환한 TodoVO를 TodoMapper를 통해 insert처리한다.
        todoMapper.insert(todoVO);
    }
}

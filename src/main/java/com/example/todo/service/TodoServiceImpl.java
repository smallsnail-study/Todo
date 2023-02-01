package com.example.todo.service;

import com.example.todo.domain.TodoVO;
import com.example.todo.dto.TodoDTO;
import com.example.todo.mapper.TodoMapper;
import com.sun.tools.javac.comp.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override   // Todo 목록 기능
    public List<TodoDTO> getAll() {
        // TodoMapper가 반환하틑 데이터 타입이 List<TodoVO>이기 때문에 List<TodoDTO>로 변환하는 작업이 필요
        List<TodoDTO> dtoList = todoMapper.selectAll().stream() // stream을 이용해서
                .map(vo -> modelMapper.map(vo, TodoDTO.class))  // 각 TodoVO는 map()을 통해서 TodoDTO로 바꾸고
                .collect(Collectors.toList());  // collect()를 이용해서 List<TodoDTO>로 묶어준다.

        return dtoList;
    }

    @Override   // Todo 조회 기능
    public TodoDTO getOne(Long tno) {

        TodoVO todoVO = todoMapper.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    @Override   // Todo 삭제 기능
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }
}

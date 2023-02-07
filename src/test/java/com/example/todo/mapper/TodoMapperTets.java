package com.example.todo.mapper;

import com.example.todo.domain.TodoVO;
import com.example.todo.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTets {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test   // TodoVO입력하여 등록하는 기능 확인용 테스트
    public void testInsert() {

        TodoVO todoVO = TodoVO.builder()
                .title("스프링 테스트")
                .dueDate(LocalDate.of(2023, 01, 29))
                .writer("user00")
                .build();

        todoMapper.insert(todoVO);
    }

    @Test   // Todo 목록 기능 테스트
    public void testSelectAll() {
        List<TodoVO> voList = todoMapper.selectAll();

        voList.forEach(vo -> log.info(vo));
    }

    @Test   // Todo 조회 기능 테스트
    public void testSelectOne() {
        TodoVO todoVO = todoMapper.selectOne(2L);   // 현재 데이터베이스에 존재하는 번호로 테스트한다.

        log.info(todoVO);
    }

    @Test   // Todo 페이징 처리된 목록 기능 테스트
    public void testSelectList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectSearch() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t","w"})
                .keyword("스프링")
                //.finished(true)
                .from(LocalDate.of(2023,02,01))
                .to(LocalDate.of(2023,02,28))
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        // forEach는 반복처리
        voList.forEach(vo -> log.info(vo));

        log.info(todoMapper.getCount(pageRequestDTO));
    }
}

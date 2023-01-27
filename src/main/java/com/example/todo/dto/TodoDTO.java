package com.example.todo.dto;

import lombok.*;

import java.time.LocalDate;

@ToString
@Data   // 객체 자료형을 파라미터로 처리하기 위한 애너테이션
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    private String title;

    private LocalDate dueDate;

    private boolean finished;

    private String writer;  // 작성자
}

package com.example.todo.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoVO {   // MyBatis와 스프링을 연동(1. VO선언)

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private String writer;
    private boolean finished;

}

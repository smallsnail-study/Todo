package com.example.todo.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data   // 객체 자료형을 파라미터로 처리하기 위한 애너테이션
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    @NotEmpty   // Null,빈문자열 불가(서버입력값 검증)
    private String title;

    @Future     // 현재보다 미래인가?(서버입력값 검증)
    private LocalDate dueDate;

    private boolean finished;

    @NotEmpty    // Null,빈문자열 불가(서버입력값 검증)
    private String writer;  // 작성자
}

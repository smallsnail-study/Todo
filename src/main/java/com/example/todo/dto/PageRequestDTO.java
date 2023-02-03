package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
// 페이지 처리의 기본적으로 요구되는 현재 페이지번호(page), 한 페이지당 보여주는 데이터의 수(size)를 확장성을 위해 DTO로 생성
    @Builder.Default    // 기본값을 가지기 위해 설정
    @Min(value = 1)
    @Positive
    private int page = 1;   // 현재 페이지번호

    @Builder.Default    // 기본값을 가지기 위해 설정
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;  // 한 페이지당 보여주는 데이터의 수

    // limit에서 사용하는 건너뛰기(skip)의 수를 getSkip()을 만들어서 사용
    public int getSkip() {
        return (page - 1) * 10; // 현재페이지가 3페이지라면 20개의 데이터를 건너뛰고 30번째의 데이터부터 보여준다.
    }

}

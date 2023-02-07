package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

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

    private String link;    // 페이지 이동에 필요한 링크

    // limit에서 사용하는 건너뛰기(skip)의 수를 getSkip()을 만들어서 사용
    public int getSkip() {
        return (page - 1) * 10; // 현재페이지가 3페이지라면 20개의 데이터를 건너뛰고 30번째의 데이터부터 보여준다.
    }

    // Todo 조회페이지에서 다시 목록으로 이동 할 때 기존 페이지를 볼 수 있도록 하기 위한 페이지 이동 정보(필요한 링크 생성 시 사용)
    public String getLink() {   // GET방식으로 페이지 이동에 필요한 링크 생성
        // if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);
          // link = builder.toString();

            // 조회,수정화면에서도 검색/필터링조건들을 유지하기 위한 if문들
            if (finished){
                builder.append("&finished=on");
            }

            if (types != null && types.length > 0) {
                for (int i = 0; i < types.length; i++) {
                    builder.append("&types=" + types[i]);
                }
            }

            if (keyword != null) {
                try {   // keword에 한글이 입력된 경우 URLEncoder를 이용해서 링크로 처리할 수 있도록 설정
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if (from != null) {
                builder.append("&from=" + from.toString());
            }

            if (to != null) {
                builder.append("&to=" + to.toString());
            }
        //}
        //return link;
        return builder.toString();
    }

    private String[] types;     // 검색종류
    private String keyword;     // 검색의 실제 값
    private boolean finished;   // 완료여부
    private LocalDate from;     // 특정기간 지정
    private LocalDate to;       // 특정가간 지정

    // 화면에서 검색 후 검색부분 초기화 방지 설정를 위한 메소드(title, writer)
    public boolean checkType(String type) {

        if (types == null || types.length == 0) {
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }

}

package com.example.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
// TodoMapper에서 가져오는 TodoVO의 목록과 전체 데이터의 수를 서비스계층에서 한번에 담아서 처리하도록 DTO로 구성
// TodoDTO의 목록
// 전체 데이터의 수
// 페이지 번호의 처리를 위한 데이터들(시작 페이지번호/끝 페이지 번호)
// 제네릭을 이용해서 설계 : 나중에 다른 종류의 객체를 이용해서 PageResponseDTO를 구성할 수 있도록 하기 위함.(게시판,회원정보도 페지징 처리가 필요할 수 있다)

    private int page;   // 현재페이지번호
    private int size;   // 페이지당 데이터의 수
    private int total;  // 전체 데이터의 수

    // 시작 페이지 번호
    private int start;
    // 끝 페이지 번호
    private int end;

    // 이전 페이지의 존재 여부
    private boolean prev;
    // 다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;    // TodoDTO의 목록 데이터

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        // 마지막 페이지 = 현재페이지번호를 10으로 나눈 값을 올림 처리 후 * 10
        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;

        // 시작 페이지 = 마지막 페이지 - 9
        this.start = this.end - 9;

        // 실제계산된마지막페이지 = 전체데이터개수 / 페이지당 데이터의 개수
        int last = (int)(Math.ceil((total/(double)size)));

        // 마지막 페이지 = 위에서계산된마지막페이지가 더 작은 경우 last값이 마지막 페이지가 되야 한다.
        this.end = end > last ? last : end;

        // 이전 페이지 = 시작페이지가 1이 아니라면 무조건 true
        this.prev = this.start > 1;

        // 다음페이지 = 전체데이터 수 > 마지막페이지 * 페이지당 개수인 경우만 true
        this.next = total > this.end * this.size;
    }
}
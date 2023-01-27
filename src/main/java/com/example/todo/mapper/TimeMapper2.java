package com.example.todo.mapper;

public interface TimeMapper2 {  // SQL을 별도의 파일로 분리(XML을 이용)

    String getNow();    // XML과 매퍼 인터페이스를 같이 결합하는 방법 1. 매퍼 인터페이스를 정의하고 메소드를 선언
}

package com.example.todo.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {   // 매퍼인터페이스->root-context.xml에 등록해주어야 한다.

    @Select("select now()") // MyBatis에는 @Select를 이용해서 쿼리를 작성할수 있다.
    String getTime();   // 데이터베이스의 현재 시각을 문자열로 처리
}

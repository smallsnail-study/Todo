package com.example.todo.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CheckboxFormatter implements Formatter<Boolean> {
    // 체크박스를 이용해서 완료여부(finished)를 클릭할 때 전송되는 값은 'on'이라는 값을 전달한다.
    // TodoDTO로 데이터를 수집할 때는 문자열 on을 boolean타입으로 처리할 수 있어야 하므로 타이븡ㄹ 변경해 주기 위해 Formatter를 이용한다.

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        if (text == null) {
            return false;
        }
        return text.equals("on");
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }

}

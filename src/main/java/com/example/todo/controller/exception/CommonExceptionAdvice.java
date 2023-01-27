package com.example.todo.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice   // 컨트롤러에서 발생하는 예외에 맞게 처리할 수 있는 기능 제공
@Log4j2
public class CommonExceptionAdvice {    // @ControllerAdvice가 선언된 클래스는 스프링의 Bean으로 처리된다.

    @ResponseBody   // REST방식, 문자열이나 JSON데이터를 그대로 전송할 떄 사용하는 어노테이션
//  NumberFormatException타입을 지정하면 @ExceptionHandler를 가진 모든 메소드에서 해당타입의 예외를 파라미터로 전달받을 수 있다.
    @ExceptionHandler(NumberFormatException.class)
    public String exceptionNumber(NumberFormatException numberFormatException) {

        log.error("-----------------------------------");
        log.error(numberFormatException.getMessage());

        return "NUMBER FORMAT EXCEPTION";   // NumberFormatException이 발생하면 화면에 "NUMBER FORMAT EXCEPTION" 이 표기된다.
    }

    @ResponseBody // REST방식, 문자열이나 JSON데이터를 그대로 전송할 떄 사용하는 어노테이션
//  범용적인 예외처리(어디서 문제가 발생하는지 메시지로 확인하기 위해 예외처리의 상위 타입인 Exception타입을 처리하도록 구성한다.)
    @ExceptionHandler(Exception.class)
    public String exceptionCommon(Exception exception) {    // 사실상 거의 모든 예외를 처리하는 용도로 사용

        log.error("-----------------------------------");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>" + exception.getMessage() + "</li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>"+stackTraceElement+"</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)    // 404예외(잘못된URL을 호출하는경우)처리를 위한 코드
//  @ResponseStatus를 이용하면 404상태에 맞는 화면을 별도로 작성할 수 있다.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "custom404";
    }

}

package com.example.todo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 해당 클래스가 스프링 Bean에 대한 설정을 하는 클래스임을 명시
public class ModelMapperConfig {    // 기존의 MapperUtil 클래스를 스프링으로 변경한 버전

    // DTO를 VO로 변환하거나 VO를 DTO로 변환해야 하는 작업이 빈번하므로 ModelMapper를 스프링의 빈으로 등록해서 처리한다.
    @Bean   // 해당 메소드의 실행 결과로 반환된 객체를 스프링의 Bean으로 등록시킨다.
    public ModelMapper getMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}

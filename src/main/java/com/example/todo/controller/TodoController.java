package com.example.todo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.todo.dto.TodoDTO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {

    @RequestMapping("/list")    // 최종경로 /todo/list
    public void list(Model model) {

        log.info("todo list.............");
    }

    /* @RequestMapping(value = "/register", method = RequestMethod.GET)    // method속성을 이용해서 GET/POST방식 구분해서 처리
    public void register() {
        log.info("todo register..........");
    } */

    //  Todo 등록을 GET방식과 POST방식으로 나눠서 설정
    @GetMapping("register") // GET방식으로 /todo/register를 이용하면 입력 가능한 화면을 보여준다.
    public void registerGET() {

        log.info("GET todo register.........");
    }

    @PostMapping("/register")   // POST방식으로 Todo등록을 처리한다.
    public String registerPOST(TodoDTO todoDTO, RedirectAttributes redirectAttributes) {     // 파라미터를 TodoDTO로 사용한다.(자동형변환)
        log.info("POST todo register..............");
        log.info(todoDTO);

        return "redirect:/todo/list";   // POST방식으로 처리 후 /todo/list로 이동할 수 있도록한다.
    }
}

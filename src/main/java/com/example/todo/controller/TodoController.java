package com.example.todo.controller;

import com.example.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.todo.dto.TodoDTO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    
    // 입력값 검증까지 확인 후 TodoService를 주입
    private final TodoService todoService;

    @RequestMapping("/list")    // 최종경로 /todo/list
    public void list(Model model) {

        log.info("todo list.............");

        // TodoService를 처리하고 Model에 데이터를 담아서 JSP로 전달한다.
        model.addAttribute("dtoList", todoService.getAll());
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
    public String registerPOST(@Valid TodoDTO todoDTO,  // @Valid (서버 입력값 검증)
                               BindingResult bindingResult, // 서버입력값 검증을 위해 파라미터로 설정
                               RedirectAttributes redirectAttributes) {     // 파라미터를 TodoDTO로 사용한다.(자동형변환)

        log.info("POST todo register..............");

        if (bindingResult.hasErrors()) {    // 검증에 문제가 있다면 다시 입력화면으로 리다이렉트처리
            log.info("has errors......");

            // 처리과정에서 잘못된 결과는 RedirectAttributes의 addFlashAttribute()를 이용해 전달한다.
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/todo/register";
        }

        log.info(todoDTO);
        
        todoService.register(todoDTO);  // TodoService의 기능을 호출하도록 구성

        return "redirect:/todo/list";   // POST방식으로 처리 후 /todo/list로 이동할 수 있도록한다.
    }

    @GetMapping("/read")    // Todo 조회 기능
    public void read(Long tno, Model model) {

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);
    }
}

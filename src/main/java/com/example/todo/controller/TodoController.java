package com.example.todo.controller;

import com.example.todo.dto.PageRequestDTO;
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

    /*
    @RequestMapping("/list")    // 최종경로 /todo/list
    public void list(Model model) {

        log.info("todo list.............");

        // TodoService를 처리하고 Model에 데이터를 담아서 JSP로 전달한다.
        model.addAttribute("dtoList", todoService.getAll());
    }
    */
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
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);  // TodoService의 기능을 호출하도록 구성

        return "redirect:/todo/list";   // POST방식으로 처리 후 /todo/list로 이동할 수 있도록한다.
    }

    // 화면처리가 같은 경우 스프링 MVC에는 여러 개의 경로를 배열과 같은 표기법을 이용해서 하나의 @GetMapping으로 처리 가능
    @GetMapping({"/read", "/modify"})    // Todo 조회 기능, 삭제 기능
    public void read(Long tno, PageRequestDTO pageRequestDTO,Model model) {
    // 조회화면에서 목록화면 이동 시 PageRequestDTO를 이용하도록 파라미터 추가

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);
    }

    @PostMapping("/remove")     // POST방식으로 동작하는 삭제기능
    public String remove(Long tno, RedirectAttributes redirectAttributes) {
        // tno파라미터가 정상적으로 전달되는지 확인 후, 목록으로 이동하게 한다.

        log.info("-----------remove------------");
        log.info("tno: " + tno);

        todoService.remove(tno);

        return "redirect:/todo/list";
    }

    @PostMapping("/modify")     // POST방식으로 동작하는 수정기능
    public String modify(@Valid TodoDTO todoDTO,    // @Valid를 이용해서 TodoDTO의 내용들을 검증한다.
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {    // 문제가 있는 경우 다시 /todo/modify로 리다이렉트시킨다.
            log.info("has errors");
            // errors라는 이름으로 BindingResult의 모든 에러들을 묶어서 전달한다.
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            // modify페이지로 다시 이동할 때 tno파라미터가 필요하므로 RedirectAttributes의 addAttribute를 이용한다.
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping("/list")    // 페이징을 적용한 Todo 목록
    public void List(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
//  PageRequestDTO를 파라미터로 처리하고, Model에 PageResponseDTO의 데이터를 담는다.

        log.info(pageRequestDTO);

        // @Valid를 이용해서 잘못된 파라미터 값들이 들어오는 경우 page는 1, size는 10으로 고정된 값을 처리하도록 구성
        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        // model에 responseDTO라는 이름으로 PageResponseDTO를 담아준다.
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }

}
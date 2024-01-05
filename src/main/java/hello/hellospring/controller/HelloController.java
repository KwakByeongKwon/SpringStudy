package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 컨트롤러는 스프링에서 @Controller 어노테이션을 적어야한다.
public class HelloController {

    // 여기서 Get은 GET POST할때 그 Get 입니다 http url을 사용자가 직접 처서 들어가면 get방식 이라고 합니다.
    @GetMapping("hello")
    /**
     * /hello 라고 들어오면 이 밑에 메서드를 실행합니다.
     */
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        /**
         * data 라는 변수에 hello!! 라는 값이 할당되어 hello 라는 html로 전송하게 됩니다.
         * 여기서 이 data를 thymeleaf 에서 사용할 수 있게 됩니다.
         */
        return "hello";
        // return 값이 내가 열 html 파일의 이름이다.

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

}



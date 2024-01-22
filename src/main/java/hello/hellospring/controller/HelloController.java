package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



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
    // @RequestParam 을 사용하면 http://localhost:8080/hello-mvc?name=spi 이렇게 주소를 입력할때 값을 전달받거나 설정해줘야 합니다.

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "<h1>hello " + name + "</h1>";
    }
    // 이런식으로 하면 html 태그가 아닌 내가 적은 문자들이 바로 나온다.


    // 기존에는 viewResolver 로 리턴을 하는데,
    // @ResponseBody 는 이를 대신해서 HttpMessageConverter가 동작되고 http의 body에 문자 내용을 직접 반환하게 됩니다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    // json 방식으로 화면이 나옴

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}



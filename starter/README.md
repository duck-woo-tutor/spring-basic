# Starter를 위한 코드로 배우는 스프링 부트

---

## 스프링과 웹
1. 정적 컨텐츠
2. MVC 템플릿 엔진
3. API

> 구분하기

### 1. 정적 컨텐츠
```resources/static/hello-static.html```
서버가 가지고있는 정적 컨텐츠를 꺼내주는 것. 
- 서버와 통신이 필요없는 자원의 개념. CSS, JS, 템플릿 엔진이 아닌 html등.

#### :cyclone: 흐름 이해 필수
파일의 위치 : ```resources/static/hello-static.html```

웹브라우저 request -> localhost:8080/hello-static.html 

-> 내장 톰켓 서버 

-> 스프링 컨테이너(관련 컨트롤러가 없음을 확인) 

-> 내장 톰켓 서버 

-> resources: static 디렉토리에서 해당 자료 찾기 "hello-static.html"

-> 서버 : "hello-static.html" -> response 웹브라우저

### 2. MVC 템플릿 엔진
MVC : Model, View, Controller

@Controller 클래스의 메소드에서 String 반환할때.

```java
@Controller
public class HelloController {

    @GetMapping("hello-mvc")
    public String helloMvc(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello-template";
    }
}
```
서버가 가지고있는 동적 컨텐츠를 꺼내주는 것.
- 템플릿 엔진 html.

#### :cyclone: 흐름 이해 필수
파일의 위치 : ```resources/tempates/hello-template.html```

웹브라우저 request -> localhost:8080/hello-mvc

-> 내장 톰켓 서버

-> 스프링 컨테이너 : 관련 컨트롤러 확인 및 로직 처리

-> 스프링 컨테이너 : viewResolver에서 템플릿 엔진 처리

- resources: tempates 디렉토리에서 해당 자료 찾기 "hello-template.html"

- 템플릿 엔진 처리 후 html 변환 

-> 스프링 컨테이너 : "hello-template.html" -> response 웹브라우저

### 3. API 
```@Controller``` + ```@ResponseBody```

또는 

```@RestController```

```java
@Controller
public class HelloController {

    @GetMapping("hello-api")
    @ResponseBody
    public SomeObject helloMvc() {
        return new SomeObject("woo", "hello!!");
    }
}
```

-> viewResolver를 사용하지 않음.
- 대신 HTTP Body에 내용 처리해서 직접 반환


#### :cyclone: 흐름 이해 필수

웹브라우저 request -> localhost:8080/hello-api

-> 내장 톰켓 서버

-> 스프링 컨테이너 : 관련 컨트롤러 확인 및 로직 처리

-> 스프링 컨테이너 : HttpMessageConverter (JsonConverter / StringConverter)
- 객체 반환 시 JsonConverter 사용됨.
- 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 메소드 반환 타입 정보 둘을 조합해서 ```HttpMessageConverter```가 선택된다.

-> 스프링 컨테이너 : Http Body : {name: spring} -> response 웹브라우저
# Starter를 위한 코드로 배우는 스프링 부트

---

## Index

1. [스프링과 웹](#스프링과-웹)
2. [스프링 빈과 의존관계](#스프링-빈과-의존관계)
3. [스프링 DB 접근 기술](#스프링-DB-접근-기술)
4. [AOP](#AOP)

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

## 스프링 빈과 의존관계

1. 컴포넌트 스캔과 자동 의존관계 설정
2. 자바 코드로 직접 스프링 빈 등록하기

```@Autowired```가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라 한다.

스프링이 컨테이너에 넣고, 관리해줄 객체를 등록해줘야, ```@Autowired```에 주입할 수 있다.

### 1. 컴포넌트 스캔과 자동 의존관계 설정

#### 컴포넌트 스캔 원리

```@Component```애노테이션이 있으면 스프링 빈으로 자동 등록된다.

아래 애노테이션은 ```@Component```을 포함하고 있다.

- ```@Controller```
- ```@Service```
- ```@Repository```

스프링부트에서 ```@Autowired``` 생략

1. final 필드 + ```@RequiredArgsConstructor```
2. 생성자가 1개만 있으면

- 참고 : 스프링 빈은 스프링 컨테이너에 싱글톤으로 등록하는게 기본설정이다.

### 2. 자바 코드로 직접 스프링 빈 등록하기

```@Configuration```이 붙은 클래스에, ```@Bean```을 붙인 메소드로 스프링 빈을 등록한다.

```java
...

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```

정형화된 컨트롤러, 서비스, 레파지토리 같은 코드는 컴포넌트 스캔을 사용하고,
정형화되지 않가나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.

## 스프링 DB 접근 기술

1. 순수 JDBC
2. 스프링 jdbcTemplate
3. JPA
4. Spring Data JPA

### 1. 순수 JDBC

고대의 개발... 나는 왜 이걸 해봤지...?

```java
...

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }
}
```

### 2. 스프링 jdbcTemplate

많이 쓰니 한번 봐 두기! JdbcTemplate과 MyBatis 같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거해준다.

```java
...

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
```

### 3. JPA

기본적인 SQL도 JPA가 만들어준다.

JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환할 수 있다.

```application.properties
spring.datasource.url=jdbc:h2:tcp://localhost/~/test
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
```

- 서비스 계층에 트랜잭션 추가

```java import
org.springframework.transaction.annotation.Transactional
```

- 스프링은 ```@Transactional```붙은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋한다.
  런타임 예외가 발생하면 롤백한다.

- JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.

```java
...

@Configuration
public class SpringConfig {
    private final EntityManager em;
    
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    
    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }
}
```

### 4. Spring Data JPA

스프링 부트와 JPA만 사용해도 개발 생산성이 정말 많이 증가하고, 개발해야할 코드도 확연히 줄어든다.
여기에 스프링 데이터 JPA를 사용하면, 인터페이스만으로 개발을 완료할 수 있다.
반복된 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공한다.

- :warning: 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술이다. JPA먼저!

```java
...

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
```

Spring Data JPA가 SpringDataJpaMemberRepository를 스프링 빈으로 자동 등록해준다.

- 스프링 데이터 <--- 스프링 데이터 JPA
  - 스프링 데이터
    - <<Interface>> Repository
      <--- <<Interface>> CrudRepository() : save(S) : S, findOne(ID) : T, count() : long, delete(T) ...
      <--- <<Interface>> PagingAndSortingRepository : findAll(Sort) : Iterable<T>, findAll(Pageable) : Page<T>
  - 스프링 데이터 JPA
    - <<Interface>> JpaRepository : findAll() : List<T>, save(Iterable<ID>) : List<S>, saveAndFlush(T) : T

- Spring Data JPA 제공 기능
  - 인터페이스를 통한 기본적인 CRUD
  - 세팅된 엔티티의 필드를 활용햐, 메서드 이름만으로 JPQL 생성 쌉 가능
  - 페이징 기능 자동 제공

- 참고 : 복잡한 동적 쿼리는 QueryDsl이라는 라이브러리 사용한다.
  해결하기 힘든 어려운 쿼리는 JPA가 제공하는 네이티브 쿼리를 사용하거나, 앞서 학습한 스프링 JdbcTemplate를 사용하면 된다.

## AOP

1. AOP가 필요한 상황

- 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)
- 핵심 관심 사항은 비즈니스 로직.
- 공통 관심 사항 -> ***AOP*** 위빙위빙~
  - ex) 모든 메소드의 호출 시간을 측정하고 싶다면? 메소드가 몇 천개라면?

2. AOP 적용

- Aspect Oriented Programming
- 공통 관심 사항과 핵슴 관심 사항분리

### AOP 적용 후 의존관계

스프링 빈에 직접 접근하는 것이 아니고, 프록시 객체를 생성해, 프록시 객체에 접근.

예제로 이해하기.
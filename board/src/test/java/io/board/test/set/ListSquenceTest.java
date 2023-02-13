package io.board.test.set;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListSquenceTest {

    @Test
    void list() {
        List<Person> persons = new ArrayList<>(List.of( new Person(1L, "냐옹"), new Person(2L, "우째") , new Person(3L, "꺙"), new Person(4L, "test")));
        System.out.println(persons);

        List<String> names = persons.stream().map(Person::getName).toList();
        System.out.println(names);
    }


}

class Person {
    private Long id;
    private String name;

    private LocalDateTime now;

    public String getName() {
        return name;
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
        this.now = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", now=" + now +
                '}';
    }
}
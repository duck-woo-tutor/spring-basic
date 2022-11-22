package playground.calculator.check;

interface Predator2 {
    String bark();
}

abstract class Animal2 {
    public String hello() {
        return "hello";
    }
}

class Dog2 extends Animal2 {
}

class Lion2 extends Animal2 implements Predator2 {
    public String bark() {
        return "Bark bark!!";
    }
}

class Sample2 {
    public static void main(String[] args) {
        Animal2 a = new Lion2();
        Lion2 b = new Lion2();
        Predator2 c = new Lion2();

        System.out.println(a.hello());  // 1번
//        System.out.println(a.bark());   // 2번 오류발생
        System.out.println(b.hello());  // 3번
        System.out.println(b.bark());   // 4번
//        System.out.println(c.hello());  // 5번 오류발생
        System.out.println(c.bark());   // 6번
    }
}
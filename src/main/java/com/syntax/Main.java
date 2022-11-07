package com.syntax;

public class Main {
    public static void main(String[] args) {
        // 변수와 상수 : 할당이 무엇은지!
        int number = 7;
        System.out.println(number);

        number = 8;
        System.out.println(number);
/*
        double number = 2; <Variable 'number' is already defined in the scope>
        이 범위(Scope) 같은 이름의 변수 선어할 수 없다.
*/
        final int NUMBER = 7;
        System.out.println(NUMBER);
/*
        NUMBER = 8; Cannot assign a value to final variable 'NUMBER'
        상수 변수인 'NUMBER'에는 할당을 할 수 없다.
 */
//        final double NUMBER = 3;Variable 'NUMBER' is already defined in the scopeVariable 'NUMBER' is already defined in the scope
        short s =1;

        float variable_float = 0.1f; // 자바는 CamelCase가 기본 convention(관례)

        float variableFloat = 0.1F; // ** 대문자가 눈에 띄기때문에 대문자를 쓰자
        long variableLong = 1234567890L;
        double variableDouble = 9.12345678901234567890D;

        System.out.println(variableFloat);
        System.out.println(variableLong);
        System.out.println(variableDouble);

        char alphabet = 'A';
        System.out.println(alphabet);

        boolean fact = true;
        System.out.println(fact);

        byte data = 'd';
        System.out.println(data);
    }
    double number = 3;
}

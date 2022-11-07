package com.syntax;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // if - else
        int num1 = 50;
        if (num1 > 0) {
            System.out.println("100보다 큰 수입니다");
        } else {
            System.out.println("100보다 작은 수입니다.");
        }

        // switch
        switch (num1) {
            case 0:
                System.out.println("응애 나 0");
                break;
            case 1:
                System.out.println("응애 나 1");
                break;
            case 2:
                System.out.println("응애 나 2");
                break;
            default:
                System.out.println("응애 나 몰라");
                break;
        }

        // 삼항 연산자
        int a = 5;

        String reuslt = (a < 10) ? "10보다 작습니다." : "10보다 큽니다.";
        System.out.println(reuslt);
    }
}

package com.syntax;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // for
        /* sudo
        for(초기값 ; 조건식 ; 증감식){
            실행 코드 블럭
        }
         */
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += (i + 1);
        }
        System.out.println(sum);

        // for-each
        int[] days = new int[]{1, 2, 3, 4};
        for (int day : days) {
            System.out.println(day);
        }

        // while
        int i = 0;
        sum = 0;
        while (i < 10) {

            if (i==3){
                continue;
            }

            if (i==5){
                break;
            }

            sum += i + 1;
            i += 1;
        }
        System.out.println(sum);

        // do-while
        i = 1;
        int result = 0;
        do {
            result += i;
            i += 1;
        } while (i < 2);
        System.out.println(result);
    }
}

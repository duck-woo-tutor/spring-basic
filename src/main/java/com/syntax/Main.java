package com.syntax;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        long bitOperatorStartTime = System.nanoTime();

        if(false & false) {
            System.out.println("펄펄");
        } else if(true & false) {
            System.out.println("투펄");
        } else if(false & true) {
            System.out.println("펄투");
        } else if(true & true) {
            System.out.println("투투");
        }
        
        System.out.println(System.nanoTime() - bitOperatorStartTime);

        System.out.println("---");
        long operatorStartTime = System.nanoTime();

        if(false && false) {
            System.out.println("펄펄");
        } else if(true && false) {
            System.out.println("투펄");
        } else if(false && true) {
            System.out.println("펄투");
        } else if(true && true) {
            System.out.println("투투");
        }

        System.out.println(System.nanoTime() - operatorStartTime);
    }
}

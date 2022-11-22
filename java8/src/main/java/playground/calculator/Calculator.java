package playground.calculator;

import java.util.List;

public class Calculator {
    int value;

    public void add(int value) {
        this.value += value;
    }

    int getValue() {
        return this.value;
    }

    boolean isOdd(int value) {
        return value % 2 == 1;
    }

    int average(int[] array) {
        int total = 0;
        for (int element : array) {
            total += element;
        }

        return total / array.length;
    }

    int average(List<Integer> list) {
        int total = 0;
        for (int element : list) {
            total += element;
        }

        return total / list.size();
    }
}

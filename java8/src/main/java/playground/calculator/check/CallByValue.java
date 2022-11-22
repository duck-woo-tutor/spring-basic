package playground.calculator.check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallByValue {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> b = a;
        System.out.println(b.size());

        a.add(4);
        System.out.println(b.size());

        System.out.println("---");
        System.out.println(b == a);

        List<String> list = new ArrayList<>();
        list.add("s");
    }
}

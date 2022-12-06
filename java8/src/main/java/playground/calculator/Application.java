package playground.calculator;

import playground.calculator.mineral.Bronze;
import playground.calculator.mineral.Gold;
import playground.calculator.mineral.Silver;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        UpgradeCalculator upgradeCalculator = new UpgradeCalculator();
        System.out.println(upgradeCalculator.getValue());

        upgradeCalculator.add(7);
        System.out.println(upgradeCalculator.getValue());

        upgradeCalculator.minus(2);
        System.out.println(upgradeCalculator.getValue());

        System.out.println("---MaxLimit 100---");

        Calculator maxLimitCalculator = new MaxLimitCalculator();
        System.out.println(maxLimitCalculator.getValue());

        maxLimitCalculator.add(100);
        System.out.println(maxLimitCalculator.getValue());

        maxLimitCalculator.add(1);
        System.out.println(maxLimitCalculator.getValue());

        System.out.println("---isOdd?---");

        System.out.println(maxLimitCalculator.isOdd(1));
        System.out.println(maxLimitCalculator.isOdd(2));

        System.out.println("---Array%List average calculate---");
        // overloading check
        Calculator calculator = new Calculator();
        System.out.println(calculator.average(new int[]{1, 2, 3, 4, 5, 6, 7}));
//        System.out.println(calculator.average(List.of(1, 2, 3, 4, 5, 6, 7)));

        System.out.println("---Mineral Calculator");
        MineralCalculator mineralCalculator = new MineralCalculator();

        mineralCalculator.add(new Gold());
        System.out.println(mineralCalculator.getValue());

        mineralCalculator.add(new Silver());
        System.out.println(mineralCalculator.getValue());

        mineralCalculator.add(new Bronze());
        System.out.println(mineralCalculator.getValue());
    }
}

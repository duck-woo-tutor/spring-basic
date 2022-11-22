package playground.calculator;

import playground.calculator.mineral.Mineral;

public class MineralCalculator {
    int value = 0;

    public void add(Mineral mineral) {
        this.value += mineral.getValue();
    }

    public int getValue() {
        return this.value;
    }
}

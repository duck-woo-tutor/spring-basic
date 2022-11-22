package playground.calculator;

public class MaxLimitCalculator extends Calculator {
    @Override
    public void add(int value) {
        int tempValue = this.value + value;
        if (tempValue > 100) {
            System.out.println("value의 값이 100을 초과하였습니다.");
            return;
        }

        super.add(value);
    }
}

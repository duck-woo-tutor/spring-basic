package syntax;

public class Basic {
    public static void main(String[] args) {
        // for
        /* pseudo code
        for(초기값 ; 조건식 ; 증감식){
            실행 코드 블럭
        }
         */
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += (i + 1);
        }
        System.out.println(sum);

        System.out.println("--for-each--");
        int[] days = new int[]{1, 2, 3, 4};
        for (int day : days) {
            System.out.println(day);
        }

        System.out.println("--while--");
        int i = 0;
        sum = 0;
        while (i < 10) {
            i += 1;
            if (i==3){
                continue;
            }

            if (i==5){
                break;
            }

            sum += i + 1;
        }
        System.out.println(sum);
        System.out.println("----");

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

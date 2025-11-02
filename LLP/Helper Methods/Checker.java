public class Checker {
    public Checker(int value, int expectedValue) {
        if (Integer.valueOf(value).equals(expectedValue)) {
            System.out.println("===================================================");
            System.out.println("[OK] Successfully Returned the expected Output [OK]");
            System.out.println("===================================================");
        } else {
            System.out.println("===================================================");
            System.out.println("[X] Returned Unexpected Output [X]");
            System.out.println("===================================================");
        }
        System.out.println();
    }
}

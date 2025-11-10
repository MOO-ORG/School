package OOP;

public class Helper {

    public static void organize(String str) {
        System.out.println();
        System.out.println("=============== " + str + " ===============");
        System.out.println();
    }

    public static void checker(Object fetchedValue, Object checkValue) {
        boolean testPassed = false;

        // Check if both are integers
        if (fetchedValue instanceof Integer && checkValue instanceof Integer) {
            int obj1 = (int) fetchedValue;
            int obj3 = (int) checkValue;
            testPassed = (obj1 == obj3);
        }
        // Check if both are strings
        else if (fetchedValue instanceof String && checkValue instanceof String) {
            String obj2 = (String) fetchedValue;
            String obj4 = (String) checkValue;
            testPassed = obj2.equals(obj4);
        }

        if (testPassed) {
            organize("Test Pass");
        } else {
            organize("Test Failed");
        }
    }
}

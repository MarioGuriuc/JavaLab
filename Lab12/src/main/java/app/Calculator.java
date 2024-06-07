package app;

public class Calculator {

    @Test
    public void testAdd() {
        int result = add(2, 3);
        if (result != 5) {
            throw new AssertionError("Addition test failed");
        }
    }

    @Test
    public void testSubtract() {
        int result = subtract(5, 3);
        if (result != 2) {
            throw new AssertionError("Subtraction test failed");
        }
    }

    @Test
    public void testMultiply() {
        int result = multiply(2, 3);
        if (result != 6) {
            throw new AssertionError("Multiplication test failed");
        }
    }

    @Test
    public void testDivide() {
        int result = divide(6, 2);
        if (result != 3) {
            throw new AssertionError("Division test failed");
        }
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }
}

public class Homework {
    private boolean checkKReduction(int num, int k) {
        if (num == k) {
            return true;
        }
        if (num < k || num < 10) {
            return false;
        }
        int nextReduction = 0;
        while (num > 0) {
            int digit = num % 10;
            int squaredDigit = digit * digit;
            nextReduction += squaredDigit;
            num /= 10;
        }
        return checkKReduction(nextReduction, k);
    }
    public void findAllKReductible(int a, int b, int k) {
        StringBuilder str = new StringBuilder("The " + k + " reductilbe numbers in the interval [" + a +
                                              ", " + b + "]" + " are:\n");
        for (int i = a; i <= b; i++) {
            boolean possibleKNumber = checkKReduction(i, k);
             if (possibleKNumber) {
                str.append(i).append(" ");
            }
        }
        System.out.println(str);
    }
}
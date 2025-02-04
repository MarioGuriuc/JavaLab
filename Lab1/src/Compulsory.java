public class Compulsory {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String[] languages = { "C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java" };
        int n = (int) (Math.random() * 1_000_000);
        n = (n * 3 + 0b10101 + 0xFF) * 6;
        int result = 0;
        boolean done = false;
        while (!done) {
            while (n >= 10) {
                result += n % 10;
                n /= 10;
            }
            result += n % 10;
            if (result <= 9) {
                done = true;
            }
            else {
                n = result;
                result = 0;
            }
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
        int a = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        int k = Integer.parseInt(args[3]);
        System.out.println(a + " " + b + " " + k);
        if (a < b && k >= 1) {
            long start = System.currentTimeMillis();
            Homework homework = new Homework();
            homework.findAllKReductible(a, b, k);
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println("Time elapsed: " + timeElapsed);
        }
        else {
            System.out.println("Invalid arguments\n");
        }
    }
}

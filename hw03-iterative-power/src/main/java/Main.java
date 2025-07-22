public class Main {
    public static void main(String... args) {

        System.out.println("Итеративный O(N) алгоритм возведения числа в степень");

        Test test1 = new Tester(new IterativePower());

        test1.run();

        System.out.println("Алгоритм возведения в степень через двоичное разложение показателя степени");
        Test test2 = new Tester(new BinaryPower());

        test2.run();
    }
}

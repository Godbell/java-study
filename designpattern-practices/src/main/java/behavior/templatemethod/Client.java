package behavior.templatemethod;

public class Client {
    public static void main(String[] args) {
        AbstractCalculator abstractCalculator = new MultiplyCalculator();
        abstractCalculator.templateMethod();
        abstractCalculator = new DivideCalculator();
        abstractCalculator.templateMethod();
    }
}

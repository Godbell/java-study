package behavior.strategy;

public class Client {
    public static void main(String[] args) {
        new CalculateContext().operation(Integer::sum);
        new CalculateContext().operation(((v1, v2) -> v1 - v2));
        new CalculateContext().operation(((v1, v2) -> v1 * v2));
        new CalculateContext().operation(((v1, v2) -> v1 / v2));
    }
}

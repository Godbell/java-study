package prob01;

public class PrinterTest {

	public static void main(String[] args) {
		Printer printer = new Printer();

		printer.println(10);
		printer.println(true);
		printer.println(5.7);
		printer.println("홍길동");
		
		printer.println(printer.sum(1, 2, 3, 4, 5, 6));
	}
}
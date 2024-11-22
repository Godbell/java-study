package paint;

public class PaintApp {
	public static void main(String[] args) {
		Point point1 = new Point();
		point1.setX(10);
		point1.setY(20);
		drawPoint(point1);
		
		Point point2 = new Point(100, 200);
		drawPoint(point1);
		erasePoint(point2);
		
		ColorPoint point3 = new ColorPoint(50, 100, "red");
//		point3.setX(50);
//		point3.setY(100);
//		point3.setColor("red");
		
//		drawPoint(point3);
//		drawShape(new Rectangle());
//		drawShape(new Triangle());
//		drawShape(new Circle());
		
		draw(point3);
		draw(new Rectangle());
		draw(new Triangle());
		draw(new Circle());
		draw(new GraphicText("안녕"));
		
		// instanceof 연산자
		// 우항이 클래스인 경우 좌항(레퍼런스 타입)의 hierarchy의 직결된 상하위만 검사 가능
		// 인터페이스라면 상관없이 사용 가능 
		Circle c = new Circle();
		
		if (c instanceof Drawable) {
			System.out.println("c는 Drawable instance입니다.");
		}
	}
	
	public static void drawPoint(Point point) {
		point.draw();
	}
	
	public static void erasePoint(Point point) {
		point.draw(false);
	}
	
	public static void drawShape(Shape shape) {
		shape.draw();
	}
	
	public static void draw(Drawable drawable) {
		drawable.draw();
	}
}
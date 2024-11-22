package prob05;

public class RectTriangle extends Shape{
	public RectTriangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public double getArea() {
		return this.height * this.width / 2;
	}

	@Override
	public double getPerimeter() {
		return this.height + this.width + Math.sqrt(width * width + height * height);
	}
}

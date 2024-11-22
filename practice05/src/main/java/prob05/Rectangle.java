package prob05;

public class Rectangle extends Shape implements Resizable {
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void resize(double rate) {
		this.height *= rate;
		this.width *= rate;
	}

	@Override
	public double getArea() {	
		return this.height * this.width;
	}

	@Override
	public double getPerimeter() {
		return (this.height + this.width) * 2;
	}

}

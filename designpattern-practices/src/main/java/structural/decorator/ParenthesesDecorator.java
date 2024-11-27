package structural.decorator;

public class ParenthesesDecorator extends Decorator {
	Component component;
	
	public ParenthesesDecorator(Component component) {
		this.component = component;
	}
	
	@Override
	public String operation() {
		return "{" + component.operation() + "}";
	}
}

package prob02;

public abstract class Bird {
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	protected abstract String getType();
	
	public abstract void fly();
	
	public abstract void sing();
	
	@Override
	public String toString() {
		return getType() + "의 이름은 " + this.name + "입니다.";
	}
}
package prob02;

public class Sparrow extends Bird {
	private final String TYPE = "참새";

	@Override
	public void fly() {
		System.out.println(
			String.format(
				"참새(%s)가 날아다닙니다.",
				this.name
			)
		);
	}
	
	@Override
	public void sing() {
		System.out.println(
			String.format(
				"참새(%s)가 소리내어 웁니다.",
				this.name
			)
		);
	}
	
	@Override
	protected String getType() {
		return TYPE;
	}
}

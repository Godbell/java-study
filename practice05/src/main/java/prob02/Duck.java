package prob02;

public class Duck extends Bird {
	private final String TYPE = "오리";
	
	@Override
	public void fly() {
		System.out.println(
			String.format(
				"오리(%s)는 날지 않습니다.",
				getName()
			)
		);
	}
	
	@Override
	public void sing() {
		System.out.println(
			String.format(
				"오리(%s)가 소리내어 웁니다.",
				getName()
			)
		);
	}
	
	@Override
	protected String getType() {
		return TYPE;
	}
}

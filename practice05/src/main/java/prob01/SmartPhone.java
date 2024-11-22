package prob01;

public class SmartPhone extends MusicPhone {
	@Override
	public void execute(String function) {
		switch (function) {
			case "앱":
				runApp();
				break;
			default:
				super.execute(function);
		}
	}
	
	@Override
	protected void playMusic() {
		System.out.println("다운로드해서 음악재생");
	}
	
	protected void runApp() {
		System.out.println("앱실행");
	}
}

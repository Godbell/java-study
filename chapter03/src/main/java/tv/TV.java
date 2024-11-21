package tv;

public class TV {
	private int channel;
	private int volume;
	private boolean power;
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}
	
	public int getChannel() {
		return this.channel;
	}
	
	public int getVolume() {
		return this.volume;
	}
	
	public boolean isPower() {
		return power;
	}
	
	public void power(boolean pow) {
		this.power = pow;
	}
	
	public void channel(int target) {
		if (!this.power) {
			return;
		}
		
		if (target > 255) {
			this.channel = 255;
		} else if (target < 1) {
			this.channel = 1;
		} else {
			this.channel = target; 
		}
	}
	
	public void channel(boolean up) {
		if (!this.power) {
			return;
		}
		
		if (up && this.channel >= 255) {
			this.channel = 1;
		} else if (!up && this.channel <= 1) {
			this.channel = 255;
		} else {
			int diff = up ? 1 : -1;
			this.channel += diff;
		}
	}
	
	public void volume(int target) {
		if (!this.power) {
			return;
		}
		
		if (target > 100) {
			this.volume = 100;
		} else if (target < 0) {
			this.volume = 0;
		} else {
			this.volume = target; 
		}
	}
	
	public void volume(boolean up) {
		if (!this.power) {
			return;
		}
		
		if (up && this.volume >= 255) {
			this.volume = 255;
		} else if (!up && this.volume <= 1) {
			this.volume = 1;
		} else {
			int diff = up ? 1 : -1;
			this.volume += diff;
		}
	}
	
	public void status() {
		System.out.println(
			String.format(
				"power: %s\nchannel: %d\nvolume: %d",
				this.power ? "ON" : "OFF",
				this.channel,
				this.volume
			)
		);
	}
}

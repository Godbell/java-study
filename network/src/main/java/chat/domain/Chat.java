package chat.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat {
	public final String nickname;
	public final String message;
	private final Date createdAt;
	
	public Chat(String nickname, String chat) {
		this.nickname = nickname;
		this.message = chat;
		this.createdAt = new Date();
	}
	
	public String getCreatedAt() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(this.createdAt);
	}
}

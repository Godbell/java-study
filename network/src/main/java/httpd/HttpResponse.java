package httpd;

public class HttpResponse {
	private int statusCode;
	private String contentType;
	private String protocol;
	private byte[] body;

	public HttpResponse() {
		this.statusCode = 200;
		this.contentType = "text/plain";
		this.setProtocol("HTTP/1.1");
		this.body = "OK".getBytes();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public void setBody(String body) {
		setBody(body.getBytes());
	}

	public static String getName(int statusCode) {
		switch (statusCode) {
		case 200:
			return "OK";
		case 400:
			return "Bad Request";
		case 404:
			return "Not Found";
		default:
			return null;
		}
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}

package httpd;

@SuppressWarnings("serial")
public abstract class HttpException extends Exception {
	public final int statusCode;
	public final String status;
	
	public HttpException(int statusCode, String status) {
		super(status);
		
		this.statusCode = statusCode;
		this.status = status;
	}
}

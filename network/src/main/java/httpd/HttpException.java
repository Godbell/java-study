package httpd;

@SuppressWarnings("serial")
public abstract class HttpException extends Exception {
	public final int statusCode;
	public final String status;

	public HttpException(int statusCode) {
		super(HttpResponse.getName(statusCode));

		this.statusCode = statusCode;
		this.status = HttpResponse.getName(statusCode);
	}
}

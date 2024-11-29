package httpd;

@SuppressWarnings("serial")
public class BadRequestException extends HttpException {
	public BadRequestException() {
		super(400);
	}
}

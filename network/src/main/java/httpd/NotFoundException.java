package httpd;

@SuppressWarnings("serial")
public class NotFoundException extends HttpException {
	public NotFoundException() {
		super(404);
	}
}

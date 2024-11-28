package httpd;

public class SimpleHttpServer {
	private static final int PORT = 8088;

	public static void main(String[] args) {
		HttpServer server = new HttpServer("0.0.0.0", PORT);
		
		server.listen();
	}
}
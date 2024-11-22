package prob03;

public class Book {
	public final int no;
	private final String title;
	private final String author;
	private int statusCode;
	
	public Book(int no, String title, String author) {
		this.no = no;
		this.title = title;
		this.author = author;
		this.statusCode = 1;
	}
	
	public void rent() {
		this.statusCode = 0;
		System.out.println("<" + this.title + ">이(가) 대여 됐습니다.");
	}
	
	public void print() {
		System.out.println(
			String.format(
				"Book No. %d, <%s>, %s, %s", 
				this.no,
				this.title,
				this.author,
				this.statusCode == 1 ? "재고있음" : "대여중"
			)
		);
	}
}

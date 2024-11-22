package prob04;

public class MyStack {
	private int top;
	private String[] buffer;

	public MyStack(int capacity) {
		this.buffer = new String[capacity];
		this.top = -1;
	}

	public void push(String s) {
		if (top == buffer.length - 1) {
			resize();
		}

		++this.top;
		buffer[top] = s;
	}

	public String pop() throws MyStackException {
		if (top <= -1) {
			throw new MyStackException();
		}
		
		String topString = this.buffer[this.top];
		this.buffer[this.top] = null;
		--this.top;
		
		return topString;
	}

	public boolean isEmpty() {
		return this.top + 1 == 0;
	}

	private void resize() {
		String[] newBuffer = new String[this.top + 2];
		
		for (int i = 0; i < this.top + 1; ++i) {
			newBuffer[i] = this.buffer[i];
		}
		
		this.buffer = newBuffer;
	}	
}
package prob04;

import java.util.Arrays;

public class MyStack02 {
	private int top;
	private Object[] buffer;

	public MyStack02(int capacity) {
		this.buffer = new Object[capacity];
		this.top = -1;
	}

	public void push(Object s) {
		if (top == buffer.length - 1) {
			resize();
		}

		++this.top;
		buffer[top] = s;
	}

	public Object pop() throws MyStackException {
		if (top <= -1) {
			throw new MyStackException();
		}
		
		Object topString = this.buffer[this.top];
		this.buffer[this.top] = null;
		--this.top;
		
		return topString;
	}

	public boolean isEmpty() {
		return this.top + 1 == 0;
	}

	private void resize() {
		this.buffer = Arrays.copyOf(this.buffer, this.buffer.length + 1);
	}
}
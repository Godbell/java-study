package prob04;

import java.util.Arrays;

public class MyStack03<T> {
	private int top;
	private T[] buffer;

	@SuppressWarnings("unchecked")
	public MyStack03(int capacity) {
		this.buffer = (T[])new Object[capacity];
		this.top = -1;
	}

	public void push(T s) {
		if (top == buffer.length - 1) {
			resize();
		}

		++this.top;
		buffer[top] = s;
	}

	public T pop() throws MyStackException {
		if (top <= -1) {
			throw new MyStackException();
		}
		
		T topString = this.buffer[this.top];
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
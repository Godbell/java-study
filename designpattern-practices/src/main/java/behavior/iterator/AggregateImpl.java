package behavior.iterator;

public class AggregateImpl<E> implements Aggregate<E> {
	private E[] data = null;
	
	public AggregateImpl(E[] data) {
		this.data = data;
	}
	
	@Override
	public Iterator<E> createIterator() {
		return new IteratorImpl();
	}
	
	private class IteratorImpl implements Iterator<E> {
		private int index = 0;
		
		@Override
		public E next() {
			return index < data.length ? data[index++] : null;
		}
		
		@Override
		public boolean hasNext() {
			return index < data.length;
		}
	}
}

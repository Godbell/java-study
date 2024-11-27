package behavior.iterator;

public interface Aggregate<E> {
	Iterator<E> createIterator();
}

package andersen.collections.collection;

public interface IHashSet<T> extends ICollection<T> {
	int contains(T element);

	boolean delete(T element);
}

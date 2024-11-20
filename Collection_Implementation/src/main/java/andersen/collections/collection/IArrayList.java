package andersen.collections.collection;

public interface IArrayList<T> extends ICollection<T> {
	/**
	 * 
	 * @param index
	 * @return element by given index, otherwise
	 */
	T get(int index);

	/**
	 * Insert @param element to @param index position
	 * 
	 */
	ICollection<T> put(int index, T element);

	/**
	 * Find and remove by @param index if exists
	 * 
	 * @return true if can be deleted, otherwise false
	 */
	boolean delete(int index);
}

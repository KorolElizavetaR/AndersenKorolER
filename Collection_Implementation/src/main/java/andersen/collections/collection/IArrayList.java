package andersen.collections.collection;

public interface IArrayList<T> extends ICollection<T>{
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
	void put(int index, T element);
	
}

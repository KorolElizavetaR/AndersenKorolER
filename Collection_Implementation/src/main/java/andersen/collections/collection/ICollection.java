package andersen.collections.collection;

interface ICollection<T> {
	/**
	 * 	Appends  @param element to the end list
	 */
	void put(T element);
	
	/**
	 * Find and remove by @param index if exists
	 * @return true if can be deleted, otherwise false
	 */
	boolean delete(int index);
}

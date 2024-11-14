package andersen.collections.collection.impl;

import andersen.collections.collection.IArrayList;

public class ArrayListImpl<T> implements IArrayList<T> {
	private final int DEFAULT_CAPACITY = 10;
	/**
	 * Defines the grows of {@link #capacity}
	 */
	private final byte RESIZE_FACTOR = 2;

	T array[];
	int size;
	int capacity;

	public ArrayListImpl() {
		this.size = 0;
		this.capacity = 0;
		this.array = (T[]) new Object[capacity];
	}

	@Override
	public void put(T element) {
		if (requiresResizing()) {
			capacity *= RESIZE_FACTOR;
			T[] bufArray = (T[]) new Object[capacity];
			for (int i = 0; i < size; i++) {
				bufArray[i] = array[i];
			}
			array = bufArray;
		}
		array[size++] = element;
	}


	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(int index, T element) {
		// TODO Auto-generated method stub

	}

	private boolean requiresResizing() {
		if ((size < capacity/RESIZE_FACTOR && capacity > DEFAULT_CAPACITY) || size == capacity) {
			return true;
		return false;
	}

}

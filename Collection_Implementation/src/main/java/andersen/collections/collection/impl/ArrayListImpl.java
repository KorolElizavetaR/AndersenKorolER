package andersen.collections.collection.impl;

import andersen.collections.collection.IArrayList;
import lombok.Getter;

public class ArrayListImpl<T> implements IArrayList<T> {
	private final int DEFAULT_CAPACITY = 10;
	/**
	 * Defines the grows/shrink of {@link #capacity}
	 */
	private final byte RESIZE_FACTOR = 2;

	T array[];
	@Getter
	private int size;
	private int capacity;

	public ArrayListImpl() {
		this.size = 0;
		this.capacity = DEFAULT_CAPACITY;
		this.array = (T[]) new Object[capacity];
	}

	/**
	 * Add @param element to the end of a list
	 * 
	 * @return current object to allow chaining
	 */
	@Override
	public ArrayListImpl<T> put(T element) {
		if (requiresResizing()) {
			capacity *= RESIZE_FACTOR;
			T[] bufArray = (T[]) new Object[capacity];
			for (int i = 0; i < size; i++) {
				bufArray[i] = array[i];
			}
			array = bufArray;
		}
		array[size++] = element;
		return this;
	}

	@Override
	public T get(int index) {
		return array[index];
	}

	@Override
	public void put(int index, T element) {
		if (requiresResizing()) {
			capacity *= RESIZE_FACTOR;
			T[] bufArray = (T[]) new Object[capacity];
			for (int i = 0; i < index; i++) {
				bufArray[i] = array[i];
			}
			for (int i = index; i < size; i++) {
				bufArray[i + 1] = array[i];
			}
			bufArray[index] = element;
			array = bufArray;
		} else {
			for (int i = index; i < size; i++) {
				array[i + 1] = array[i];
			}
			array[index] = element;
		}
	}

	// REQUIRES REFACTORING //
	private boolean requiresResizing() {
		if ((size < capacity / RESIZE_FACTOR && capacity > DEFAULT_CAPACITY) || size == capacity) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int index) {
		// TODO Auto-generated method stub
		return false;
	}
}

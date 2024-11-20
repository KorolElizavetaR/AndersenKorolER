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
	public ArrayListImpl<T> put(int index, T element) {
		if (index > size)
			throw new IndexOutOfBoundsException(index);
		if (index == size) {
			return put(element);
		}
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
			for (int i = size; i > index; i--) {
				array[i] = array[i - 1];
			}
			array[index] = element;
		}
		size++;
		return this;
	}

	private boolean requiresResizing() {
		return (size <= (capacity / RESIZE_FACTOR) && capacity > DEFAULT_CAPACITY) || size == capacity;
	}

	@Override
	public boolean delete(int index) {
		if (index > size)
			throw new IndexOutOfBoundsException(index);
		if (requiresResizing()) {
			capacity /= RESIZE_FACTOR;
			T[] bufArray = (T[]) new Object[capacity];
			for (int i = 0; i < index; i++) {
				bufArray[i] = array[i];
			}
			for (int i = index; i < size - 1; i++) {
				bufArray[i] = array[i + 1];
			}
			array = bufArray;
		} else {
			for (int i = index; i < size; i++) {
				array[i] = array[i + 1];
			}
			array[size] = null;
		}
		size--;
		return false;
	}
}

package andersen.collections.collection.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import andersen.collections.collection.IHashSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class HashSetImpl<T> implements IHashSet<T>, Iterable<T> {
	/**
	 * I've decided to use ArrayList to ease my suffer from working with memory,
	 * hope that's okay.
	 */
	@Getter
	List<MappedValue> hashArr = new ArrayList<HashSetImpl<T>.MappedValue>();

	@AllArgsConstructor
	@Data
	class MappedValue {
		protected T value;
		protected int hash;
	}

	@Override
	public HashSetImpl<T> put(T element) {
		MappedValue hashElement = new MappedValue(element, element.hashCode());
		int index = contains(hashElement);
		if (index < 0) {
			index = -index - 1;
		} else {
			return this;
		}
		hashArr.add(index, hashElement);
		return this;
	}

	@Override
	public boolean delete(T element) {
		int index = contains(element);
		if (index < 0) {
			return false;
		}
		hashArr.remove(index);
		return true;
	}

	@Override
	public int contains(T element) {
		MappedValue hashElement = new MappedValue(element, element.hashCode());
		return contains(hashElement);
	}

	private int contains(MappedValue hashElement) {
		return Collections.binarySearch(hashArr, hashElement, Comparator.comparing(MappedValue::getHash));
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private final Iterator<MappedValue> innerIterator = hashArr.iterator();

			@Override
			public boolean hasNext() {
				return innerIterator.hasNext();
			}

			@Override
			public T next() {
				return innerIterator.next().getValue();
			}
		};
	}
}

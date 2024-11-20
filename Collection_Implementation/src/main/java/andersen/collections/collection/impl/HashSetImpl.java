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

	public boolean delete(T element) {
		int index = contains(element);
		if (index < 0) {
			return false;
		}
		hashArr.remove(index);
		return true;
	}

	public int contains(T element) {
		MappedValue hashElement = new MappedValue(element, element.hashCode());
		return Collections.binarySearch(hashArr, hashElement, Comparator.comparing(MappedValue::getHash));
	}

	private int contains(MappedValue hashElement) {
		return Collections.binarySearch(hashArr, hashElement, Comparator.comparing(MappedValue::getHash));
	}

//	private int binarySearcherForHash(long key) {
//		int low = 0;
//		int high = hashArr.size() - 1;
//
//		while (low <= high) {
//			int mid = (low + high) >>> 1;
//			long midVal = hashArr.get(mid).hash;
//			if (midVal < key)
//				low = mid + 1;
//			else if (midVal > key)
//				high = mid - 1;
//			else
//				return mid; 
//		}
//		return -(low + 1);
//	}

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

package andersen.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import andersen.collections.collection.IArrayList;
import andersen.collections.collection.impl.ArrayListImpl;

public class Application {
	public static void main(String[] args) {
		ArrayListImpl<Integer> arr = new ArrayListImpl<>();
		arr.put(2).put(4).put(5).put(6);
		for (int i = 0; i < arr.getSize(); i++) {
			System.out.print(arr.get(i) + " ");
		}
	}
}

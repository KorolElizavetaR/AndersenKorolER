package andersen.collections;

import andersen.collections.collection.impl.HashSetImpl;

public class Application {
	public static void main(String[] args) {
//		ArrayListImpl<Integer> arr = new ArrayListImpl<>();
//		arr.put(2).put(4).put(5).put(6).put(2, 10);
//		arr.delete(1);
//		for (int i = 0; i < arr.getSize(); i++) {
//			System.out.print(arr.get(i) + " ");
//		}
		
		HashSetImpl<String> hash = new HashSetImpl<>();
		hash.put("Apple").put("Wine").put("Party").put("Sweden").put("AAAAA");
		hash.delete("Apple");
		for (String val : hash) {
			System.out.print(val + " | ");
		}
	}
}

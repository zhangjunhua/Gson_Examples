import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Example {
	static void basicExamples() {
		Gson gson = new Gson();
		gson.toJson(1); // ==> 1
		gson.toJson("abcd"); // ==> "abcd"
		gson.toJson(new Long(10)); // ==> 10
		int[] values = { 1 };
		gson.toJson(values); // ==> [1]

		// 反序列化
		int one = gson.fromJson("1", int.class);
		Integer One = gson.fromJson("1", Integer.class);
		Long ONE = gson.fromJson("1", Long.class);
		Boolean bool = gson.fromJson("false", Boolean.class);
		String str = gson.fromJson("\"abc\"", String.class);
		String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
	}

	static void objExamples() {
		BagOfPrimitives obj = new BagOfPrimitives();
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
	}

	static void arrayExamples() {
		Gson gson = new Gson();
		int[] ints = { 1, 2, 3, 4, 5 };
		String[] strings = { "abc", "def", "ghi" };

		// 序列化
		String intsrt = gson.toJson(ints); // ==> [1,2,3,4,5]
		String strstr = gson.toJson(strings); // ==> ["abc", "def", "ghi"]

		// 反序列化
		int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);
		String[] strings2 = gson.fromJson(strstr, String[].class);
	}

	static void collectionExamples() {
		Gson gson = new Gson();
		ArrayList<Integer> ints = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

		// 序列化
		String json = gson.toJson(ints); // ==> json is [1,2,3,4,5]
		System.out.println(ints.getClass());
		// 反序列化
		Type collectionType = new TypeToken<ArrayList<Integer>>() {
		}.getType();
		ArrayList<Integer> ints2 = gson.fromJson(json, collectionType);
		// ==> ints2 和 ints 相同
	}

	static void genericTypeExamples() {
		Gson gson = new Gson();
		Foo<Integer> foo = new Foo<Integer>(1);
		System.out.println(foo.getClass());
		Type fooType = new TypeToken<Foo<Integer>>() {}.getType();
		String json=gson.toJson(foo, fooType);

		Foo<Integer> foo2=gson.fromJson(json, fooType);
	}

	public static void main(String[] args) {
		basicExamples();
		objExamples();
		arrayExamples();
		collectionExamples();
		genericTypeExamples();
	}
}

class Foo<T> {
	T value;
	public Foo(T value) {
		this.value=value;
	}
}

class BagOfPrimitives {
	private int value1 = 1;
	private String value2 = "abc";
	private transient int value3 = 3;

	BagOfPrimitives() {
		// 无参构造方法
	}
}
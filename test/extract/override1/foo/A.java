package foo;

import java.util.List;

public class A {

	public String method1(List<Integer> l) {
		int sum = getSum(l);
		System.out.println("method1");
		System.out.println("method1");
		System.out.println("method1");
		return "sum : " + sum;
	}

	protected int getSum(List<Integer> l) {
		int sum = 0;
		for (Integer i : l) {
			sum += i;
		}
		return sum;
	}

}

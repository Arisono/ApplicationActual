package com.app.demo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :LiuJie 2015年11月6日 下午2:35:19
 * @注释:
 */
public class TestItemOne {
	public String id;
	public String content;
	public static List<TestItemOne> TestItemOneS = new ArrayList<TestItemOne>();
	static {
		addTestItemOne(new TestItemOne("1", "TestItemOne 1"));
		addTestItemOne(new TestItemOne("2", "TestItemOne 2"));
		addTestItemOne(new TestItemOne("3", "TestItemOne 3"));
		addTestItemOne(new TestItemOne("4", "TestItemOne 4"));
		addTestItemOne(new TestItemOne("5", "TestItemOne 5"));
		addTestItemOne(new TestItemOne("6", "TestItemOne 6"));
	}

	private static void addTestItemOne(TestItemOne TestItemOne) {
		TestItemOneS.add(TestItemOne);
	}

	public TestItemOne(String id, String content) {
		this.id = id;
		this.content = content;
	}

	@Override
	public String toString() {
		return content;
	}

}

package com.nbcedu.function.schoolmaster2.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;


import com.google.common.collect.Lists;
import com.google.common.collect.Queues;

public class Random {
	public static void main(String[] args) {
		
		List<String> origin = Arrays.asList("a","b","c","d","e","f","g");
		
		print(origin);
		
		List<String> newList = new ArrayList<String>();
		newList.addAll(origin);
		print(newList);
		
		Collections.shuffle(newList);
		Queue<String> que = Queues.newArrayDeque(newList);
		print(newList);
		print(origin);
		
		for (int i = 0; i < newList.size(); i++) {
			System.out.println(que.poll());
		}
	}
	
	private static final void print(List list){
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ",");
		}
		System.out.println();
	}
}

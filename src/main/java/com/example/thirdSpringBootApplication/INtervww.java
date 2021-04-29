package com.example.thirdSpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class INtervww {
	 Map<String,Integer> map = new HashMap();
	
	public INtervww()
	{
		map.put("dev", 1);
		map.put("viresh", 2);
	}

	public void see()
	{
		System.out.println(map.get("dev"));
		System.out.println(map.get("dev"));
	}
	public static char[] removeDuplicates(char[] arg) {
		
		
		ArrayList<Character> list = new ArrayList<Character>();
		int j = 0;
		int k = 0;
		while (k < arg.length) {
			if (!list.contains(arg[k])) {
				list.add(arg[k]);
				k++;
			} else {
				
				if (arg[k] == list.get(j)) 
                { 
					 list.remove(j); 
					 j = 0;
				} 
                else
                {
                    if(j<list.size()) 
					 j++;
                }

			}
		}

		Object[] obj = list.toArray();
		char[] c = new char[obj.length];

		for (int i = 0; i < obj.length; i++) {
			char ch = (char) obj[i];
			c[i] = ch;

		}
		return c;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	System.out.println(removeDuplicates(new char[] { 'a', 'b', 'c', 'a', 'c', 'a' }));
		INtervww v = new INtervww();
		v.see();

	}

}
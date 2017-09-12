package com.hhly.smartdata.util;

import java.util.HashSet;
import java.util.List;

public class ListUtil {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}
}

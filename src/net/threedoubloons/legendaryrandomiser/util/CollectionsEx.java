package net.threedoubloons.legendaryrandomiser.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CollectionsEx {
	public static boolean hasDuplicate(Collection<?> c) {
		Set<?> s = new HashSet<Object>(c);
		return s.size() != c.size();
	}
}

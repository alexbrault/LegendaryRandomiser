package net.threedoubloons.legendaryrandomiser.data;

import java.util.EnumSet;

public enum Sets {
	CoreSet,
	DarkCity;	
	
	public long mask() {
		return (1L << ordinal());
	}
	
	public static EnumSet<Sets> make(long flags) {
		EnumSet<Sets> sets = EnumSet.noneOf(Sets.class);
		
		for (Sets s : Sets.values()) {
			if ((flags & s.mask()) != 0) {
				sets.add(s);
			}
		}		
		
		return sets;
	}
	
	public static EnumSet<Sets> make(Sets... sets) {
		EnumSet<Sets> ret = EnumSet.noneOf(Sets.class);
		
		for (Sets s : sets) {
			ret.add(s);
		}		
		
		return ret;
	}
	
	public static long unmake(EnumSet<Sets> sets) {
		long ret = 0L;
		for (Sets s : sets) {
			ret |= s.mask();
		}
		
		return ret;
	}
	
	public static long unmake(Sets... sets) {
		long ret = 0L;
		for (Sets s : sets) {
			ret |= s.mask();
		}
		
		return ret;
	}
}

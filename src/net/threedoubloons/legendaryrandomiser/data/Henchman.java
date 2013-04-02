package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;
import net.threedoubloons.legendaryrandomiser.data.Villain;

public class Henchman extends Villain implements Serializable {
	private static final long serialVersionUID = -3646715723455606919L;
	public Henchman(int name, int pictureId) {
		super(name, pictureId);
	}

	public final static Henchman handNinja = new Henchman(R.string.hand_ninja, R.drawable.hand_ninja);
	public final static Henchman doombots = new Henchman(R.string.doombots, R.drawable.doombots);
	public final static Henchman sentinel = new Henchman(R.string.sentinel, R.drawable.sentinel);
	public final static Henchman mutates = new Henchman(R.string.mutates, R.drawable.mutates);
	
	private final static Henchman[] coreSet = {handNinja, doombots, sentinel, mutates};

	private static List<Henchman> all;
	public static void initialiseAllList(EnumSet<Sets> activeSets) {
		List<Henchman> all = new ArrayList<Henchman>();
		if (activeSets.contains(Sets.CoreSet)) {
			all.addAll(Arrays.asList(coreSet));
		}
		
		Henchman.all = Collections.unmodifiableList(all);
	}
	
	
	public static List<Henchman> getAllHenchmen() {
		return all;
	}
}


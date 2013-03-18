package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

public class Mastermind extends CardBase implements Serializable {
	private static final long serialVersionUID = -8346554204948014704L;
	private final Villain alwaysLeads;
	
	public Villain getAlwaysLeads() {
		return alwaysLeads;
	}
	public Mastermind(int name, int pictureId, Villain alwaysLeads) {
		super(name, pictureId);
		this.alwaysLeads = alwaysLeads;
	}

	private final static Mastermind[] coreSet = {
		new Mastermind(R.string.red_skull, R.drawable.red_skull, Villain.hydra), 
		new Mastermind(R.string.loki, R.drawable.loki, Villain.enemiesOfAsgard), 
		new Mastermind(R.string.dr_doom, R.drawable.drdoom, Henchman.doombots), 
		new Mastermind(R.string.magneto, R.drawable.magneto, Villain.brotherhood)
	};

	private static List<Mastermind> all;
	public static void initialiseAllList(long sets) {
		List<Mastermind> all = new ArrayList<Mastermind>();
		if ((sets & Sets.CoreSet) == Sets.CoreSet) {
			all.addAll(Arrays.asList(coreSet));
		}
		
		Mastermind.all = Collections.unmodifiableList(all);
	}
	
	public final static List<Mastermind> getAll() {
		return all;
	}
}
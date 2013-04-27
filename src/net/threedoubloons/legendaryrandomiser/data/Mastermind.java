package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

public enum Mastermind implements ICardBase {
	none(0, 0, Villain.none),
	redSkull(R.string.red_skull, R.drawable.red_skull, Villain.hydra), 
	loki(R.string.loki, R.drawable.loki, Villain.enemiesOfAsgard), 
	drDoom(R.string.dr_doom, R.drawable.drdoom, Villain.doombots), 
	magneto(R.string.magneto, R.drawable.magneto, Villain.brotherhood)
	;
	private final Villain alwaysLeads;
	private final CardBase card;
	
	private Mastermind(int name, int pictureId, Villain alwaysLeads) {
		card = new CardBase(name, pictureId);
		this.alwaysLeads = alwaysLeads;
	}
	
	public Villain getAlwaysLeads() {
		return alwaysLeads;
	}

	@Override
	public CardBase getCard() {
		return card;
	}
	
	public static Mastermind get(String name) {
		return Enum.valueOf(Mastermind.class, name);
	}

	private final static Mastermind[] coreSet = {
		redSkull, loki, drDoom, magneto
	};

	private final static Mastermind[] darkCity = {};

	private static List<Mastermind> all;
	public static void initialiseAllList(EnumSet<Sets> activeSets) {
		List<Mastermind> all = new ArrayList<Mastermind>();
		if (activeSets.contains(Sets.CoreSet)) {
			all.addAll(Arrays.asList(coreSet));
		}

		if (activeSets.contains(Sets.DarkCity)) {
			all.addAll(Arrays.asList(darkCity));
		}
		
		Mastermind.all = Collections.unmodifiableList(all);
	}
	
	public final static List<Mastermind> getAll() {
		return all;
	}
}
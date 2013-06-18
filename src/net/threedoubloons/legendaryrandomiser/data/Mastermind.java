package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

public enum Mastermind implements ICardBase {
	none(0, 0, Villain.none),
	redSkull(R.string.red_skull, R.drawable.red_skull, Villain.hydra), 
	loki(R.string.loki, R.drawable.loki, Villain.enemiesOfAsgard), 
	drDoom(R.string.dr_doom, R.drawable.drdoom, Villain.doombots), 
	magneto(R.string.magneto, R.drawable.magneto, Villain.brotherhood),
	
	// Dark City
	apocalypse(0, R.drawable.missing_img, Villain.fourHorsemen),
	kingpin(0, R.drawable.missing_img, Villain.streetsOfNY),
	mephisto(0, R.drawable.missing_img, Villain.underworld),
	mrSinister(0, R.drawable.missing_img, Villain.marauders),
	stryfe(0, R.drawable.missing_img, Villain.mlf),
	;
	private final Villain alwaysLeads;
	private final CardBase card;
	
	private Mastermind(int name, int pictureId, Villain alwaysLeads) {
		this(name, pictureId, alwaysLeads, Sets.CoreSet);
	}
	
	private Mastermind(int name, int pictureId, Villain alwaysLeads, Sets set) {
		card = new CardBase(name, pictureId, set);
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

	private static List<Mastermind> all;
	public static void initialiseAllList(EnumSet<Sets> activeSets) {
		List<Mastermind> all = new ArrayList<Mastermind>();

		for (Mastermind m : values()) {
			if (activeSets.contains(m.card.getExpansion())) {
				all.add(m);
			}
		}
		
		Mastermind.all = Collections.unmodifiableList(all);
	}
	
	public final static List<Mastermind> getAll() {
		return all;
	}
}
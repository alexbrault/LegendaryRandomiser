package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

public enum Mastermind implements ICardBase {
	none(0, 0, Villain.none),
	redSkull(R.string.mm_red_skull, R.drawable.red_skull, Villain.hydra), 
	loki(R.string.mm_loki, R.drawable.loki, Villain.enemiesOfAsgard), 
	drDoom(R.string.mm_dr_doom, R.drawable.drdoom, Villain.doombots), 
	magneto(R.string.mm_magneto, R.drawable.magneto, Villain.brotherhood),
	
	// Dark City
	apocalypse(R.string.mm_apocalypse, R.drawable.missing_img, Villain.fourHorsemen, Sets.DarkCity),
	kingpin(R.string.mm_kingpin, R.drawable.missing_img, Villain.streetsOfNY, Sets.DarkCity),
	mephisto(R.string.mm_mephisto, R.drawable.missing_img, Villain.underworld, Sets.DarkCity),
	mrSinister(R.string.mm_mr_sinister, R.drawable.missing_img, Villain.marauders, Sets.DarkCity),
	stryfe(R.string.mm_stryfe, R.drawable.missing_img, Villain.mlf, Sets.DarkCity),
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
		
		all.remove(none);
		
		Mastermind.all = Collections.unmodifiableList(all);
	}
	
	public final static List<Mastermind> getAll() {
		return all;
	}
}
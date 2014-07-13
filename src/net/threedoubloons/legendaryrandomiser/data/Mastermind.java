package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

public enum Mastermind implements ICardBase {
	none(0, 0, Villain.none),
	redSkull(R.string.mm_red_skull, R.drawable.mm_red_skull, Villain.hydra), 
	loki(R.string.mm_loki, R.drawable.mm_loki, Villain.enemiesOfAsgard), 
	drDoom(R.string.mm_dr_doom, R.drawable.mm_drdoom, Villain.doombots), 
	magneto(R.string.mm_magneto, R.drawable.mm_magneto, Villain.brotherhood),
	
	// Dark City
	apocalypse(R.string.mm_apocalypse, R.drawable.mm_apocalypse, Villain.fourHorsemen, Sets.DarkCity),
	kingpin(R.string.mm_kingpin, R.drawable.mm_kingpin, Villain.streetsOfNY, Sets.DarkCity),
	mephisto(R.string.mm_mephisto, R.drawable.mm_mephisto, Villain.underworld, Sets.DarkCity),
	mrSinister(R.string.mm_mr_sinister, R.drawable.mm_mr_sinister, Villain.marauders, Sets.DarkCity),
	stryfe(R.string.mm_stryfe, R.drawable.mm_stryfe, Villain.mlf, Sets.DarkCity),
	
	// Fantastic Four
	galactus(R.string.mm_galactus, R.drawable.mm_galactus, Villain.heralds, Sets.FantasticFour),
	moleman(R.string.mm_moleman, R.drawable.mm_moleman, Villain.subterranea, Sets.FantasticFour),
	
	// Paint the Town Red
	carnage(R.string.mm_carnage, R.drawable.mm_carnage, Villain.maxCarnage, Sets.PaintRed),
	mysterio(R.string.mm_mysterio, R.drawable.mm_mysterio, Villain.sinisterSix, Sets.PaintRed),
	
	// Villains
	drStrange(R.string.mm_drStrange, R.drawable.mm_drStrange, Villain.defenders, Sets.Villains),
	nickFury(R.string.mm_nick_fury, R.drawable.mm_nick_fury, Villain.avengers, Sets.Villains),
	odin(R.string.mm_odin, R.drawable.mm_odin, Villain.asgardianWarriors, Sets.Villains),
	professorX(R.string.mm_mr_professor_x, R.drawable.mm_professor_x, Villain.xmenFirstClass, Sets.Villains),
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

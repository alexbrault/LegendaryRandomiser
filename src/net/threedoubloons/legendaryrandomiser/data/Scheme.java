package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.GameDetails;
import net.threedoubloons.legendaryrandomiser.R;

public class Scheme extends CardBase implements Serializable {
	private static final long serialVersionUID = 10206070375160723L;
	private final int longName;

	public interface SchemeAction {
		public void apply(GameDetails details);
	}
	private static HashMap<String, SchemeAction> schemeActions = new HashMap<String, SchemeAction>();
	static {
		schemeActions.put("robbery", new SchemeAction(){
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				details.setVillainDeckContentsForCardType(CardType.bystander, 12);
			}});
		schemeActions.put("breakout",  new SchemeAction() {
			public void apply(GameDetails details) {
				details.setNumHenchmen(details.getNumHenchmen() + 1);
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
			}});
		schemeActions.put("invasion",  new SchemeAction() {
			public void apply(GameDetails details) {
				details.setNumHeroes(6);
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				details.setVillainDeckContentsForCardType(CardType.hero, 12);
				if (!details.addPreferredVillain(Villain.skrulls)) {
					details.addError("Scheme requires the Skrulls villain group");
				}
			}});
		schemeActions.put("portals",  new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 7);
			}});
		schemeActions.put("civilwar",  new SchemeAction() {
			public void apply(GameDetails details) {
				switch(details.getNumPlayers()) {
				case 2:
					details.setNumHeroes(4);
					// FALLTHROUGH
				case 3:
					details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
					break;
				case 4:
				case 5:
					details.setVillainDeckContentsForCardType(CardType.schemeTwist, 5);
					break;
				}
			}});
		schemeActions.put("cosmiccube",  new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
			}});
		schemeActions.put("killbots",  new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 5);
				details.setVillainDeckContentsForCardType(CardType.bystander, 18);
				details.addNote("Place 3 scheme twists next to the scheme");
			}});
		schemeActions.put("virus",  new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				if (details.getNumPlayers() < 5) {
					details.addNote(String.format("Use only %d Wounds cards", details.getNumPlayers() * 6));
				}
			}});
	}
	
	public Scheme(int longName, int shortName, String action) {
		super(shortName, 0);
		this.longName = longName;
		this.action = action;
	}

	private final String action;
	
	public int getLongName() {
		return longName;
	}

	public void applyScheme(GameDetails details) {
		schemeActions.get(action).apply(details);
	}
	
	public final static Scheme breakout = new Scheme(R.string.breakout, R.string.breakout_short, "breakout");
	public final static Scheme robbery = new Scheme(R.string.robbery, R.string.robbery_short, "robbery");
	public final static Scheme invasion = new Scheme(R.string.invasion, R.string.invasion_short, "invasion");
	public final static Scheme portals = new Scheme(R.string.portals, R.string.portals_short, "portals");
	public final static Scheme civilWar = new Scheme(R.string.civilwar, R.string.civilwar_short, "civilwar");
	public final static Scheme cosmicCube = new Scheme(R.string.cosmiccube, R.string.cosmiccube_short, "cosmiccube");
	public final static Scheme killbots = new Scheme(R.string.killbots, R.string.killbots_short, "killbots");
	public final static Scheme virus = new Scheme(R.string.virus, R.string.virus_short, "virus");

	private static List<Scheme> all;
	private static List<Scheme> allSP;
	public static void initialiseAllList(long sets) {
		List<Scheme> all = new ArrayList<Scheme>();
		List<Scheme> allSP = new ArrayList<Scheme>();
		if ((sets & Sets.CoreSet) == Sets.CoreSet) {
			all.addAll(Arrays.asList(coreSet));
			allSP.addAll(Arrays.asList(coreSetSP));
		}
		
		Scheme.all = Collections.unmodifiableList(all);
		Scheme.allSP = Collections.unmodifiableList(allSP);
	}
	
	public final static List<Scheme> getAll(int numPlayers) {
		if (numPlayers == 1) {
			return allSP;
		} else {
			return all;
		}
	}
	
	public final static Scheme[] coreSet = {breakout, robbery, invasion, portals, civilWar, cosmicCube, killbots, virus};
	public final static Scheme[] coreSetSP = {robbery, invasion, portals, cosmicCube, killbots, virus};
}

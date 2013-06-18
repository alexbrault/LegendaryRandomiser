package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.GameDetails;
import net.threedoubloons.legendaryrandomiser.R;

public enum Scheme implements ICardBase {
	none(0, 0),
	breakout(R.string.breakout, R.string.breakout_short, Sets.CoreSet, false),
	robbery(R.string.robbery, R.string.robbery_short),
	invasion(R.string.invasion, R.string.invasion_short),
	portals(R.string.portals, R.string.portals_short),
	civilWar(R.string.civilwar, R.string.civilwar_short, Sets.CoreSet, false),
	cosmicCube(R.string.cosmiccube, R.string.cosmiccube_short),
	killbots(R.string.killbots, R.string.killbots_short),
	virus(R.string.virus, R.string.virus_short),
	
	// Dark City
	demonCitizens(0, 0, Sets.DarkCity),
	plutonium(0, 0, Sets.DarkCity),
	xCutioner(0, 0, Sets.DarkCity),
	earthquake(0, 0, Sets.DarkCity),
	saveHumans(0, 0, Sets.DarkCity),
	babyHope(0, 0, Sets.DarkCity),
	blowupHelicarrier(0, 0, Sets.DarkCity),
	crimeWave(0, 0, Sets.DarkCity),
	;
	private final CardBase card;
	private final int longName;
	private final boolean isSPAcceptable;

	@Override
	public CardBase getCard() {
		return card;
	}
	
	public static Scheme get(String name) {
		return Enum.valueOf(Scheme.class, name);
	}
		
	private Scheme(int longName, int shortName) {
		this(longName, shortName,  Sets.CoreSet, true);
	}
	
	private Scheme(int longName, int shortName, Sets expansionSymbol) {
		this(longName, shortName,  expansionSymbol, true);
	}
	
	private Scheme(int longName, int shortName, Sets expansionSymbol, boolean isSPAcceptable) {
		card = new CardBase(shortName, 0, expansionSymbol);
		this.longName = longName;
		this.isSPAcceptable = isSPAcceptable;
	}
	
	public int getLongName() {
		return longName;
	}
	
	public boolean isSPAcceptable() {
		return isSPAcceptable;
	}

	public void applyScheme(GameDetails details) {
		SchemeAction action = schemeActions.get(name());
		if (action != null) {
			action.apply(details);
		}
	}

	private static List<Scheme> all;
	public static void initialiseAllList(EnumSet<Sets> activeSets) {
		List<Scheme> all = new ArrayList<Scheme>();

		for (Scheme s : values()) {
			if (activeSets.contains(s.card.getExpansion())) {
				all.add(s);
			}
		}
		
		Scheme.all = Collections.unmodifiableList(all);
	}
	
	public final static List<Scheme> getAll() {
		return all;
	}

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
		schemeActions.put("civilWar",  new SchemeAction() {
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
		schemeActions.put("cosmicCube",  new SchemeAction() {
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
		schemeActions.put("demonCitizens", new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				details.setVillainDeckContentsForCardType(CardType.bystander, 0);
				details.setVillainDeckContentsForCardType(CardType.makeSpecificCardType(Hero.jeanGrey), 14);
			}});
		schemeActions.put("xCutioner", new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				details.setVillainDeckContentsForCardType(CardType.bystander, 0);
				details.setVillainDeckContentsForCardType(CardType.makeSpecificCardType(Hero.angel), 14); // TODO reserve random hero;
			}});
		schemeActions.put("plutonium", new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				details.setNumVillains(details.getNumVillains() + 1);
			}});
		schemeActions.put("earthquake", new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
			}});
		schemeActions.put("saveHumans", new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				details.addNote(String.format("Add %d bystanders to the hero deck", details.getNumPlayers() == 1 ? 12 : 24));
			}});
		schemeActions.put("babyHope", new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				details.addNote("Place baby Hope on this scheme");
			}});
		schemeActions.put("blowupHelicarrier", new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				details.setNumHeroes(6);
			}});
		schemeActions.put("crimeWave", new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				if (!details.addPreferredVillain(Villain.maggiaGoons)) {
					details.addError("Scheme requires the Maggia Goons henchman group");
				}
			}});
	}
}

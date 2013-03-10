package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;
import java.util.HashMap;

import net.threedoubloons.legendaryrandomiser.GameDetails;

public class Scheme implements Serializable {
	private static final long serialVersionUID = 10206070375160723L;

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
				//details.setNumHeroes(4);
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				details.setVillainDeckContentsForCardType(CardType.hero, 12);
				details.addPreferredVillain(Villain.skrulls);
			}});
		schemeActions.put("portals",  new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 7);
			}});
		schemeActions.put("civilwar",  new SchemeAction() {
			public void apply(GameDetails details) {
				switch(details.getNumPlayers()) {
				case 2:
					//details.setNumHeroes(4);
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
				//details.addExtra();
			}});
		schemeActions.put("virus",  new SchemeAction() {
			public void apply(GameDetails details) {
				details.setVillainDeckContentsForCardType(CardType.schemeTwist, 8);
				if (details.getNumPlayers() < 5) {
					//details.addExtra();
				}
			}});
	}
	
	public Scheme(String name, String action) {
		super();
		this.name = name;
		this.action = action;
	}

	private final String name;
	private final String action;

	public String getName() {
		return name;
	}
	
	public void applyScheme(GameDetails details) {
		schemeActions.get(action).apply(details);
	}
	
	public final static Scheme breakout = new Scheme("a Negative Zone Prison Breakout", "breakout");
	public final static Scheme robbery = new Scheme("a Midtown Bank Robbery", "robbery");
	public final static Scheme invasion = new Scheme("a Secret Invasion of the Skrull Shapeshifters", "invasion");
	public final static Scheme portals = new Scheme("to open Portals to the Dark Dimension", "portals");
	public final static Scheme civilWar = new Scheme("a Super Hero Civil War", "civilwar");
	public final static Scheme cosmicCube = new Scheme("to Unleash the Power of the Cosmic Cube", "cosmiccube");
	public final static Scheme killbots = new Scheme("to Replace Earth's Leaders with Killbots", "killbots");
	public final static Scheme virus = new Scheme("to release the Legacy Virus", "virus");
	
	public final static Scheme[] all = {breakout, robbery, invasion, portals, civilWar, cosmicCube, killbots, virus};
	public final static Scheme[] allSinglePlayer = {robbery, invasion, portals, cosmicCube, killbots, virus};
}

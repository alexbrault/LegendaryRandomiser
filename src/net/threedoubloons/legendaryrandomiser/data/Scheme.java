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
				details.setVillainDeckContentsForCardType("Twist", 8);
				details.setVillainDeckContentsForCardType("Bystander", 12);
			}});
		schemeActions.put("breakout",  new SchemeAction() {
			public void apply(GameDetails details) {
				details.setNumHenchmen(details.getNumHenchmen() + 1);
				details.setVillainDeckContentsForCardType("Twist", 8);
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
	
	public final Scheme breakout = new Scheme("Negative Zone Prison Breakout", "breakout");
	public final Scheme robbery = new Scheme("Midtown Bank Robbery", "robbery");
	
	public final Scheme[] all = {breakout, robbery};
}

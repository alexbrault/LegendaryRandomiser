package net.threedoubloons.legendaryrandomiser.data;

import net.threedoubloons.legendaryrandomiser.GameDetails;

public class Scheme {	
	public interface SchemeAction {
		public void apply(GameDetails details);
	}
	
	public Scheme(String name, SchemeAction action) {
		super();
		this.name = name;
		this.action = action;
	}

	private final String name;
	private SchemeAction action;

	public String getName() {
		return name;
	}
	
	public void applyScheme(GameDetails details) {
		action.apply(details);
	}
	
	public final Scheme breakout = new Scheme("Negative Zone Prison Breakout", new SchemeAction() {
		public void apply(GameDetails details) {
			details.setNumHenchmen(details.getNumHenchmen() + 1);
		}});
	
	public final Scheme[] all = {breakout};
}

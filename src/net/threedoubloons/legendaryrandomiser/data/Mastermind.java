package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;

import net.threedoubloons.legendaryrandomiser.R;

public class Mastermind implements Serializable {
	private static final long serialVersionUID = -8346554204948014704L;
	private final String name;
	private final int pictureId;
	private final Villain alwaysLeads;
	public String getName() {
		return name;
	}
	public int getPictureId() {
		return pictureId;
	}
	public Villain getAlwaysLeads() {
		return alwaysLeads;
	}
	public Mastermind(String name, int pictureId, Villain alwaysLeads) {
		super();
		this.name = name;
		this.pictureId = pictureId;
		this.alwaysLeads = alwaysLeads;
	}

	public final static Mastermind[] all = {
		new Mastermind("Red Skull", R.drawable.red_skull, Villain.hydra), 
		new Mastermind("Loki", R.drawable.loki, Villain.enemiesOfAsgard), 
		new Mastermind("Dr. Doom", R.drawable.drdoom, Henchman.doombots), 
		new Mastermind("Magneto", R.drawable.magneto, Villain.brotherhood)
	};
	public final static Mastermind nullMastermind = new Mastermind("NULL", 0, null);
}
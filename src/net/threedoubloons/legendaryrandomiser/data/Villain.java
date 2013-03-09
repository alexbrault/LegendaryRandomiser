package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;

import net.threedoubloons.legendaryrandomiser.R;

public class Villain implements Serializable {
	private static final long serialVersionUID = 4309878817795983831L;
	private String name;
	private int pictureId;
	public String getName() {
		return name;
	}
	public int getPictureId() {
		return pictureId;
	}
	public Villain(String name, int pictureId) {
		super();
		this.name = name;
		this.pictureId = pictureId;
	}
	
	public final static Villain skrulls = new Villain("Skrulls", R.drawable.skrulls);
	public final static Villain spiderfoes = new Villain("Spider-Foes", R.drawable.spiderfoes);
	public final static Villain enemiesOfAsgard = new Villain("Enemies of Asgard", R.drawable.enemies_of_asgard);
	public final static Villain brotherhood = new Villain("Brotherhood", R.drawable.brotherhood);
	public final static Villain hydra = new Villain("Hydra", R.drawable.hydra);
	public final static Villain mastersOfEvil = new Villain("Masters of Evil", R.drawable.masters);
	public final static Villain radiation = new Villain("Radiation", R.drawable.radiation);
	
	public final static Villain[] all = {skrulls, spiderfoes, enemiesOfAsgard, brotherhood, hydra, mastersOfEvil, radiation};
}

package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;

import net.threedoubloons.legendaryrandomiser.R;

public class Villain implements Serializable {
	private static final long serialVersionUID = 4309878817795983831L;
	private int name;
	private int pictureId;
	public int getName() {
		return name;
	}
	public int getPictureId() {
		return pictureId;
	}
	public Villain(int name, int pictureId) {
		super();
		this.name = name;
		this.pictureId = pictureId;
	}
	
	public final static Villain skrulls = new Villain(R.string.skrulls, R.drawable.skrulls);
	public final static Villain spiderfoes = new Villain(R.string.spiderfoes, R.drawable.spiderfoes);
	public final static Villain enemiesOfAsgard = new Villain(R.string.frost_giants, R.drawable.enemies_of_asgard);
	public final static Villain brotherhood = new Villain(R.string.brotherhood, R.drawable.brotherhood);
	public final static Villain hydra = new Villain(R.string.hydra, R.drawable.hydra);
	public final static Villain mastersOfEvil = new Villain(R.string.masters_of_evil, R.drawable.masters);
	public final static Villain radiation = new Villain(R.string.radiation, R.drawable.radiation);
	
	public final static Villain[] all = {skrulls, spiderfoes, enemiesOfAsgard, brotherhood, hydra, mastersOfEvil, radiation};
}

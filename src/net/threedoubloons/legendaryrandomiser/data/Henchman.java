package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;

import net.threedoubloons.legendaryrandomiser.R;
import net.threedoubloons.legendaryrandomiser.data.Villain;

public class Henchman extends Villain implements Serializable {
	private static final long serialVersionUID = -3646715723455606919L;
	public Henchman(String name, int pictureId) {
		super(name, pictureId);
	}

	public final static Henchman handNinja = new Henchman("Hand Ninjas", R.drawable.hand_ninja);
	public final static Henchman doombots = new Henchman("Doombot Legion", R.drawable.doombots);
	public final static Henchman sentinel = new Henchman("Sentinels", R.drawable.sentinel);
	public final static Henchman mutates = new Henchman("Savage Land Mutates", R.drawable.mutates);
	
	public final static Henchman[] all = {handNinja, doombots, sentinel, mutates};
}


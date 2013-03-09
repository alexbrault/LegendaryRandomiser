package net.threedoubloons.legendaryrandomiser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GameDetails implements Serializable {
	private static final long serialVersionUID = -6690498520538188426L;

	public static class Mastermind implements Serializable {
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
			new GameDetails.Mastermind("Red Skull", R.drawable.red_skull, GameDetails.Villain.hydra), 
			new GameDetails.Mastermind("Loki", R.drawable.loki, GameDetails.Villain.enemiesOfAsgard), 
			new GameDetails.Mastermind("Dr. Doom", R.drawable.drdoom, GameDetails.Henchman.doombots), 
			new GameDetails.Mastermind("Magneto", R.drawable.magneto, GameDetails.Villain.brotherhood)
		};
		public final static Mastermind nullMastermind = new Mastermind("NULL", 0, null);
	}
	
	public static class Villain implements Serializable {
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
		
		public final static Villain skrulls = new Villain("Skrulls", R.drawable.missing_img);
		public final static Villain spiderfoes = new Villain("Spider-Foes", R.drawable.missing_img);
		public final static Villain enemiesOfAsgard = new Villain("Enemies of Asgard", R.drawable.missing_img);
		public final static Villain brotherhood = new Villain("Brotherhood", R.drawable.missing_img);
		public final static Villain hydra = new Villain("Hydra", R.drawable.missing_img);
		public final static Villain mastersOfEvil = new Villain("Masters of Evil", R.drawable.missing_img);
		public final static Villain radiation = new Villain("Radiation", R.drawable.missing_img);
		
		public final static Villain[] all = {skrulls, spiderfoes, enemiesOfAsgard, brotherhood, hydra, mastersOfEvil, radiation};
	}
	public static class Henchman extends Villain implements Serializable {
		private static final long serialVersionUID = -3646715723455606919L;
		public Henchman(String name, int pictureId) {
			super(name, pictureId);
		}

		public final static Henchman handNinja = new Henchman("Hand Ninjas", R.drawable.missing_img);
		public final static Henchman doombots = new Henchman("Doombot Legion", R.drawable.missing_img);
		public final static Henchman sentinel = new Henchman("Sentinels", R.drawable.missing_img);
		public final static Henchman mutates = new Henchman("Savage Land Mutates", R.drawable.missing_img);
		
		public final static Henchman[] all = {handNinja, doombots, sentinel, mutates};
	}
	
	private static Random r = new Random();

	private int numPlayers = 2;
	private int numVillains = 2;
	private static int[] numVillainsPerPlayer = {0, 1, 2, 3, 3, 4};
	private int numHenchmen = 1;
	private static int[] numHenchmenPerPlayer = {0, 1, 1, 1, 2, 2};
	private Mastermind mastermind;
	private ArrayList<Villain> villains = new ArrayList<Villain>();
	private ArrayList<Henchman> henchmen = new ArrayList<Henchman>();
	
	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	public final Mastermind getMastermind() {
		if (mastermind != null)
			return mastermind;
		return Mastermind.nullMastermind;
	}
	
	public final ArrayList<Villain> getVillains() {
		return villains;
	}
	
	public final ArrayList<Henchman> getHenchmen() {
		return henchmen;
	}
	
	public void randomiseAll() {
		if (mastermind == null) {
			addRandomMastermind();
		}
		applyPlayerCountToVillains();
		addAlwaysLeads();
		addVillains();
		addHenchmen();
	}
	
	public void addRandomMastermind() {
		int mPosition = r.nextInt(Mastermind.all.length);
		mastermind = Mastermind.all[mPosition];
	}
	
	private void applyPlayerCountToVillains() {
		numVillains = numVillainsPerPlayer[numPlayers];
		numHenchmen = numHenchmenPerPlayer[numPlayers];
	}
	
	public boolean addAlwaysLeads() {
		if (numPlayers == 1) {
			return true;
		}
		
		Villain preferred = getMastermind().getAlwaysLeads();
		if (preferred == null) {
			return false;
		}
		
		if (preferred instanceof Henchman) {
			return addPreferredHenchman(preferred);
		} else {
			return addPreferredVillain(preferred);
		}
	}
	
	private boolean addPreferredVillain(Villain preferred) {
		if (villains.contains(preferred)) {
			return true;
		}
		
		if (villains.size() >= numVillains) {
			return false;
		}
		
		villains.add(preferred);
		return true;
	}
	
	private boolean addPreferredHenchman(Villain preferred) {
		if (henchmen.contains(preferred)) {
			return true;
		}
		
		if (henchmen.size() >= numHenchmen) {
			return false;
		}
		
		henchmen.add((Henchman)preferred);
		return true;
	}

	private void addVillains() {
		while (villains.size() < numVillains) {
			addRandomVillain();
		}
	}
	
	private void addRandomVillain() {
		int vPosition;
		Villain v;
		do {
			vPosition = r.nextInt(Villain.all.length);
			v = Villain.all[vPosition];
		} while (villains.contains(v));
		villains.add(v);
	}

	private void addHenchmen() {
		while (henchmen.size() < numHenchmen) {
			addRandomHenchman();
		}
	}
	
	private void addRandomHenchman() {
		int vPosition;
		Henchman v;
		do {
			vPosition = r.nextInt(Henchman.all.length);
			v = Henchman.all[vPosition];
		} while (henchmen.contains(v));
		henchmen.add(v);
	}
}

package net.threedoubloons.legendaryrandomiser;

import java.io.Serializable;
import java.util.Random;

public class GameDetails implements Serializable {
	private static final long serialVersionUID = -6690498520538188426L;

	public static class Mastermind implements Serializable {
		private static final long serialVersionUID = -8346554204948014704L;
		private String name;
		private int pictureId;
		public String getName() {
			return name;
		}
		public int getPictureId() {
			return pictureId;
		}
		public Mastermind(String name, int pictureId) {
			super();
			this.name = name;
			this.pictureId = pictureId;
		}
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
	}
	public static class Henchman extends Villain implements Serializable {
		private static final long serialVersionUID = -3646715723455606919L;
		public Henchman(String name, int pictureId) {
			super(name, pictureId);
		}

		public final static Henchman handNinja = new Henchman("Hand Ninjas", R.drawable.missing_img);
		public final static Henchman doombots = new Henchman("Doombot Legion", R.drawable.missing_img);
		public final static Henchman skrulls = new Henchman("Skrulls", R.drawable.missing_img);
		public final static Henchman sentinel = new Henchman("Sentinels", R.drawable.missing_img);
		public final static Henchman mutates = new Henchman("Savage Land Mutates", R.drawable.missing_img);
	}
	
	private Random r = new Random();

	private int numPlayers = 2;
	private Mastermind mastermind;
	
	public final static Mastermind[] masterminds = {
		new Mastermind("Red Skull", R.drawable.red_skull), 
		new Mastermind("Loki", R.drawable.loki), 
		new Mastermind("Dr. Doom", R.drawable.drdoom), 
		new Mastermind("Magneto", R.drawable.magneto)
	};
	private final static Mastermind nullMastermind = new Mastermind("NULL", 0);

	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	public final Mastermind getMastermind() {
		if (mastermind != null)
			return mastermind;
		return nullMastermind;
	}
	
	public void randomiseAll() {
		if (mastermind == null) {
			randomiseMastermind();
		}
	}
	
	public void randomiseMastermind() {
		int mPosition = r.nextInt(masterminds.length);
		mastermind = masterminds[mPosition];
	}
}

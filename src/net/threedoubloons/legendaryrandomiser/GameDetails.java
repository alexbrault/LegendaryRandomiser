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
	
	public GameDetails() {
		r = new Random();
	}

	private int numPlayers = 2;
	private Mastermind mastermind;
	private Random r;
	
	public final static Mastermind[] masterminds = {new Mastermind("Red Skull", R.drawable.ic_launcher), new Mastermind("Loki", R.drawable.ic_launcher)};
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

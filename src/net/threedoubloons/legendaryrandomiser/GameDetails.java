package net.threedoubloons.legendaryrandomiser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import net.threedoubloons.legendaryrandomiser.data.Henchman;
import net.threedoubloons.legendaryrandomiser.data.Mastermind;
import net.threedoubloons.legendaryrandomiser.data.Villain;

public class GameDetails implements Serializable {
	private static final long serialVersionUID = -6690498520538188426L;
	private static Random r = new Random();

	private int numPlayers = 2;
	private int numVillains = 2;
	private final static int[] numVillainsPerPlayer = {0, 1, 2, 3, 3, 4};
	private int numHenchmen = 1;
	private final static int[] numHenchmenPerPlayer = {0, 1, 1, 1, 2, 2};
	private Mastermind mastermind;
	private ArrayList<Villain> villains = new ArrayList<Villain>();
	private ArrayList<Henchman> henchmen = new ArrayList<Henchman>();
	
	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	public int getNumVillains() {
		return numVillains;
	}

	public void setNumVillains(int numVillains) {
		this.numVillains = numVillains;
	}

	public int getNumHenchmen() {
		return numHenchmen;
	}

	public void setNumHenchmen(int numHenchmen) {
		this.numHenchmen = numHenchmen;
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
	
	public boolean addPreferredVillain(Villain preferred) {
		if (villains.contains(preferred)) {
			return true;
		}
		
		if (villains.size() >= numVillains) {
			return false;
		}
		
		villains.add(preferred);
		return true;
	}
	
	public boolean addPreferredHenchman(Villain preferred) {
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

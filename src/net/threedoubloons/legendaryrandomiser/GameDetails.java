package net.threedoubloons.legendaryrandomiser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.threedoubloons.legendaryrandomiser.data.CardType;
import net.threedoubloons.legendaryrandomiser.data.Henchman;
import net.threedoubloons.legendaryrandomiser.data.Hero;
import net.threedoubloons.legendaryrandomiser.data.Mastermind;
import net.threedoubloons.legendaryrandomiser.data.Scheme;
import net.threedoubloons.legendaryrandomiser.data.Sets;
import net.threedoubloons.legendaryrandomiser.data.Villain;

public class GameDetails implements Serializable {
	private long activeSets = Sets.CoreSet;
	private static final long serialVersionUID = -6690498520538188426L;
	private static Random r = new Random();

	private int numPlayers = 2;
	private int numVillains = 2;
	private int numHenchmen = 1;
	private int numHeroes = 1;
	private Mastermind mastermind;
	private ArrayList<Villain> villains = new ArrayList<Villain>();
	private ArrayList<Henchman> henchmen = new ArrayList<Henchman>();
	private Scheme scheme;
	private HashMap<CardType, Integer> villainDeckContents = new HashMap<CardType, Integer>();
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	private ArrayList<String> notes = new ArrayList<String>();
	private ArrayList<String> errors = new ArrayList<String>();
	
	private final static int[] numVillainsPerPlayer = {0, 1, 2, 3, 3, 4};
	private final static int[] numHenchmenPerPlayer = {0, 1, 1, 1, 2, 2};
	private final static int[] numHeroesPerPlayer = {0, 3, 5, 5, 5, 6};
	private final static int[] numBystanderPerPlayer = {0, 1, 2, 8, 8, 12};
	
	public GameDetails() {
		initialiseLists();
	}
	
	public void setActiveSets(long newSets) {
		activeSets = newSets;
		initialiseLists();
	}
	
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
	
	public int getNumHeroes() {
		return numHeroes;
	}

	public void setNumHeroes(int numHeroes) {
		this.numHeroes = numHeroes;
	}
	
	public void setMastermind(Mastermind m) {
		mastermind = m;
	}

	public final Mastermind getMastermind() {
		return mastermind;
	}
	
	public void setMastermind(int m) {
		if (m == -1) {
			mastermind = null;
		} else {
			mastermind = Mastermind.getAll().get(m);
		}
	}
	
	public int getMastermindPosition() {
		return Mastermind.getAll().indexOf(mastermind);
	}
	
	public final Iterable<Villain> getVillains() {
		return villains;
	}
	
	public final Iterable<Henchman> getHenchmen() {
		return henchmen;
	}
	
	public final Iterable<Hero> getHeroes() {
		return heroes;
	}
	
	public final Iterable<String> getNotes() {
		return notes;
	}
	
	public final Iterable<String> getErrors() {
		return errors;
	}
	
	public int getNumNotes() {
		return notes.size() + errors.size();
	}
	
	public void removeVillainAtPosition(int pos) {
		villains.remove(pos);
	}
	
	public final Iterable<Map.Entry<CardType, Integer>> getVillainsDeckContents() {
		return villainDeckContents.entrySet();
	}
	
	public void setVillainDeckContentsForCardType(CardType type, int number) {
		villainDeckContents.put(type, number);
	}
	
	public void randomiseAll() {
		notes.clear();
		errors.clear();
		addRandomMastermind();
		applyPlayerCount();
		addRandomScheme();
		applyScheme();
		addAlwaysLeads();
		addVillains();
		addHenchmen();
		addHeroes();
		checkInconsistencies();
	}

	private void checkInconsistencies() {
		if (numPlayers == 1 && !getScheme().isSPAcceptable()) {
			addError("Scheme is not designed for singleplayer games");
		}
	}

	private void initialiseLists() {
		Hero.initialiseAllList(activeSets);
		Mastermind.initialiseAllList(activeSets);
		Scheme.initialiseAllList(activeSets);
		Villain.initialiseAllList(activeSets);
		Henchman.initialiseAllList(activeSets);
	}

	public int addRandomMastermind() {
		if (mastermind != null) return getMastermindPosition();
		int mPosition = r.nextInt(Mastermind.getAll().size());
		mastermind = Mastermind.getAll().get(mPosition);
		return mPosition;
	}
	
	private void applyPlayerCount() {
		numVillains = numVillainsPerPlayer[numPlayers];
		numHenchmen = numHenchmenPerPlayer[numPlayers];
		setNumHeroes(numHeroesPerPlayer[numPlayers]);
		setVillainDeckContentsForCardType(CardType.bystander, numBystanderPerPlayer[numPlayers]);
		setVillainDeckContentsForCardType(CardType.masterStrike, numPlayers > 1 ? 5 : 1);
		if (numPlayers == 1) {
			addNote("Use only 3 henchman cards");
		}
	}
	
	public int addRandomScheme() {
		if (scheme != null) return getSchemePosition();
		int mPosition;
		Scheme scheme;
		boolean isAcceptable;
		do {
			mPosition = r.nextInt(Scheme.getAll().size());
			scheme = Scheme.getAll().get(mPosition);
			isAcceptable = numPlayers == 1 ? scheme.isSPAcceptable() : true; 
		} while (!isAcceptable);
		this.scheme = Scheme.getAll().get(mPosition);
		return mPosition;
	}
	
	private void applyScheme() {
		getScheme().applyScheme(this);
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
	
	public int addRandomVillain() {
		int mPosition;
		Villain v;
		do {
			mPosition = r.nextInt(Villain.getAll().size());
			v = Villain.getAll().get(mPosition);
		} while (villains.contains(v));
		villains.add(v);
		return mPosition;
	}
	
	public void addVillain(int vp) {
		Villain v = Villain.getAll().get(vp);
		villains.add(v);
	}

	private void addHenchmen() {
		while (henchmen.size() < numHenchmen) {
			addRandomHenchman();
		}
	}
	
	private int addRandomHenchman() {
		int vPosition;
		Henchman v;
		do {
			vPosition = r.nextInt(Henchman.getAllHenchmen().size());
			v = Henchman.getAllHenchmen().get(vPosition);
		} while (henchmen.contains(v));
		henchmen.add(v);
		return vPosition;
	}

	private void addHeroes() {
		while (heroes.size() < numHeroes) {
			addRandomHero();
		}
	}
	
	private int addRandomHero() {
		int vPosition;
		Hero h;
		do {
			vPosition = r.nextInt(Hero.getAll().size());
			h = Hero.getAll().get(vPosition);
		} while (heroes.contains(h));
		heroes.add(h);
		return vPosition;
	}

	public final Scheme getScheme() {
		return scheme;
	}
	
	public int getSchemePosition() {
		return Scheme.getAll().indexOf(scheme);
	}

	public void setScheme(Scheme s) {
		scheme = s;
	}
	
	public void setScheme(int position) {
		if (position == -1) {
			scheme = null;
		} else {
			scheme = Scheme.getAll().get(position);
		}
	}
	
	public void addNote(String note) {
		notes.add(note);
	}
	
	public void addError(String note) {
		errors.add(note);
	}
}

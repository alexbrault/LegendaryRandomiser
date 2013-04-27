package net.threedoubloons.legendaryrandomiser;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.threedoubloons.legendaryrandomiser.data.CardType;
import net.threedoubloons.legendaryrandomiser.data.Hero;
import net.threedoubloons.legendaryrandomiser.data.Mastermind;
import net.threedoubloons.legendaryrandomiser.data.Scheme;
import net.threedoubloons.legendaryrandomiser.data.Sets;
import net.threedoubloons.legendaryrandomiser.data.Villain;
import android.os.Parcel;
import android.os.Parcelable;

public class GameDetails implements Parcelable {
	private static Random r = new Random();
	private final static int[] numVillainsPerPlayer = {0, 1, 2, 3, 3, 4};
	private final static int[] numHenchmenPerPlayer = {0, 1, 1, 1, 2, 2};
	private final static int[] numHeroesPerPlayer = {0, 3, 5, 5, 5, 6};
	private final static int[] numBystanderPerPlayer = {0, 1, 2, 8, 8, 12};

	private EnumSet<Sets> activeSets = Sets.make(Sets.CoreSet);
	private int numPlayers = 2;
	private int numVillains = 2;
	private int numHenchmen = 1;
	private int numHeroes = 1;
	private Mastermind mastermind = Mastermind.none;
	private Scheme scheme = Scheme.none;
	private ArrayList<Villain> villains = new ArrayList<Villain>();
	private ArrayList<Villain> henchmen = new ArrayList<Villain>();
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	private transient HashMap<CardType, Integer> villainDeckContents = new HashMap<CardType, Integer>();
	private transient ArrayList<String> notes = new ArrayList<String>();
	private transient ArrayList<String> errors = new ArrayList<String>();
	
	public GameDetails() {
		initialiseLists();
	}

	public GameDetails(GameDetails other) {
		super();
		this.activeSets = other.activeSets;
		this.numPlayers = other.numPlayers;
		this.numVillains = other.numVillains;
		this.numHenchmen = other.numHenchmen;
		this.numHeroes = other.numHeroes;
		this.mastermind = other.mastermind;
		this.scheme = other.scheme;
		this.villains = new ArrayList<Villain>(other.villains);
		this.henchmen = new ArrayList<Villain>(other.henchmen);
		this.heroes = new ArrayList<Hero>(other.heroes);
	}
	
	public GameDetails(Parcel in) {
		activeSets = Sets.make(in.readLong());
		numPlayers = in.readInt();
		
		mastermind = Mastermind.get(in.readString());
		scheme = Scheme.get(in.readString());
		
		applyPlayerCount();
		applyScheme();
		
		int arrSize = in.readInt();
		villains = new ArrayList<Villain>(arrSize);
		for (int i = 0; i < arrSize; ++i) {
			villains.add(Villain.get(in.readString()));
		}
		
		arrSize = in.readInt();
		henchmen = new ArrayList<Villain>(arrSize);
		for (int i = 0; i < arrSize; ++i) {
			henchmen.add(Villain.get(in.readString()));
		}

		arrSize = in.readInt();
		heroes = new ArrayList<Hero>(arrSize);
		for (int i = 0; i < arrSize; ++i) {
			heroes.add(Hero.get(in.readString()));
		}
	}

	public void setActiveSets(long newSets) {
		activeSets = Sets.make(newSets);
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

	public final Mastermind getMastermind() {
		return mastermind;
	}
	
	public final Iterable<Villain> getVillains() {
		return villains;
	}
	
	public final Iterable<Villain> getHenchmen() {
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
	}

	private void initialiseLists() {
		Hero.initialiseAllList(activeSets);
		Mastermind.initialiseAllList(activeSets);
		Scheme.initialiseAllList(activeSets);
		Villain.initialiseAllList(activeSets);
	}

	public void addRandomMastermind() {
		if (mastermind != Mastermind.none) return;
		int mPosition = r.nextInt(Mastermind.getAll().size());
		mastermind = Mastermind.getAll().get(mPosition);
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
	
	public void addRandomScheme() {
		if (scheme != Scheme.none) return;
		int mPosition;
		Scheme scheme;
		boolean isAcceptable;
		do {
			mPosition = r.nextInt(Scheme.getAll().size());
			scheme = Scheme.getAll().get(mPosition);
			isAcceptable = numPlayers == 1 ? scheme.isSPAcceptable() : true; 
		} while (!isAcceptable);
		this.scheme = scheme;
	}
	
	private void applyScheme() {
		scheme.applyScheme(this);
	}
	
	public boolean addAlwaysLeads() {
		if (numPlayers == 1) {
			return true;
		}
		
		Villain preferred = getMastermind().getAlwaysLeads();
		if (preferred == null) {
			return false;
		}
		
		if (preferred.isHenchman()) {
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
		
		henchmen.add(preferred);
		return true;
	}

	private void addVillains() {
		while (villains.size() < numVillains) {
			addRandomVillain();
		}
	}
	
	private void addRandomVillain() {
		int mPosition;
		Villain v;
		do {
			mPosition = r.nextInt(Villain.getAll().size());
			v = Villain.getAll().get(mPosition);
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
		Villain v;
		do {
			vPosition = r.nextInt(Villain.getAllHenchmen().size());
			v = Villain.getAllHenchmen().get(vPosition);
		} while (henchmen.contains(v));
		henchmen.add(v);
	}

	private void addHeroes() {
		while (heroes.size() < numHeroes) {
			addRandomHero();
		}
	}
	
	private void addRandomHero() {
		int vPosition;
		Hero h;
		do {
			vPosition = r.nextInt(Hero.getAll().size());
			h = Hero.getAll().get(vPosition);
		} while (heroes.contains(h));
		heroes.add(h);
	}

	public final Scheme getScheme() {
		return scheme;
	}
	
	public void addNote(String note) {
		notes.add(note);
	}
	
	public void addError(String note) {
		errors.add(note);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(Sets.unmake(activeSets));
		dest.writeInt(numPlayers);
		
		dest.writeString(mastermind.name());
		dest.writeString(scheme.name());
		
		dest.writeInt(villains.size());
		for (Villain v : villains) {
			dest.writeString(v.name());
		}
		
		dest.writeInt(henchmen.size());
		for (Villain h : henchmen) {
			dest.writeString(h.name());
		}

		dest.writeInt(heroes.size());
		for (Hero h : heroes) {
			dest.writeString(h.name());
		}
	}
	
	 public static final Parcelable.Creator<GameDetails> CREATOR
		     = new Parcelable.Creator<GameDetails>() {
		 public GameDetails createFromParcel(Parcel in) {
		     return new GameDetails(in);
		 }
		
		 public GameDetails[] newArray(int size) {
		     return new GameDetails[size];
		 }
	 };
}

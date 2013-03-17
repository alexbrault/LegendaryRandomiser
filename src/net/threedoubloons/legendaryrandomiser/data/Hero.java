package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

public class Hero implements Serializable {
	private static final long serialVersionUID = -8899751440661774614L;

	private final String name;
	private final int pictureId;
	private final int affiliationPictureId;
	
	public Hero(String name, int pictureId, int affiliationPictureId) {
		super();
		this.name = name;
		this.pictureId = pictureId;
		this.affiliationPictureId = affiliationPictureId;
	}

	public String getName() {
		return name;
	}

	public int getPictureId() {
		return pictureId;
	}
	
	public int getAffiliationPictureId() {
		return affiliationPictureId;
	}

	public final static Hero blackWidow = new Hero("Black Widow", R.drawable.black_widow, R.drawable.affiliation_avengers);
	public final static Hero captain = new Hero("Captain America", R.drawable.captain_a, R.drawable.affiliation_avengers);
	public final static Hero cyclops = new Hero("Cyclops", R.drawable.cyclops, R.drawable.affiliation_xmen);
	public final static Hero deadpool = new Hero("Deadpool", R.drawable.deadpool, 0);
	public final static Hero emmaFrost = new Hero("Emma Frost", R.drawable.emma_frost, R.drawable.affiliation_xmen);
	public final static Hero gambit = new Hero("Gambit", R.drawable.gambit, R.drawable.affiliation_xmen);
	public final static Hero hawkeye = new Hero("Hawkeye", R.drawable.hawkeye, R.drawable.affiliation_avengers);
	public final static Hero hulk = new Hero("Hulk", R.drawable.hulk, R.drawable.affiliation_avengers);
	public final static Hero ironMan = new Hero("Iron Man", R.drawable.ironman, R.drawable.affiliation_avengers);
	public final static Hero nickFury = new Hero("Nick Fury", R.drawable.nick_fury, R.drawable.affiliation_shield);
	public final static Hero rogue = new Hero("Rogue", R.drawable.rogue, R.drawable.affiliation_xmen);
	public final static Hero spiderman = new Hero("Spiderman", R.drawable.spiderman,  R.drawable.affiliation_spiderfriends);
	public final static Hero storm = new Hero("Storm", R.drawable.storm, R.drawable.affiliation_xmen);
	public final static Hero thor = new Hero("Thor", R.drawable.thor, R.drawable.affiliation_avengers);
	public final static Hero wolverine = new Hero("Wolverine", R.drawable.wolverine, R.drawable.affiliation_xmen);
	
	public final static Hero[] coreSet = {blackWidow, captain, cyclops, deadpool, emmaFrost, gambit, hawkeye, hulk, ironMan, nickFury, rogue, spiderman, storm, thor, wolverine};

	private static List<Hero> all;
	public static void initialiseAllList(long sets) {
		List<Hero> all = new ArrayList<Hero>();
		if ((sets & Sets.CoreSet) == Sets.CoreSet) {
			all.addAll(Arrays.asList(coreSet));
		}
		
		Hero.all = Collections.unmodifiableList(all);
	}
	
	public final static List<Hero> getAll() {
		return all;
	}
}

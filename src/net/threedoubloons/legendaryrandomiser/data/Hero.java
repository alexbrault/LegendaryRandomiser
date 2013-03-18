package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

public class Hero extends CardBase implements Serializable {
	private static final long serialVersionUID = -8899751440661774614L;

	private final int affiliationPictureId;
	
	public Hero(int name, int pictureId, int affiliationPictureId) {
		super(name, pictureId);
		this.affiliationPictureId = affiliationPictureId;
	}
	
	public int getAffiliationPictureId() {
		return affiliationPictureId;
	}

	public final static Hero blackWidow = new Hero(R.string.blackWidow, R.drawable.black_widow, R.drawable.affiliation_avengers);
	public final static Hero captain = new Hero(R.string.captain, R.drawable.captain_a, R.drawable.affiliation_avengers);
	public final static Hero cyclops = new Hero(R.string.cyclops, R.drawable.cyclops, R.drawable.affiliation_xmen);
	public final static Hero deadpool = new Hero(R.string.deadpool, R.drawable.deadpool, 0);
	public final static Hero emmaFrost = new Hero(R.string.emmaFrost, R.drawable.emma_frost, R.drawable.affiliation_xmen);
	public final static Hero gambit = new Hero(R.string.gambit, R.drawable.gambit, R.drawable.affiliation_xmen);
	public final static Hero hawkeye = new Hero(R.string.hawkeye, R.drawable.hawkeye, R.drawable.affiliation_avengers);
	public final static Hero hulk = new Hero(R.string.hulk, R.drawable.hulk, R.drawable.affiliation_avengers);
	public final static Hero ironMan = new Hero(R.string.ironMan, R.drawable.ironman, R.drawable.affiliation_avengers);
	public final static Hero nickFury = new Hero(R.string.nickFury, R.drawable.nick_fury, R.drawable.affiliation_shield);
	public final static Hero rogue = new Hero(R.string.rogue, R.drawable.rogue, R.drawable.affiliation_xmen);
	public final static Hero spiderman = new Hero(R.string.spiderman, R.drawable.spiderman,  R.drawable.affiliation_spiderfriends);
	public final static Hero storm = new Hero(R.string.storm, R.drawable.storm, R.drawable.affiliation_xmen);
	public final static Hero thor = new Hero(R.string.thor, R.drawable.thor, R.drawable.affiliation_avengers);
	public final static Hero wolverine = new Hero(R.string.wolverine, R.drawable.wolverine, R.drawable.affiliation_xmen);
	
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

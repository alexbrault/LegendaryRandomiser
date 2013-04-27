package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

public enum Hero implements ICardBase {
	none(0, 0, 0),
	blackWidow(R.string.blackWidow, R.drawable.black_widow, R.drawable.affiliation_avengers),
	captain(R.string.captain, R.drawable.captain_a, R.drawable.affiliation_avengers),
	cyclops(R.string.cyclops, R.drawable.cyclops, R.drawable.affiliation_xmen),
	deadpool(R.string.deadpool, R.drawable.deadpool, 0),
	emmaFrost(R.string.emmaFrost, R.drawable.emma_frost, R.drawable.affiliation_xmen),
	gambit(R.string.gambit, R.drawable.gambit, R.drawable.affiliation_xmen),
	hawkeye(R.string.hawkeye, R.drawable.hawkeye, R.drawable.affiliation_avengers),
	hulk(R.string.hulk, R.drawable.hulk, R.drawable.affiliation_avengers),
	ironMan(R.string.ironMan, R.drawable.ironman, R.drawable.affiliation_avengers),
	nickFury(R.string.nickFury, R.drawable.nick_fury, R.drawable.affiliation_shield),
	rogue(R.string.rogue, R.drawable.rogue, R.drawable.affiliation_xmen),
	spiderman(R.string.spiderman, R.drawable.spiderman,  R.drawable.affiliation_spiderfriends),
	storm(R.string.storm, R.drawable.storm, R.drawable.affiliation_xmen),
	thor(R.string.thor, R.drawable.thor, R.drawable.affiliation_avengers),
	wolverine(R.string.wolverine, R.drawable.wolverine, R.drawable.affiliation_xmen),
	
	;
	
	private final CardBase card;
	private final int affiliationPictureId;
		
	private Hero(int name, int pictureId, int affiliationPictureId) {
		card = new CardBase(name, pictureId);
		this.affiliationPictureId = affiliationPictureId;
	}
	
	public int getAffiliationPictureId() {
		return affiliationPictureId;
	}

	@Override
	public CardBase getCard() {
		return card;
	}
	
	public static Hero get(String name) {
		return Enum.valueOf(Hero.class, name);
	}
	
	public final static Hero[] coreSet = {blackWidow, captain, cyclops, deadpool, emmaFrost, gambit, hawkeye, hulk, ironMan, nickFury, rogue, spiderman, storm, thor, wolverine};

	private static List<Hero> all;
	public static void initialiseAllList(EnumSet<Sets> activeSets) {
		List<Hero> all = new ArrayList<Hero>();
		if (activeSets.contains(Sets.CoreSet)) {
			all.addAll(Arrays.asList(coreSet));
		}
		
		Hero.all = Collections.unmodifiableList(all);
	}
	
	public final static List<Hero> getAll() {
		return all;
	}
}

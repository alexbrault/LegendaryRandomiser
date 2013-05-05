package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

final class C {
	static final int COVERT = R.drawable.ic_colour_covert;
	static final int INSTINCT = R.drawable.ic_colour_instinct; 
	static final int RANGED = R.drawable.ic_colour_ranged;
	static final int STRENGTH = R.drawable.ic_colour_strength;
	static final int TECH = R.drawable.ic_colour_tech;
}

public enum Hero implements ICardBase {
	none(0, 0, 0, null),
	blackWidow(R.string.blackWidow, R.drawable.black_widow, R.drawable.affiliation_avengers,
			new int[]{C.TECH, C.COVERT, C.COVERT, C.COVERT}),
	captain(R.string.captain, R.drawable.captain_a, R.drawable.affiliation_avengers,
			new int[]{C.INSTINCT, C.STRENGTH, C.TECH, C.COVERT}),
	cyclops(R.string.cyclops, R.drawable.cyclops, R.drawable.affiliation_xmen,
			new int[]{C.STRENGTH, C.RANGED, C.RANGED, C.RANGED}),
	deadpool(R.string.deadpool, R.drawable.deadpool, 0,
			new int[]{C.TECH, C.COVERT, C.INSTINCT, C.INSTINCT}),
	emmaFrost(R.string.emmaFrost, R.drawable.emma_frost, R.drawable.affiliation_xmen,
			new int[]{C.RANGED, C.COVERT, C.INSTINCT, C.STRENGTH}),
	gambit(R.string.gambit, R.drawable.gambit, R.drawable.affiliation_xmen,
			new int[]{C.RANGED, C.COVERT, C.INSTINCT, C.INSTINCT}),
	hawkeye(R.string.hawkeye, R.drawable.hawkeye, R.drawable.affiliation_avengers,
			new int[]{C.INSTINCT, C.TECH, C.TECH, C.TECH}),
	hulk(R.string.hulk, R.drawable.hulk, R.drawable.affiliation_avengers,
			new int[]{C.INSTINCT, C.STRENGTH, C.STRENGTH, C.STRENGTH}),
	ironMan(R.string.ironMan, R.drawable.ironman, R.drawable.affiliation_avengers,
			new int[]{C.RANGED, C.TECH, C.TECH, C.TECH}),
	nickFury(R.string.nickFury, R.drawable.nick_fury, R.drawable.affiliation_shield,
			new int[]{C.TECH, C.COVERT, C.STRENGTH, C.TECH}),
	rogue(R.string.rogue, R.drawable.rogue, R.drawable.affiliation_xmen,
			new int[]{C.STRENGTH, C.COVERT, C.COVERT, C.STRENGTH}),
	spiderman(R.string.spiderman, R.drawable.spiderman,  R.drawable.affiliation_spiderfriends,
			new int[]{C.INSTINCT, C.STRENGTH, C.TECH, C.COVERT}),
	storm(R.string.storm, R.drawable.storm, R.drawable.affiliation_xmen,
			new int[]{C.RANGED, C.RANGED, C.COVERT, C.RANGED}),
	thor(R.string.thor, R.drawable.thor, R.drawable.affiliation_avengers,
			new int[]{C.STRENGTH, C.RANGED, C.RANGED, C.RANGED}),
	wolverine(R.string.wolverine, R.drawable.wolverine, R.drawable.affiliation_xmen,
			new int[]{C.INSTINCT, C.INSTINCT, C.INSTINCT, C.INSTINCT}),
	
	;
	
	private final CardBase card;
	private final int affiliationPictureId;
	private final int[] cardColours;
		
	private Hero(int name, int pictureId, int affiliationPictureId, int[] cardColours) {
		card = new CardBase(name, pictureId);
		this.affiliationPictureId = affiliationPictureId;
		this.cardColours = cardColours;
	}
	
	public int getAffiliationPictureId() {
		return affiliationPictureId;
	}
	
	public int getCardColour(int position) {
		if (cardColours == null) return 0;
		return cardColours[position];
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

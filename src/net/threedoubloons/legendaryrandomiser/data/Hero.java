package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
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
	blackWidow(R.string.hero_blackWidow, R.drawable.black_widow, R.drawable.affiliation_avengers, new int[]{C.TECH, C.COVERT, C.COVERT, C.COVERT}),
	captain(R.string.hero_captain, R.drawable.captain_a, R.drawable.affiliation_avengers, new int[]{C.INSTINCT, C.STRENGTH, C.TECH, C.COVERT}),
	cyclops(R.string.hero_cyclops, R.drawable.cyclops, R.drawable.affiliation_xmen, new int[]{C.STRENGTH, C.RANGED, C.RANGED, C.RANGED}),
	deadpool(R.string.hero_deadpool, R.drawable.deadpool, 0, new int[]{C.TECH, C.COVERT, C.INSTINCT, C.INSTINCT}),
	emmaFrost(R.string.hero_emmaFrost, R.drawable.emma_frost, R.drawable.affiliation_xmen, new int[]{C.RANGED, C.COVERT, C.INSTINCT, C.STRENGTH}),
	gambit(R.string.hero_gambit, R.drawable.gambit, R.drawable.affiliation_xmen, new int[]{C.RANGED, C.COVERT, C.INSTINCT, C.INSTINCT}),
	hawkeye(R.string.hero_hawkeye, R.drawable.hawkeye, R.drawable.affiliation_avengers, new int[]{C.INSTINCT, C.TECH, C.TECH, C.TECH}),
	hulk(R.string.hero_hulk, R.drawable.hulk, R.drawable.affiliation_avengers, new int[]{C.INSTINCT, C.STRENGTH, C.STRENGTH, C.STRENGTH}),
	ironMan(R.string.hero_ironMan, R.drawable.ironman, R.drawable.affiliation_avengers, new int[]{C.RANGED, C.TECH, C.TECH, C.TECH}),
	nickFury(R.string.hero_nickFury, R.drawable.nick_fury, R.drawable.affiliation_shield, new int[]{C.TECH, C.COVERT, C.STRENGTH, C.TECH}),
	rogue(R.string.hero_rogue, R.drawable.rogue, R.drawable.affiliation_xmen, new int[]{C.STRENGTH, C.COVERT, C.COVERT, C.STRENGTH}),
	spiderman(R.string.hero_spiderman, R.drawable.spiderman,  R.drawable.affiliation_spiderfriends, new int[]{C.INSTINCT, C.STRENGTH, C.TECH, C.COVERT}),
	storm(R.string.hero_storm, R.drawable.storm, R.drawable.affiliation_xmen, new int[]{C.RANGED, C.RANGED, C.COVERT, C.RANGED}),
	thor(R.string.hero_thor, R.drawable.thor, R.drawable.affiliation_avengers, new int[]{C.STRENGTH, C.RANGED, C.RANGED, C.RANGED}),
	wolverine(R.string.hero_wolverine, R.drawable.wolverine, R.drawable.affiliation_xmen, new int[]{C.INSTINCT, C.INSTINCT, C.INSTINCT, C.INSTINCT}),
	
	//Dark City
	angel(R.string.hero_angel, R.drawable.missing_img, R.drawable.affiliation_xmen, new int[]{C.COVERT, C.STRENGTH, C.INSTINCT, C.STRENGTH}, Sets.DarkCity),
	blade(R.string.hero_blade, R.drawable.missing_img, R.drawable.affiliation_marvelknights, new int[]{C.COVERT, C.STRENGTH, C.TECH, C.INSTINCT}, Sets.DarkCity),
	bishop(R.string.hero_bishop, R.drawable.missing_img, R.drawable.affiliation_xmen, new int[]{C.RANGED, C.COVERT, C.RANGED, C.TECH}, Sets.DarkCity),
	cable(R.string.hero_cable, R.drawable.missing_img, R.drawable.affiliation_xforce, new int[]{C.TECH, C.RANGED, C.COVERT, C.RANGED}, Sets.DarkCity),
	colossus(R.string.hero_colossus, R.drawable.missing_img, R.drawable.affiliation_xforce, new int[]{C.STRENGTH, C.STRENGTH, C.COVERT, C.STRENGTH}, Sets.DarkCity),
	daredevil(R.string.hero_daredevil, R.drawable.missing_img, R.drawable.affiliation_marvelknights, new int[]{C.STRENGTH, C.INSTINCT, C.COVERT, C.INSTINCT}, Sets.DarkCity),
	domino(R.string.hero_domino, R.drawable.missing_img, R.drawable.affiliation_xforce, new int[]{C.TECH, C.INSTINCT, C.TECH, C.COVERT}, Sets.DarkCity),
	electra(R.string.hero_electra, R.drawable.missing_img, R.drawable.affiliation_marvelknights, new int[]{C.COVERT, C.INSTINCT, C.INSTINCT, C.INSTINCT}, Sets.DarkCity),
	forge(R.string.hero_forge, R.drawable.missing_img, R.drawable.affiliation_xforce, new int[]{C.TECH, C.TECH, C.TECH, C.TECH}, Sets.DarkCity),
	ghostRider(R.string.hero_ghost_rider, R.drawable.missing_img, R.drawable.affiliation_marvelknights, new int[]{C.TECH, C.RANGED, C.STRENGTH, C.RANGED}, Sets.DarkCity),
	iceman(R.string.hero_iceman, R.drawable.missing_img, R.drawable.affiliation_xmen, new int[]{C.RANGED, C.RANGED, C.STRENGTH, C.RANGED}, Sets.DarkCity),
	ironFist(R.string.hero_ironfist, R.drawable.missing_img, R.drawable.affiliation_marvelknights, new int[]{C.STRENGTH, C.INSTINCT, C.STRENGTH, C.STRENGTH}, Sets.DarkCity),
	jeanGrey(R.string.hero_jean_grey, R.drawable.missing_img, R.drawable.affiliation_xmen, new int[]{C.RANGED, C.COVERT, C.COVERT, C.RANGED}, Sets.DarkCity),
	nightCrawler(R.string.hero_nightcrawler, R.drawable.missing_img, R.drawable.affiliation_xmen, new int[]{C.INSTINCT, C.COVERT, C.INSTINCT, C.COVERT}, Sets.DarkCity),
	professorX(R.string.hero_profx, R.drawable.missing_img, R.drawable.affiliation_xmen, new int[]{C.RANGED, C.INSTINCT, C.RANGED, C.COVERT}, Sets.DarkCity),
	punisher(R.string.hero_punisher, R.drawable.missing_img, R.drawable.affiliation_marvelknights, new int[]{C.TECH, C.TECH, C.STRENGTH, C.TECH}, Sets.DarkCity),
	wolverine_dc(R.string.hero_wolverine, R.drawable.missing_img, R.drawable.affiliation_xforce, new int[]{C.INSTINCT, C.COVERT, C.STRENGTH, C.COVERT}, Sets.DarkCity),
	;
	
	private final CardBase card;
	private final int affiliationPictureId;
	private final int[] cardColours;
		
	private Hero(int name, int pictureId, int affiliationPictureId, int[] cardColours) {
		this(name, pictureId, affiliationPictureId, cardColours, Sets.CoreSet);
	}
	
	private Hero(int name, int pictureId, int affiliationPictureId, int[] cardColours, Sets set) {
		card = new CardBase(name, pictureId, set);
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

	private static List<Hero> all;
	public static void initialiseAllList(EnumSet<Sets> activeSets) {
		List<Hero> all = new ArrayList<Hero>();

		for (Hero h : values()) {
			if (activeSets.contains(h.card.getExpansion())) {
				all.add(h);
			}
		}
		
		all.remove(none);
		
		Hero.all = Collections.unmodifiableList(all);
	}
	
	public final static List<Hero> getAll() {
		return all;
	}
}

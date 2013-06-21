package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

final class C {
	static final int COVERT = R.drawable.col_covert;
	static final int INSTINCT = R.drawable.col_instinct; 
	static final int RANGED = R.drawable.col_ranged;
	static final int STRENGTH = R.drawable.col_strength;
	static final int TECH = R.drawable.col_tech;
}

public enum Hero implements ICardBase {
	none(0, 0, 0, null),
	blackWidow(R.string.hero_blackWidow, R.drawable.hero_black_widow, R.drawable.team_avengers, new int[]{C.TECH, C.COVERT, C.COVERT, C.COVERT}),
	captain(R.string.hero_captain, R.drawable.hero_captain_a, R.drawable.team_avengers, new int[]{C.INSTINCT, C.STRENGTH, C.TECH, C.COVERT}),
	cyclops(R.string.hero_cyclops, R.drawable.hero_cyclops, R.drawable.team_xmen, new int[]{C.STRENGTH, C.RANGED, C.RANGED, C.RANGED}),
	deadpool(R.string.hero_deadpool, R.drawable.hero_deadpool, 0, new int[]{C.TECH, C.COVERT, C.INSTINCT, C.INSTINCT}),
	emmaFrost(R.string.hero_emmaFrost, R.drawable.hero_emma_frost, R.drawable.team_xmen, new int[]{C.RANGED, C.COVERT, C.INSTINCT, C.STRENGTH}),
	gambit(R.string.hero_gambit, R.drawable.hero_gambit, R.drawable.team_xmen, new int[]{C.RANGED, C.COVERT, C.INSTINCT, C.INSTINCT}),
	hawkeye(R.string.hero_hawkeye, R.drawable.hero_hawkeye, R.drawable.team_avengers, new int[]{C.INSTINCT, C.TECH, C.TECH, C.TECH}),
	hulk(R.string.hero_hulk, R.drawable.hero_hulk, R.drawable.team_avengers, new int[]{C.INSTINCT, C.STRENGTH, C.STRENGTH, C.STRENGTH}),
	ironMan(R.string.hero_ironMan, R.drawable.hero_ironman, R.drawable.team_avengers, new int[]{C.RANGED, C.TECH, C.TECH, C.TECH}),
	nickFury(R.string.hero_nickFury, R.drawable.hero_nick_fury, R.drawable.team_shield, new int[]{C.TECH, C.COVERT, C.STRENGTH, C.TECH}),
	rogue(R.string.hero_rogue, R.drawable.hero_rogue, R.drawable.team_xmen, new int[]{C.STRENGTH, C.COVERT, C.COVERT, C.STRENGTH}),
	spiderman(R.string.hero_spiderman, R.drawable.hero_spiderman,  R.drawable.team_spiderfriends, new int[]{C.INSTINCT, C.STRENGTH, C.TECH, C.COVERT}),
	storm(R.string.hero_storm, R.drawable.hero_storm, R.drawable.team_xmen, new int[]{C.RANGED, C.RANGED, C.COVERT, C.RANGED}),
	thor(R.string.hero_thor, R.drawable.hero_thor, R.drawable.team_avengers, new int[]{C.STRENGTH, C.RANGED, C.RANGED, C.RANGED}),
	wolverine(R.string.hero_wolverine, R.drawable.hero_wolverine, R.drawable.team_xmen, new int[]{C.INSTINCT, C.INSTINCT, C.INSTINCT, C.INSTINCT}),
	
	//Dark City
	angel(R.string.hero_angel, R.drawable.int_missing_img, R.drawable.team_xmen, new int[]{C.COVERT, C.STRENGTH, C.INSTINCT, C.STRENGTH}, Sets.DarkCity),
	blade(R.string.hero_blade, R.drawable.int_missing_img, R.drawable.team_marvelknights, new int[]{C.COVERT, C.STRENGTH, C.TECH, C.INSTINCT}, Sets.DarkCity),
	bishop(R.string.hero_bishop, R.drawable.int_missing_img, R.drawable.team_xmen, new int[]{C.RANGED, C.COVERT, C.RANGED, C.TECH}, Sets.DarkCity),
	cable(R.string.hero_cable, R.drawable.int_missing_img, R.drawable.team_xforce, new int[]{C.TECH, C.RANGED, C.COVERT, C.RANGED}, Sets.DarkCity),
	colossus(R.string.hero_colossus, R.drawable.int_missing_img, R.drawable.team_xforce, new int[]{C.STRENGTH, C.STRENGTH, C.COVERT, C.STRENGTH}, Sets.DarkCity),
	daredevil(R.string.hero_daredevil, R.drawable.int_missing_img, R.drawable.team_marvelknights, new int[]{C.STRENGTH, C.INSTINCT, C.COVERT, C.INSTINCT}, Sets.DarkCity),
	domino(R.string.hero_domino, R.drawable.int_missing_img, R.drawable.team_xforce, new int[]{C.TECH, C.INSTINCT, C.TECH, C.COVERT}, Sets.DarkCity),
	electra(R.string.hero_electra, R.drawable.int_missing_img, R.drawable.team_marvelknights, new int[]{C.COVERT, C.INSTINCT, C.INSTINCT, C.INSTINCT}, Sets.DarkCity),
	forge(R.string.hero_forge, R.drawable.int_missing_img, R.drawable.team_xforce, new int[]{C.TECH, C.TECH, C.TECH, C.TECH}, Sets.DarkCity),
	ghostRider(R.string.hero_ghost_rider, R.drawable.int_missing_img, R.drawable.team_marvelknights, new int[]{C.TECH, C.RANGED, C.STRENGTH, C.RANGED}, Sets.DarkCity),
	iceman(R.string.hero_iceman, R.drawable.int_missing_img, R.drawable.team_xmen, new int[]{C.RANGED, C.RANGED, C.STRENGTH, C.RANGED}, Sets.DarkCity),
	ironFist(R.string.hero_ironfist, R.drawable.int_missing_img, R.drawable.team_marvelknights, new int[]{C.STRENGTH, C.INSTINCT, C.STRENGTH, C.STRENGTH}, Sets.DarkCity),
	jeanGrey(R.string.hero_jean_grey, R.drawable.int_missing_img, R.drawable.team_xmen, new int[]{C.RANGED, C.COVERT, C.COVERT, C.RANGED}, Sets.DarkCity),
	nightCrawler(R.string.hero_nightcrawler, R.drawable.int_missing_img, R.drawable.team_xmen, new int[]{C.INSTINCT, C.COVERT, C.INSTINCT, C.COVERT}, Sets.DarkCity),
	professorX(R.string.hero_profx, R.drawable.int_missing_img, R.drawable.team_xmen, new int[]{C.RANGED, C.INSTINCT, C.RANGED, C.COVERT}, Sets.DarkCity),
	punisher(R.string.hero_punisher, R.drawable.int_missing_img, R.drawable.team_marvelknights, new int[]{C.TECH, C.TECH, C.STRENGTH, C.TECH}, Sets.DarkCity),
	wolverine_dc(R.string.hero_wolverine, R.drawable.int_missing_img, R.drawable.team_xforce, new int[]{C.INSTINCT, C.COVERT, C.STRENGTH, C.COVERT}, Sets.DarkCity),
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

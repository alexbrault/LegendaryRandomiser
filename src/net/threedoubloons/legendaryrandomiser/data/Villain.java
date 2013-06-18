package net.threedoubloons.legendaryrandomiser.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

public enum Villain implements ICardBase {
	none(0, 0),
	skrulls(R.string.skrulls, R.drawable.skrulls),
	spiderfoes(R.string.spiderfoes, R.drawable.spiderfoes),
	enemiesOfAsgard(R.string.frost_giants, R.drawable.enemies_of_asgard),
	brotherhood(R.string.brotherhood, R.drawable.brotherhood),
	hydra(R.string.hydra, R.drawable.hydra),
	mastersOfEvil(R.string.masters_of_evil, R.drawable.masters),
	radiation(R.string.radiation, R.drawable.radiation),
	
	// Dark City
	emissariesOfEvil(0, R.drawable.missing_img, false, Sets.DarkCity),
	fourHorsemen(0, R.drawable.missing_img, false, Sets.DarkCity),
	marauders(0, R.drawable.missing_img, false, Sets.DarkCity),
	mlf(0, R.drawable.missing_img, false, Sets.DarkCity),
	streetsOfNY(0, R.drawable.missing_img, false, Sets.DarkCity),
	underworld(0, R.drawable.missing_img, false, Sets.DarkCity),


	handNinja(R.string.hand_ninja, R.drawable.hand_ninja, true),
	doombots(R.string.doombots, R.drawable.doombots, true),
	sentinel(R.string.sentinel, R.drawable.sentinel, true),
	mutates(R.string.mutates, R.drawable.mutates, true),
	
	//Dark city
	maggiaGoons(0, R.drawable.missing_img, true, Sets.DarkCity),
	phalanx(0, R.drawable.missing_img, true, Sets.DarkCity),
	;
	
	private CardBase card;
	private boolean isHenchman;
	
	private Villain(int name, int pictureId) {
		this(name, pictureId, false, Sets.CoreSet);
	}

	private Villain(int name, int pictureId, boolean isHenchman) {
		this(name, pictureId, isHenchman, Sets.CoreSet);
	}
	
	private Villain(int name, int pictureId, boolean isHenchman, Sets set) {
		card = new CardBase(name, pictureId, set);
		this.isHenchman = isHenchman;
	}
	
	public CardBase getCard() {
		return card;
	}
	
	public boolean isHenchman() {
		return isHenchman;
	}
	

	public static Villain get(String name) {
		return Enum.valueOf(Villain.class, name);
	}

	private static List<Villain> allVillain;
	private static List<Villain> allHenchmen;
	public static void initialiseAllList(EnumSet<Sets> activeSets) {
		List<Villain> allV = new ArrayList<Villain>();
		List<Villain> allH = new ArrayList<Villain>();
		
		for (Villain v : values()) {
			if (activeSets.contains(v.card.getExpansion())) {
				if (v.isHenchman()) {
					allH.add(v);
				} else {
					allV.add(v);
				}
			}
		}
		
		Villain.allVillain = Collections.unmodifiableList(allV);
		Villain.allHenchmen = Collections.unmodifiableList(allH);
	}
	
	public static List<Villain> getAll() {
		return allVillain;
	}

	public static List<Villain> getAllHenchmen() {
		return allHenchmen;
	}
}

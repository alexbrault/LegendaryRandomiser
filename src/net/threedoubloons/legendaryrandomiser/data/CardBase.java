package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;


public class CardBase implements Serializable {
	private static final long serialVersionUID = 9170258208807528519L;
	
	private final int name;
	private final int pictureId;
	private final Sets expansion;
	
	public CardBase(int name, int pictureId) {
		this(name, pictureId, Sets.CoreSet);
	}
	
	public CardBase(int name, int pictureId, Sets expansion) {
		this.name = name;
		this.pictureId = pictureId;
		this.expansion = expansion;
	}

	public final int getName() {
		return name;
	}

	public final int getPictureId() {
		return pictureId;
	}

	public int getExpansionSymbol() {
		return expansion.getSymbol();
	}
	
	public Sets getExpansion() {
		return expansion;
	}
}
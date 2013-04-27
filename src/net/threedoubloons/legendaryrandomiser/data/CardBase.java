package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;

import net.threedoubloons.legendaryrandomiser.R;

public class CardBase implements Serializable {
	private static final long serialVersionUID = 9170258208807528519L;
	
	private final int name;
	private final int pictureId;
	private final int expansionSymbol;
	
	public CardBase(int name, int pictureId) {
		this(name, pictureId, R.drawable.ic_exp_core);
	}
	
	public CardBase(int name, int pictureId, int expansionSymbol) {
		this.name = name;
		this.pictureId = pictureId;
		this.expansionSymbol = expansionSymbol;
	}

	public final int getName() {
		return name;
	}

	public final int getPictureId() {
		return pictureId;
	}

	public int getExpansionSymbol() {
		return expansionSymbol;
	}
}

interface ICardBase {
	CardBase getCard();
}
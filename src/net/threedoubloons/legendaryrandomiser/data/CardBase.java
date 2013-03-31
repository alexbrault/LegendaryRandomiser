package net.threedoubloons.legendaryrandomiser.data;

import net.threedoubloons.legendaryrandomiser.R;

public abstract class CardBase {
	private final int name;
	private final int pictureId;
	private final int expansionSymbol;
	
	protected CardBase(int name, int pictureId) {
		this(name, pictureId, R.drawable.ic_exp_core);
	}
	
	protected CardBase(int name, int pictureId, int expansionSymbol) {
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

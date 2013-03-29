package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;

public abstract class CardBase implements Serializable {
	private static final long serialVersionUID = -7650201963254206540L;
	private final int name;
	private final int pictureId;
	protected CardBase(int name, int pictureId) {
		this.name = name;
		this.pictureId = pictureId;
	}

	public final int getName() {
		return name;
	}

	public final int getPictureId() {
		return pictureId;
	}
}

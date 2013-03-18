package net.threedoubloons.legendaryrandomiser.data;

public abstract class CardBase {
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

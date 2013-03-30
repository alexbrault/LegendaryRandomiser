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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name;
		result = prime * result + pictureId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		CardBase other = (CardBase) obj;
		if (name != other.name)
			return false;
		if (pictureId != other.pictureId)
			return false;
		return true;
	}
}

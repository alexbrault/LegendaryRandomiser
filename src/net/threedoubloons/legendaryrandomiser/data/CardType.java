package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;

import net.threedoubloons.legendaryrandomiser.R;

public class CardType implements Serializable {
	private static final long serialVersionUID = 7331451167355656289L;
	
	private final String name;
	private final int pictureId;
	
	public CardType(String name, int pictureId) {
		super();
		this.name = name;
		this.pictureId = pictureId;
	}

	public String getName() {
		return name;
	}

	public int getPictureId() {
		return pictureId;
	}
	
	public final static CardType bystander = new CardType("Bystander", R.drawable.missing_img);
	public final static CardType masterStrike = new CardType("Master Strike", R.drawable.missing_img);
	public final static CardType schemeTwist = new CardType("Scheme Twist", R.drawable.missing_img);
}

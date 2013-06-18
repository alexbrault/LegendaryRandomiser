package net.threedoubloons.legendaryrandomiser.data;

import java.io.Serializable;

import net.threedoubloons.legendaryrandomiser.R;

public class CardType implements Serializable {
	private static final long serialVersionUID = 7331451167355656289L;
	
	private final int name;
	private final int pictureId;
	
	public CardType(int name, int pictureId) {
		super();
		this.name = name;
		this.pictureId = pictureId;
	}

	public int getName() {
		return name;
	}

	public int getPictureId() {
		return pictureId;
	}
	
	public final static CardType bystander = new CardType(R.string.ct_bystander, R.drawable.bystander);
	public final static CardType masterStrike = new CardType(R.string.ct_master_strike, R.drawable.masterstrike);
	public final static CardType schemeTwist = new CardType(R.string.ct_scheme, R.drawable.scheme_twist);
	public final static CardType hero = new CardType(R.string.ct_hero, R.drawable.heroes);
	
	public static CardType makeSpecificCardType(ICardBase card) {
		return new SpecificCard(card.getCard());
	}
	
	private static class SpecificCard extends CardType {
		private static final long serialVersionUID = 1145013808617730184L;

		public SpecificCard(CardBase card) {
			super(card.getName(), card.getPictureId());
		}
		
	}
}

package net.threedoubloons.legendaryrandomiser.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;
import net.threedoubloons.legendaryrandomiser.data.Hero;
import net.threedoubloons.legendaryrandomiser.data.ICardBase;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetupCardsAdapter implements ListAdapter {
	private List<ICardBase> cards;
	private LayoutInflater inflater;
	
	public SetupCardsAdapter(Context context, Collection<? extends ICardBase> cards) {
		super();
		this.cards = new ArrayList<ICardBase>(cards);
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return cards.size();
	}

	@Override
	public Object getItem(int pos) {
		return cards.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return cards.get(pos).getCard().getPictureId();
	}

	@Override
	public int getItemViewType(int arg0) {
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		if (!(view instanceof RelativeLayout)) {
			view = inflater.inflate(R.layout.legendary_item_label, null);
		}
		
		ICardBase card = cards.get(pos);
		
		TextView label = (TextView)view.findViewById(R.id.lil_label);
		ImageView expansion = (ImageView)view.findViewById(R.id.lil_expansion_icon);
		ImageView affiliation = (ImageView)view.findViewById(R.id.lil_affiliation_icon);
		
		label.setText(card.getCard().getName());
		label.setCompoundDrawablesWithIntrinsicBounds(card.getCard().getPictureId(), 0, 0, 0);
		expansion.setImageResource(card.getCard().getExpansionSymbol());
		expansion.setTag(card.getCard().getExpansion());
		
		if (card instanceof Hero) {
			Hero h = (Hero)card;
			
			affiliation.setImageResource(h.getAffiliationPictureId());
			
			ImageView colour;
			colour = (ImageView)view.findViewById(R.id.hl_common0);
			colour.setImageResource(h.getCardColour(0));
			colour = (ImageView)view.findViewById(R.id.hl_common1);
			colour.setImageResource(h.getCardColour(1));
			colour = (ImageView)view.findViewById(R.id.hl_uncommon);
			colour.setImageResource(h.getCardColour(2));
			colour = (ImageView)view.findViewById(R.id.hl_rare);
			colour.setImageResource(h.getCardColour(3));
			view.findViewById(R.id.hl_colours).setVisibility(View.VISIBLE);
		} else {
			view.findViewById(R.id.hl_colours).setVisibility(View.GONE);
		}


		return view;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		return cards.size() == 0;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver obs) {
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver obs) {
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

}

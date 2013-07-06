package net.threedoubloons.legendaryrandomiser.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import net.threedoubloons.legendaryrandomiser.R;
import net.threedoubloons.legendaryrandomiser.data.Hero;
import net.threedoubloons.legendaryrandomiser.data.ICardBase;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetupCardsAdapter implements ListAdapter {
	private List<SortableCardBase> cards;
	private LayoutInflater inflater;
	private List<DataSetObserver> observers = new ArrayList<DataSetObserver>();
		
	public SetupCardsAdapter(Context context, Collection<? extends ICardBase> cards) {
		this.cards = new ArrayList<SortableCardBase>(cards.size());
		Resources res = context.getResources();
		
		for (ICardBase c : cards) {
			SortableCardBase s = new SortableCardBase(res, c);
			this.cards.add(s);
		}
		
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		sortCards();
	}

	private void sortCards() {
		Collections.sort(this.cards, new Comparator<SortableCardBase>() {
			public int compare(SortableCardBase lhs, SortableCardBase rhs) {
				return lhs.key.compareTo(rhs.key);
			}
		});
	}
	
	public void toggleCard(Object card) {
		if (card instanceof SortableCardBase) {
			SortableCardBase c = (SortableCardBase)card;
			c.isActive = !c.isActive;
			sortCards();
			notifyObservers();
		}
	}

	private void notifyObservers() {
		for (DataSetObserver obs: observers) {
			obs.onChanged();
		}
	}

	@Override
	public int getCount() {
		return cards.size();
	}
	
	private ICardBase get(int pos) {
		return cards.get(pos).card;
	}

	@Override
	public Object getItem(int pos) {
		return cards.get(pos).card;
	}

	@Override
	public long getItemId(int pos) {
		return get(pos).getCard().getPictureId();
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
		
		SortableCardBase c = cards.get(pos);
		ICardBase card = c.card;
		
		view.setTag(R.id.lil_label, c);
		
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

		if (c.isActive) {
			label.setEnabled(true);
			label.setPaintFlags(label.getPaintFlags() & ~(Paint.STRIKE_THRU_TEXT_FLAG));
		} else {
			label.setEnabled(false);
			label.setPaintFlags(label.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
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
		return getCount() == 0;
	}
	
	@Override
	public void registerDataSetObserver(DataSetObserver obs) {
		observers.add(obs);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver obs) {
		observers.remove(obs);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

	private static class SortableCardBase {
		public final String key;
		public final ICardBase card;
		public boolean isActive;
		
		public SortableCardBase(Resources res, ICardBase card) {
			this.card = card;
			this.key = res.getString(card.getCard().getName()).toLowerCase(Locale.US);
			isActive = true;
		}
	}
}

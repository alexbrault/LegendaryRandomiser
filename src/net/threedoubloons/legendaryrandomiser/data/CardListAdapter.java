package net.threedoubloons.legendaryrandomiser.data;

import java.util.List;

import net.threedoubloons.legendaryrandomiser.R;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class CardListAdapter<T extends ICardBase> implements SpinnerAdapter {
	private List<T> list;
	private LayoutInflater inflater;
	public class RandomiseThisAction{}
	
	public CardListAdapter(List<T> list, Context context) {
		this.list = list;
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return list.size() + 2;
	}

	@Override
	public Object getItem(int pos) {
		if (pos == 0)
			return null;
		if (pos == list.size() + 1) 
			return new RandomiseThisAction();
		return list.get(pos - 1);
	}

	@Override
	public long getItemId(int pos) {
		if (pos == 0)
			return -1;
		if (pos == list.size() + 1) 
			return -2;
		return list.get(pos - 1).getCard().getPictureId();
	}

	@Override
	public int getItemViewType(int arg0) {
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		if (!(view instanceof LinearLayout)) {
			view = inflater.inflate(R.layout.legendary_item_label, null);
		}
		
		TextView v = (TextView)view.findViewById(R.id.lil_label);
		
		if (pos == 0) {
			v.setText("No preference");
			v.setTextColor(Color.GRAY);
			v.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		} else if (pos == list.size() + 1) {
			v.setText("Randomise now");
			v.setTextColor(Color.BLUE);
			v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_random, 0, 0, 0);
		} else {
			CardBase card = list.get(pos).getCard();
			v.setText(card.getName());
			v.setTextColor(Color.BLACK);
			v.setCompoundDrawablesWithIntrinsicBounds(card.getPictureId(), 0, 0, 0);
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
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
	}

	@Override
	public View getDropDownView(int arg0, View arg1, ViewGroup arg2) {
		return getView(arg0, arg1, arg2);
	}

}

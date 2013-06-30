package net.threedoubloons.legendaryrandomiser.containers;

import java.util.Collection;

import net.threedoubloons.legendaryrandomiser.R;
import net.threedoubloons.legendaryrandomiser.adapters.SetupCardsAdapter;
import net.threedoubloons.legendaryrandomiser.data.ICardBase;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SetupCardListContainer implements OnClickListener {
	private SetupCardsAdapter adapter;
	private LinearLayout layout;
	private Observer obs;
	
	public SetupCardListContainer(Context context, LinearLayout layout, Collection<? extends ICardBase> cards) {
		this.adapter = new SetupCardsAdapter(context, cards); 
		obs = new Observer();
		adapter.registerDataSetObserver(obs);
		this.layout = layout;
		refreshData();
	}
	
	public void dispose() {
		adapter.unregisterDataSetObserver(obs);
	}

	public void refreshData() {
		if (adapter != null) {
			int adapterCount = adapter.getCount();
			for (int i = 0; i < adapterCount; ++i) {
				View oldView = layout.getChildAt(i);
				View newView = adapter.getView(i, oldView, null);
				
				if (oldView != newView) {
					
					if (oldView != null) {
						layout.removeViewAt(i);
					}
					
					layout.addView(newView, i);
					newView.setOnClickListener(this);
				}
			}
			
			layout.removeViews(adapterCount, layout.getChildCount() - adapterCount);
		} else {
			layout.removeAllViews();
		}
	}
	
	private class Observer extends DataSetObserver {
		@Override
		public void onChanged() {
			refreshData();
			super.onChanged();
		}
	}

	@Override
	public void onClick(View v) {
		Object o = v.getTag(R.id.lil_label);
		if (o instanceof ICardBase) {
			adapter.toggleCard((ICardBase)o);
		}
	}
}

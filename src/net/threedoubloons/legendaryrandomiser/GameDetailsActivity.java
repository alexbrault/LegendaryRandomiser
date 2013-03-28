package net.threedoubloons.legendaryrandomiser;

import java.util.Map;

import net.threedoubloons.legendaryrandomiser.data.CardType;
import net.threedoubloons.legendaryrandomiser.data.Henchman;
import net.threedoubloons.legendaryrandomiser.data.Hero;
import net.threedoubloons.legendaryrandomiser.data.Mastermind;
import net.threedoubloons.legendaryrandomiser.data.Villain;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameDetailsActivity extends Activity {
	public static final int RESULT_REDO = RESULT_FIRST_USER + 0;
	GameDetails details;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_details);
		// Show the Up button in the action bar.
		setupActionBar();
		
		details = (GameDetails)getIntent().getSerializableExtra(OptionsSelectActivity.GAME_OPTIONS);
		if (details == null) {
			details = new GameDetails();
		}
		
		details.randomiseAll();
		TextView view;
		
		view = (TextView)findViewById(R.id.mastermind);		
		Mastermind m = details.getMastermind();
		view.setText(m.getName());
		view.setCompoundDrawablesWithIntrinsicBounds(m.getPictureId(), 0, 0, 0);

		view = (TextView)findViewById(R.id.scheme);
		view.setText(details.getScheme().getLongName());
		
		LayoutInflater inflater = getLayoutInflater();
		LinearLayout list;
		
		list = (LinearLayout)findViewById(R.id.villains_list);
		for (Villain villain : details.getVillains()) {
			TextView v = (TextView)inflater.inflate(R.layout.legendary_item_label, null);
			v.setText(villain.getName());
			v.setCompoundDrawablesWithIntrinsicBounds(villain.getPictureId(), 0, 0, 0);
			list.addView(v);
		}

		list = (LinearLayout)findViewById(R.id.henchmen_list);
		for (Henchman h : details.getHenchmen()) {
			TextView v = (TextView)inflater.inflate(R.layout.legendary_item_label, null);
			v.setText(h.getName());
			v.setCompoundDrawablesWithIntrinsicBounds(h.getPictureId(), 0, 0, 0);
			list.addView(v);
		}
		
		list = (LinearLayout)findViewById(R.id.villaindeck_list);
		for (Map.Entry<CardType, Integer> card : details.getVillainsDeckContents()) {
			TextView v = (TextView)inflater.inflate(R.layout.legendary_item_label, null);
			v.setText(String.format("%d %s", card.getValue(), card.getKey().getName()));
			v.setCompoundDrawablesWithIntrinsicBounds(card.getKey().getPictureId(), 0, 0, 0);
			list.addView(v);
		}
		
		list = (LinearLayout)findViewById(R.id.heroes_list);
		for (Hero h : details.getHeroes()) {
			TextView v = (TextView)inflater.inflate(R.layout.legendary_item_label, null);
			v.setText(h.getName());
			v.setCompoundDrawablesWithIntrinsicBounds(h.getPictureId(), 0, h.getAffiliationPictureId(), 0);
			list.addView(v);
		}
		
		if (details.getNumNotes() > 0) {
			findViewById(R.id.notes_layout).setVisibility(View.VISIBLE);
			list = (LinearLayout)findViewById(R.id.notes_list);
			for (String h : details.getNotes()) {
				TextView v = (TextView)inflater.inflate(R.layout.legendary_item_label, null);
				v.setText(h);
				v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bullet, 0, 0, 0);
				list.addView(v);
			}
			for (String h : details.getErrors()) {
				TextView v = (TextView)inflater.inflate(R.layout.legendary_item_label, null);
				v.setText(h);
				v.setTextColor(getResources().getColor(R.color.error_message));
				v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bullet_important, 0, 0, 0);
				list.addView(v);
			}
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	@SuppressLint("NewApi")
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			//NavUtils.navigateUpFromSameTask(this);
			finish();
			return true;
		case R.id.reroll:
			setResult(RESULT_REDO);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

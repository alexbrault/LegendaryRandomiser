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
import android.widget.ImageView;
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
		TextView label;
		View v;
		ImageView expansion;
		
		v = findViewById(R.id.mastermind);
		label = (TextView)v.findViewById(R.id.lil_label);
		expansion = (ImageView)v.findViewById(R.id.lil_expansion_icon);
		Mastermind m = details.getMastermind();
		label.setText(m.getName());
		label.setCompoundDrawablesWithIntrinsicBounds(m.getPictureId(), 0, 0, 0);
		expansion.setImageResource(m.getExpansionSymbol());

		v = findViewById(R.id.scheme);
		label = (TextView)v.findViewById(R.id.lil_label);
		expansion = (ImageView)v.findViewById(R.id.lil_expansion_icon);
		label.setText(details.getScheme().getLongName());
		expansion.setImageResource(details.getScheme().getExpansionSymbol());
		
		LayoutInflater inflater = getLayoutInflater();
		LinearLayout list;
		
		list = (LinearLayout)findViewById(R.id.villains_list);
		for (Villain villain : details.getVillains()) {
			v = inflater.inflate(R.layout.legendary_item_label, null);
			label = (TextView)v.findViewById(R.id.lil_label);
			expansion = (ImageView)v.findViewById(R.id.lil_expansion_icon);
			label.setText(villain.getName());
			label.setCompoundDrawablesWithIntrinsicBounds(villain.getPictureId(), 0, 0, 0);
			expansion.setImageResource(villain.getExpansionSymbol());
			list.addView(v);
		}

		list = (LinearLayout)findViewById(R.id.henchmen_list);
		for (Henchman h : details.getHenchmen()) {
			v = inflater.inflate(R.layout.legendary_item_label, null);
			label = (TextView)v.findViewById(R.id.lil_label);
			expansion = (ImageView)v.findViewById(R.id.lil_expansion_icon);
			label.setText(h.getName());
			label.setCompoundDrawablesWithIntrinsicBounds(h.getPictureId(), 0, 0, 0);
			expansion.setImageResource(h.getExpansionSymbol());
			list.addView(v);
		}
		
		list = (LinearLayout)findViewById(R.id.villaindeck_list);
		for (Map.Entry<CardType, Integer> card : details.getVillainsDeckContents()) {
			v = inflater.inflate(R.layout.legendary_item_label, null);
			label = (TextView)v.findViewById(R.id.lil_label);
			label.setText(String.format("%d %s", card.getValue(), card.getKey().getName()));
			label.setCompoundDrawablesWithIntrinsicBounds(card.getKey().getPictureId(), 0, 0, 0);
			list.addView(v);
		}
		
		list = (LinearLayout)findViewById(R.id.heroes_list);
		for (Hero h : details.getHeroes()) {
			v = inflater.inflate(R.layout.legendary_item_label, null);
			label = (TextView)v.findViewById(R.id.lil_label);
			ImageView affiliation = (ImageView)v.findViewById(R.id.lil_affiliation_icon);
			expansion = (ImageView)v.findViewById(R.id.lil_expansion_icon);
			label.setText(h.getName());
			label.setCompoundDrawablesWithIntrinsicBounds(h.getPictureId(), 0, 0, 0);
			affiliation.setImageResource(h.getAffiliationPictureId());
			expansion.setImageResource(h.getExpansionSymbol());
			
			list.addView(v);
		}
		
		if (details.getNumNotes() > 0) {
			findViewById(R.id.notes_layout).setVisibility(View.VISIBLE);
			list = (LinearLayout)findViewById(R.id.notes_list);
			for (String h : details.getNotes()) {
				v = inflater.inflate(R.layout.legendary_item_label, null);
				label = (TextView)v.findViewById(R.id.lil_label);
				label.setText(h);
				label.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bullet, 0, 0, 0);
				list.addView(v);
			}
			for (String h : details.getErrors()) {
				v = inflater.inflate(R.layout.legendary_item_label, null);
				label = (TextView)v.findViewById(R.id.lil_label);
				label.setText(h);
				label.setTextColor(getResources().getColor(R.color.error_message));
				label.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bullet_important, 0, 0, 0);
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

package net.threedoubloons.legendaryrandomiser;

import net.threedoubloons.legendaryrandomiser.data.Henchman;
import net.threedoubloons.legendaryrandomiser.data.Mastermind;
import net.threedoubloons.legendaryrandomiser.data.Villain;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class GameDetailsActivity extends Activity {
	private TextView mastermind;
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
		mastermind = (TextView)findViewById(R.id.mastermind);
		
		Mastermind m = details.getMastermind();
		mastermind.setText(m.getName());
		mastermind.setCompoundDrawablesWithIntrinsicBounds(m.getPictureId(), 0, 0, 0);
		
		LayoutInflater inflater = getLayoutInflater();
		LinearLayout villainsList = (LinearLayout)findViewById(R.id.villains_list);
		for (Villain villain : details.getVillains()) {
			TextView v = (TextView)inflater.inflate(R.layout.legendary_item_label, null);
			v.setText(villain.getName());
			v.setCompoundDrawablesWithIntrinsicBounds(villain.getPictureId(), 0, 0, 0);
			villainsList.addView(v);
		}

		LinearLayout henchmenList = (LinearLayout)findViewById(R.id.henchmen_list);
		for (Henchman h : details.getHenchmen()) {
			TextView v = (TextView)inflater.inflate(R.layout.legendary_item_label, null);
			v.setText(h.getName());
			v.setCompoundDrawablesWithIntrinsicBounds(h.getPictureId(), 0, 0, 0);
			henchmenList.addView(v);
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

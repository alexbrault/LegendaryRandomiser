package net.threedoubloons.legendaryrandomiser;

import java.util.Map;

import net.threedoubloons.legendaryrandomiser.adapters.SetupCardsAdapter;
import net.threedoubloons.legendaryrandomiser.data.CardType;
import net.threedoubloons.legendaryrandomiser.data.Mastermind;
import net.threedoubloons.legendaryrandomiser.data.Sets;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class GameDetailsActivity extends Activity {
	GameDetails details;
	private Toast toast = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_details);
		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean stayAwake = sharedPreferences.getBoolean("prefs_stayawake", false);
		findViewById(android.R.id.content).setKeepScreenOn(stayAwake);
		
		// Show the Up button in the action bar.
		setupActionBar();
				
		if (savedInstanceState == null) {
			randomiseDetails();
		} else {
			details = (GameDetails)savedInstanceState.getParcelable(OptionsSelectActivity.GAME_OPTIONS);
		}
		setupContents();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(OptionsSelectActivity.GAME_OPTIONS, details);
	}

	private void randomiseDetails() {
		details = new GameDetails((GameDetails)getIntent().getParcelableExtra(OptionsSelectActivity.GAME_OPTIONS));
		if (details == null) {
			details = new GameDetails();
		}
		
		details.randomiseAll();
	}

	private void setupContents() {
		TextView label;
		View v;
		ImageView expansion;
		
		v = findViewById(R.id.mastermind);
		label = (TextView)v.findViewById(R.id.lil_label);
		expansion = (ImageView)v.findViewById(R.id.lil_expansion_icon);
		Mastermind m = details.getMastermind();
		label.setText(m.getCard().getName());
		label.setCompoundDrawablesWithIntrinsicBounds(m.getCard().getPictureId(), 0, 0, 0);
		expansion.setImageResource(m.getCard().getExpansionSymbol());
		expansion.setTag(m.getCard().getExpansion());

		v = findViewById(R.id.scheme);
		label = (TextView)v.findViewById(R.id.lil_label);
		expansion = (ImageView)v.findViewById(R.id.lil_expansion_icon);
		label.setText(details.getScheme().getLongName());
		expansion.setImageResource(details.getScheme().getCard().getExpansionSymbol());
		expansion.setTag(details.getScheme().getCard().getExpansion());
		
		LayoutInflater inflater = getLayoutInflater();
		
		fillList((LinearLayout)findViewById(R.id.villains_list), new SetupCardsAdapter(this, details.getVillains()));
		fillList((LinearLayout)findViewById(R.id.henchmen_list), new SetupCardsAdapter(this, details.getHenchmen()));
		fillList((LinearLayout)findViewById(R.id.heroes_list), new SetupCardsAdapter(this, details.getHeroes()));

		LinearLayout list;
		list = (LinearLayout)findViewById(R.id.villaindeck_list);
		list.removeAllViews();
		for (Map.Entry<CardType, Integer> card : details.getVillainsDeckContents()) {
			if (card.getValue() == 0) {
				continue;
			}
			
			v = inflater.inflate(R.layout.legendary_item_label, null);
			label = (TextView)v.findViewById(R.id.lil_label);
			label.setText(String.format("%d %s", card.getValue(), getString(card.getKey().getName())));
			label.setCompoundDrawablesWithIntrinsicBounds(card.getKey().getPictureId(), 0, 0, 0);
			list.addView(v);
		}
		
		list = (LinearLayout)findViewById(R.id.notes_list);
		list.removeAllViews();
		if (details.getNumNotes() > 0) {
			findViewById(R.id.notes_layout).setVisibility(View.VISIBLE);
			for (String h : details.getNotes()) {
				v = inflater.inflate(R.layout.legendary_item_label, null);
				label = (TextView)v.findViewById(R.id.lil_label);
				label.setText(h);
				label.setCompoundDrawablesWithIntrinsicBounds(R.drawable.int_bullet, 0, 0, 0);
				list.addView(v);
			}
			for (String h : details.getErrors()) {
				v = inflater.inflate(R.layout.legendary_item_label, null);
				label = (TextView)v.findViewById(R.id.lil_label);
				label.setText(h);
				label.setTextColor(getResources().getColor(R.color.error_message));
				label.setCompoundDrawablesWithIntrinsicBounds(R.drawable.int_bullet_important, 0, 0, 0);
				list.addView(v);
			}
		} else {
			findViewById(R.id.notes_layout).setVisibility(View.GONE);
		}
		
		
		((ScrollView)findViewById(R.id.scroll)).smoothScrollTo(0, 0);
	}

	private void fillList(LinearLayout list, Adapter adapter) {
		int adapterCount = adapter.getCount();
		for (int i = 0; i < adapterCount; ++i) {
			View oldView = list.getChildAt(i);
			View newView = adapter.getView(i, oldView, null);
			if (oldView != newView) {
				if (oldView != null) {
					list.removeViewAt(i);
				}
				
				list.addView(newView, i);
			}
		}
		
		list.removeViews(adapterCount, list.getChildCount() - adapterCount);
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
	
	public void showExpansion(View v) {
		Object tag = v.getTag();
		if (tag instanceof Sets) {
			Sets set = (Sets)tag;
			if (set.getName() != 0) {
				if (toast == null) {
					toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
				}
				
				toast.setText(set.getName());
				toast.show();
			}
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.activity_game_details);
		setupContents();
		// Show the Up button in the action bar.
		//setupActionBar();
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
			//R.java was modified manually! Reverting to generated version!
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			//NavUtils.navigateUpFromSameTask(this);
			finish();
			return true;
		case R.id.reroll:
			Animation start = AnimationUtils.loadAnimation(this, R.anim.slide_out);
			final Animation end = AnimationUtils.loadAnimation(this, R.anim.slide_in);
			start.setAnimationListener(new Animation.AnimationListener() {
				public void onAnimationStart(Animation animation) {}
				public void onAnimationRepeat(Animation animation) {}
				@Override
				public void onAnimationEnd(Animation animation) {
					randomiseDetails();
					setupContents();
					findViewById(R.id.scroll).startAnimation(end);
				}
			});
			findViewById(R.id.scroll).startAnimation(start);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

package net.threedoubloons.legendaryrandomiser;

import net.threedoubloons.legendaryrandomiser.data.CardListAdapter;
import net.threedoubloons.legendaryrandomiser.data.Mastermind;
import net.threedoubloons.legendaryrandomiser.data.Scheme;
import net.threedoubloons.legendaryrandomiser.data.Villain;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class OptionsSelectActivity extends Activity implements OnSeekBarChangeListener, OnItemSelectedListener {
	private static final int RANDOMISE_GAME = 0;
	public static final String GAME_OPTIONS = "net.threedoubloons.legendaryrandomiser.GAME_OPTIONS";
	private GameDetails options;
	private TextView numPlayers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options_select);
		
		options = new GameDetails();
		
		SeekBar numPlayersBar = (SeekBar)findViewById(R.id.num_players_seek);
		numPlayersBar.setOnSeekBarChangeListener(this);
		
		numPlayers = (TextView)findViewById(R.id.num_players);
		numPlayers.setText(Integer.toString(options.getNumPlayers()));
		
		Spinner s;
		s = (Spinner)findViewById(R.id.mastermind_spinner);
		s.setAdapter(new CardListAdapter<Mastermind>(Mastermind.getAll(), this));
		s.setOnItemSelectedListener(this);
		
		s = (Spinner)findViewById(R.id.scheme_spinner);
		s.setAdapter(new CardListAdapter<Scheme>(Scheme.getAll(), this));
		s.setOnItemSelectedListener(this);
		
		s = (Spinner)findViewById(R.id.villain0_spinner);
		s.setAdapter(new CardListAdapter<Villain>(Villain.getAll(), this));
		s.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.options_select, menu);
		return true;
	}

	public void randomiseGame(View v) {
		Intent i = new Intent();
		i.setClass(getApplicationContext(), GameDetailsActivity.class);
		i.putExtra(GAME_OPTIONS, options);
		startActivityForResult(i, RANDOMISE_GAME);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
		case RANDOMISE_GAME:
			if (resultCode == GameDetailsActivity.RESULT_REDO) {
				Intent i = new Intent();
				i.setClass(getApplicationContext(), GameDetailsActivity.class);
				i.putExtra(GAME_OPTIONS, options);
				startActivityForResult(i, RANDOMISE_GAME);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		options.setNumPlayers(progress + 1);
		numPlayers.setText(Integer.toString(options.getNumPlayers()));		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		Object selected = parent.getItemAtPosition(pos);
		String tag = (String)parent.getTag();
		SpinnerTag t = SpinnerTag.valueOf(SpinnerTag.class, tag);
		switch(t.getCategory()) {
		case Mastermind:
			if (selected instanceof CardListAdapter.RandomiseThisAction) {
				options.setMastermind(-1);
				parent.setSelection(options.addRandomMastermind() + 1);
			} else {
				options.setMastermind((Mastermind)selected);
			}
			break;
		case Scheme:
			if (selected instanceof CardListAdapter.RandomiseThisAction) {
				options.setScheme(-1);
				parent.setSelection(options.addRandomScheme() + 1);
			} else {
				options.setScheme((Scheme)selected);
			}
			break;
		case Villain:
			if (selected instanceof CardListAdapter.RandomiseThisAction) {
				options.addRandomVillain();
			} else if (selected != null) {
				options.addVillain(pos - 1);
			}
			parent.setSelection(0);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
	
	private static enum SpinnerTag {
		Mastermind(SpinnerCategory.Mastermind),
		Scheme(SpinnerCategory.Scheme),
		Villain0(SpinnerCategory.Villain, 0),
		Villain1(SpinnerCategory.Villain, 1),
		Villain2(SpinnerCategory.Villain, 2),
		Villain3(SpinnerCategory.Villain, 3),
		Henchman0(SpinnerCategory.Henchman, 0),
		Henchman1(SpinnerCategory.Henchman, 1),
		Henchman2(SpinnerCategory.Henchman, 2),
		Hero0(SpinnerCategory.Hero, 0),
		Hero1(SpinnerCategory.Hero, 1),
		Hero2(SpinnerCategory.Hero, 2),
		Hero3(SpinnerCategory.Hero, 3),
		Hero4(SpinnerCategory.Hero, 4),
		Hero5(SpinnerCategory.Hero, 5);
		
		private final SpinnerCategory category;
		private final int index;
		SpinnerTag(SpinnerCategory cat) {
			this(cat, 0);
		}
		SpinnerTag(SpinnerCategory cat, int idx) {
			category = cat;
			index = idx;
		}
		
		public SpinnerCategory getCategory() {
			return category;
		}
		
		public int getIndex() {
			return index;
		}
	}
	
	private static enum SpinnerCategory {
		Mastermind,
		Scheme,
		Villain,
		Henchman,
		Hero,
	}
}

package net.threedoubloons.legendaryrandomiser;

import net.threedoubloons.legendaryrandomiser.data.CardListAdapter;
import net.threedoubloons.legendaryrandomiser.data.Mastermind;
import net.threedoubloons.legendaryrandomiser.data.Scheme;
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
		Object selected;
		String tag = (String)parent.getTag();
		SpinnerTag t = SpinnerTag.valueOf(SpinnerTag.class, tag);
		switch(t.getCategory()) {
		case Mastermind:
			selected = parent.getItemAtPosition(pos);
			if (selected instanceof CardListAdapter.RandomiseThisAction) {
				options.setMastermind(-1);
				parent.setSelection(options.addRandomMastermind() + 1);
			} else {
				options.setMastermind(pos - 1);
			}
		case Scheme:
			selected = parent.getItemAtPosition(pos);
			if (selected instanceof CardListAdapter.RandomiseThisAction) {
				options.setScheme(-1);
				parent.setSelection(options.addRandomScheme() + 1);
			} else {
				options.setScheme(pos - 1);
			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
	
	private static enum SpinnerTag {
		Mastermind(SpinnerCategory.Mastermind),
		Scheme(SpinnerCategory.Scheme),
		Villain0(SpinnerCategory.Villain),
		Villain1(SpinnerCategory.Villain),
		Villain2(SpinnerCategory.Villain),
		Villain3(SpinnerCategory.Villain),
		Henchman0(SpinnerCategory.Henchman),
		Henchman1(SpinnerCategory.Henchman),
		Henchman2(SpinnerCategory.Henchman),
		Hero0(SpinnerCategory.Hero),
		Hero1(SpinnerCategory.Hero),
		Hero2(SpinnerCategory.Hero),
		Hero3(SpinnerCategory.Hero),
		Hero4(SpinnerCategory.Hero),
		Hero5(SpinnerCategory.Hero);
		private final SpinnerCategory category;
		SpinnerTag(SpinnerCategory cat) {
			category = cat;
		}
		public SpinnerCategory getCategory() {
			return category;
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

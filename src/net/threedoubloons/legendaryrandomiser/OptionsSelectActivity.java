package net.threedoubloons.legendaryrandomiser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class OptionsSelectActivity extends Activity implements OnSeekBarChangeListener {
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
		
		numPlayers = (TextView)findViewById(R.id.numPlayers);
		numPlayers.setText(Integer.toString(options.getNumPlayers()));
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
}

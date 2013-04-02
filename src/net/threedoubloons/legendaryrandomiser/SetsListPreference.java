package net.threedoubloons.legendaryrandomiser;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SetsListPreference extends DialogPreference {
	private CharSequence[] entries;
	private Drawable[] entryIcons;
	private long alwaysOn = 0;
	private long value;
	private long oldValue;
	private SetEntry[] fullEntries;

	public SetsListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttributes(attrs);
		setDialogLayoutResource(R.layout.sets_list);
	}

	public SetsListPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttributes(attrs);
		setDialogLayoutResource(R.layout.sets_list);
	}

	private void initAttributes(AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SetsListPreference);
		
		entries = a.getTextArray(R.styleable.SetsListPreference_android_entries);
		String alwaysOn_str = a.getText(R.styleable.SetsListPreference_alwaysOn).toString();
		try {
			alwaysOn = Long.decode(alwaysOn_str).longValue();
		} catch (NumberFormatException e) {
			Log.e(getKey(), String.format("Couldn't parse the alwaysOn value: %s", alwaysOn_str));
			alwaysOn = 0L;
		}
		
		int icnsRes = a.getResourceId(R.styleable.SetsListPreference_entryIcons, 0);
		if (icnsRes != 0) {
			TypedArray icns = a.getResources().obtainTypedArray(icnsRes);
			entryIcons = new Drawable[icns.length()];
			for (int i = 0; i < icns.length(); ++i) {
				entryIcons[i] = icns.getDrawable(i);
			}
			
			icns.recycle();
		}
		a.recycle();
		
		fullEntries = new SetEntry[entries.length];
		for (int i = 0; i < entries.length; ++i) {
			fullEntries[i] = new SetEntry(entries[i], entryIcons[i]);
		}
	}
    
    /**
     * Sets the human-readable entries to be shown in the list. This will be
     * shown in subsequent dialogs.
     * <p>
     * Each entry must have a corresponding index in
     * {@link #setEntryValues(CharSequence[])}.
     * 
     * @param entries The entries.
     * @see #setEntryValues(CharSequence[])
     */
    public void setEntries(CharSequence[] entries) {
        this.entries = entries;
    }
    
    /**
     * @see #setEntries(CharSequence[])
     * @param entriesResId The entries array as a resource.
     */
    public void setEntries(int entriesResId) {
        setEntries(getContext().getResources().getTextArray(entriesResId));
    }
    
    /**
     * The list of entries to be shown in the list in subsequent dialogs.
     * 
     * @return The list as an array.
     */
    public CharSequence[] getEntries() {
        return entries;
    }

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value | alwaysOn;
		
		persistLong(this.value);
	}
    
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        final CharSequence defaultValue = a.getText(index);
        long result;
        try {
        	result = Long.decode(defaultValue.toString()).longValue();
		} catch (NumberFormatException e) {
			Log.e(getKey(), String.format("Couldn't parse the default value: %s", defaultValue));
			result = 0L;
		}
        return result;
    }
    
    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setValue(restoreValue ? getPersistedLong(0) : (Long)defaultValue);
    }

	@Override
	protected View onCreateDialogView() {
		View content = super.onCreateDialogView();
		final ListView l = (ListView)content;	
		l.setAdapter(new ArrayAdapter<SetEntry>(getContext(), R.layout.set_label, fullEntries) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v = super.getView(position, convertView, parent);
				l.setItemChecked(position, isChecked(position));
				TextView tv = (TextView)v;
				if (isAlwaysOn(position)) {
					v.setEnabled(false);
					v.setClickable(true);
				} else {
					v.setEnabled(true);
					v.setClickable(false);
				}
				tv.setCompoundDrawablesWithIntrinsicBounds(getItem(position).icon, null, null, null);
				return v;
			}
		});
		
		l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				boolean checked = l.isItemChecked(position);
				setChecked(position, checked);
			}
		});
		
		return content;
	}
    
    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        
        if (positiveResult && (oldValue != value)) {
            if (callChangeListener(value)) {
                setValue(value);
            }
        }
        
        oldValue = value;
    }

    
    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            // No need to save instance state
            return superState;
        }
        
        final SavedState myState = new SavedState(superState);
        myState.value = getValue();
        return myState;
    }
    
    private static class SavedState extends BaseSavedState {
        long value;
        
        public SavedState(Parcel source) {
            super(source);
            value = source.readLong();
        }
        
        public SavedState(Parcelable superState) {
            super(superState);
        }
        
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeLong(value);
        }
    }
    
    private void setChecked(int position, boolean checked) {
    	if (checked) {
    		value = value | (1L << position);
    	} else {
    		value = value & ~(1L << position);
    	}
    }
    
    public boolean isChecked(int position) {
    	return (value & (1L << position)) != 0;
    }
    
    public boolean isAlwaysOn(int position) {
    	return (alwaysOn & (1L << position)) != 0;
    }
    
    private static class SetEntry {
    	public CharSequence name;
    	public Drawable icon;
    	
    	public SetEntry(CharSequence name, Drawable icon) {
    		this.name = name;
    		this.icon = icon;
    	}

		@Override
		public String toString() {
			return name.toString();
		}
    }
}

package com.example.panelkonfiguracji;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView settingsListView;
    private TextView editingLabelTextView;
    private SeekBar valueSeekBar;
    private TextView seekBarValueTextView;

    private ArrayList<String> settingNames = new ArrayList<>();
    private ArrayList<Integer> settingValues = new ArrayList<>();
    private ArrayList<String> settingUnits = new ArrayList<>();
    private ArrayList<String> displayItemsForListView = new ArrayList<>();

    private int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        settingsListView = findViewById(R.id.settingsListView);
        editingLabelTextView = findViewById(R.id.editingLabelTextView);
        valueSeekBar = findViewById(R.id.valueSeekBar);
        seekBarValueTextView = findViewById(R.id.seekBarValueTextView);


        initializeSettingsData();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.list_item_setting,
                R.id.itemTextView,
                displayItemsForListView
        );
        settingsListView.setAdapter(adapter);


        settingsListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedItemPosition = position;
            updateEditingLabel();
            updateSeekBar();
        });


        valueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && selectedItemPosition != -1) {
                    seekBarValueTextView.setText("Wartość: " + progress + settingUnits.get(selectedItemPosition));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (selectedItemPosition != -1) {
                    int newValue = seekBar.getProgress();
                    settingValues.set(selectedItemPosition, newValue);
                    updateListViewItem(selectedItemPosition);
                }
            }
        });
    }

    private void initializeSettingsData() {

        settingNames.add("Jasność Ekranu");
        settingNames.add("Głośność Dźwięków");
        settingNames.add("Czas Automatycznej Blokady");
        settingNames.add("Czułość Dotyku");
        settingNames.add("Rozmiar Tekstu");

        settingValues.add(50);
        settingValues.add(80);
        settingValues.add(30);
        settingValues.add(60);
        settingValues.add(40);

        settingUnits.add("%");
        settingUnits.add("%");
        settingUnits.add("s");
        settingUnits.add("%");
        settingUnits.add("%");


        for (int i = 0; i < settingNames.size(); i++) {
            displayItemsForListView.add(settingNames.get(i) + ": " +
                    settingValues.get(i) +
                    settingUnits.get(i));
        }
    }

    private void updateEditingLabel() {
        if (selectedItemPosition != -1) {
            editingLabelTextView.setText("Edytujesz: " + settingNames.get(selectedItemPosition));
            valueSeekBar.setEnabled(true);
        }
    }

    private void updateSeekBar() {
        if (selectedItemPosition != -1) {
            valueSeekBar.setProgress(settingValues.get(selectedItemPosition));
            seekBarValueTextView.setText("Wartość: " +
                    settingValues.get(selectedItemPosition) +
                    settingUnits.get(selectedItemPosition));
        }
    }

    private void updateListViewItem(int position) {
        displayItemsForListView.set(position,
                settingNames.get(position) + ": " +
                        settingValues.get(position) +
                        settingUnits.get(position));

        ((ArrayAdapter)settingsListView.getAdapter()).notifyDataSetChanged();
    }
}
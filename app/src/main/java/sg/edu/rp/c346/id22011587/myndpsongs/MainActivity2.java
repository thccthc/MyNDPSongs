package sg.edu.rp.c346.id22011587.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView lv;
    DBHelper db;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    Button btnShow;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = findViewById(R.id.lv);
        btnShow = findViewById(R.id.btnShow);
        spinner = findViewById(R.id.spinner);

        db = new DBHelper(MainActivity2.this);

        // Retrieve all songs from the database
        List<Song> songList = db.getAllSongs();
        db.close();

        aa = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(aa);

        // Set item click listener for the ListView
        lv.setOnItemClickListener((adapterView, view, position, id) -> {
            // Retrieve the clicked song from the adapter
            Song clickedSong = aa.getItem(position);
            // Launch the third Activity and pass the clicked song to it
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        });

        btnShow.setOnClickListener(v -> {
            // Retrieve only songs with 5 stars from the database
            List<Song> fiveStarSongs = db.getSongsWithRating(5);

            // Update the adapter with the filtered song list
            aa.clear();
            aa.addAll(fiveStarSongs);
            aa.notifyDataSetChanged();
        });
    }
}
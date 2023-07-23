package sg.edu.rp.c346.id22011587.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {

    EditText etTitle, etSingers, etYear, etStars;
    Button btnUpdate, btnDelete;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Retrieve the passed song information
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("song")) {
            Song song = (Song) intent.getSerializableExtra("song");

            // Populate the UI elements with the song details
            etTitle = findViewById(R.id.editTextTitle);
            etSingers = findViewById(R.id.editTextSingers);
            etYear = findViewById(R.id.editTextYear);
            etStars = findViewById(R.id.editTextStars);
            btnUpdate = findViewById(R.id.btnInsert);
            btnDelete = findViewById(R.id.btnShowList);


            etTitle.setText(song.getTitle());
            etSingers.setText(song.getSingers());
            etYear.setText(song.getYear());
            etStars.setText(song.getStars());
        }

//        btnUpdate.setOnClickListener(v -> {
//            // Save the updated song details to the database
//            String newTitle = etTitle.getText().toString();
//            String singers = etSingers.getText().toString();
//            String year = etYear.getText().toString();
//            String stars = etStars.getText().toString();
//
//            // Update the song in the database
//            DBHelper dbHelper = new DBHelper(MainActivity3.this);
//            Song updatedSong = new Song(song.get_Id(), newTitle, singers, year, stars);
//            dbHelper.updateSong(updatedSong);
//            dbHelper.close();
//
//            // Show a success message to the user
//            Toast.makeText(MainActivity3.this, "Song details updated successfully", Toast.LENGTH_SHORT).show();
//            finish(); // Close MainActivity3 and go back to MainActivity2
//        });

//        btnDelete.setOnClickListener(v -> {
//            // Delete the song from the database using the DBHelper class
//            DBHelper dbHelper = new DBHelper(MainActivity3.this);
//            dbHelper.deleteSong(song.get_Id());
//            dbHelper.close();
//
//            // Show a success message to the user
//            Toast.makeText(MainActivity3.this, "Song deleted successfully", Toast.LENGTH_SHORT).show();
//            finish(); // Close MainActivity3 and go back to MainActivity2
//        });
    }
}
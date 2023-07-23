package sg.edu.rp.c346.id22011587.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear, etStars;
    Button btnInsert, btnShowList;
    ListView lv;

    ArrayList<Song> al;
    //ArrayAdapter<Song> aa;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize views
        etTitle = findViewById(R.id.editTextTitle);
        etSingers = findViewById(R.id.editTextSingers);
        etYear = findViewById(R.id.editTextYear);
        etStars = findViewById(R.id.editTextStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Song>();
//        aa = new ArrayAdapter<Song>(this,
//                android.R.layout.simple_list_item_1, al);

        adapter = new CustomAdapter(this, R.layout.row, al);

//        lv.setAdapter(aa);

        lv.setAdapter(adapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the song details from the user input
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                String year = etYear.getText().toString();
                String stars = etStars.getText().toString();

                // Create an instance of the DBHelper class
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                // Insert the song record into the database
                dbHelper.insertSong(title, singers, year, stars);
                dbHelper.close();

                // Show a success message to the user
                Toast.makeText(MainActivity.this, "Song record inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for Show List button
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }
}
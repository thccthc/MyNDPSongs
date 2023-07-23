package sg.edu.rp.c346.id22011587.myndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Song {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_Id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    public String toString() { return "Title: " + title + '\n' + "Singers: " + singers + '\n' + "Year: " + year + '\n' + "Stars: " + stars;}

    public static class CustomAdapter extends ArrayAdapter {

        Context parent_context;
        int layout_id;
        ArrayList<Song> listOfSongs;

        public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
            super(context, resource, objects);

            parent_context = context;
            layout_id = resource;
            listOfSongs = objects;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Obtain the LayoutInflater object
            LayoutInflater inflater = (LayoutInflater)
                    parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // "Inflate" the View for each row
            View rowView = inflater.inflate(layout_id, parent, false);

            // Obtain the UI components and do the necessary binding
            TextView tvTitle = rowView.findViewById(R.id.textView5);
            TextView tvSingers = rowView.findViewById(R.id.textView6);
            TextView tvYear = rowView.findViewById(R.id.textView7);
            TextView tvStars = rowView.findViewById(R.id.textView8);

            // Obtain the Android Version information based on the position
            Song currentVersion = listOfSongs.get(position);

            // Set values to the TextView to display the corresponding information
            tvTitle.setText(currentVersion.getTitle());
            tvSingers.setText(currentVersion.getSingers());
            tvYear.setText(currentVersion.getYear());
            tvStars.setText(currentVersion.getStars());

            return rowView;
        }

    }
}

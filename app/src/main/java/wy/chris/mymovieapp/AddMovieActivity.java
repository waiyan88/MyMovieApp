package wy.chris.mymovieapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class AddMovieActivity extends AppCompatActivity {

    FirebaseConnect firebaseConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        // Add Movie Categories
        Spinner seriescategory=findViewById(R.id.movie_category);
        List<String> list = new ArrayList<>();
        firebaseConnect=new FirebaseConnect();
        firebaseConnect.getAllCategory();
        for(int i=0;i<FirebaseConnect.categoryModels.size();i++)
        {
            list.add(FirebaseConnect.categoryModels.get(i).categoryname);

        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seriescategory.setAdapter(dataAdapter);


        // Add Series Name

        Spinner seriesname=findViewById(R.id.movie_series_name);
        List<String> list1 = new ArrayList<>();
        firebaseConnect=new FirebaseConnect();
        firebaseConnect.getAllSeries();
        for(int i=0;i<FirebaseConnect.seriesModels.size();i++)
        {
            list1.add(FirebaseConnect.seriesModels.get(i).seriesName);

        }
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seriesname.setAdapter(dataAdapter1);

    }

    public void saveMovie(View view) {
        EditText edtmoviename=findViewById(R.id.movie_name);

        EditText edtimagelink=findViewById(R.id.movie_image_link);
        EditText edtmovievideolink=findViewById(R.id.movie_video_link);
        try {
            Spinner moviecategory = findViewById(R.id.movie_category);
            CategoryModel categoryModel = FirebaseConnect.categoryModels.get(moviecategory.getSelectedItemPosition());
            Spinner serieName = findViewById(R.id.movie_series_name);
            SeriesModel seriesModel = FirebaseConnect.seriesModels.get(serieName.getSelectedItemPosition());

            MovieModel movieModel = new MovieModel();
            movieModel.movieName = edtmoviename.getText().toString().trim();
            movieModel.movieImageLink = edtimagelink.getText().toString().trim();
            movieModel.movieVideoLink = edtmovievideolink.getText().toString().trim();
            movieModel.movieCategory = categoryModel.categoryname;
            movieModel.SeriesName = seriesModel.seriesName;

            if (!(movieModel.movieName.equals("") || movieModel.movieName == null)) {
                if(!(movieModel.movieImageLink.equals("") || movieModel.movieImageLink == null))
                {
                    if(!(movieModel.movieVideoLink.equals("") || movieModel.movieVideoLink == null))
                    {
                        firebaseConnect.saveMovie(movieModel);
                        edtmoviename.setText("");
                        edtimagelink.setText("");
                        edtmovievideolink.setText("");
                        Toasty.success(getApplicationContext(),"Movie Save Successfully.",Toasty.LENGTH_LONG).show();
                    }
                    else
                    {
                        new AlertDialog.Builder(this)
                                .setTitle("Cannot Save Movie")
                                .setMessage("Please Insert  Movie Video Link!")

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }
                else
                {
                    new AlertDialog.Builder(this)
                            .setTitle("Cannot Save  Movie")
                            .setMessage("Please Insert  Movie Image Link!")

                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
            else
            {
                new AlertDialog.Builder(this)
                        .setTitle("Cannot Save  Movie")
                        .setMessage("Please Insert  Movie Name")

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }



        }
        catch (Exception ex)
        {

            new AlertDialog.Builder(this)
                    .setTitle("Cannot Save Series")
                    .setMessage("Please Fill  Data!")

                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

    }

    public void btnCancelMovie(View view) {
        EditText edtmoviename=findViewById(R.id.movie_name);

        EditText edtimagelink=findViewById(R.id.movie_image_link);
        EditText edtmovievideolink=findViewById(R.id.movie_video_link);
        edtmoviename.setText("");
        edtimagelink.setText("");
        edtmovievideolink.setText("");
    }
}

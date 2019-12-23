package wy.chris.mymovieapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieNavFragment extends Fragment {


    public MovieNavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_movie_nav, container, false);
        FloatingActionButton floatingActionButton=view.findViewById(R.id.addmovie);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),AddMovieActivity.class);
                startActivity(intent);
            }
        });
        return  view;
    }

    private  class MovieAdapter extends BaseAdapter
    {
        ArrayList<MovieModel> movieModels=new ArrayList<MovieModel>();

        public MovieAdapter(ArrayList<MovieModel> movieModels) {
            this.movieModels = movieModels;
        }

        @Override
        public int getCount() {
            return movieModels.size();
        }

        @Override
        public Object getItem(int i) {
            return movieModels.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater=getLayoutInflater();
            View view1=inflater.inflate(R.layout.movielayout,null);
            MovieModel movieModel=movieModels.get(i);
            TextView txtsrno=view1.findViewById(R.id.edText);
            TextView movieName=view1.findViewById(R.id.edLstview);
            txtsrno.setText(String.valueOf(i+1));
            movieName.setText(movieModel.movieName);
            return view1;
        }
    }

}

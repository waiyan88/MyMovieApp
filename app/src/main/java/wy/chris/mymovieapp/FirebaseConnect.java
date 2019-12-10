package wy.chris.mymovieapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirebaseConnect {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference movieref=db.collection("movies");
    CollectionReference series=db.collection("series");
    CollectionReference category=db.collection("categories");

    public void saveCategory(CategoryModel model)
    {

        category.add(model)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG","Category Save Success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAE","Category Fail");
            }
        });



    }
    public void saveSeries(SeriesModel model)
    {

        series.add(model)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG","Series Save Success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAE","Series Save  Fail");
            }
        });



    }

    public void saveMovie(MovieModel model)
    {

        movieref.add(model)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG","Movie Save Success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAE","Movie Save  Fail");
            }
        });



    }

    public static ArrayList<CategoryModel> categoryModels=new ArrayList<CategoryModel>();
    public void getAllCategory()
    {
        category.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                categoryModels.clear();;
                for(QueryDocumentSnapshot snapshot:queryDocumentSnapshots)
                {
                    CategoryModel categoryModel=snapshot.toObject(CategoryModel.class);
                    categoryModels.add(categoryModel);
                }

            }
        });
    }

    public static ArrayList<SeriesModel> seriesModels=new ArrayList<SeriesModel>();
    public void getAllSeries()
    {
        series.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                seriesModels.clear();;
                for(QueryDocumentSnapshot snapshot:queryDocumentSnapshots)
                {
                   SeriesModel seriesModel=snapshot.toObject(SeriesModel.class);
                   seriesModels.add(seriesModel);
                }

            }
        });
    }





}

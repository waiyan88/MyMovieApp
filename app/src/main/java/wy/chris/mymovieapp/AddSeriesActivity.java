package wy.chris.mymovieapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class AddSeriesActivity extends AppCompatActivity {
    FirebaseConnect firebaseConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        Spinner seriescategory=findViewById(R.id.seriescategory);
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
    }

    public void btnsaveSeries(View view) {

        EditText edtseriesname=findViewById(R.id.seriesname);
        EditText edtseriesimage=findViewById(R.id.seriesimagelink);
        EditText edtserisvideolink=findViewById(R.id.seriesvideolink);
        Spinner seriescategory=findViewById(R.id.seriescategory);
        try {
            CategoryModel m = FirebaseConnect.categoryModels.get(seriescategory.getSelectedItemPosition());
            SeriesModel seriesModel = new SeriesModel();
            seriesModel.seriesName = edtseriesname.getText().toString().trim();
            seriesModel.seriesImageLink = edtseriesimage.getText().toString().trim();
            seriesModel.seriesVideoLink = edtserisvideolink.getText().toString().trim();
            seriesModel.seriesCategory = m.categoryname;


            if (!(seriesModel.seriesName.equals("") || seriesModel.seriesName == null)) {
                if(!(seriesModel.seriesImageLink.equals("") || seriesModel.seriesImageLink == null))
                {
                    if(!(seriesModel.seriesVideoLink.equals("") || seriesModel.seriesVideoLink == null))
                    {
                        firebaseConnect.saveSeries(seriesModel);
                        edtseriesimage.setText("");
                        edtseriesname.setText("");
                        edtserisvideolink.setText("");
                        Toasty.success(getApplicationContext(),"Series Save Successly",Toasty.LENGTH_LONG).show();
                    }
                    else
                    {
                        new AlertDialog.Builder(this)
                                .setTitle("Cannot Save Series")
                                .setMessage("Please Insert Series Video Link!")

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
                            .setTitle("Cannot Save Series")
                            .setMessage("Please Insert Series Image Link!")

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
                        .setTitle("Cannot Save Series")
                        .setMessage("Please Insert Series Name")

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

    public void btnCancelSeries(View view) {
        EditText edtseriesname=findViewById(R.id.seriesname);
        EditText edtseriesimage=findViewById(R.id.seriesimagelink);
        EditText edtserisvideolink=findViewById(R.id.seriesvideolink);
        edtseriesimage.setText("");
        edtseriesname.setText("");
        edtserisvideolink.setText("");
    }
}

package wy.chris.mymovieapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import es.dmoral.toasty.Toasty;

public class AddCategoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
    }

    public void btnsavecategory(View view) {
        EditText edtcastegoryname=findViewById(R.id.categoryname);
        CategoryModel categoryModel=new CategoryModel();
        categoryModel.setCategoryname(edtcastegoryname.getText().toString());

        try {

            if(categoryModel.categoryname.equals("") || categoryModel.categoryname==null)
            {
                new AlertDialog.Builder(this)
                        .setTitle("Cannot Save  Category")
                        .setMessage("Please Insert  Category Name")

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else {
                FirebaseConnect firebaseConnect = new FirebaseConnect();
                firebaseConnect.saveCategory(categoryModel);
                edtcastegoryname.setText("");
                Toasty.success(getApplicationContext(),"Category Save Successfully",Toasty.LENGTH_LONG).show();
            }
        }
        catch (Exception ex)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Cannot Save  Category")
                    .setMessage("Please Insert  Data")

                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }

    public void btnCancelCategory(View view) {
        EditText edtcastegoryname=findViewById(R.id.categoryname);
        edtcastegoryname.setText("");
    }
}

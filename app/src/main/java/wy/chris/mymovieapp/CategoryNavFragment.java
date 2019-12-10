package wy.chris.mymovieapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryNavFragment extends Fragment {


    public CategoryNavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category_nav, container, false);
        ListView lstcategories=view.findViewById(R.id.lstcategories);
        FirebaseConnect firebaseConnect=new FirebaseConnect();
        firebaseConnect.getAllCategory();;
        CategoryAdapter categoryAdapter=new CategoryAdapter(FirebaseConnect.categoryModels);
        lstcategories.setAdapter(categoryAdapter);

        FloatingActionButton floatingActionButton=view.findViewById(R.id.addcategory);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),AddCategoryActivity.class);
                startActivity(intent);
            }
        });

        return  view;
    }


    private  class CategoryAdapter extends BaseAdapter
    {
        ArrayList<CategoryModel> categoryModels=new ArrayList<CategoryModel>();

        public CategoryAdapter(ArrayList<CategoryModel> categoryModels) {
            this.categoryModels = categoryModels;
        }

        @Override
        public int getCount() {
            return categoryModels.size();
        }

        @Override
        public Object getItem(int i) {
            return categoryModels.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater=getLayoutInflater();
            View view1=inflater.inflate(R.layout.categorylayout,null);
            CategoryModel categoryModel=categoryModels.get(i);
            TextView txtsrno=view1.findViewById(R.id.categorysr);
            TextView categoryname=view1.findViewById(R.id.categorylistname);
            txtsrno.setText(String.valueOf(i+1));
            categoryname.setText(categoryModel.categoryname);
            return view1;
        }
    }

}

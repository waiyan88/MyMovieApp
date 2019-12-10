package wy.chris.mymovieapp;

public class CategoryModel {
    String categoryname;

    public CategoryModel(String categoryname) {
        this.categoryname = categoryname;
    }

    public CategoryModel() {
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}

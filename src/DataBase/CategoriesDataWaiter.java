package DataBase;

import BackOffice.CoffeShop.Category;
import DataBase.Singleton.DataWaiter;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDataWaiter {

    public List<Category> GetAllCategories() {
        List<Category> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try {
            results = DataWaiter.GetInstance().GetData("SELECT Category_Id, Store_ID, CatName FROM Categories;"); // Fetch category data using the specific method for categories
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;
                Category category = new Category();
                category.setCategory_Id(Integer.parseInt(cols.get(0).toString()));
                category.setStore_ID(Integer.parseInt(cols.get(1).toString()));
                category.setCatName(cols.get(2).toString());
                list.add(category);
            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving categories - Waiter");
        }
        return list;
    }


    public List<Category> GetAllCategories(String searchParam) {
        List<Category> list = new ArrayList<>();
        List<Object> results = new ArrayList<>();
        try {
            results = DataWaiter.GetInstance().GetData("SELECT Category_Id, Store_ID, CatName FROM Categories WHERE  CatName like '%" + searchParam + "%';"); // Fetch category data using the specific method for a single category
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;
                Category category = new Category();
                category.setCategory_Id(Integer.parseInt(cols.get(0).toString()));
                category.setStore_ID(Integer.parseInt(cols.get(1).toString()));
                category.setCatName(cols.get(2).toString());
                list.add(category);
            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving all categories - Waiter");
        }
        return list;
    }

    public Category GetOneCategory(String searchParam) {
        Category category = new Category();
        List<Object> results = new ArrayList<>();
        try {
            results = DataWaiter.GetInstance().GetData("SELECT Category_Id, Store_ID, CatName FROM Categories WHERE Category_Id = "+ searchParam +";"); // Fetch category data using the specific method for a single category
            for (Object row : results) {
                List<Object> cols = (List<Object>) row;
                category.setCategory_Id(Integer.parseInt(cols.get(0).toString()));
                category.setStore_ID(Integer.parseInt(cols.get(1).toString()));
                category.setCatName(cols.get(2).toString());
            }
        } catch (Exception ex) {
            System.out.println("There was an error retrieving a specific category - Waiter");
        }
        return category;
    }
}

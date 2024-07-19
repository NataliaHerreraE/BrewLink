package BackOffice.CoffeShop;

//import DataBase.CoffeShopWaiter;
import DataBase.CategoriesDataWaiter;
import DataBase.Singleton.DataWaiter;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int store_ID;
    private int category_Id;
    private String catName;

    public int getStore_ID() {
        return store_ID;
    }

    public void setStore_ID(int store_ID) {
        this.store_ID = store_ID;
    }

    public int getCategory_Id() {
        return category_Id;
    }

    public void setCategory_Id(int category_Id) {
        this.category_Id = category_Id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<Category> GetAllCategories() {
        List<Category> list = new ArrayList<>();
        try {
            CategoriesDataWaiter waiter = new CategoriesDataWaiter();
            list = waiter.GetAllCategories();
        } catch(Exception ex) {
            System.out.println("Error retrieving all categories");
        }
        return list;
    }

    public void DisplayAllCategories() {
        try {
            List<Category> list = GetAllCategories();

            System.out.println("Categories:");
            for (Category cat : list) {
                System.out.println("Category ID: " + cat.getCategory_Id());
                System.out.println("Store ID: " + cat.getStore_ID());
                System.out.println("Category Name: " + cat.getCatName());
                System.out.println("-----------------------");
            }

        } catch(Exception ex) {
            System.out.println("Error displaying all categories");
        }
    }

    public static List<Category> GetAllCategories(String searchParam) {
        List<Category> categoryList = new ArrayList<>();
        try {
            CategoriesDataWaiter categoriesDataWaiter = new CategoriesDataWaiter();
            categoryList = categoriesDataWaiter.GetAllCategories(searchParam);
            }
        catch (Exception ex) {
            System.out.println("There was an error retrieving category - Category");
        }
        return categoryList;
    }

    public static Category GetCategory(String catId) {
        Category category = new Category();
        try {
           category = new CategoriesDataWaiter().GetOneCategory(catId);
        } catch (Exception ex) {
            System.out.println("There was an error retrieving category - Category");
        }
        return category;
    }

}

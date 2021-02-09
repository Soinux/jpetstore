package service;

import domain.Category;
import domain.Item;
import domain.Product;
import persistence.CategoryDAO;
import persistence.Impl.CategoryDAOImpl;
import persistence.Impl.ItemDAOImpl;
import persistence.Impl.ProductDAOImpl;
import persistence.ItemDAO;
import persistence.ProductDAO;

import java.util.List;

public class CatalogService {
    //业务逻辑层调用数据访问层
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private ItemDAO itemDAO;


    public CatalogService(){
        categoryDAO = new CategoryDAOImpl();
        productDAO = new ProductDAOImpl();
        itemDAO = new ItemDAOImpl();
    }

    public List<Category> getCategoryList() {

        return categoryDAO.getCategoryList();
    }

    public Category getCategory(String categoryId) {

        return categoryDAO.getCategory(categoryId);
    }

    public Product getProduct(String productId) {

        return productDAO.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategory(categoryId);
    }

    /* TODO enable using more than one keyword*/
    public List<Product> searchProductList(String keyword) {
        return productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }
}

package persistence.Impl;

import domain.Product;
import persistence.DBUtil;
import persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    //查询语句
    private static final String GET_PRODUCT_LIST_BY_CATEGORY = "SELECT productid, name, descn as description, " +
            "category as categoryId FROM product WHERE category = ?";
    private static final String GET_PRODUCT = "SELECT productid, name, descn as description, category as categoryId " +
            "FROM product WHERE productid = ?";
    private static final String SEARCH_PRODUCT_LIST = "select productid, name, descn as description, " +
            "CATEGORY as categoryId from product WHERE lower(name) like ?";

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_PRODUCT_LIST_BY_CATEGORY);
            pStatement.setString(1, categoryId);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));

                products.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_PRODUCT);
            pStatement.setString(1, productId);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(SEARCH_PRODUCT_LIST);
            pStatement.setString(1, keywords);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));

                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}

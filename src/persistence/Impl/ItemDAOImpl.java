package persistence.Impl;

import domain.Item;
import domain.Product;
import persistence.DBUtil;
import persistence.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {

    private static final String UPDATE_INVENTORY_QUANTITY = "UPDATE inventory SET qty = qty - ? " +
            "WHERE itemid = ?";
    private static final String GET_INVENTORY_QUANTITY = "SELECT qty AS value FROM inventory WHERE itemid = ?";
    private static final String GET_ITEM_LIST_BY_PRODUCT = "SELECT I.itemid, listprice, unitcost, " +
            "supplier AS supplierId, I.productid AS \"product.productId\", name AS \"product.name\", " +
            " descn AS \"product.description\", category AS \"product.categoryId\", status,  attr1 AS attribute1, " +
            "attr2 AS attribute2, attr3 AS attribute3, attr4 AS attribute4, attr5 AS attribute5 FROM item I, product P " +
            "WHERE P.productid = I.productid AND I.productid = ?";
    private static final String GET_ITEM = "select I.itemid, listprice, unitcost, supplier AS supplierId, " +
            "I.productid AS \"product.productId\", name AS \"product.name\", descn AS \"product.description\", " +
            "category AS \"product.categoryId\", status, attr1 AS attribute1, attr2 AS attribute2, " +
            "attr3 AS attribute3, attr4 AS attribute4, attr5 AS attribute5, qty AS quantity from item I, " +
            "inventory V, product P where P.productid = I.productid and I.itemid = V.itemid and I.itemid = ?";

    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY);
            String itemId = param.keySet().iterator().next();
            Integer increment = (Integer)param.get(itemId);
            pStatement.setInt(1, increment.intValue());
            pStatement.setString(2, itemId);
            pStatement.executeUpdate();
            DBUtil.closePreparedStatent(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int result = -1;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY);
            pStatement.setString(1, itemId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                result = resultSet.getInt(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<Item>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT);
            pStatement.setString(1, productId);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Item item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                itemList.add(item);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item =null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_ITEM);
            pStatement.setString(1, itemId);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }
}

package persistence.Impl;

import domain.Account;
import persistence.AccountDAO;
import persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOImpl implements AccountDAO {

    private static final String GET_ACCOUNT_BY_USERNAME = "SELECT" +
            "          signon.username," +
            "          account.email," +
            "          account.firstname," +
            "          account.lastname," +
            "          account.status," +
            "          account.addr1 AS address1," +
            "          account.addr2 AS address2," +
            "          account.city," +
            "          account.state," +
            "          account.zip," +
            "          account.country," +
            "          account.phone," +
            "          profile.langpref AS languagePreference," +
            "          profile.favcategory AS favouriteCategoryId," +
            "          profile.mylistopt AS listOption," +
            "          profile.banneropt AS bannerOption," +
            "          bannerdata.bannername" +
            "    FROM account, profile, signon, bannerdata" +
            "    WHERE account.userid = ?" +
            "      AND signon.username = account.userid" +
            "      AND profile.userid = account.userid" +
            "      AND profile.favcategory = bannerdata.favcategory";

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT \n" +
            "signon.username, account.email, account.firstname, account.lastname, \n" +
            "account.status, account.addr1 AS address1, account.addr2 AS address2, account.city,  account.state, account.zip, account.country, account.phone, \n" +
            "profile.langpref AS languagePreference, profile.favcategory AS favouriteCategoryId, profile.mylistopt AS listOption, profile.banneropt AS bannerOption, \n" +
            "bannerdata.bannername \n" +
            "FROM account, profile, signon, bannerdata \n" +
            "WHERE account.userid = ?\n" +
            "AND signon.password = ?\n" +
            "AND signon.username = account.userid \n" +
            "AND profile.userid = account.userid \n" +
            "AND profile.favcategory = bannerdata.favcategory";

    private static final String INSERT_ACCOUNT = "    INSERT INTO account" +
            "      (email, firstname, lastname, status, addr1, addr2, city, state, zip, country, phone, userid)" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
    private static final String INSERT_PROFILE = "   INSERT INTO profile (langpref, favcategory, userid) VALUES (?, ?, ?)";
    private static final String INSERT_SIGNON = "INSERT INTO signon (password,username) VALUES (?, ?)";
    private static final String UPDATE_ACCOUNT = "UPDATE account SET" +
            "      email = ?," +
            "      firstname = ?," +
            "      lastname = ?," +
            "      status = ?," +
            "      addr1 = ?," +
            "      addr2 = ?," +
            "      city = ?," +
            "      state = ?," +
            "      zip = ?," +
            "      country = ?," +
            "      phone = ?" +
            "    WHERE userid = ?";
    private static final String UPDATE_PROFILE = "UPDATE profile SET langpref = ?, favcategory = ? WHERE userid = ?";
    private static final String UPDATE_SIGNON = "UPDATE signon SET password = ? WHERE username = ?";

    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account account1 = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                account1 = new Account();
                account1.setUsername(resultSet.getString(1));
                account1.setEmail(resultSet.getString(2));
                account1.setFirstName(resultSet.getString(3));
                account1.setLastName(resultSet.getString(4));
                account1.setStatus(resultSet.getString(5));
                account1.setAddress1(resultSet.getString(6));
                account1.setAddress2(resultSet.getString(7));
                account1.setCity(resultSet.getString(8));
                account1.setState(resultSet.getString(9));
                account1.setZip(resultSet.getString(10));
                account1.setCountry(resultSet.getString(11));
                account1.setPhone(resultSet.getString(12));
                account1.setLanguagePreference(resultSet.getString(13));
                account1.setFavouriteCategoryId(resultSet.getString(14));
                account1.setListOption(resultSet.getBoolean(15));
                account1.setBannerOption(resultSet.getBoolean(16));
                account1.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account1;
    }

    @Override
    public void insertAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SIGNON);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SIGNON);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

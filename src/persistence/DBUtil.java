package persistence;

import java.sql.*;

public class DBUtil {
    private static final String driveString = "com.mysql.cj.jdbc.Driver";
    private static final String connectionString = "jdbc:mysql://192.168.160.135/jpetstore?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
    private static final String username = "jpetstore";
    private static final String password = "123456";

    //获取连接
    public static Connection getConnection() throws Exception {
        Connection connection = null;

        try {
            Class.forName(driveString);
            connection = DriverManager.getConnection(connectionString, username, password);
        }catch (Exception e){
            throw e;
        }

        return connection;
    }

    public static void closeStatement(Statement statement) throws Exception {
        statement.close();
    }

    public static void closePreparedStatent(PreparedStatement pStatement) throws Exception{
        pStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws Exception{
        resultSet.close();
    }

    //关闭连接
    public static void closeConnection(Connection connection) throws Exception {
        connection.close();
    }

}

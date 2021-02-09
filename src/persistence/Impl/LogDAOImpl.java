package persistence.Impl;

import persistence.DBUtil;
import persistence.LogDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LogDAOImpl implements LogDAO {

    private static final String INSERT_LOG = "insert into log (userid, loginfo) vALUES (?, ?)";

    @Override
    public void insertLog(String username, String logInfo) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOG);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, logInfo);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

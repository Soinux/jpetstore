package persistence.Impl;

import domain.Sequence;
import persistence.DBUtil;
import persistence.SequenceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDAOImpl implements SequenceDAO {
    private static final String GET_SEQUENCE = "SELECT name, nextid FROM sequence WHERE name = ?";
    private static final String UPDATE_SEQUENCE = "UPDATE sequence SET nextid = ? WHERE name = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence sequence1 = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SEQUENCE);
            preparedStatement.setString(1, sequence.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                sequence1 = new Sequence();
                sequence1.setName(resultSet.getString(1));
                sequence1.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sequence1;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SEQUENCE);
            preparedStatement.setInt(1, sequence.getNextId());
            preparedStatement.setString(2, sequence.getName());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

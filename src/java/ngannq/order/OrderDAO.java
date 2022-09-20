/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import ngannq.utils.DBHelper;

/**
 *
 * @author User
 */
public class OrderDAO implements Serializable {

    public int createOrder(Date date, double total, String username)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet rs = null;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Insert into [Order] "
                        + "(date, total, username) "
                        + "Values (?, ?, ?)";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setDate(1, date);
                statement.setDouble(2, total);
                statement.setString(3, username);
                //4. Excute Query
                int affectedRows = statement.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    String sql2 = "Select top 1 id "
                            + "From [Order]"
                            + "Order by id desc";
                    statement2 = connection.prepareStatement(sql2);
                    rs = statement2.executeQuery();
                    if (rs.next()) {
                        return rs.getInt("id");
                    }
                }
            }//end if connection is not null
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement2 != null) {
                statement2.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return 0;
    }

    public boolean updateTotalOrder(int orderId, double total)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Update [Order] "
                        + "Set total = ? "
                        + "Where id = ?";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setDouble(1, total);
                statement.setInt(2, orderId);
                //4. Excute Query
                int affectedRows = statement.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    result = true;
                }
            }//end if connection is not null
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public boolean removeOrder(int orderId)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Delete From [Order] "
                        + "Where id = ?";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setInt(1, orderId);
                //4. Excute Query
                int affectedRows = statement.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    result = true;
                }
            }//end if connection is not null
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
    public boolean removeOrderByAcc(String username)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Delete From [Order] "
                        + "Where username = ?";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                //4. Excute Query
                int affectedRows = statement.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    result = true;
                }
            }//end if connection is not null
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
    public ArrayList<Integer> getOrderIDByAcc(String username)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Integer> result = new ArrayList<>();
        try {
            //1. Make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL string
                String sql = "Select id "
                        + "From [Order] "
                        + "Where username = ?";
                //3. Create statement Object
                statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                //4. Execute Query
                rs = statement.executeQuery();
                //5. Process result
                while (rs.next()) {
                    result.add(rs.getInt("id"));
                }
            }//end if connection is not null
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
}

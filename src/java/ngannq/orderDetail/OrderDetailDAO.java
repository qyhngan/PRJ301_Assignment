/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import ngannq.utils.DBHelper;

/**
 *
 * @author User
 */
public class OrderDetailDAO implements Serializable{
    public double createOrderDetail(String productId, int quantity, 
            double price, int orderID)
        throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        double total = 0;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Insert into OrderDetail "
                        + "(id, quantity, price, total, orderID) "
                        + "Values (?, ?, ?, ?, ?)";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setString(1, productId);
                statement.setInt(2, quantity);
                statement.setDouble(3, price);
                statement.setDouble(4, price * quantity);
                statement.setInt(5, orderID);
                
                //4. Excute Query
                int affectedRows = statement.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    total = price*quantity;
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
        return total;
    }
    
    public boolean removeOrderDetail(int orderID)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Delete From [OrderDetail] "
                        + "Where orderID = ?";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setInt(1, orderID);
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.product;

import java.io.Serializable;
import java.sql.Connection;
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
public class ProductDAO implements Serializable {

    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void getProductList()
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL string
                String sql = "Select id, title, quantity, price "
                        + "From Product";
                //3. Create statement Object
                statement = connection.prepareStatement(sql);
                //4. Execute query
                rs = statement.executeQuery();
                //5. Process result
                while (rs.next()) {
                    String id = rs.getString("id");
                    String title = rs.getString("title");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    ProductDTO dto = new ProductDTO(id, title, quantity, price);
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    }//end products are not exist
                    this.products.add(dto);
                }//end while traverse result set
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
    }

    public ProductDTO getProduct(String id)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        ProductDTO product = null;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Select title, quantity, price "
                        + "From Product "
                        + "Where id = ?";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setString(1, id);
                //4. Excute Query
                rs = statement.executeQuery();
                //5. Process result
                if (rs.next()) {
                    String title = rs.getString("title");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    product = new ProductDTO(id, title, quantity, price);
                }//end while traverse result set
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
        return product;
    }
    
    public boolean updateQuantity(String proID, int quantity, int reqQuan)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
         
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Update Product "
                        + "Set quantity = ? "
                        + "Where id = ?";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setDouble(1, quantity-reqQuan);
                statement.setString(2, proID);
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

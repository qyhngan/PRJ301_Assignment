/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.registration;

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
public class RegistrationDAO implements Serializable{
    public RegistrationDTO checkLogin(String username, String password) 
            throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        
        try {
            //1. Make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL string
                String sql = "Select username, lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //3. Create statement Object
                statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                //4. Execute Query
                rs = statement.executeQuery();
                //5. Process result
                if (rs.next()) {
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullName, role);
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
    
    private List<RegistrationDTO> accounts;
    
    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastName(String searchValue)
            throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            //1. Make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL string
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //3. Create statement Object
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = statement.executeQuery();
                //5. Process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end accounts are not exist
                    //accounts has been existed
                    this.accounts.add(dto);
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
    
    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Delete From Registration "
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
    
    public boolean updateAccount(String username, String password,
            boolean isAdmin)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setString(1, password);
                statement.setBoolean(2, isAdmin);
                statement.setString(3, username);
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
    
    public boolean createAccount(RegistrationDTO dto)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;
        try {
            //1. make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Insert into Registration "
                        + "(username, password, isAdmin , lastname) "
                        + "Values (?, ?, ?, ?)";
                //3. Create Statement Object
                statement = connection.prepareStatement(sql);
                statement.setString(1, dto.getUsername());
                statement.setString(2, dto.getPassword());
                statement.setBoolean(3, dto.isRole());
                statement.setString(4, dto.getFullName());
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

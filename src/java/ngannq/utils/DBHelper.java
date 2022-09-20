/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author User
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection() throws NamingException, SQLException{
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource datasource = (DataSource)tomcatContext.lookup("DS007");
        Connection connection = datasource.getConnection();
        return connection;
    }
    
    public static void getSiteMaps(ServletContext context) throws IOException{
        String siteMapFile = context.getInitParameter("SITEMAPS_PATH");
        InputStream is = null;
        Properties properties = new Properties();
        
        try {
            is = context.getResourceAsStream(siteMapFile);
            properties.load(is);
            context.setAttribute("SITEMAPS", properties);
            
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}

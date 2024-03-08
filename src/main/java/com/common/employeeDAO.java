package com.common;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static com.common.JDBCTemplate.close;

public class employeeDAO {

    private Properties prop = new Properties();

    public employeeDAO(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/mapper/employee-query.xml"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void countEmp (Connection con ){

        Statement stmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("select");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            while (rset.next()){
                System.out.println(rset.getString("부서명") +" , " + rset.getString("부서인원") );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(stmt);
            close(rset);
        }

    }
}



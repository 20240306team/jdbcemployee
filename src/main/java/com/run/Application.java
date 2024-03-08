package com.run;

import com.common.employeeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {

    Connection con = getConnection();

    employeeDAO select = new employeeDAO();

    select.countEmp(con);

    }
}






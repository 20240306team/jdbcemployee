package com.run;

import com.common.employeeDAO;
import com.common.employeeDTO;

import java.sql.Connection;
import java.util.List;

import static com.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

        Connection con = getConnection();

        employeeDAO empRegist = new employeeDAO();

        List<employeeDTO> emplist =  empRegist.getEverything(con);

        for(employeeDTO allemp : emplist){
            System.out.println("allemp = " + allemp);
        }
    }
}

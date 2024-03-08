package com.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;
import java.util.Scanner;

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

    public List<employeeDTO> getEverything(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;
        List<employeeDTO> empList = null;
        employeeDTO row = null;
        String query = prop.getProperty("getEverything");
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            empList = new ArrayList<>();
            row = new employeeDTO();
            while(rset.next()){
                row.setEmpID(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));
                empList.add(row);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{

            close(stmt);
            close(rset);
        }


        return empList;
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
        } finally{
          close(stmt);
          close(rset);
          }




    }
    public int phonechange(Connection con){

        PreparedStatement pstmt = null;

        int result = 0;




        String query = prop.getProperty("phonechange");
        Scanner sc  = new Scanner(System.in);
        System.out.print("수정할 사원의 이름을 입력해 주세요 : ");
        String empName = sc.nextLine();
        System.out.print("바꿀 번호를 입력해 주세요 : ");
        String empPhone = sc.nextLine();

        try {
            pstmt=con.prepareStatement(query);
            pstmt.setString(1,empPhone);
            pstmt.setString(2,empName);

            result=pstmt.executeUpdate();






        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }

        return result;
    }

    public int empAdd(Connection con, employeeDTO newEmp) {

        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("insertMember");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1,newEmp.getEmpID());
            pstmt.setString(2,newEmp.getEmpName());
            pstmt.setString(3,newEmp.getEmpNo());
            pstmt.setString(4,newEmp.getEmail());
            pstmt.setString(5,newEmp.getPhone());
            pstmt.setString(6,newEmp.getDeptCode());
            pstmt.setString(7,newEmp.getJobCode());
            pstmt.setString(8,newEmp.getSalLevel());
            pstmt.setInt(9,newEmp.getSalary());
            pstmt.setDouble(10,newEmp.getBonus());
            pstmt.setString(11,newEmp.getManagerId());
            pstmt.setDate(12,newEmp.getHireDate());
            pstmt.setDate(13,newEmp.getEntDate());
            pstmt.setString(14,newEmp.getEntYn());

            result=pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;

    }
}



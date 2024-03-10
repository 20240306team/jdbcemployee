package com.run;

import com.common.employeeDAO;


import java.sql.*;

import static com.common.JDBCTemplate.close;


import com.common.employeeDTO;

import java.util.List;
import java.util.Scanner;

import static com.common.JDBCTemplate.getConnection;

public class Application {
    
    public static void main(String[] args) {


    Connection con = getConnection();

    employeeDAO empRegist = new employeeDAO();

    empRegist.countEmp(con);



    List<employeeDTO> emplist =  empRegist.getEverything(con);

        for(employeeDTO allemp : emplist){
            System.out.println("allemp = " + allemp);
        }


       int result = empRegist.phonechange(con);

        if(result == 0){
            System.out.println("입력실패");
        }else{
            System.out.println("입력성공");
        }

        int result1 = 0;
        employeeDTO newEmp = new employeeDTO();


        Scanner sc = new Scanner(System.in);
        System.out.print("새로운 신입사원 id : ");
        String empId = sc.nextLine();
        System.out.print("새로운 신입사원 이름 : ");
        String empName = sc.nextLine();
        System.out.print("새로운 신입사원 No : ");
        String empNo = sc.nextLine();
        System.out.print("새로운 신입사원 Email : ");
        String email = sc.nextLine();
        System.out.print("새로운 신입사원 핸드폰번호 : ");
        String phone = sc.nextLine();
        System.out.print("새로운 신입사원 부서코드 : ");
        String deptCode = sc.nextLine();
        System.out.print("새로운 신입사원 직업코드 : ");
        String jobCode = sc.nextLine();
        System.out.print("새로운 신입사원 급여레벨 : ");
        String salLevel = sc.nextLine();
        System.out.print("새로운 신입사원 급여 : ");
        int sallary = sc.nextInt();
        System.out.print("새로운 신입사원 보너스 : ");
        double bonus = sc.nextDouble();
        System.out.print("새로운 신입사원 매니저id : ");
        String managerId = sc.nextLine();
        System.out.print("새로운 신입사원 고용일 : ");
        Date hireDate = Date.valueOf(sc.nextLine());
        System.out.print("새로운 신입사원 마지막날짜 : ");
        Date entDate =Date.valueOf(sc.nextLine());
        System.out.print("새로운 신입사원 yes or no : ");
        String entYn = sc.nextLine();

        newEmp.setEmpID(empId);
        newEmp.setEmpName(empName);
        newEmp.setEmpNo(empNo);
        newEmp.setEmail(email);
        newEmp.setPhone(phone);
        newEmp.setDeptCode(deptCode);
        newEmp.setJobCode(jobCode);
        newEmp.setSalLevel(salLevel);
        newEmp.setSalary(sallary);
        newEmp.setBonus(bonus);
        newEmp.setManagerId(managerId);
        newEmp.setHireDate(hireDate);
        newEmp.setEntDate(entDate);
        newEmp.setEntYn(entYn);

        result1 = empRegist.empAdd(con,newEmp);
        if (result1>0){
            System.out.println("출력 성공함");}
        else{
            System.out.println("실패...");
        }
        //con: 쿼리를 연결해주는 값
        int result2 = empRegist.getout(con,newEmp.getEmpName());

        if(result2 != 0){
            System.out.println("성공적으로 사원을 내보냈습니다 ");
        }else{
            System.out.println("살아남았군요");
        }




    }
}






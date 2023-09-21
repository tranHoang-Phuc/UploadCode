/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DepartmentDAO;
import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import model.Department;
import model.Student;

/**
 *
 * @author tran Hoang Phuc
 */
public class RegisterController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        StudentDAO studentDAO = new StudentDAO();
        
        List<Student> students = studentDAO.getAllStudents();
        List<Department> departments = departmentDAO.getAllDepartments();
        
        req.setAttribute("students", students);
        req.setAttribute("departments", departments);
        
        req.getRequestDispatcher("view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");
        String did = req.getParameter("department");
        
        Student s = new Student();
        s.setSname(name);
        s.setGender(gender.equals("male"));
        s.setDob(Date.valueOf(dob));
        Department d = new Department();
        d.setDid(Integer.parseInt(did));
        s.setDepartment(d);
        
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.insert(s);
        doGet(req, resp);
    }
    
    
}

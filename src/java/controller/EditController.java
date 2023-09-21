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
public class EditController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StudentDAO studentDAO = new StudentDAO();
        int sid = Integer.parseInt(req.getParameter("sid"));
        String sname = req.getParameter("name");
        boolean gender = req.getParameter("gender").equals("male");
        Date dob = Date.valueOf(req.getParameter("dob"));
        int did = Integer.parseInt(req.getParameter("department"));

        Department d = new Department();
        d.setDid(did);
        Student s = new Student(sid, sname, gender, dob, d);
        studentDAO.updateStudent(s);
        resp.sendRedirect("register");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        StudentDAO studentDao = new StudentDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Department> departments = departmentDAO.getAllDepartments();
        int sid = Integer.parseInt(req.getParameter("id"));
        Student s = studentDao.getStudentById(sid);
        if (s != null) {
            req.setAttribute("student", s);
        }
        req.setAttribute("departments", departments);
        req.getRequestDispatcher("view/editor.jsp").forward(req, resp);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Student;

/**
 *
 * @author tran Hoang Phuc
 */
public class StudentDAO extends DBContext {

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        String sql = "select s.sid, \n"
                + "	   s.sname, \n"
                + "	   s.gender, \n"
                + "	   s.dob, \n"
                + "	   s.did, \n"
                + "	   d.dname \n"
                + "  from Students s join Department d \n"
                + "    on s.did = d.did";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));

                Department d = new Department();
                d.setDid(rs.getInt("did"));
                d.setDname(rs.getString("dname"));

                s.setDepartment(d);
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public void insert(Student s) {
        String sql = "INSERT INTO [Students]\n"
                + "           ([sname]\n"
                + "           ,[gender]\n"
                + "           ,[dob]\n"
                + "           ,[did])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, s.getSname());
            stm.setBoolean(2, s.isGender());
            stm.setDate(3, s.getDob());
            stm.setInt(4, s.getDepartment().getDid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int sid) {
        String sql = "DELETE FROM [dbo].[Students]\n"
                + "      WHERE [sid] = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Student getStudentById(int sid) {
        Student s = null;
        String sql = "SELECT [sid]\n"
                + "      ,[sname]\n"
                + "      ,[gender]\n"
                + "      ,[dob]\n"
                + "      ,s.[did],\n"
                + "	  d.dname\n"
                + "  FROM [Students] s join [Department] d \n"
                + "  ON s.did = d.did\n"
                + "  WHERE [sid] = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                Department d = new Department();
                d.setDid(rs.getInt("did"));
                d.setDname(rs.getString("dname"));
                s.setDepartment(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public void updateStudent(Student s) {
        String sql = "UPDATE [dbo].[Students]\n"
                + "   SET [sname] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[did] = ?\n"
                + " WHERE [sid] = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, s.getSname());
            stm.setBoolean(2, s.isGender());
            stm.setDate(3, s.getDob());
            stm.setInt(4, s.getDepartment().getDid());
            stm.setInt(5, s.getSid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

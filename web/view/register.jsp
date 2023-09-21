<%-- 
    Document   : register
    Created on : Sep 15, 2023, 12:06:10 PM
    Author     : tran Hoang Phuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Student" %>
<%@page import="model.Department" %>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <form action="register" method="POST">
            <label for="name">Name: </label>
            <input type="text" name="name" id="name"> <br>
            <label>Gender </label>
            <input type="radio" name="gender" id="male"> <label for="male">Male</label> 
            <input type="radio" name="gender" id="female"> <label for="female">Female</label> <br>
            <label>Date of Birth</label>
            <input type="date" name="dob"><br>
            <label>Department</label>
            <select name="department">
                <c:forEach items="${requestScope.departments}" var="d">
                    <option value="${d.did}">${d.dname}</option>
                </c:forEach>                
            </select>
            <button type="submit">Register</button>
        </form>
        <c:if test="${requestScope.students.size() > 0}">
            <table border="1px">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Date of Birth</th>
                    <th>Department</th>
                    <th>Action</th>                
                </tr>
                <c:forEach items="${requestScope.students}" var="s"> 
                    <tr>
                        <td>${s.sid}</td>
                        <td>${s.sname}</td>
                        <td>${s.gender?"Male":"Female"}</td>
                        <td><fmt:formatDate type="date" value="${s.dob}"/></td>
                        <td>${s.department.dname}</td>
                        <td>
                            <form action="delete" method="POST">
                                <input type="hidden" value="${s.sid}" name="id">
                                <button>Delete</button>
                            </form>
                            <form action="edit" method="GET">
                                <input type="hidden" value="${s.sid}" name="id">
                                <button>Edit</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>     
        </c:if>
    </body>
</html>

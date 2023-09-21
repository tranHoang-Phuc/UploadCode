<%-- 
    Document   : editor
    Created on : Sep 19, 2023, 10:40:36 PM
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
        <title>Editor</title>
    </head>
    <body>
        <form action="edit" method="POST">
            <input type="hidden" name="sid" value="${requestScope.student.sid}">
            <label for="name">Name: </label>
            <input type="text" name="name" id="name" value="${requestScope.student.sname}"><br>
            <label>Gender </label>
            <c:choose>
                <c:when test="${requestScope.student.gender}">
                    <input type="radio" name="gender" id="male" checked="checked">
                    <label for="male">Male</label> 
                    <input type="radio" name="gender" id="female">
                    <label for="female">Female</label> <br>
                </c:when>
                <c:otherwise>
                    <input type="radio" name="gender" id="male">
                    <label for="male">Male</label> 
                    <input type="radio" name="gender" id="female" checked="checked">
                    <label for="female">Female</label> <br>
                </c:otherwise>
            </c:choose>
            <label>Date of Birth</label>
            <input type="date" name="dob" value="${requestScope.student.dob}"><br>
            <label>Department</label>
            <select name="department">
                <c:forEach items="${requestScope.departments}" var="d">
                    <option value="${d.did}" <c:if test="${d.did == requestScope.student.department.did}">selected</c:if>>${d.dname}</option>
                </c:forEach>                
            </select>
            <button type="submit">Edit</button>
        </form>
    </body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: viennguyenthi
  Date: 2024/07/28
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #2F3645;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 10px;
            color: #2F3645;
            font-size: 18px;
            font-weight: 700;
        }
        input[type="text"],
        input[type="email"],
        input[type="tel"],
        input[type="date"],
        input[type="password"],
        select {
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 10px;
            text-align: center;
            border-radius: 10px;
            font-weight: 800;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #7D8ABC;
            color: white;
        }
        .btn-group {
            margin-bottom: 20px;
        }
        .btn-group label {
            display: inline-block;
            margin-right: 10px;
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 6px 12px;
            border-radius: 10px;
            font-weight: 800;
            font-size: 16px;
            cursor: pointer;
        }
        .btn-group input[type="radio"] {
            display: none;
        }
        .btn-group label:hover {
            background-color: #7D8ABC;
            color: white;
        }
        .link {
            text-decoration: none;
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 10px 12px;
            text-align: center;
            display: inline-block;
            border-radius: 3px;
            font-weight: 800;
            font-size: 16px;
            margin-top: 20px;
        }
        .link:hover {
            background-color: #7D8ABC;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Create New User</h2>
    <form action="/UserServlet" method="post">
        <input type="hidden" name="action" value="edit"/>
        <input type="hidden" name="usersId" value="${userUpdate.usersId}">

        <label for="userName">User Name:</label>
        <input type="text" id="userName" name="userName" value="${userUpdate.userName}" required>

        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName"  value="${userUpdate.fullName}" required>

        <p>Gender:</p>
        <div class="btn-group">
            <label>
                <input type="radio" name="gender" value="true" ${userUpdate.gender ? "checked" : ""}   required> Male
            </label>
            <label>
                <input type="radio" name="gender" value="false" ${!userUpdate.gender ? "checked" : ""}  required> Female
            </label>
        </div>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address"  value="${userUpdate.address}" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email"  value="${userUpdate.email}" required>

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" value="${userUpdate.phone}" required>

        <label for="birthday">Birthday:</label>
        <input type="date" id="birthday" name="birthday"  value="${userUpdate.birthday}" required>

        <p>Status:</p>
        <div class="btn-group">
            <label>
                <input type="radio" name="status" value="Active" value="true" ${userUpdate.status ? "checked" : ""}  required> Active
            </label>
            <label>
                <input type="radio" name="status" value="Inactive" value="false" ${!userUpdate.status ? "checked" : ""}  required> Inactive
            </label>
        </div>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${userUpdate.password}"required>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" value="${userUpdate.confirmPassword}" required>

        <input type="submit" value="edit">
    </form>
    <a href="/views/users" class="link"><< Back</a>
</div>
</body>
</html>


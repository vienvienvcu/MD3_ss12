<%--
  Created by IntelliJ IDEA.
  User: viennguyenthi
  Date: 2024/07/28
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>User List</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #EEEDEB;
        }

        th {
            background-color: #FFC7ED;
            color: #2F3645;
            font-weight: 700;
            font-size: 18px;
        }

        input[type=text] {
            width: 300px;
            box-sizing: border-box;
            border: 1px solid #2F3645;
            border-radius: 10px;
            font-size: 16px;
            background-color: white;
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            transition: width 0.4s ease;
        }

        form {
            margin-top: 30px;
            margin-bottom: 20px;
        }

        h2 {
            text-align: center;
            color: #2F3645;
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
            font-size: 20px;
        }

        .link:hover {
            background-color: #7D8ABC;
            color: white;
        }

        .item {
            text-decoration: none;
            background-color: #304463;
            color: #FFC7ED;
            border: 1px solid #2F3645;
            padding: 8px 10px;
            text-align: center;
            display: inline-block;
            border-radius: 3px;
            font-weight: 800;
            font-size: 14px;
        }

        .item:hover {
            background-color: #7D8ABC;
            color: white;
        }

        .content {
            display: flex;
            justify-content: space-around;
            align-items: center;
        }

    </style>

    <script>
        function confirmDelete(id) {
            if (confirm('Bạn có chắc chắn muốn xóa người dùng này không?')) {
                window.location.href = 'UserServlet?action=delete&usersId=' + id;
            }
        }
    </script>

</head>
<body>
<p>${errorMessage}</p>
<div class="content">
    <div><a href="/UserServlet?action=add" class="link">+ Add User</a></div>
    <form action="/UserServlet" method="get">
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="userName" placeholder="Search by name"/>
        <button type="submit" class="link">Search</button>
    </form>
</div>

<div class="container">
    <h2>User List</h2>
    <div class="message">
        <c:if test="${not empty param.message}">
            <p>${param.message}</p>
        </c:if>
    </div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>User Name</th>
            <th>Full Name</th>
            <th>Gender</th>
            <th>Address</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Birthday</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.usersId}</td>
                <td>${user.userName}</td>
                <td>${user.fullName}</td>
                <td>${user.gender ? "Male" : "Female"}</td>
                <td>${user.address}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" /></td>
                <td>${user.status ? "Active" : "Inactive"}</td>
                <td>
                    <a href="UserServlet?action=edit&usersId=${user.usersId}" class="item">Edit</a>
                    <a href="javascript:confirmDelete(${user.usersId})" class="item">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="/index.jsp" class="link back"><< Home</a>
</body>
</html>

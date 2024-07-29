package ra.ss12_usermanagement.controller;

import ra.ss12_usermanagement.model.entity.PasswordUtil;
import ra.ss12_usermanagement.model.entity.User;
import ra.ss12_usermanagement.model.service.IUserService;
import ra.ss12_usermanagement.model.serviceImpl.UserServiceImpl;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    private IUserService<User, Integer> userService = new UserServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
              showCreateForm(request,response);
                break;
            case "edit":
                showUpdateForm(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;
            case "search":
                searchUserByName(request,response);
                break;
            default:
                listUsers(request, response);
                break;
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
               createUser(request, response);
                break;
            case "edit":
                updateUser(request, response);
                break;
            default:
               listUsers(request, response);
                break;
        }
    }
    private void  listUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //        goi getAll cua UserService
        List<User>  userList = userService.findAll();
//        add vao request
        request.setAttribute("users", userList);
        try {
            request.getRequestDispatcher("views/users.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/newUser.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
    private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("fullName");
        String genderStr = request.getParameter("gender");
        boolean gender = "true".equals(genderStr);
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthdayStr = request.getParameter("birthday");
        String statusStr = request.getParameter("status");
        boolean status = "true".equals(statusStr);
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Kiểm tra mật khẩu
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            try {
                request.getRequestDispatcher("views/newUser.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        // Tạo đối tượng User và lưu vào cơ sở dữ liệu
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setFullName(fullName);
        newUser.setGender(gender);
        newUser.setAddress(address);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            newUser.setBirthday(sdf.parse(birthdayStr));
        } catch (ParseException e) {
            try {
                throw new ServletException("Invalid date format.", e);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            }
        }
        newUser.setStatus(status);
        newUser.setPassword(PasswordUtil.hashPassword(password));
        newUser.setConfirmPassword(PasswordUtil.hashPassword(confirmPassword));

        boolean result = userService.save(newUser);
        if (result) {
            listUsers(request, response);
        } else {
            request.setAttribute("errorMessage", "Failed to add user.");
            try {
                request.getRequestDispatcher("views/newUser.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int usersId = Integer.parseInt(request.getParameter("usersId"));
        User existingUser = userService.findById(usersId);
        request.setAttribute("userUpdate", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/showFormUpdate.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int usersId = Integer.parseInt(request.getParameter("usersId"));
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("fullName");
        String genderStr = request.getParameter("gender");
        boolean gender = "true".equals(genderStr);
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthdayStr = request.getParameter("birthday");
        String statusStr = request.getParameter("status");
        boolean status = "true".equals(statusStr);
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        User userUpdate = userService.findById(usersId);
        if (userUpdate == null) {
            request.setAttribute("errorMessage", "User not found.");
        }else {
            userUpdate.setUserName(userName);
            userUpdate.setFullName(fullName);
            userUpdate.setGender(gender);
            userUpdate.setAddress(address);
            userUpdate.setEmail(email);
            userUpdate.setPhone(phone);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                userUpdate.setBirthday(sdf.parse(birthdayStr));
            } catch (ParseException e) {
                try {
                    throw new ServletException("Invalid date format.", e);
                } catch (ServletException ex) {
                    throw new RuntimeException(ex);
                }
            }
            userUpdate.setStatus(status);
            userUpdate.setPassword(PasswordUtil.hashPassword(password));
            userUpdate.setConfirmPassword(PasswordUtil.hashPassword(confirmPassword));

            boolean result = userService.update(userUpdate);
            if (result) {
                listUsers(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to add user.");
                try {
                    request.getRequestDispatcher("views/newUser.jsp").forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int usersId = Integer.parseInt(request.getParameter("usersId"));
        User user = userService.findById(usersId);
        if (user == null) {
            request.setAttribute("errorMessage", "User not found.");
        }else {
            userService.delete(usersId);
            request.setAttribute("message", "product has been deleted successfully.");
        }
        response.sendRedirect("/UserServlet");
    }
    private void searchUserByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        List<User> userList;
        if (userName == null || userName.isEmpty()) {
            request.setAttribute("errorMessage", "User not found.");
        } else {
            userList = userService.searchUserByName(userName);
            request.setAttribute("users", userList);
            request.getRequestDispatcher("views/users.jsp").forward(request, response);
        }
    }
    public void destroy() {
    }
}
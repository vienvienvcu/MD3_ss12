package ra.ss12_usermanagement.model.daoImpl;

import ra.ss12_usermanagement.model.dao.IUserDAO;
import ra.ss12_usermanagement.model.entity.PasswordUtil;
import ra.ss12_usermanagement.model.entity.User;
import ra.ss12_usermanagement.model.util.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO<User,Integer> {
    @Override
    public List<User> searchUserByName(String userName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<User> userList = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call pro_searchUserByName(?)}");
            callableStatement.setString(1, userName);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsersId(resultSet.getInt("userId"));
                user.setUserName(resultSet.getString("userName"));
                user.setFullName(resultSet.getString("fullName"));
                user.setGender(resultSet.getBoolean("gender"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPassword(resultSet.getString("password"));
                user.setConfirmPassword(resultSet.getString("confirmPassword"));
                user.setCreationDate(resultSet.getDate("creationDate"));
                user.setUpdatedDate(resultSet.getDate("updatedDate"));
                user.setStatus(resultSet.getBoolean("status"));
                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return userList;
    }


    @Override
    public List<User> findAll() {
//        thuc hien ket noi csdl
        Connection collections = null;
        CallableStatement callableStatement = null;
        List<User> userList = null;
        try {
            // 1 thuc hien ket noi den csld
            collections = ConnectionDB.openConnection();
            // 2 khoi tao doi tuong CallableStatement  de goi den procedure
            callableStatement = collections.prepareCall("{call pro_getAllUser()}");
//            thuc hien call va nhan ResultSet
//            pro la cau select thi thuc hien phuong thuc executeQuery();
//            pro la cau lenh thuc hien insert ,update, delete, ma chi co tham so vao- executeUpdate()
//             pro la cau lenh thuc hien insert ,update, delete, ma chi co tham so ra - execute()

            ResultSet resultSet = callableStatement.executeQuery();
            userList = new ArrayList<>();
            // 4 duyet resultSet day du lieu ra list
            while (resultSet.next()) {
                User user = new User();
                user.setUsersId(resultSet.getInt("userId"));
                user.setUserName(resultSet.getString("userName"));
                user.setFullName(resultSet.getString("fullName"));
                user.setGender(resultSet.getBoolean("gender"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setStatus(resultSet.getBoolean("status"));
//                add vao list
                userList.add(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(collections,callableStatement);
        }
        return userList;
    }

    @Override
    public boolean save(User user) {
        Connection collections = null;
        CallableStatement callableStatement = null;
        boolean result = true;
        try {
          collections = ConnectionDB.openConnection();
          callableStatement =collections.prepareCall("{call pro_insertUser(?,?,?,?,?,?,?,?,?,?,?)}");
//          thuc hien set gia tri cac tham so vao
            callableStatement.setString(1, user.getUserName());
            callableStatement.setString(2, user.getFullName());
            callableStatement.setBoolean(3, user.isGender());
            callableStatement.setString(4, user.getAddress());
            callableStatement.setString(5, user.getEmail());
            callableStatement.setString(6, user.getPhone());
            callableStatement.setDate(7,new Date(user.getBirthday().getTime()));
            // Mã hóa mật khẩu trước khi gửi vào cơ sở dữ liệu
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            callableStatement.setString(8, hashedPassword);
            callableStatement.setString(9, user.getConfirmPassword());
            callableStatement.setBoolean(10,user.isStatus());

//            thuc hien dang ki kieu du lieu cua cac tham so ra
            callableStatement.registerOutParameter(11, Types.INTEGER);
//            THUC HIEN GOI PROCEDURE
            callableStatement.execute();

            // Lấy giá trị tham số đầu ra nếu cần
            int count = callableStatement.getInt(11);
            System.out.println("Number of users: " + count);

        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(collections,callableStatement);
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        Connection collections = null;
        CallableStatement callableStatement = null;
        boolean result = true;
        try {
            // Mở kết nối đến cơ sở dữ liệu
            collections = ConnectionDB.openConnection();

            // Chuẩn bị gọi stored procedure với 11 tham số
            callableStatement = collections.prepareCall("{call pro_updateUser(?,?,?,?,?,?,?,?,?,?,?)}");

            // Cài đặt giá trị cho các tham số
            callableStatement.setInt(1, user.getUsersId()); // Tham số inUserId
            callableStatement.setString(2, user.getUserName()); // Tham số inUserName
            callableStatement.setString(3, user.getFullName()); // Tham số inFullName
            callableStatement.setBoolean(4, user.isGender()); // Tham số inGender
            callableStatement.setString(5, user.getAddress()); // Tham số inAddress
            callableStatement.setString(6, user.getEmail()); // Tham số inEmail
            callableStatement.setString(7, user.getPhone()); // Tham số inPhone
            callableStatement.setDate(8, new java.sql.Date(user.getBirthday().getTime())); // Tham số inBirthday
            // Mã hóa mật khẩu trước khi gửi vào cơ sở dữ liệu
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            callableStatement.setString(9, hashedPassword); // Tham số inPassword
            callableStatement.setString(10, user.getConfirmPassword()); // Tham số inConfirmPassword
            callableStatement.setBoolean(11, user.isStatus()); // Tham số inStatus

            // Thực hiện stored procedure
            callableStatement.executeUpdate();

        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        } finally {
            // Đóng kết nối và đối tượng CallableStatement
            ConnectionDB.closeConnection(collections, callableStatement);
        }
        return result;
    }


    @Override
    public boolean delete(Integer id) {
        Connection collections = null;
        CallableStatement callableStatement = null;
        boolean result = false;
        try {
            // Mở kết nối đến cơ sở dữ liệu
            collections = ConnectionDB.openConnection();

            // Chuẩn bị gọi stored procedure với 1 tham số
            callableStatement = collections.prepareCall("{call pro_deleteUser(?)}");

            // Cài đặt giá trị cho tham số
            callableStatement.setInt(1, id);

            // Thực hiện stored procedure
            int rowsAffected = callableStatement.executeUpdate();

            // Nếu số hàng bị ảnh hưởng lớn hơn 0, xem như xóa thành công
            if (rowsAffected > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và đối tượng CallableStatement
            ConnectionDB.closeConnection(collections, callableStatement);
        }

        return result;
    }


    @Override
    public User findById(Integer id) {
        //        thuc hien ket noi csdl
        Connection collections = null;
        CallableStatement callableStatement = null;
        User userInformation = null;
        try {
            // 1 thuc hien ket noi den csld
            collections = ConnectionDB.openConnection();
            // 2 khoi tao doi tuong CallableStatement  de goi den procedure
            callableStatement = collections.prepareCall("{call pro_getUserById(?)}");
//            set gia tri cho tham so vao
            callableStatement.setInt(1, id);
//            thuc hien call va nhan ResultSet
//            pro la cau select thi thuc hien phuong thuc executeQuery();
//            pro la cau lenh thuc hien insert ,update, delete, ma chi co tham so vao- executeUpdate()
//             pro la cau lenh thuc hien insert ,update, delete, ma chi co tham so ra - execute()

            ResultSet resultSet = callableStatement.executeQuery();
            userInformation = new User();
            // 4 duyet resultSet day du lieu ra list
            if (resultSet.next()) {
                userInformation.setUsersId(resultSet.getInt("userId"));
                userInformation.setUserName(resultSet.getString("userName"));
                userInformation.setFullName(resultSet.getString("fullName"));
                userInformation.setGender(resultSet.getBoolean("gender"));
                userInformation.setAddress(resultSet.getString("address"));
                userInformation.setEmail(resultSet.getString("email"));
                userInformation.setPhone(resultSet.getString("phone"));
                userInformation.setBirthday(resultSet.getDate("birthday"));
                userInformation.setPassword(resultSet.getString("password"));
                userInformation.setConfirmPassword(resultSet.getString("confirmPassword"));
                userInformation.setStatus(resultSet.getBoolean("status"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(collections,callableStatement);
        }
        return userInformation;
    }
}

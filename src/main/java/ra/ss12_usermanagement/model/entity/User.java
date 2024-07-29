package ra.ss12_usermanagement.model.entity;

import java.util.Date;

public class User {
        private int usersId;
        private String userName;
        private String fullName;
        private boolean gender;
        private String address;
        private String email;
        private String phone;
        private Date birthday;
        private String password;
        private String confirmPassword;
        private Date creationDate;
        private Date updatedDate;
        private boolean status;

        public User() {
        }
        public User(int usersId, String userName, String fullName, boolean gender, String address, String email, String phone, Date  birthday,String password, String confirmPassword,
                    Date creationDate, Date updatedDate, boolean status) {
            this.usersId = usersId;
            this.userName = userName;
            this.fullName = fullName;
            this.gender = gender;
            this.address = address;
            this.email = email;
            this.phone = phone;
            this.birthday = birthday;
            this.password = password;
            this.confirmPassword = confirmPassword;
            this.creationDate = creationDate;
            this.updatedDate = updatedDate;
            this.status = status;
        }

        public User (int usersId,String userName,String email, String phone, String password, String confirmPassword){
            this.usersId = usersId;
            this.userName = userName;
            this.email = email;
            this.phone = phone;
            this.password = password;
            this.confirmPassword = confirmPassword;

        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public Date getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public boolean isGender() {
            return gender;
        }

        public void setGender(boolean gender) {
            this.gender = gender;
        }



        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public Date getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(Date updatedDate) {
            this.updatedDate = updatedDate;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUsersId() {
            return usersId;
        }

        public void setUsersId(int usersId) {
            this.usersId = usersId;
        }

    }


package ra.ss12_usermanagement.model.entity;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    // Mã hóa mật khẩu
    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    // Kiểm tra mật khẩu
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified;
    }
}

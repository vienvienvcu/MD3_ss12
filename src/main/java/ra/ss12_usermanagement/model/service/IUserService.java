package ra.ss12_usermanagement.model.service;

import java.util.List;

public interface IUserService<T,V> extends IUserServiceManagement <T,V> {
    List<T> searchUserByName(String userName);
}

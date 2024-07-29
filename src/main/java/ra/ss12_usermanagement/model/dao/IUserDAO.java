package ra.ss12_usermanagement.model.dao;

import java.util.List;

public interface IUserDAO <T,V> extends IUserManagementDao<T,V>{
    List<T> searchUserByName(String userName);

}

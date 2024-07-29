package ra.ss12_usermanagement.model.serviceImpl;

import ra.ss12_usermanagement.model.dao.IUserDAO;
import ra.ss12_usermanagement.model.daoImpl.UserDAOImpl;
import ra.ss12_usermanagement.model.entity.User;
import ra.ss12_usermanagement.model.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService<User,Integer> {
    private IUserDAO<User,Integer> userDAO = new UserDAOImpl();
    @Override
    public List<User> searchUserByName(String userName) {
        return userDAO.searchUserByName(userName);
    }

    @Override
    public List findAll() {
        return userDAO.findAll();
    }

    @Override
    public boolean save(User user) {
        return userDAO.save(user);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean delete(Integer id) {
        return userDAO.delete(id);
    }

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id);
    }


}

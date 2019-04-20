package kg.it.service;

import kg.it.dao.UserDao;
import kg.it.model.User;
import kg.it.model.UserAuth;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class UserService {
    @POST
    @Produces({MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML})
    public UserAuth registration(User user) {
        UserDao userDao = new UserDao();
        UserAuth userAuth = new UserAuth();
        if (userDao.isLoginExist(user.getLogin())) {
            userAuth.message = " Такой пользователь уже существует";
        } else {
            userAuth.dataCheking(user.getLogin(), user.getPassword());
            userAuth.message = " Регистрация прошла успешно";
            userDao.addUser(user);
        }
        return userAuth;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML})
    public UserAuth authorization(@HeaderParam("login") String login,
                                  @HeaderParam("password") String password) {
        UserDao userDao = new UserDao();
        UserAuth userAuth = new UserAuth();
        if (userDao.auth(login,password)) {
            userAuth.dataCheking(login, password);
            userAuth.message = " Авторизация прошла успешно";
        } else {
            userAuth.auth = "";
            userAuth.message = " Неверный логин или пароль";
        }
        return userAuth;
    }
}
package kg.it.dao;

import kg.it.model.User;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDao {
    private final String url = "jdbc:postgresql://138.68.52.248/gr2";
    private final String user = "gruppa2";
    private final String password = "456qwe";

    private static final HashMap<Integer, User> users = new HashMap();

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public UserDao() {
        loadData();
    }

    private void loadData() {
        String SQL = "select * from users ";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.put(rs.getInt("id"),
                        new User(rs.getInt("id"),
                                rs.getString("fio"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("login")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean isLoginExist(String login) {
        String SQL = "select login from users ";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (login.equals(rs.getString("login"))) return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void addUser(User user) {
        String SQL = "insert into users (fio, password, login) values(?, ?, ?) ";

        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, user.getFio());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getLogin());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<User> getUsers() {
        Collection<User> u = users.values();
        List<User> list = new ArrayList();
        list.addAll(u);
        return list;
    }

    public boolean auth(String login, String password) {
        for (User u : UserDao.getUsers()) {
            if (u.getLogin().equals(login)
                    && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
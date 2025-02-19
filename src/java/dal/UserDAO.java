/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.util.*;
import java.lang.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.User;


/**
 *
 * @author dung0
 */
public class UserDAO extends DBContext{
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public List<User> getAll(){
        List<User> list = new ArrayList<>();
        String sql = "select * from users";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                User u = new User(rs.getInt("user_id"), rs.getString("username"), 
                        rs.getString("password"), rs.getString("role"), 
                        rs.getString("full_name"), rs.getString("email"), rs.getString("phone"),
                        rs.getTimestamp("created_at").toLocalDateTime().format(df));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
//19/10
    //thêm 1 user
    //thêm 1 user vào data
    //mỗi lần tạo thì lấy datetime.now()
    //khi tạo tk cho khách thì mặc định role trong object User sẽ là user
    public void insertUser(User u){
        String sql = "insert into users(username, password, role, full_name, email, phone, created_at) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUsername());
            st.setString(2, u.getPassword());
            st.setString(3, u.getRole());
            st.setString(4, u.getFullname());
            st.setString(5, u.getEmail());
            st.setString(6, u.getPhone());
            st.setTimestamp(7, Timestamp.valueOf(LocalDateTime.parse(u.getCreatedAt(), df)));
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //update thông tin người dùng 
    public void updateUser(User u){
        String sql = "update users set password = ?, role = ?, full_name = ?, email = ?, phone = ?, created_at = ? where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getPassword());
            st.setString(2, u.getRole());
            st.setString(3, u.getFullname());
            st.setString(4, u.getEmail());
            st.setString(5, u.getPhone());
            st.setTimestamp(6, Timestamp.valueOf(LocalDateTime.parse(u.getCreatedAt(), df)));
            st.setString(7, u.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    

    
    
    //login
    public User login(String username, String password) {
        String query = "select * from users\n" +
                    "where username = ?\n" +
                    "and password = ?";
        try {
            Statement st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return new User(rs.getInt("user_id"), 
                              rs.getString("username"), 
                        rs.getString("password"), 
                        rs.getString("role"), 
                        rs.getString("full_name"), 
                        rs.getString("email"), 
                        rs.getString("phone"), 
                        rs.getTimestamp("created_at").toLocalDateTime().format(df));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    //get User theo ten
    public User getUserByUsername(String user) {
        String query = "select * from users\n" +
                    "where username = ?";
        try {
            Statement st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return new User(rs.getInt("user_id"), 
                              rs.getString("username"), 
                        rs.getString("password"), 
                        rs.getString("role"), 
                        rs.getString("full_name"), 
                        rs.getString("email"), 
                        rs.getString("phone"), 
                        rs.getTimestamp("created_at").toLocalDateTime().format(df));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    //delete a user by username
    public void deleteUserByUsername(String username){
        String sql = "delete from users where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
}

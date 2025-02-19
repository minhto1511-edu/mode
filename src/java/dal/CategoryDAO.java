/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.util.*;
import java.lang.*;
import model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author dung0
 */
public class CategoryDAO extends DBContext{
    
    //lấy tất cả danh mục món
    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        String sql = "select * from categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Category c = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    //lấy danh mục món bằng tên
    public Category getCategoryByName(String name){
        String sql = "select * from categories where category_name like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Category c = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    //tạo danh mục món mới
    public void createCategory(String name_cate){
        String sql = "insert into categories(category_name) values(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name_cate);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    //xóa danh mục món
    public void deleteCategory(int cid){
        String sql = "delete from categories where category_id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
}

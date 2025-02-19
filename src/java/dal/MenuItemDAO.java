 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.util.*;
import java.lang.*;
import model.MenuItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author dung0
 */
public class MenuItemDAO extends DBContext{
    
    //lấy tất cả các món
    public List<MenuItem> getAllMenuItem(){
        List<MenuItem> list = new ArrayList<>();
        String sql = "select * from menu_items";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    MenuItem mi = new MenuItem(rs.getInt("item_id"), rs.getString("item_name"), 
                            rs.getString("item_description"), rs.getDouble("price"), rs.getInt("category_id"), 
                            rs.getString("image_url"));
                    list.add(mi);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return list;
    }
    
    //lấy tất cả các món trong danh mục món
    public List<MenuItem> getAllByCid(int Cid){
        List<MenuItem> list = new ArrayList<>();
        String sql = "select * from menu_items where category_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Cid);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                MenuItem mi = new MenuItem(rs.getInt("item_id"), rs.getString("item_name"), 
                            rs.getString("item_description"), rs.getDouble("price"), rs.getInt("category_id"), 
                            rs.getString("image_url"));
                list.add(mi);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    //lấy món bằng id
    public MenuItem getMenuItemById(int mid){
        String sql = "select * from menu_items where item_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mid);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                MenuItem mi = new MenuItem(rs.getInt("item_id"), rs.getString("item_name"), 
                            rs.getString("item_description"), rs.getDouble("price"), rs.getInt("category_id"), 
                            rs.getString("image_url"));
                return mi;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    //lấy món bằng tên
    public MenuItem getMenuItemByName(String name){
        String sql = "select * from menu_items where item_name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                MenuItem mi = new MenuItem(rs.getInt("item_id"), rs.getString("item_name"), 
                            rs.getString("item_description"), rs.getDouble("price"), rs.getInt("category_id"), 
                            rs.getString("image_url"));
                return mi;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    //sửa thông tin món
    public void updateMenuItem(MenuItem mi){
        String sql = "update menu_items set item_name = ?, item_description = ?, price = ?, category_id = ?, image_url = ? where item_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, mi.getItemName());
            st.setString(2, mi.getItemDescription());
            st.setDouble(3, mi.getPrice());
            st.setInt(4, mi.getCategoryId());
            st.setString(5, mi.getImageUrl());
            st.setInt(6, mi.getId());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //xóa món bằng id
    public void deleteMenuItem(int item_id){
        String sql = "delete from menu_items where item_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, item_id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //xóa món trong danh mục món
    public void deleteMenuItemByCategoryId(int cid){
        String sql = "delete from menu_items where category_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //tạo món mới
    public boolean createMenuItem(MenuItem item) {
        String sql = "insert into menu_items(item_name, item_description, price, category_id, image_url)\n" +
                                     "values(?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, item.getItemName());
            st.setString(2, item.getItemDescription());
            st.setDouble(3, item.getPrice());
            st.setInt(4, item.getCategoryId());
            st.setString(5, item.getImageUrl());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}

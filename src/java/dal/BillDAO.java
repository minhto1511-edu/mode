/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.util.*;
import java.lang.*;
import model.Bill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author dung0
 */
public class BillDAO extends DBContext{
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    //Lấy toàn bộ bill
    public List<Bill> getAllBill(){
        List<Bill> list = new ArrayList<>();
        String sql = "select * from bills";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Bill b = new Bill(rs.getInt("bill_id"), rs.getInt("order_id"), 
                        rs.getTimestamp("bill_time").toLocalDateTime().format(df), 
                        rs.getDouble("total_amount"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    //lấy bill trong ngày
    public List<Bill> getAllBillInDay(){
        List<Bill> list = new ArrayList<>();
        String sql = "select * from bills where CONVERT(DATE, created_at) = CONVERT(DATE, GETDATE())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Bill b = new Bill(rs.getInt("bill_id"), rs.getInt("order_id"), 
                        rs.getTimestamp("bill_time").toLocalDateTime().format(df), 
                        rs.getDouble("total_amount"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    // tao bill neu bill chua co
    public void createBill(int oid, double total){
        String sql = "insert into bills(order_id, total_amount) values(?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.setDouble(2, total);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //Lấy tổng thu nhập trong ngày
    public double totalToday(){
        String sql = "select sum(total_amount) as 'total' from bills where CONVERT(DATE, bill_time) = CONVERT(DATE, GETDATE())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getDouble("total");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    //Tổng bill
    public double total(){
        String sql = "select sum(total_amount) as 'total' from bills";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getDouble("total");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    
    
}

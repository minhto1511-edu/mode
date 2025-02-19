/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.util.*;
import java.lang.*;
import model.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author dung0
 */
public class TableDAO extends DBContext{
    //get all table
    public List<Table> getAll(){
        List<Table> list = new ArrayList<>();
        String sql = "select * from tables";
        try{
          PreparedStatement st = connection.prepareStatement(sql);
          ResultSet rs = st.executeQuery();
          while(rs.next()){
              Table t = new Table(rs.getInt("table_id"), rs.getInt("table_number") , 
                      rs.getString("status"), rs.getInt("capacity"));
              list.add(t);
          }
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    //get table by table_id
    public Table getTableById(int tid){
        String sql = "select * from tables where table_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tid);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Table t = new Table(rs.getInt("table_id"), rs.getInt("table_number") , 
                      rs.getString("status"), rs.getInt("capacity"));
                return t;
            }
        } catch (Exception e) {
        }
        return null;
    }
    public Table getTableByIdAndStatus(int tid, String status){
        String sql = "select * from tables where table_id = ? and status like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tid);
            st.setString(2, "%"+status+"%");
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Table t = new Table(rs.getInt("table_id"), rs.getInt("table_number") , 
                      rs.getString("status"), rs.getInt("capacity"));
                return t;
            }
        } catch (Exception e) {
        }
        return null;
    }
    //get table by status
    public List<Table> getAllTableByStatus(String status){
        String sql = "select * from tables where status like ?";
        List<Table> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%"+status+"%");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Table t = new Table(rs.getInt("table_id"), rs.getInt("table_number") , 
                      rs.getString("status"), rs.getInt("capacity"));
                list.add(t);
            }
        } catch (Exception e) {
        }
        return list;
    }
    //get table by table_number
    public Table getTableByTableNumber(int table_number){
        String sql = "select * from tables where table_number = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, table_number);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Table t = new Table(rs.getInt("table_id"), rs.getInt("table_number") , 
                      rs.getString("status"), rs.getInt("capacity"));
                return t;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    //insert a new table
    public void insertNewTable(int tnum, int cnum){
        String sql = "insert into tables(table_number, capacity) values(?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tnum);
            st.setInt(2, cnum);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //update table 
    public void updateTable(int tid, int tnum, int cnum, String status){
        String sql = "update tables set table_number = ?, capacity = ?, status = ? where table_id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tnum);
            st.setInt(2, cnum);
            st.setString(3, status);
            st.setInt(4, tid);
            st.executeUpdate();  
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    //update status of table 
    public void updateStatusTable(int tid, String status){
        String sql = "update tables set status = ? where table_id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, tid);
            st.executeUpdate();  
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        TableDAO td = new TableDAO();
        List<Table> list = td.getAllTableByStatus("available");
        for (Table table : list) {
            System.out.println(table);
        }
    }
}

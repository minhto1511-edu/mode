/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.CustomerRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.RequestDTO;

/**
 *
 * @author lenha
 */
public class CustomerRequestDAO extends DBContext {

    DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
    //tạo request mới
    public void createRequest(int orderItemId) {
        String sql = "insert into customer_requests(order_item_id)\n"
                + "values(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderItemId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //lấy tất cả các request có trong ngày
    public List<RequestDTO> getRequestInDay() {
        List<RequestDTO> list = new ArrayList<>();
        String sql = "select request_id, request_time, cr.status, item_name, table_number, quantity from customer_requests cr\n"
                + "join order_items oi on cr.order_item_id = oi.order_item_id\n"
                + "join orders o on oi.order_id = o.order_id\n"
                + "join tables t on t.table_id = o.table_id\n"
                + "join menu_items m on m.item_id = oi.item_id\n"
                + "where CONVERT(DATE, request_time) = CONVERT(DATE, GETDATE())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RequestDTO r = new RequestDTO(rs.getInt(1),
                        rs.getTimestamp(2).toLocalDateTime().format(df),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6));
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    //lấy request bằng order_item_id
    public CustomerRequest getRequestByOrderItemId(int orderItemId) {
        String sql = "select * from customer_requests\n"
                + "where order_item_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderItemId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CustomerRequest cr = new CustomerRequest(rs.getInt("request_id"),
                        rs.getInt("order_item_id"),
                        rs.getTimestamp("request_time").toLocalDateTime().format(df),
                        rs.getString("status"));
                return cr;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    //thay đổi trạng thái request
    public void updateStatus(int request, String status) {
        String sql = "update customer_requests\n"
                + "set status = ?\n"
                + "where request_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, request);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    //xóa request
    public void deleteRequest(int orderItemId) {
        String sql = "delete from customer_requests\n"
                + "where order_item_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderItemId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}

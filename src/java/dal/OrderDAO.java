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
import java.time.format.DateTimeFormatter;
import model.Order;
import model.OrderItem;
import model.User;

/**
 *
 * @author dung0
 */
public class OrderDAO extends DBContext {

    MenuItemDAO mD = new MenuItemDAO();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //lấy toan bo order
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from orders";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order(rs.getInt("order_id"), rs.getInt("table_id"),
                        rs.getString("order_status"),
                        rs.getTimestamp("created_at").toLocalDateTime().format(df),
                        rs.getDouble("total_amount"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //lấy order bằng id
    public Order getOrderById(int oid) {
        String sql = "select * from orders where order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order o = new Order(rs.getInt("order_id"), rs.getInt("table_id"),
                        rs.getString("order_status"),
                        rs.getTimestamp("created_at").toLocalDateTime().format(df),
                        rs.getDouble("total_amount"));
                return o;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //sửa thông tin đơn hàng
    public void updateOrder(Order u, double total) {
        String sql = "update orders set table_id = ?,  order_status = ?, total_amount = ? where order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getTableId());
            st.setString(2, "pay");
            st.setDouble(3, total);
            st.setInt(4, u.getOrderId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //tạo order mới cho bàn
    public void createOrder(int tid) {
        String sql = "insert into orders(table_id, order_status) values(?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tid);
            st.setString(2, "unpaid");
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //xóa order
    public void deleteOrderById(int oid) {
        String sql = "delete from orders where order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    //get order gui sang cho khach
    public Order getOrderInfo(int tableId, String status) {
        String sql = "select * from orders where table_id = ? and order_status like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tableId);
            st.setString(2, "%" + status + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order o = new Order(rs.getInt("order_id"), rs.getInt("table_id"),
                        rs.getString("order_status"),
                        rs.getTimestamp("created_at").toLocalDateTime().format(df),
                        rs.getDouble("total_amount"));
                return o;
            }
        } catch (Exception e) {
        }
        return null;
    }

    //lấy tất cả order item trong order bằng order id
    public List<OrderItem> getAllItemInOrder(int oid) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "select * from order_items where order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderItem oi = new OrderItem(rs.getInt("order_item_id"), getOrderById(rs.getInt("order_id")),
                        mD.getMenuItemById(rs.getInt("item_id")), rs.getInt("quantity"),
                        rs.getString("item_status"));
                list.add(oi);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    //lấy tất cả order item trong order bằng order_item status
    public List<OrderItem> getAllItemInOrderByItemStatus(int oid, String status) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "select * from order_items where order_id = ? and item_status like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.setString(2, "%"+status+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderItem oi = new OrderItem(rs.getInt("order_item_id"), getOrderById(rs.getInt("order_id")),
                        mD.getMenuItemById(rs.getInt("item_id")), rs.getInt("quantity"),
                        rs.getString("item_status"));
                list.add(oi);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    //lấy tất cả order item trong order với item_status != status
    public List<OrderItem> getAllItemInOrderByDifferentStatus(int oid, String status) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "select * from order_items where order_id = ? and item_status not like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.setString(2, "%"+status+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderItem oi = new OrderItem(rs.getInt("order_item_id"), getOrderById(rs.getInt("order_id")),
                        mD.getMenuItemById(rs.getInt("item_id")), rs.getInt("quantity"),
                        rs.getString("item_status"));
                list.add(oi);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    //lay order_item bang order_item_id
    public OrderItem getOrderItemById(int order_item_id) {
        String sql = "select * from order_items where order_item_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, order_item_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderItem oi = new OrderItem(rs.getInt("order_item_id"), getOrderById(rs.getInt("order_id")),
                        mD.getMenuItemById(rs.getInt("item_id")), rs.getInt("quantity"),
                        rs.getString("item_status"));
                return oi;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    //get order_item by order_id and item_id
    public OrderItem checkOrderItem(int item_id, Order u) {
        String sql = "select * from order_items where item_id = ? and order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, item_id);
            st.setInt(2, u.getOrderId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderItem oi = new OrderItem(rs.getInt("order_item_id"), u,
                        mD.getMenuItemById(rs.getInt("item_id")), rs.getInt("quantity"),
                        rs.getString("item_status"));
                return oi;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    //get order_item by order_id and item_id and item_status not like unaccepted
    public OrderItem getOrderItemByDifferentStatus(int item_id, Order u, String status) {
        String sql = "select * from order_items where item_id = ? and order_id = ? and item_status not like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, item_id);
            st.setInt(2, u.getOrderId());
            st.setString(3, "%"+status+"%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderItem oi = new OrderItem(rs.getInt("order_item_id"), u,
                        mD.getMenuItemById(rs.getInt("item_id")), rs.getInt("quantity"),
                        rs.getString("item_status"));
                return oi;
            }
        } catch (Exception e) {
        }
        return null;
    }
    //get order_item by order_id and item_id and item_status
    public OrderItem getOrderItemInOrderByStatus(int item_id, Order u, String status) {
        String sql = "select * from order_items where item_id = ? and order_id = ? and item_status like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, item_id);
            st.setInt(2, u.getOrderId());
            st.setString(3, "%"+status+"%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderItem oi = new OrderItem(rs.getInt("order_item_id"), u,
                        mD.getMenuItemById(rs.getInt("item_id")), rs.getInt("quantity"),
                        rs.getString("item_status"));
                return oi;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    //check con mon nao cua table ay chua served ko
    public OrderItem getOrderItemNotServed(Order u) {
        String sql = "select * from order_items where order_id = ? and item_status not like 'served'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getOrderId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderItem oi = new OrderItem(rs.getInt("order_item_id"), u,
                        mD.getMenuItemById(rs.getInt("item_id")), rs.getInt("quantity"),
                        rs.getString("item_status"));
                return oi;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    //Neu item_id da ton tai thi update quantity cua order_item (dung ben for_user)
    public void updateOrderItem(OrderItem oi, int action) {
        String sql = "update order_items set quantity=? where order_id=? and order_item_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int change;
            if (action == 1) {
                change = oi.getQuantity() - 1;

            } else {
                change = oi.getQuantity() + 1;
            }
            st.setInt(1, change);
            st.setInt(2, oi.getOrderId().getOrderId());
            st.setInt(3, oi.getOrderItemId());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    // Dùng để update tình trạng order_item
    public void updateItemStatus(String status, int order_item_id) {
        String sql = "update order_items set item_status = ? where order_item_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, order_item_id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //insert order_item neu no chua ton tai trong hoa don
    public void insertOrderItem(int oid, int mid) {
        String sql = "insert into order_items(order_id, item_id, quantity) values(?,?,1)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.setInt(2, mid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    //delete order_item by order_id(dung de xoa item truoc khi xoa order)
    public void deleteOrderItemByOid(int oid) {
        String sql = "delete from order_items where order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    //dung de huy mon
    public void cancelOrderItem(int id, int oid) {
        String sql = "delete from order_items where order_id = ? and order_item_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    //dung de xoa item_id truoc khi xoa menu_item
    public void deleteItemId(int item_id) {
        String sql = "delete from order_items where item_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, item_id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //delete item by oid và status
    public void deleteItemByOidAndStatus(int oid, String status) {
        String sql = "delete from order_items where order_id = ? and item_status like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.setString(2, "%"+status+"%");
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //update quantity
    public void updateQuantity(int quantity, OrderItem oi){
        String sql = "update order_items set quantity = ? where order_id = ? and item_id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, oi.getOrderId().getOrderId());
            st.setInt(3, oi.getItemInMenu().getId());
            st.executeUpdate();
        } catch(SQLException e){
            
        }
    }
    
    //delete by status
    public void deleteOrderItemBYStatus(String status, OrderItem oi){
        String sql = "delete from order_items where order_id = ? and item_id = ? and item_status like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oi.getOrderId().getOrderId());
            st.setInt(2, oi.getItemInMenu().getId());
            st.setString(3, "%"+status+"%");
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //select sum quantity
    public int getQuantity(OrderItem oi){
        String sql = "select sum(quantity) as 'quantity' from order_items where order_id = ? and item_id= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oi.getOrderId().getOrderId());
            st.setInt(2, oi.getItemInMenu().getId());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getInt("quantity");
            }
        } catch (Exception e) {
        }
        return oi.getQuantity();
    }
    
    //dem so mon da duoc phuc vu ngay hom nay
    public int countFoodToday(){
        String sql = "select count(distinct item_id) as 'CountFood' from order_items oi inner join orders o on oi.order_id = o.order_id where CONVERT(DATE, o.created_at) = CONVERT(DATE, GETDATE())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getInt("CountFood");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
}

//private int orderItemId;
//    private Order orderId;
//    private Menu_item itemInMenu;
//    private int quantity;
//    private String item_status;

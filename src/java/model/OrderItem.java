/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.*;
import java.lang.*;


/**
 *
 * @author dung0
 */
public class OrderItem {
    private int orderItemId;
    private Order orderId;//update lại kiểu dữ liệu của orderId
    private MenuItem itemInMenu;//update lại kiểu dữ liệu của itemMenu
    private int quantity;
    private String item_status;

    public OrderItem() {
    }

    public OrderItem(int orderItemId, Order orderId, MenuItem itemInMenu, int quantity, String item_status) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemInMenu = itemInMenu;
        this.quantity = quantity;
        this.item_status = item_status;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public MenuItem getItemInMenu() {
        return itemInMenu;
    }

    public void setItemInMenu(MenuItem itemInMenu) {
        this.itemInMenu = itemInMenu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItem_status() {
        return item_status;
    }

    public void setItem_status(String item_status) {
        this.item_status = item_status;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "orderItemId=" + orderItemId + ", orderId=" + orderId + ", itemInMenu=" + itemInMenu + ", quantity=" + quantity + ", item_status=" + item_status + '}';
    }
    
}

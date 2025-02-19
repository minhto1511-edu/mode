/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lenha
 */
public class RequestDTO {
    private int requestId;
    private String requestTime;
    private String status;
    private String itemName;
    private int tableNumber;
    private int quantity;

    public RequestDTO() {
    }

    public RequestDTO(int requestId, String requestTime, String status, String itemName, int tableNumber, int quantity) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.status = status;
        this.itemName = itemName;
        this.tableNumber = tableNumber;
        this.quantity = quantity;
    }

    
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}

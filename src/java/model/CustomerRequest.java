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
public class CustomerRequest {
    private int requestId;
    private int orderItemId;
    private String requestTime;
    private String status;

    public CustomerRequest() {
    }

    public CustomerRequest(int requestId, int orderItemId, String requestTime, String status) {
        this.requestId = requestId;
        this.orderItemId = orderItemId;
        this.requestTime = requestTime;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
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
    
    
}

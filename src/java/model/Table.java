/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.*;
import java.lang.*;
import java.io.*;



/**
 *
 * @author Dung
 */
public class Table {
    private int tableId;
    private int tableNumber;
    private String status;
    private int capacity;

    public Table(int tableId, int tableNumber, String status, int capacity) {
        this.tableId = tableId;
        this.tableNumber = tableNumber;
        this.status = status;
        this.capacity = capacity;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}


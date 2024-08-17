/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Yuan
 */
public class CustomResponse {

    private String message = "";
    private boolean status;

    // Constructor
    public CustomResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public CustomResponse(){}
    // Getters
    public String getMessage() {
        return message;
    }

    public boolean getStatus() {
        return status;
    }

    // Setters (if you need to modify the fields after object creation)
    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

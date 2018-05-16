/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.util.Calendar;

/**
 *
 * @author adrianohrl
 */
public abstract class Loggable extends Person {
    
    private String username;
    private String password;
    private String email;

    public Loggable() {
    }

    public Loggable(String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(name, phone, dob, address, rg, cpf);
    }

    public Loggable(String username, String password, String email, String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(name, phone, dob, address, rg, cpf);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + username + ")";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}

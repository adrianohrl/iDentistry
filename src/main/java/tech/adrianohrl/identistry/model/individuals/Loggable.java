/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author adrianohrl
 */
@Entity
public abstract class Loggable extends Person {
    
    @Column(unique = true)
    private String username;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Configuration configuration;

    public Loggable() {
    }

    public Loggable(String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(name, phone, dob, address, rg, cpf);
        configuration = Configuration.getDefault();
    }

    public Loggable(String username, String password, String email, String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(name, phone, dob, address, rg, cpf, email);
        this.username = username;
        this.password = password;
        configuration = Configuration.getDefault();
    }
    
    public boolean authenticate(String username, char[] password) {
        Argon2 argon2 = Argon2Factory.create();
        return this.username.equals(username) && argon2.verify(this.password, password);
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

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.util.Calendar;
import javax.persistence.Entity;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Assistant extends Loggable {

    public Assistant() {
    }

    public Assistant(String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(name, phone, dob, address, rg, cpf);
    }

    public Assistant(String username, String password, String email, String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(username, password, email, name, phone, dob, address, rg, cpf);
    }
    
}

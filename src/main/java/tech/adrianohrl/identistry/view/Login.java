/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import tech.adrianohrl.identistry.model.individuals.Address;
import tech.adrianohrl.identistry.model.individuals.CRO;
import tech.adrianohrl.identistry.model.individuals.Dentist;
import tech.adrianohrl.identistry.model.individuals.Specialty;

/**
 *
 * @author adrianohrl
 */
public class Login {
    
    public static boolean authenticate(String username, char[] password) {
        Dentist marina = Login.getMarina();
        return marina.authenticate(username, password);
    }
    
    private static Dentist getMarina() {
        return null;
    }
    
}

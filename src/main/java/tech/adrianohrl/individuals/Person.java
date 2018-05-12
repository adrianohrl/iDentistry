/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.individuals;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author adrianohrl
 */
public class Person implements Comparable<Person>, Serializable {
    
    private String name;
    private String phone;
    private Calendar dob;
    private Address address;
    private String rg;
    private String cpf;

    public Person() {
    }

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Person(String name, String phone, Calendar dob, Address address) {
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
    }

    public Person(String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.rg = rg;
        this.cpf = cpf;
    }
    
    public int getAge() {
        Calendar today = new GregorianCalendar();
        return today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
    }
    
    public boolean isUnderage() {
        return getAge() < 18;
    }

    @Override
    public int compareTo(Person person) {
        return name.compareToIgnoreCase(person.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Person && equals((Person) obj);
    }
    
    public boolean equals(Person person) {
        return person != null && name.equalsIgnoreCase(person.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}

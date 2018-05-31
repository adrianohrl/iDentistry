/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Person implements Comparable<Person>, Serializable {
    
    @Id
    private String name;
    private String picture;
    private Genders gender;
    private String phone;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Calendar dob;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Address address;
    @Column(unique = true)
    private String rg;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private boolean whatsapp;
    @Column(unique = true)
    private String facebook;
    @Column(unique = true)
    private String instagram;
    private String occupation;

    public Person() {
    }

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Person(String name, String picture, String phone, Calendar dob, Address address) {
        this.name = name;
        this.picture = picture;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
    }

    public Person(String name, String picture, String phone, Calendar dob, Address address, String rg, String cpf, String occupation) {
        this.name = name;
        this.picture = picture;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.rg = rg;
        this.cpf = cpf;
        this.occupation = occupation;
    }

    public Person(String name, String picture, String phone, Calendar dob, Address address, String rg, String cpf, String email, String occupation) {
        this.name = name;
        this.picture = picture;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.occupation = occupation;
    }

    public Person(String name, String picture, Genders gender, String phone, Calendar dob, Address address, String rg, String cpf, String email, boolean whatsapp, String facebook, String instagram, String occupation) {
        this.name = name;
        this.picture = picture;
        this.gender = gender;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.whatsapp = whatsapp;
        this.facebook = facebook;
        this.instagram = instagram;
        this.occupation = occupation;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    
}

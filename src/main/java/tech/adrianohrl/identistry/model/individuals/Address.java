/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.Serializable;

/**
 *
 * @author adrianohrl
 */
public class Address implements Comparable<Address>, Serializable {
    
    private long code;
    private String street;
    private int number;
    private String area;
    private String obs;
    private String zip;
    private String city;
    private String state;
    private String country;

    public Address() {
    }

    public Address(String street, int number, String area, String obs, String zip, String city, String state, String country) {
        this.street = street;
        this.number = number;
        this.area = area;
        this.obs = obs;
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @Override
    public int compareTo(Address address) {
        return !country.equalsIgnoreCase(address.country) ? country.compareToIgnoreCase(address.country) :
                !state.equalsIgnoreCase(address.state) ? state.compareToIgnoreCase(address.street) :
                !city.equalsIgnoreCase(address.city) ? city.compareToIgnoreCase(address.city) :
                !area.equalsIgnoreCase(address.area) ? area.compareToIgnoreCase(address.area) :
                !street.equalsIgnoreCase(address.street) ? street.compareToIgnoreCase(address.street) :
                number - address.number;
    }

    @Override
    public String toString() {
        return street + ", " + number + ", " + area + ", " + zip + ", " + city + ", " + state + ", " + country;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Address && equals((Address) obj);
    }
    
    public boolean equals(Address address) {
        return address != null && country.equalsIgnoreCase(address.country) 
                && state.equalsIgnoreCase(address.state) && city.equalsIgnoreCase(address.city) 
                && area.equalsIgnoreCase(address.area) && street.equalsIgnoreCase(address.street)
                && number == address.number;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}

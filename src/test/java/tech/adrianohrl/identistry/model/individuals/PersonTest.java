/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author adrianohrl
 */
public class PersonTest {
    
    public PersonTest() {
    }

    @Test
    public void testIsValidPhone() {
        Person p = new Person();
        p.setPhone("(95) 8965-3831");
        Assert.assertTrue(p.isValidPhone());
        p.setPhone("(95) 28965-3831");
        Assert.assertTrue(p.isValidPhone());
        p.setPhone("(95) 28965-38231");
        Assert.assertFalse(p.isValidPhone());
        p.setPhone("(95) 1233-38231");
        Assert.assertFalse(p.isValidPhone());
    }

    @Test
    public void testIsValidEmail() {
        Person p = new Person();
        p.setEmail("example@domain.com");
        Assert.assertTrue(p.isValidEmail());
        p.setEmail("exam.ple2@domain.com.br");
        Assert.assertTrue(p.isValidEmail());
        p.setEmail("exam@ple2@domain.com.br");
        Assert.assertFalse(p.isValidEmail());
    }

    @Test
    public void testIsValidCPF() {
        Person p = new Person();
        p.setCpf("546.471.429-49");
        Assert.assertTrue(p.isValidCPF());
        p.setCpf("54647142949");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("546.471429-49");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("546.471.42949");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("546471429-49");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("546.471429-49");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("111.111.111-11");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("222.222.222-22");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("333.333.333-33");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("444.444.444-44");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("555.555.555-55");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("666.666.666-66");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("777.777.777-77");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("888.888.888-88");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("999.999.999-99");
        Assert.assertFalse(p.isValidCPF());
        p.setCpf("000.000.000-00");
        Assert.assertFalse(p.isValidCPF());
    }

    @Test
    public void testIsUnderage() {
        Person p = new Person();
        p.setDob(new GregorianCalendar(1990, Calendar.MARCH, 17));
        Assert.assertFalse(p.isUnderage());
        p.setDob(new GregorianCalendar());
        Assert.assertTrue(p.isUnderage());
    }
    
}

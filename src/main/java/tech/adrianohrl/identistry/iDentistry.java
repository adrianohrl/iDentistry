/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry;

import tech.adrianohrl.identistry.model.dentitions.Dentition;
import tech.adrianohrl.identistry.model.dentitions.factory.DeciduousDentitionFactory;
import tech.adrianohrl.identistry.model.dentitions.factory.DentitionFactory;
import tech.adrianohrl.identistry.model.dentitions.factory.PermanentDentitionFactory;

/**
 *
 * @author adrianohrl
 */
public class iDentistry {
    
    public static void main(String[] args) {
        DentitionFactory factory = new DeciduousDentitionFactory();
        Dentition dentition = factory.create();
        System.out.println("" + dentition + "\n\n");
        factory = new PermanentDentitionFactory();
        dentition = factory.create();
        System.out.println("" + dentition + "\n\n");
    }
    
}

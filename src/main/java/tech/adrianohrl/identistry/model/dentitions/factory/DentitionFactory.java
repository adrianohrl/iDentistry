/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.dentitions.factory;

import tech.adrianohrl.identistry.model.dentitions.Dentition;

/**
 *
 * @author adrianohrl
 */
public abstract class DentitionFactory {
    
    protected static int[] numberings = {11, 12, 13, 14, 15, 16, 17, 18,
                                         21, 22, 23, 24, 25, 26, 27, 28,
                                         31, 32, 33, 34, 35, 36, 37, 38,
                                         41, 42, 43, 44, 45, 46, 47, 48,
                                         51, 52, 53, 54, 55,
                                         61, 62, 63, 64, 65,
                                         71, 72, 73, 74, 75,
                                         81, 82, 83, 84, 85};
    
    public abstract Dentition create();
    
}

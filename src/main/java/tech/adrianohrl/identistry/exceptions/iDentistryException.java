/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.exceptions;

import org.apache.log4j.Logger;

/**
 *
 * @author adrianohrl
 */
public class iDentistryException extends RuntimeException {
    
    private static final Logger log = Logger.getLogger(iDentistryException.class);

    public iDentistryException(String message) {
        super(message);
        log.error(message);
    }
    
}

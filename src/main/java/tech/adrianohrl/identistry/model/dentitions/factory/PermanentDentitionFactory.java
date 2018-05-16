/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.dentitions.factory;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import tech.adrianohrl.identistry.model.dentitions.Dentition;
import tech.adrianohrl.identistry.model.dentitions.Dentitions;
import tech.adrianohrl.identistry.model.dentitions.Stages;
import tech.adrianohrl.identistry.model.dentitions.Tooth;

/**
 *
 * @author adrianohrl
 */
public class PermanentDentitionFactory extends DentitionFactory {
    
    private static Logger log = Logger.getLogger(PermanentDentitionFactory.class);

    @Override
    public Dentition create() {
        List<Tooth> teeth = new ArrayList<>();
        log.debug("Creating a Permanent Dentition via its factory...");
        for (int numbering : DentitionFactory.numberings) {
            Tooth tooth = new Tooth(numbering, true, Dentitions.isDeciduous(numbering));
            log.trace("Numbering: " + numbering + ", Tooth: " + tooth);
            teeth.add(tooth);
        }     
        log.debug("Number of teeth: " + teeth.size());
        return new Dentition(Stages.PERMANENT, teeth);
    }
    
}

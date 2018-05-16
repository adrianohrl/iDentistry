/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.dentitions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author adrianohrl
 */
public class Dentition implements Iterable<Tooth> {
    
    private long code;
    private Stages stage;
    private List<Tooth> teeth = new ArrayList<>();

    public Dentition() {
    }

    public Dentition(Stages stage, List<Tooth> teeth) {
        this.stage = stage;
        this.teeth = teeth;
    }

    @Override
    public Iterator<Tooth> iterator() {
        return teeth.iterator();
    }

    @Override
    public String toString() {
        String aux = stage + ":\n";
        for (Tooth tooth : this) {
            aux += "\n\t" + tooth;
        }
        aux += "\n\n\tVisible Teeth:";
        for (Tooth tooth : getVisibleTeeth()) {
            aux += "\n\t\t" + tooth;
        }
        return aux;
    }
    
    public Tooth getTooth(int numbering) {
        int index = teeth.indexOf(numbering);
        return index != -1 ? teeth.get(index) : null;
    }
    
    public List<Tooth> getEruptedTeeth() {
        List<Tooth> eruptedTeeth = new ArrayList<>();
        for (Tooth tooth : this) {
            if (tooth.isErupted()) {
                eruptedTeeth.add(tooth);
            }
        }
        return eruptedTeeth;
    }
    
    public List<Tooth> getVisibleTeeth() {
        List<Tooth> visibleTeeth = new ArrayList<>();
        for (Tooth tooth : this) {
            if (tooth.isErupted() && !tooth.isExtracted()) {
                visibleTeeth.add(tooth);
            }
        }
        return visibleTeeth;
    }
    
    public List<Tooth> getExtractedTeeth() {
        List<Tooth> extractedTeeth = new ArrayList<>();
        for (Tooth tooth : this) {
            if (tooth.isErupted() && !tooth.isExtracted()) {
                extractedTeeth.add(tooth);
            }
        }
        return extractedTeeth;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Stages getStage() {
        return stage;
    }

    public void setStage(Stages stage) {
        this.stage = stage;
    }

    public List<Tooth> getTeeth() {
        return teeth;
    }

    public void setTeeth(List<Tooth> teeth) {
        this.teeth = teeth;
    }
    
}

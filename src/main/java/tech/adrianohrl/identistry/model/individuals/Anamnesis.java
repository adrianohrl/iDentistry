/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Anamnesis implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    private String mainComplaint = "";
    private boolean healingProblem = false;
    private boolean anesthesiaProblem = false;
    private boolean antibioticProblem = false;
    private boolean bleedingProblem = false;
    private boolean sensitiveTeeth = false;
    private boolean bleedingGingiva = false;
    private boolean floss = true;
    private boolean pregnancy = false;
    private String allergy = "";
    private String disases = "";
    private String medicalTreatment = "";
    private String medicines = "";
    private String habits = "";
    private String obs = "";

    public Anamnesis() {
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMainComplaint() {
        return mainComplaint;
    }

    public void setMainComplaint(String mainComplaint) {
        this.mainComplaint = mainComplaint;
    }

    public boolean isHealingProblem() {
        return healingProblem;
    }

    public void setHealingProblem(boolean healingProblem) {
        this.healingProblem = healingProblem;
    }

    public boolean isAnesthesiaProblem() {
        return anesthesiaProblem;
    }

    public void setAnesthesiaProblem(boolean anesthesiaProblem) {
        this.anesthesiaProblem = anesthesiaProblem;
    }

    public boolean isAntibioticProblem() {
        return antibioticProblem;
    }

    public void setAntibioticProblem(boolean antibioticProblem) {
        this.antibioticProblem = antibioticProblem;
    }

    public boolean isBleedingProblem() {
        return bleedingProblem;
    }

    public void setBleedingProblem(boolean bleedingProblem) {
        this.bleedingProblem = bleedingProblem;
    }

    public boolean isSensitiveTeeth() {
        return sensitiveTeeth;
    }

    public void setSensitiveTeeth(boolean sensitiveTeeth) {
        this.sensitiveTeeth = sensitiveTeeth;
    }

    public boolean isBleedingGingiva() {
        return bleedingGingiva;
    }

    public void setBleedingGingiva(boolean bleedingGingiva) {
        this.bleedingGingiva = bleedingGingiva;
    }

    public boolean isFloss() {
        return floss;
    }

    public void setFloss(boolean floss) {
        this.floss = floss;
    }

    public boolean isPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(boolean pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getDisases() {
        return disases;
    }

    public void setDisases(String disases) {
        this.disases = disases;
    }

    public String getMedicalTreatment() {
        return medicalTreatment;
    }

    public void setMedicalTreatment(String medicalTreatment) {
        this.medicalTreatment = medicalTreatment;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
}

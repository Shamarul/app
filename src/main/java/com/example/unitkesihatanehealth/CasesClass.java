package com.example.unitkesihatanehealth;

public class CasesClass {
    public String condition, symptoms, status, name, instruction;

    public CasesClass() {
    }

    public CasesClass (String condition, String symptoms, String status, String name, String instruction) {
        this.condition = condition;
        this.symptoms = symptoms;
        this.status = status;
        this.name = name;
        this.instruction = instruction;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}

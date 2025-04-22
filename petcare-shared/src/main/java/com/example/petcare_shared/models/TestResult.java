package com.example.petcare_shared.models;

public class TestResult {
    private String id;
    private String petId;
    private String date;
    private String type;
    private String result;

    public TestResult() {}

    public TestResult(String id, String petId, String date, String type, String result) {
        this.id = id;
        this.petId = petId;
        this.date = date;
        this.type = type;
        this.result = result;
    }

    public String getId() { return id; }
    public String getPetId() { return petId; }
    public String getDate() { return date; }
    public String getType() { return type; }
    public String getResult() { return result; }

    public void setId(String id) { this.id = id; }
    public void setPetId(String petId) { this.petId = petId; }
    public void setDate(String date) { this.date = date; }
    public void setType(String type) { this.type = type; }
    public void setResult(String result) { this.result = result; }
}

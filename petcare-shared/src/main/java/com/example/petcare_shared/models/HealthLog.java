package com.example.petcare_shared.models;
public class HealthLog {
    private String id;
    private String petId;
    private String date;
    private String note;
    private String writtenBy; // "owner" or "vet"

    public HealthLog() {}

    public HealthLog(String id, String petId, String date, String note, String writtenBy) {
        this.id = id;
        this.petId = petId;
        this.date = date;
        this.note = note;
        this.writtenBy = writtenBy;
    }

    public String getId() { return id; }
    public String getPetId() { return petId; }
    public String getDate() { return date; }
    public String getNote() { return note; }
    public String getWrittenBy() { return writtenBy; }

    public void setId(String id) { this.id = id; }
    public void setPetId(String petId) { this.petId = petId; }
    public void setDate(String date) { this.date = date; }
    public void setNote(String note) { this.note = note; }
    public void setWrittenBy(String writtenBy) { this.writtenBy = writtenBy; }
}

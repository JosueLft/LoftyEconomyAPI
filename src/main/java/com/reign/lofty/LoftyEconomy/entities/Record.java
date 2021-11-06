package com.reign.lofty.LoftyEconomy.entities;

import com.reign.lofty.LoftyEconomy.enums.RecordCategory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Record implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    private String uid;
    private String description;
    private String date;
    private Double value;
    private Integer recordCategory;

    public Record() {}

    public Record(String uid, String description, String date, Double value, RecordCategory recordCategory) {
        this.uid = uid;
        this.description = description;
        this.date = date;
        this.value = value;
        setRecordCategory(recordCategory);
    }

    public Record(String description, String date, Double value, int recordCategory) {
        this.description = description;
        this.date = date;
        this.value = value;
        this.recordCategory = recordCategory;
    }

    public Record(String description, Double value) {
        this.description = description;
        this.value = value;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public RecordCategory getRecordCategory() {
        return RecordCategory.valueOf(recordCategory);
    }
    public void setRecordCategory(RecordCategory recordCategory) {
        if(recordCategory != null) {
            this.recordCategory = recordCategory.getCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return uid == record.uid;
    }
    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
    @Override
    public String toString() {
        return String.format(
                "UID: %s\nDescription: %s\nValor: %f\nData: %s\nCategoria: %s\n",
                this.getUid(),
                this.getDescription(),
                this.getValue(),
                this.getDate(),
                this.getRecordCategory()
        );
    }
}
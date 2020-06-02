package com.app.entities;


import javax.persistence.*;

@Entity
@Table(name = "statistics_table_new")
public class StatisticEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "process_id")
    private Integer processId;

    @Column(name = "total_count")
    private Integer totalCount;

    @Column(name = "unique_number")
    private Integer uniqueNumber;

    @Column(name = "valid_number")
    private Integer validNumber;

    @Column(name = "min_value")
    private Double minValue;

    @Column(name = "max_value")
    private Double maxValue;

    @Column(name = "popular_value")
    private Double popularValue;

    public StatisticEntity() {
        setMaxValue((double)0);
        setMinValue((double)0);
        setUniqueNumber(0);
        setValidNumber(0);
    }

    public void incValidNumber() {this.validNumber++;}

    public void incUniqueNumber() {this.uniqueNumber++;}

    public Double getMinValue() {
        return this.minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(Integer uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public Integer getValidNumber() {
        return validNumber;
    }

    public void setValidNumber(Integer validNumber) {
        this.validNumber = validNumber;
    }

    public Double getPopularValue() {
        return popularValue;
    }

    public void setPopularValue(Double popularValue) {
        this.popularValue = popularValue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

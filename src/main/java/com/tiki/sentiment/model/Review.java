package com.tiki.sentiment.model;

import javax.persistence.*;

@Entity
@Table(name = "sentiment_review")
public class Review {
    @GeneratedValue
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerId) {
        this.customer = customerId;
    }

    @Basic
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", content='" + content + '\'' +
                ", version=" + version +
                '}';
    }
}

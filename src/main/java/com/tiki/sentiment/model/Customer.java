package com.tiki.sentiment.model;

import javax.persistence.*;

@Entity
@Table(name = "sentiment_customer")
public class Customer {


    @Basic
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String basic) {
        this.name = basic;
    }

    @GeneratedValue
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

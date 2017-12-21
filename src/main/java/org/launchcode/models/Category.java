package org.launchcode.models;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category {
    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min=3, max=15)
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @RequestMapping("")
    public String indexHandler(){
        return "index";
    }
}

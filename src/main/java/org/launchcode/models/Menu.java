package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {
    @NotNull
    @Size(min=3, max=15)
    private String name;


    @Id
    @GeneratedValue
    int id;

    @ManyToMany
    List<Cheese> cheeses;

    public void addItem( Cheese item ) {
        // TODO: make sure this sucka don't repeat
        cheeses.add( item );
    }


    public Menu(){}

    public Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

}

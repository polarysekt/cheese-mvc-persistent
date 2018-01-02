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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min=3, max=15)
    private String name;

    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue
    int id;

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    @ManyToMany
    List<Cheese> cheeses;

    public void addItem( Cheese item ) {
        // todo make sure this sucka don't repeat
        cheeses.add( item );
    }


    public Menu(){}

    public Menu(String name) {
        this.name = name;
    }
}

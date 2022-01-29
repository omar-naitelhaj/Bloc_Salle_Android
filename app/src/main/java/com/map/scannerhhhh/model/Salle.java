package com.map.scannerhhhh.model;

public class Salle {
    private String id;
    private String name;
    private String type;
    private Bloc bloc;
    private int occupied;


    public Salle(String id, String name, String type, Bloc bloc, int occupied) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.bloc = bloc;
        this.occupied = occupied;
    }

    public Salle(String name, String type, Bloc bloc, int occupied) {
        this.name = name;
        this.type = type;
        this.bloc = bloc;
        this.occupied = occupied;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bloc getBloc() {
        return bloc;
    }

    public String getBlocName() {
        return bloc.getName();
    }

    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", bloc=" + bloc +
                '}';
    }
}


package org.example;

public class Tarea {
    private int id;
    private String descripcion;
    private boolean completada;

    public Tarea() {
        this.id = id;
        this.descripcion = descripcion;
        this.completada = completada;
    }
// Constructor, getters, setters, etc.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}

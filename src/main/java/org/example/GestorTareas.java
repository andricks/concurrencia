package org.example;

import java.util.ArrayList;
import java.util.List;

public class GestorTareas implements IGestorTareas {

    private List<Tarea> tareas = new ArrayList<>();
    private Object lockObject = new Object(); // Para sincronización de acceso concurrente

    @Override
    public void agregarTarea(Tarea tarea) {
        synchronized (lockObject) {
            tareas.add(tarea);
        }
    }

    @Override
    public void actualizarTarea(Tarea tarea) {
        synchronized (lockObject) {
            Tarea tareaExistente = tareas.stream()
                    .filter(t -> t.getId() == tarea.getId())
                    .findFirst()
                    .orElse(null);

            if (tareaExistente != null) {
                tareaExistente.setDescripcion(tarea.getDescripcion());
                tareaExistente.setCompletada(tarea.isCompletada());
            }
        }
    }

    @Override
    public void eliminarTarea(int idTarea) {
        synchronized (lockObject) {
            tareas.removeIf(t -> t.getId() == idTarea);
        }
    }

    @Override
    public List<Tarea> obtenerTareasPendientes() {
        synchronized (lockObject) {
            List<Tarea> tareasPendientes = new ArrayList<>();
            for (Tarea tarea : tareas) {
                if (!tarea.isCompletada()) {
                    tareasPendientes.add(tarea);
                }
            }
            return tareasPendientes;
        }
    }

    @Override
    public List<Tarea> obtenerTareasCompletadas() {
        synchronized (lockObject) {
            List<Tarea> tareasCompletadas = new ArrayList<>();
            for (Tarea tarea : tareas) {
                if (tarea.isCompletada()) {
                    tareasCompletadas.add(tarea);
                }
            }
            return tareasCompletadas;
        }
    }
}

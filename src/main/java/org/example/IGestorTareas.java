package org.example;
import java.util.List;



    public interface IGestorTareas {
        void agregarTarea(Tarea tarea);
        void actualizarTarea(Tarea tarea);
        void eliminarTarea(int idTarea);
        List<Tarea> obtenerTareasPendientes();
        List<Tarea> obtenerTareasCompletadas();
    }


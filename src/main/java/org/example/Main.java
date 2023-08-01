package org.example;
import java.util.Scanner;
import java.util.List;
public class Main {
    private static IGestorTareas gestorTareas = new GestorTareas();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        while (true) {
            System.out.println("------------ GESTIÓN DE TAREAS ------------");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Actualizar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Mostrar tareas pendientes");
            System.out.println("5. Mostrar tareas completadas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    agregarTarea();
                    break;
                case "2":
                    actualizarTarea();
                    break;
                case "3":
                    eliminarTarea();
                    break;
                case "4":
                    mostrarTareasPendientes();
                    break;
                case "5":
                    mostrarTareasCompletadas();
                    break;
                case "6":
                    System.out.println("Gracias por usar la aplicación. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    public static void agregarTarea() {
        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion = scanner.nextLine();

        Tarea tarea = new Tarea();
        tarea.setId(obtenerNuevoId());
        tarea.setDescripcion(descripcion);
        tarea.setCompletada(false);

        gestorTareas.agregarTarea(tarea);
        System.out.println("Tarea agregada con éxito.");
    }

    public static void actualizarTarea() {
        System.out.print("Ingrese el ID de la tarea a actualizar: ");
        int idTarea = Integer.parseInt(scanner.nextLine());

        Tarea tarea = obtenerTareaPorId(idTarea);

        if (tarea != null) {
            System.out.print("Ingrese la nueva descripción de la tarea: ");
            String descripcion = scanner.nextLine();

            System.out.print("¿La tarea está completada? (S/N): ");
            String completada = scanner.nextLine();

            tarea.setDescripcion(descripcion);
            tarea.setCompletada(completada.equalsIgnoreCase("S"));
            System.out.println("Tarea actualizada con éxito.");
        } else {
            System.out.println("Tarea no encontrada. Intente nuevamente.");
        }
    }

    public static void eliminarTarea() {
        System.out.print("Ingrese el ID de la tarea a eliminar: ");
        int idTarea = Integer.parseInt(scanner.nextLine());

        Tarea tarea = obtenerTareaPorId(idTarea);

        if (tarea != null) {
            gestorTareas.eliminarTarea(idTarea);
            System.out.println("Tarea eliminada con éxito.");
        } else {
            System.out.println("Tarea no encontrada. Intente nuevamente.");
        }
    }

    public static void mostrarTareasPendientes() {
        System.out.println("------------ TAREAS PENDIENTES ------------");
        List<Tarea> tareasPendientes = gestorTareas.obtenerTareasPendientes();
        mostrarTareas(tareasPendientes);
    }

    public static void mostrarTareasCompletadas() {
        System.out.println("------------ TAREAS COMPLETADAS ------------");
        List<Tarea> tareasCompletadas = gestorTareas.obtenerTareasCompletadas();
        mostrarTareas(tareasCompletadas);
    }

    public static void mostrarTareas(List<Tarea> tareas) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
        } else {
            for (Tarea tarea : tareas) {
                System.out.println("ID: " + tarea.getId() + ", Descripción: " + tarea.getDescripcion() +
                        ", Completada: " + (tarea.isCompletada() ? "Sí" : "No"));
            }
        }
        System.out.println("------------------------------------------");
    }

    public static int obtenerNuevoId() {
        return gestorTareas.obtenerTareasPendientes().size() + gestorTareas.obtenerTareasCompletadas().size() + 1;
    }

    public static Tarea obtenerTareaPorId(int idTarea) {
        for (Tarea tarea : gestorTareas.obtenerTareasPendientes()) {
            if (tarea.getId() == idTarea) {
                return tarea;
            }
        }
        for (Tarea tarea : gestorTareas.obtenerTareasCompletadas()) {
            if (tarea.getId() == idTarea) {
                return tarea;
            }
        }
        return null;
    }
}


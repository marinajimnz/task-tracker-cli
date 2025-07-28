import java.util.ArrayList;

public class TaskCLI {
    public static void main(String[] args) throws Exception {
        // Pruebas
        Task task1 = new Task( "Desayunar");
        Task task2 = new Task( "Comer");
        Task task3 = new Task( "Merendar");
        Task task4 = new Task( "Cenar");

        ArrayList<Task> listaTareas = new ArrayList<>();
        listaTareas.add(task1);
        listaTareas.add(task2);
        listaTareas.add(task3);
        listaTareas.add(task4);
        
    }
}

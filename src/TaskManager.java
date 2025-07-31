import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Encargado de gestionar todas las tareas:
 * Añadir, eliminar, modificar y guardar en JSON.
 */
public class TaskManager {

    // ---- ATRIBUTOS ----
    /**
     * Atributo que guarda las tareas que va añadiendo el usuario.
     */
    private ArrayList<Task> tasks;

    /**
     * Atributo del path del archivo .json.
     */
    private final Path FILE_PATH = Path.of("tasks.json");

    // ---- CONSTRUCTOR ----
    /**
     * Inicializa el gestor de tareas y carga el archivo JSON.
     * 
     * @param filePath Ruta del archivo JSON.
     */
    public TaskManager() {
        this.tasks = loadTasks();
    }

    // ---- MÉTODOS ----
    /**
     * Añade una nueva tarea con la descripción dada.
     * 
     * @param description Descripción de la tarea.
     */
    public void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("Task added successfully (ID: " + newTask.getId());
    }

    /**
     * Actualiza la descripción de una tarea existente.
     * 
     * @param id             ID de la tarea.
     * @param newDescription Nueva descripción.
     */
    public void updateTask(int id, String newDescription) {
        Task taskToUpdate = getTaskById(id);
        taskToUpdate.setDescription(newDescription);
    }

    /**
     * Elimina una tarea por ID.
     * 
     * @param id ID de la tarea a eliminar.
     */
    public void deleteTask(int id) {
        Task taskToDelete = getTaskById(id);
        tasks.remove(taskToDelete);
    }

    /**
     * Cambia el estado de una tarea a "in-progress".
     * 
     * @param id ID de la tarea.
     */
    public void markInProgress(int id) {
        Task taskToUpdate = getTaskById(id);
        taskToUpdate.setStatus(Status.IN_PROGRESS);
    }

    /**
     * Cambia el estado de una tarea a "done".
     * 
     * @param id ID de la tarea.
     */
    public void markDone(int id) {
        Task taskToUpdate = getTaskById(id);
        taskToUpdate.setStatus(Status.DONE);
    }

    /**
     * Lista todas las tareas.
     */
    public void listAllTasks() {
        for(Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    /**
     * Lista todas las tareas filtradas por estado.
     * 
     * @param status Estado: "to-do", "in-progress", "done".
     */
    public void listTasksByStatus(Status status) {
            for(Task task : tasks) {
                if(task.getStatus().equals(status)) {
                    System.out.println(task.toString());
                }
        }
    }

    /**
     * Guarda todas las tareas actuales en el archivo JSON.
     */
    public void saveTasks() {
        ArrayList<String> jsonTasks = new ArrayList<>();
        for(Task task : tasks) {
            jsonTasks.add(task.toJson());
        }

        String jsonContent = "[\n" + String.join(",\n", jsonTasks) + "\n]"; // Añade corchetes al principio y final del JSON

        try { // Intenta escribir en el archivo
            Files.writeString(FILE_PATH, jsonContent);
        } catch(IOException e) { // Si no lo consigue
            System.out.println("Tasks couldn't be saved to the JSON file.");
        }
    }

    /**
     * Carga las tareas desde el archivo JSON.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) { // Si el archivo no existe
            throw new IllegalArgumentException("The file named tasks.json doensn't exist.");
        }

        // Si el archivo existe.
        try {
            String fileContent = Files.readString(FILE_PATH);
            if (fileContent.startsWith("[") && fileContent.endsWith("]")) {
                fileContent = fileContent.substring(1, fileContent.length() - 1).trim();

                // Separa los objetos por "},"
                String[] taskArray = fileContent.split("(?<=\\}),\\s*");

                for (String taskJson : taskArray) {
                    taskJson = taskJson.trim();

                    // Asegura que cada objeto termina con }.
                    if (!taskJson.endsWith("}")) {
                        taskJson += "}";
                    }

                    taskList.add(Task.fromJson(taskJson));
                }
            }
        } catch (IOException e) {
            System.out.println("The file couldn't be read.");
        }

        return taskList;
    }

    /**
     * Busca una tarea por su ID.
     * 
     * @param id ID de la tarea.
     * @return Tarea encontrada o null si no existe.
     */
    public Task getTaskById(int id) {
        for(Task task : tasks) {
            if(task.getId() == id) {
                return task;
            }
        }

        return null;
    }
}

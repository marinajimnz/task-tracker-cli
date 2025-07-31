import java.nio.file.Path;
import java.util.List;

/**
 * Encargado de gestionar todas las tareas:
 * Añadir, eliminar, modificar y guardar en JSON
 */
public class TaskManager {

    // ---- ATRIBUTOS ----
    private List<Task> tasks;
    private final Path filePath = Path.of("tasks.json");

    // ---- CONSTRUCTOR ----
    /**
     * Inicializa el gestor de tareas y carga el archivo JSON.
     * @param filePath Ruta del archivo JSON.
     */
    public TaskManager(String filePath);

    // ---- MÉTODOS ----
    /**
     * Añade una nueva tarea con la descripción dada.
     * @param description Descripción de la tarea.
     */
    public void addTask(String description);

    /**
     * Actualiza la descripción de una tarea existente.
     * @param id ID de la tarea.
     * @param newDescription Nueva descripción.
     */
    public void updateTask(int id, String newDescription);

    /**
     * Elimina una tarea por ID.
     * @param id ID de la tarea a eliminar.
     */
    public void deleteTask(int id);

    /**
     * Cambia el estado de una tarea a "in-progress".
     * @param id ID de la tarea.
     */
    public void markInProgress(int id);

    /**
     * Cambia el estado de una tarea a "done".
     * @param id ID de la tarea.
     */
    public void markDone(int id);

    /**
     * Lista todas las tareas.
     */
    public List<Task> listAllTasks();

    /**
     * Lista todas las tareas filtradas por estado.
     * @param status Estado: "to-do", "in-progress", "done".
     */
    public List<Task> listTasksByStatus(String status);

    /**
     * Guarda todas las tareas actuales en el archivo JSON.
     */
    public void saveTasks();

    /**
     * Carga las tareas desde el archivo JSON.
     */
    public void loadTasks();

    /**
     * Busca una tarea por su ID.
     * @param id ID de la tarea.
     * @return Tarea encontrada o null si no existe.
     */
    public Task getTaskById(int id);
}

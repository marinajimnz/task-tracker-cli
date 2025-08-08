import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Responsible for managing all tasks:
 * Add, delete, modify and save to JSON.
 */
public class TaskManager {

    // ---- ATTRIBUTES ----
    /**
     * Attribute that stores the tasks that the user adds.
     */
    private ArrayList<Task> tasks;

    /**
     * Attribute for the .json file path.
     */
    private final Path FILE_PATH = Path.of("tasks.json");

    // ---- CONSTRUCTOR ----
    /**
     * Initializes the task manager and loads the JSON file.
     * 
     * @param filePath Path to the JSON file.
     */
    public TaskManager() {
        this.tasks = loadTasks();
    }

    // ---- METHODS ----
    /**
     * Adds a new task with the given description.
     * 
     * @param description Task description.
     */
    public void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("Task added successfully (ID: " + newTask.getId() + ")");
    }

    /**
     * Updates the description of an existing task.
     * 
     * @param id             Task ID.
     * @param newDescription New description.
     */
    public void updateTask(int id, String newDescription) {
        Task taskToUpdate = getTaskById(id);
        if (taskToUpdate != null) {
            taskToUpdate.setDescription(newDescription);
            System.out.println("Task with id " + id + " updated successfully");
        } else {
            System.out.println("ID: " + id + " doesn't exist.");
        }

    }

    /**
     * Deletes a task by ID.
     * 
     * @param id ID of the task to delete.
     */
    public void deleteTask(int id) {
        Task taskToDelete = getTaskById(id);
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            System.out.println("Task with id " + id + " deleted successfully");
            saveTasks();
        } else {
            System.out.println("ID: " + id + " doesn't exist.");
        }

    }

    /**
     * Changes the status of a task.
     * 
     * @param id Task ID.
     */
    public void updateStatus(int id, Status status) {
        Task taskToUpdate = getTaskById(id);
        if (taskToUpdate != null) {
            taskToUpdate.setStatus(status);
            System.out.println("Task with id " + id + " updated successfully to status: " + status);
            saveTasks();
        } else {
            System.out.println("ID: " + id + " doesn't exist.");
        }
    }

    /**
     * Changes the task status to "in-progress".
     * 
     * @param id Task ID.
     */
    public void markInProgress(int id) {
        updateStatus(id, Status.IN_PROGRESS);
        saveTasks();
    }

    /**
     * Changes the task status to "done".
     * 
     * @param id Task ID.
     */
    public void markDone(int id) {
        updateStatus(id, Status.DONE);
        saveTasks();
    }

    /**
     * Lists all tasks.
     */
    public void listAllTasks() {
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    /**
     * Lists all tasks filtered by status.
     * 
     * @param status Status: "to-do", "in-progress", "done".
     */
    public void listTasksByStatus(Status status) {
        for (Task task : tasks) {
            if (task.getStatus().equals(status)) {
                System.out.println(task.toString());
            }
        }
    }

    /**
     * Saves all current tasks to the JSON file.
     */
    public void saveTasks() {
        ArrayList<String> jsonTasks = new ArrayList<>();
        for (Task task : tasks) {
            jsonTasks.add(task.toJson());
        }

        String jsonContent = "[\n" + String.join(",\n", jsonTasks) + "\n]"; // Adds brackets at the beginning and end of
                                                                            // the JSON

        try { // Attempts to write to the file
            Files.writeString(FILE_PATH, jsonContent);
        } catch (IOException e) { // If it doesn't succeed
            System.out.println("Tasks couldn't be saved to the JSON file.");
        }
    }

    /**
     * Loads tasks from the JSON file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) { // If the file doesn't exist
            return taskList;
        }

        // If the file exists.
        try {
            String fileContent = Files.readString(FILE_PATH);
            if (fileContent.startsWith("[") && fileContent.endsWith("]")) {
                fileContent = fileContent.substring(1, fileContent.length() - 1).trim();

                // Key verification to avoid error with empty file (only "[]")
                if (fileContent.isEmpty()) {
                    return taskList;
                }

                // Separates objects by "},"
                String[] taskArray = fileContent.split("(?<=\\}),\\s*");

                for (String taskJson : taskArray) {
                    taskJson = taskJson.trim();

                    // Ensures each object ends with }
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
     * Searches for a task by its ID.
     * 
     * @param id Task ID.
     * @return Found task or null if it doesn't exist.
     */
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }
}

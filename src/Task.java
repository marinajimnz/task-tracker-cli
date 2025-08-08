import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Individual task of the Task Tracker.
 */
public class Task {

    // ---- ATTRIBUTES ----
    /**
     * Attribute that represents the task id.
     */
    private int id;

    /**
     * Attribute that represents the task id.
     */
    private static int nextId = 1;

    /**
     * Attribute that represents the description (text) of the task.
     */
    private String description;

    // TO-DO Create ENUM for states
    /**
     * Attribute that represents the task status.
     * Can be: to-do, in-progress or done.
     */
    private Status status;

    /**
     * Attribute that represents the date when the task is created.
     * In ISO 8601 format.
     */
    private String createdAt;

    /**
     * Attribute that represents the date when the task is modified.
     */
    private String updatedAt = null;

    // ---- CONSTRUCTORS ----
    /**
     * Constructor to create a new task from console.
     * 
     * @param description Task description.
     */
    public Task(String description) {
        id = nextId++;
        this.description = description;
        this.status = Status.TO_DO;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Constructor to create a new task from JSON.
     * 
     * @param id          Unique task identifier.
     * @param description Task description.
     * @param status      Task status.
     * @param createdAt   Task creation date.
     * @param updatedAt   Task modification date.
     */
    public Task(String id, String description, Status status, String createdAt, String updatedAt) {
        this.id = Integer.parseInt(id);
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ---- METHODS ----

    /**
     * Returns the task ID.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the task description.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a new description for the task.
     * 
     * @param description New description.
     */
    public void setDescription(String description) {
        this.description = description;
        updateTimestamp();
    }

    /**
     * Returns the current task status.
     * 
     * @return status Task status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the task status.
     * 
     * @param status Status: "to-do", "in-progress" or "done".
     */
    public void setStatus(Status status) {
        this.status = status;
        updateTimestamp();
    }

    /**
     * Returns the creation date.
     * 
     * @return createdAt Creation date.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the last modification date.
     * 
     * @return updatedAt Modification date.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Updates the last modification date to the current one.
     */
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Converts the task to a JSON-like representation (as String).
     */
    public String toJson() {
        return String.format("{\n" +
                "\"id\": %d,\n" +
                "\"description\": \"%s\",\n" +
                "\"status\": \"%s\",\n" +
                "\"createdAt\": \"%s\",\n" +
                "\"updatedAt\": \"%s\"\n" +
                "}",
                id, description, status, createdAt, updatedAt == null ? "null" : updatedAt);
    }

    /**
     * Converts a JSON line to a Task object.
     * 
     * @param json JSON line with the task fields.
     * @return Converted task.
     */
    public static Task fromJson(String jsonFile) {
        // Error handling if it finds an empty JSON.
        if (jsonFile == null || jsonFile.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON can't be null or empty.");
        }

        // Cleans the JSON without removing all quotes (only the braces).
        jsonFile = jsonFile.trim();
        if (jsonFile.startsWith("{")) {
            jsonFile = jsonFile.substring(1);
        }
        if (jsonFile.endsWith("}")) {
            jsonFile = jsonFile.substring(0, jsonFile.length() - 1);
        }

        // Separates elements by comma (commas inside values between quotes don't).
        String[] elements = jsonFile.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        // Error handling if JSON elements are different from 5, which are the
        // Task attributes.
        if (elements.length != 5) {
            throw new IllegalArgumentException("Invalid number of elements, must be 5.");
        }

        String id = "";
        String description = "";
        String status = "";
        Status statusEnum = Status.TO_DO;
        String createdAt = "";
        String updatedAt = "";

        for (String element : elements) {
            String[] keyValue = element.trim().split(":", 2); // Can only have 2 elements: key and value.

            if (keyValue.length != 2) {
                continue;
            }

            // Removes quotes from key and value
            String key = keyValue[0].trim().replace("\"", ""); // Key.
            String value = keyValue[1].trim().replace("\"", ""); // Value.

            // Saves values in their corresponding variables according to the key.
            switch (key) {
                case "id":
                    id = value;
                    break;
                case "description":
                    description = value;
                    break;
                case "status":
                    if (value.equals("TO_DO")) {
                        statusEnum = Status.TO_DO;
                    } else if (value.equals("IN_PROGRESS")) {
                        statusEnum = Status.IN_PROGRESS;
                    } else if (value.equals("DONE")) {
                        statusEnum = Status.DONE;
                    }
                    break;
                case "createdAt":
                    createdAt = value;
                    break;
                case "updatedAt":
                    updatedAt = value;
                    break;
            }
        }

        // To check that ids are unique.
        int taskId = Integer.parseInt(id);
        if (taskId >= nextId) {
            nextId = taskId + 1;
        }

        return new Task(id, description, statusEnum, createdAt, updatedAt);
    }

    /**
     * Returns a readable representation of the task.
     */
    @Override
    public String toString() {
        return "Id: " + id + "\nDescription: " + description + "\nStatus: " + status
                + "\nCreated at: " + createdAt + "\nUpdated at: " + updatedAt;
    }
}

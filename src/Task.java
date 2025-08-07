import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tarea individual del Task Tracker.
 */
public class Task {

    // ---- ATRIBUTOS ----
    /**
     * Atributo que representa al id de la tarea.
     */
    private int id;

    /**
     * Atributo que representa al id de la tarea.
     */
    private static int nextId = 1;

    /**
     * Atributo que representa la descripción (texto) de la tarea.
     */
    private String description;

    // TO-DO Crear ENUM para los estados
    /**
     * Atributo que representa el estado de la tarea.
     * Puede ser: to-do, in-progress o done.
     */
    private Status status;

    /**
     * Atributo que representa la fecha en la que se crea la tarea.
     * En formato ISO 8601.
     */
    private String createdAt;

    /**
     * Atributo que representa la fecha en la que se modifica la tarea.
     */
    private String updatedAt = null;

    // ---- CONSTRUCTORES ----
    /**
     * Constructor para crear una nueva tarea desde consola.
     * 
     * @param description Descripción de la tarea.
     */
    public Task(String description) {
        id = nextId++;
        this.description = description;
        this.status = Status.TO_DO;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Constructor para crear una nueva tarea desde JSON.
     * 
     * @param id          Identificador único de la tarea.
     * @param description Descripción de la tarea.
     * @param status      Estado de la tarea.
     * @param createdAt   Fecha creación de la tarea.
     * @param updatedAt   Fecha modificación de la tarea.
     */
    public Task(String id, String description, Status status, String createdAt, String updatedAt) {
        this.id = Integer.parseInt(id);
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ---- MÉTODOS ----

    /**
     * Devuelve el ID de la tarea.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve la descripción de la tarea.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece una nueva descripción para la tarea.
     * 
     * @param description Nueva descripción.
     */
    public void setDescription(String description) {
        this.description = description;
        updateTimestamp();
    }

    /**
     * Devuelve el estado actual de la tarea.
     * 
     * @return status Estado de la tarea.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Establece el estado de la tarea.
     * 
     * @param status Estado: "to-do", "in-progress" o "done".
     */
    public void setStatus(Status status) {
        this.status = status;
        updateTimestamp();
    }

    /**
     * Devuelve la fecha de creación.
     * 
     * @return createdAt Fecha de creación.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Devuelve la fecha de última modificación.
     * 
     * @return updatedAt Fecha de modificación.
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Actualiza la fecha de última modificación a la actual.
     */
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Convierte la tarea a una representación tipo JSON (como String).
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
     * Convierte una línea JSON a un objeto Task.
     * 
     * @param json Línea JSON con los campos de la tarea.
     * @return Tarea convertida.
     */
    public static Task fromJson(String jsonFile) {
        // Captura de error si encuentra un JSON vacío.
        if (jsonFile == null || jsonFile.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON can't be null or empty.");
        }

        // Limpia el JSON sin eliminar todas las comillas (solo las llaves).
        jsonFile = jsonFile.trim();
        if (jsonFile.startsWith("{")) {
            jsonFile = jsonFile.substring(1);
        }
        if (jsonFile.endsWith("}")) {
            jsonFile = jsonFile.substring(0, jsonFile.length() - 1);
        }

        // Separa elementos por coma (comas dentro de valores entre comillas no).
        String[] elements = jsonFile.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        // Captura el error si los elementos del JSON son diferentes a 5, que son los
        // atributos del Task.
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
            String[] keyValue = element.trim().split(":", 2); // Solo puede haber 2 elemento: clave y valor.

            if (keyValue.length != 2) {
                continue;
            }

            // Elimina las comillas de la clave y el valor
            String key = keyValue[0].trim().replace("\"", ""); // Clave.
            String value = keyValue[1].trim().replace("\"", ""); // Valor.

            // Guarda los valores en sus variables correspondientes según la clave.
            switch (key) {
                case "id":
                    id = value;
                    break;
                case "description":
                    description = value;
                    break;
                case "status":
                    if (value == "TO_DO") {
                        statusEnum = Status.TO_DO;
                    } else if (value == "IN_PROGRESS") {
                        statusEnum = Status.IN_PROGRESS;
                    } else if (status == "DONE") {
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

        // Para comprobar que los id son únicos.
        int taskId = Integer.parseInt(id);
        if (taskId >= nextId) {
            nextId = taskId + 1;
        }

        return new Task(id, description, statusEnum, createdAt, updatedAt);
    }

    /**
     * Devuelve una representación legible de la tarea.
     */
    @Override
    public String toString() {
        return "Id: " + id + "\nDescription: " + description + "\nStatus: " + status
                + "\nCreated at: " + createdAt + "\nUpdated at: " + updatedAt;
    }
}

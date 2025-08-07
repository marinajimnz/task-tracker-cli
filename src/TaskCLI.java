import java.util.ArrayList;

public class TaskCLI {
    public static void main(String[] args) throws Exception {
        TaskManager taskManager = new TaskManager();

        if(args.length < 1) {
            throw new IllegalArgumentException("To use the task app you havo to write in the command line:\n" + 
                                "java -cp bin TaskCLI <command> \\\"[arguments]");
        }

        String command = args[0].toLowerCase();

        switch (command) {
            case "add":
                taskManager.addTask(args[1]);
                break;
            case "update":
                taskManager.updateTask(Integer.parseInt(args[1]), args[2]);
                break;
            case "delete":
                taskManager.deleteTask(Integer.parseInt(args[1]));
                break;
            case "mark-in-progress":
                taskManager.markInProgress(Integer.parseInt(args[1]));
                break;
            case "mark-done":
                taskManager.markDone(Integer.parseInt(args[1]));
                break;
            case "list-done":
                taskManager.listTasksByStatus(Status.DONE);
                break;
            case "list-to-do":
                taskManager.listTasksByStatus(Status.TO_DO);            
                break;
            case "list-in-progress":
                taskManager.listTasksByStatus(Status.IN_PROGRESS);
                break;
            case "list-all":
                taskManager.listAllTasks();
                break;
                
            default:
                System.out.println("Unknown command.");
                break;
        }
        
    }
}

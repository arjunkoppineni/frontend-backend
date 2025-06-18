
@RestController
@RequestMapping("/api/tasks")
@PreAuthorize("hasRole('USER')")
public class TaskController {

    @Autowired private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(Authentication auth) {
        return taskService.getTasksByUser(auth.getName());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task, Authentication auth) {
        return taskService.saveTask(task, auth.getName());
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}

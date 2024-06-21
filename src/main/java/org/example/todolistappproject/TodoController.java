package org.example.todolistappproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.*;
import java.time.LocalDate;

public class TodoController {
    @FXML
    private TextField taskInput;

    @FXML
    private ComboBox<String> priorityInput;

    @FXML
    private ComboBox<String> categoryInput;

    @FXML
    private DatePicker deadlineInput;

    @FXML
    private ListView<Task> taskList;

    private static final String FILE_NAME = "tasks.txt";

    @FXML
    private void initialize() {
        loadTasks();
        taskList.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> listView) {
                return new ListCell<Task>() {
                    @Override
                    protected void updateItem(Task task, boolean empty) {
                        super.updateItem(task, empty);
                        if (task != null) {
                            setText(task.toString());
                            if (task.isCompleted()) {
                                setStyle("-fx-text-fill: grey; -fx-strikethrough: true;");
                            } else {
                                setStyle("-fx-text-fill: black; -fx-strikethrough: false;");
                            }
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void addTask() {
        String taskDescription = taskInput.getText();
        String priority = priorityInput.getValue();
        String category = categoryInput.getValue();
        LocalDate deadline = deadlineInput.getValue();

        if (taskDescription.isEmpty()) {
            showAlert("Input Error", "Task cannot be empty.");
            return;
        }
        if (priority == null) {
            showAlert("Input Error", "Please select a priority.");
            return;
        }
        if (category == null) {
            showAlert("Input Error", "Please select a category.");
            return;
        }
        if (deadline == null) {
            showAlert("Input Error", "Please select a deadline.");
            return;
        }

        Task newTask = new Task(taskDescription, priority, deadline, category);
        taskList.getItems().add(newTask);
        taskInput.clear();
        priorityInput.setValue(null);
        categoryInput.setValue(null);
        deadlineInput.setValue(null);
        saveTasks();
    }

    @FXML
    private void removeTask() {
        int selectedIdx = taskList.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            taskList.getItems().remove(selectedIdx);
            saveTasks();
        } else {
            showAlert("Selection Error", "No task selected for removal.");
        }
    }

    @FXML
    private void completeTask() {
        int selectedIdx = taskList.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            Task task = taskList.getItems().get(selectedIdx);
            task.setCompleted(true);
            taskList.refresh(); // Refresh the ListView to update item appearance
            saveTasks();
        } else {
            showAlert("Selection Error", "No task selected for completion.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : taskList.getItems()) {
                writer.write(task.getDescription() + "," + task.isCompleted() + "," + task.getPriority() + "," + task.getDeadline() + "," + task.getCategory());
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert("Save Error", "Could not save tasks.");
        }
    }

    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Task task = new Task(parts[0], parts[2], LocalDate.parse(parts[3]), parts[4]);
                    task.setCompleted(Boolean.parseBoolean(parts[1]));
                    taskList.getItems().add(task);
                }
            }
        } catch (IOException e) {
            showAlert("Load Error", "Could not load tasks.");
        }
    }
}

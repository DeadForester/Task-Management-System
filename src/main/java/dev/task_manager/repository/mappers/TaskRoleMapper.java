package dev.task_manager.repository.mappers;

import dev.task_manager.model.task.Status;
import dev.task_manager.model.task.Task;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskRoleMapper {

    @SneakyThrows
    public static Task mapRow(ResultSet resultSet) {
        if (resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getLong("task_id"));
            task.setTitle(resultSet.getString("task_title"));
            task.setDescription(resultSet.getString("task_description"));
            task.setStatus(Status.valueOf(resultSet.getString("task_status")));
            Timestamp timestamp = resultSet.getTimestamp("task_expiration_date");
            if (timestamp != null) {
                task.setLocalDateTime(timestamp.toLocalDateTime());
            }
            return task;
        }
        return null;
    }


    @SneakyThrows
    public static List<Task> mapRowList(ResultSet resultSet) {
        List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getLong("task_id"));
            if (!resultSet.wasNull()) {
                task.setTitle(resultSet.getString("task_title"));
                task.setDescription(resultSet.getString("task_description"));
                task.setStatus(Status.valueOf(resultSet.getString("task_status")));
                Timestamp timestamp = resultSet.getTimestamp("task_expiration_date");
                if (timestamp != null) {
                    task.setLocalDateTime(timestamp.toLocalDateTime());
                }
                tasks.add(task);
            }
        }
        return tasks;
    }


}

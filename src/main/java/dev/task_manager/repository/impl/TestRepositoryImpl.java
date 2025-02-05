package dev.task_manager.repository.impl;

import dev.task_manager.model.exception.ResourceMappingException;
import dev.task_manager.model.task.Task;
import dev.task_manager.repository.DataSourceConfiguration;
import dev.task_manager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TestRepositoryImpl implements TaskRepository {

    private DataSourceConfiguration dataSourceConfiguration;

    private final String FIND_BY_ID = """
            SELECT t.id as task_id,
                   t.title as task_title,
                   t.description as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status as task_status
            FROM tasklist.tasks t
            WHERE id = ?
            """;

    private final String FIND_ALL_BY_USER_ID = """
            SELECT t.id as task_id,
                   t.title as task_title,
                   t.description as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status as task_status
            FROM tasklist.tasks t
            join tasklist.user_tasks ut on t.id = ut.task_id
            WHERE ut.user_id = ?
            """;

    private final String ASSIGN = """
            INSERT INTO tasklist.user_tasks (task_id, user_id)
            VALUES (?,?)
            """;

    private final String UPDATE = """
            UPDATE tasklist.tasks
            SET title = ?,
                description = ?,
                expiration_date = ?,
                status = ?
            WHERE id = ?
            """;

    private final String CREATE = """
            INSERT INTO tasklist.tasks(title,description,expiration_date,status)
            VALUES(?,?,?,?)
            """;

    private final String DELETE = """
            DELETE FROM tasklist.tasks
            WHERE id = ?
            """;

    @Override
    public Optional<Task> findById(Long id) {
        try{
            Connection connection = dataSourceConfiguration.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1,id);
            try(ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TaskRoleMapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Error while finding user by id.");
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        return List.of();
    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void create(Task task) {

    }

    @Override
    public void delete(Long id) {

    }
}

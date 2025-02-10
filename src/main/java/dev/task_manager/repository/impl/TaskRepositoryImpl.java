package dev.task_manager.repository.impl;

import dev.task_manager.entity.exception.ResourceMappingException;
import dev.task_manager.entity.task.Task;
import dev.task_manager.repository.DataSourceConfiguration;
import dev.task_manager.repository.TaskRepository;
import dev.task_manager.repository.mappers.TaskRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final DataSourceConfiguration dataSourceConfiguration;

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
        try {
            Connection connection = dataSourceConfiguration.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TaskRoleMapper.mapRow(rs));
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Error while finding user by id.");
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        try {
            Connection connection = dataSourceConfiguration.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                return TaskRoleMapper.mapRowList(rs);
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Error while finding all_user by id.");
        }
    }

    @Override
    public void assignToUserById(Long taskId, Long userId) {
        try {
            Connection connection = dataSourceConfiguration.getConnection();
            PreparedStatement statement = connection.prepareStatement(ASSIGN);
            statement.setLong(1, taskId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Error while assigning user by id.");
        }
    }

    @Override
    public void update(Task task) {
        try {
            Connection connection = dataSourceConfiguration.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }
            if (task.getLocalDateTime() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getLocalDateTime()));
            }
            statement.setString(4, task.getStatus().name());
            statement.setLong(5, task.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Error while updating user.");
        }
    }

    @Override
    public void create(Task task) {
        try {
            Connection connection = dataSourceConfiguration.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }
            if (task.getLocalDateTime() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getLocalDateTime()));
            }
            statement.setString(4, task.getStatus().name());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                task.setId(rs.getLong(1));
            }
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Error while creating task.");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfiguration.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new ResourceMappingException("Error while delete task.");
        }
    }
}

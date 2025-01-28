package dev.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority {
    HIGH(2),
    MEDIUM(1),
    LOW(0);

    private final Integer id;

    public static Priority getPriority(int id) {
        for (Priority priority : Priority.values()) {
            if (priority.getId() == id) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Статус с ID: " + id + " не найден");
    }

    public static int getId(Priority priority) {
        return priority.getId();
    }

}

package dev.api.enums;

import lombok.Getter;

@Getter
public enum Priority {
    HIGH(2),
    MEDIUM(1),
    LOW(0);

    private final int id;

    Priority(int id) {
        this.id = id;
    }

    public static Priority getPriority(int id){
        for (Priority priority : Priority.values()) {
            if(priority.getId() == id){
                return priority;
            }
        }
        throw new IllegalArgumentException("Статус с ID: " + id + " не найден");
    }






}

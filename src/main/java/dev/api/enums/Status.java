package dev.api.enums;

import lombok.Getter;

@Getter
public enum Status {
    CANCELLED(0),
    COMPLETED(1),
    NOT_APPROVED(2),
    ACCEPTED(3),
    IN_PROGRESS(4);

    private final int id;

    Status(int id) {
        this.id = id;
    }

    public static Status getStatus(int id) {
        for (Status status : Status.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Статус с ID: " + id + " не найден");
    }
}

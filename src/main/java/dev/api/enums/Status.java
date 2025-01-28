package dev.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    CANCELLED(0),
    COMPLETED(1),
    NOT_APPROVED(2),
    ACCEPTED(3),
    IN_PROGRESS(4);

    private final int id;


    public static Status getStatus(int id) {
        for (Status status : Status.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Статус с ID: " + id + " не найден");
    }

    public static int getId(Status status) {
        return status.getId();
    }

}

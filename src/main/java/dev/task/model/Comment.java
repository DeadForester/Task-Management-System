package dev.task.model;

import lombok.Data;

@Data
public class Comment {

    private Long id;
    private String text;
    private User author;
    private Task task;

}

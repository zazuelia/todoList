package com.joseelia.todoList.model;

import lombok.Data;

@Data
public class UserForm {

    private String userName;
    private String taskName;
    private boolean checked;
}

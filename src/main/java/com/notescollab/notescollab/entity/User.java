package com.notescollab.notescollab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;
    private String loginId;
    private String fullName;
    private String email;
    private String password;

}

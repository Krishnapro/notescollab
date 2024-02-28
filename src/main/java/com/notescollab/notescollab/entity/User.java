package com.notescollab.notescollab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long userid;
    private String username;
    private String fullname;
    private String emailid;
    private String password;


}

package com.photgraphy.studio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private String displayName;
    private String photoUrl;
}

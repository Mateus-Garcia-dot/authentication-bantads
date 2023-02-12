package com.bantads.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authentication")
public class AuthenticationModel {
    @Id
    private String uuid = java.util.UUID.randomUUID().toString();
    private String account;
    private String login;
    private String password;
    private Integer type;
    private Boolean isApproved;
    private Boolean isPending;
}

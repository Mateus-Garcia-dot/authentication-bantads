package com.bantads.authentication.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authentication")
public class AuthenticationModel {
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    private Long account;
    private String login;
    private String password;
    private Integer type;
    private Boolean isApproved;
    private Boolean isPending;
}

package com.project.disqus.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    Long id;
    String username;
    String password;

}

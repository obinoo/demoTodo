package com.example.demoTodo.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class  Us {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @NotBlank
    @Size(min = 3, message = "At least 3 characters")
    @Column(name = "email", unique = true)
    @Getter
    @Setter
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, message = "At least 5 characters should be used")
    @Getter
    @Setter
    private String password;


    public Us(String email, String password) {
        this.email = email;
        this.password = password;
    }

}

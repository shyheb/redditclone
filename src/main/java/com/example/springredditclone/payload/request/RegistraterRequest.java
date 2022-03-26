package com.example.springredditclone.payload.request;

import com.example.springredditclone.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistraterRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    @NotEmpty
    private Set<ERole> roles;
}

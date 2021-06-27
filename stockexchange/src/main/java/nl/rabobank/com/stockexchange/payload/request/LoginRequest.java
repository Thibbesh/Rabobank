package nl.rabobank.com.stockexchange.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
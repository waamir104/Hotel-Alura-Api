package dev.waamir.hotelaluraapi.adapter.dto.resource.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRegisterRequest {
    
    private String username;
    private String password1;
    private String password2;
    
}

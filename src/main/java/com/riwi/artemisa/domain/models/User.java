package com.riwi.artemisa.domain.models;

import com.riwi.artemisa.infrastructure.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Integer id;
    String userName;
    Role role;
}

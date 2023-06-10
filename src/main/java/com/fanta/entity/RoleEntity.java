package com.fanta.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntity implements Entity {
    private ActorEntity actor;
    private PerformanceEntity performance;
    private String roleName;
}

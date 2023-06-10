package com.fanta.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerformanceEntity implements Entity {
    private int id;
    private LocalDateTime dateTime;
    private PlayEntity play;
}

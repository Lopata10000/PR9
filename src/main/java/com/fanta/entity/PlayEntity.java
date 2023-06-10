package com.fanta.entity;

import com.fanta.entity.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayEntity implements Entity {
    private int id;
    private String title;
    private String author;
    private int yearWritten;
}

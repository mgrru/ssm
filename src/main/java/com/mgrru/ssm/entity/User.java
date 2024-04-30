package com.mgrru.ssm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class User {
    private int id;
    private String name;
    private File photo;
}

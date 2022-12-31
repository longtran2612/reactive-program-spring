package com.example.reactiveprogramspring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 4:04 PM 31-Dec-22
 * Long Tran
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String departmentId;
}

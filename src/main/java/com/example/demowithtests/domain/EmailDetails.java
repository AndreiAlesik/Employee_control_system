package com.example.demowithtests.domain;

// Importing required classes

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.ConstructorParameters;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    //    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;


}

package com.shubhada.productservice.inheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ms_mentor")
public class Mentor extends User{
    private int noOfSessions;
    private int noOfMentees;

}

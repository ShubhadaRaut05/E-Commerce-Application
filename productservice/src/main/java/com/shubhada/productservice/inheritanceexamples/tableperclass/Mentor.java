package com.shubhada.productservice.inheritanceexamples.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tbc_mentor")
public class Mentor extends User{
    private int noOfSessions;
    private int noOfMentees;

}

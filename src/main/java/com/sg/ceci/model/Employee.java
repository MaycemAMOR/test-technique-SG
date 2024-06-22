package com.sg.ceci.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    List<Earning> earnings;
    boolean intern;
}

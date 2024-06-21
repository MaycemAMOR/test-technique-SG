package com.sg.ceci.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employees {
    List<Revenue> revenue;
    boolean intern;
}

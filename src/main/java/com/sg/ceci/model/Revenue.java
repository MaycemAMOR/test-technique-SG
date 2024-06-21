package com.sg.ceci.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Revenue {

    private BigDecimal value;
    private Type type;
}

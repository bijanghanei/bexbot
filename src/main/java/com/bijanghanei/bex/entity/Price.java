package com.bijanghanei.bex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price {
    private boolean success;
    private double price;
    private double high;
    private double low;
    private String time;
}

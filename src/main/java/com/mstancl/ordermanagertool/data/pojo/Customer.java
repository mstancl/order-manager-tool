package com.mstancl.ordermanagertool.data.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Customer {

    private String firstName;
    private String surname;
    private String phoneNumber;
    private String email;

}

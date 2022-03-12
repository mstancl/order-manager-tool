package com.mstancl.ordermanagertool.data.pojo;

import com.mstancl.ordermanagertool.data.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class Order {

    private long id;
    private Customer customer;
    private LocalDate dateWhenReceived;
    private LocalDate dueDate;
    private String orderType;
    private String descriptionOfOrder;
    private String solutionForOrder;
    private int estimatedPrice;
    private Status status;

}

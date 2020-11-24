package com.bank.sg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Operation {

    private int operationId;

    private OperationType operationType;

    private double operationAmount;

    private LocalDateTime operationDate;

    private double operationBalance;
}

package com.client.contract.dto;

import lombok.Data;

@Data
public class ContractDto {

    private Long id;
    private String dateOfDrawingUpTheContract;
    private String dateOfLastUpdate;
    private Boolean relevance;

}

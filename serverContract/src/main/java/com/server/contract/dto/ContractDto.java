package com.server.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ContractDto {

    private Long id;
    private Date dateOfDrawingUpTheContract;
    private Date dateOfLastUpdate;
    private Boolean relevance;

}

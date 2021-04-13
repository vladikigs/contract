package com.server.contract.controller;

import com.server.contract.dto.ContractDto;
import com.server.contract.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("contract")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/get-list")
    @ResponseBody
    public List<ContractDto> getListContracts() {
        return contractService.getServices();
    }


}

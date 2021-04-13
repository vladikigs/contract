package com.server.contract.service;

import com.server.contract.dto.ContractDto;
import com.server.contract.entity.Contract;
import com.server.contract.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class ContractService {

    private final ContractRepository contractRepository;

    @Value("${deadline.for.contract.renewal.in.days}")
    private int countDays;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<ContractDto> getServices() {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream().map(this::contractToContractDto).collect(Collectors.toList());
    }

    private Boolean setStatusContract(Date dateUpdateContract) {
        Calendar nowDate = Calendar.getInstance();
        Calendar calendarDateUpdateContract = Calendar.getInstance();
        calendarDateUpdateContract.setTime(dateUpdateContract);
        long diffDays = (nowDate.getTimeInMillis() - calendarDateUpdateContract.getTimeInMillis()) / (24 * 60 * 60 * 1000);
        return diffDays < countDays;
    }

    private ContractDto contractToContractDto(Contract contract) {
        return new ContractDto(contract.getId(),
                contract.getDateOfDrawingUpTheContract(),
                contract.getDateOfLastUpdate(),
                setStatusContract(contract.getDateOfLastUpdate()));
    }

}

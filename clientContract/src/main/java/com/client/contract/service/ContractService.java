package com.client.contract.service;

import com.client.contract.api.ContractPlaceHolderApi;
import com.client.contract.dto.ContractDto;
import com.client.contract.entity.Contract;
import com.client.contract.controller.ContractTableController;
import retrofit2.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ContractService {

    private String SERVER_URL;

    public void getContracts(ContractTableController contractTableController) {

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/application.properties")) {
            Properties property = new Properties();
            property.load(fileInputStream);
            SERVER_URL = property.getProperty("server.url");
        } catch (IOException ex) {
            contractTableController.showErrorAlert(ex.getMessage());
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContractPlaceHolderApi contractPlaceHolderApi = retrofit.create(ContractPlaceHolderApi.class);

        Call<List<ContractDto>> call = contractPlaceHolderApi.getContacts();
        call.enqueue(new Callback<List<ContractDto>>() {
            @Override
            public void onResponse(Call<List<ContractDto>> call, Response<List<ContractDto>> response) {
                if (response.isSuccessful()) {
                    List<ContractDto> body = response.body();
                    if (body == null) {
                        contractTableController.showErrorAlert("Ответ с сервера без данных");
                    } else {
                        contractTableController.setDataInTableView(contractDtoToContract(body));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<ContractDto>> call, Throwable throwable) {
                contractTableController.showErrorAlert(throwable.getMessage());
            }
        });
    }

    private List<Contract> contractDtoToContract(List<ContractDto> contractDtos) {
        return contractDtos.stream()
                .map(i -> new Contract(parseStringDate(i.getDateOfDrawingUpTheContract()),
                                       parseStringDate(i.getDateOfLastUpdate()),
                                       i.getRelevance()))
                .collect(Collectors.toList());
    }

    public Date parseStringDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXXXX");
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date, dateTimeFormatter)
                .withOffsetSameInstant(ZoneOffset.UTC);

        return new Date(offsetDateTime.toInstant().toEpochMilli());
    }

}



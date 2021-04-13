package com.client.contract.api;

import com.client.contract.dto.ContractDto;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ContractPlaceHolderApi {

    @GET("/contract/get-list")
    Call<List<ContractDto>> getContacts();

}

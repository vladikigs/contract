package com.client.contract.controller;

import com.client.contract.entity.Contract;
import com.client.contract.service.ContractService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.application.Platform.runLater;

public class ContractTableController implements Initializable {

    @FXML
    TableView<Contract> tableViewContract;

    @FXML
    TableColumn<Contract, String> dateOfDrawingUpTheContract;
    @FXML
    TableColumn<Contract, String> dateOfLastUpdate;
    @FXML
    TableColumn<Contract, String> relevance;
    @FXML
    Button updateDataButton;
    @FXML
    ProgressIndicator progressLoadingIndicator;

    ObservableList<Contract> data;

    private ContractService contractService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contractService = new ContractService();
        updateDataTableView();
        relevance.setSortable(false);
        updateDataButton.setOnAction(e -> updateDataTableView());
    }

    public void updateDataTableView() {
        progressLoadingIndicator.setProgress(-1);
        updateDataButton.setDisable(true);
        contractService.getContracts(this);
    }

    public void setDataInTableView(List<Contract> contractList) {
        data = FXCollections.observableArrayList(
                contractList
        );

        dateOfDrawingUpTheContract.setCellValueFactory(
            new PropertyValueFactory<>("dateOfDrawingUpTheContract")
        );

        dateOfLastUpdate.setCellValueFactory(
            new PropertyValueFactory<>("dateOfLastUpdate")
        );

        relevance.setCellValueFactory(
            new PropertyValueFactory<>("relevance")
        );

        tableViewContract.setItems(data);
        enableButtonAndSetDoneProgressBar();
    }

    private void enableButtonAndSetDoneProgressBar() {
        runLater(() -> {
            progressLoadingIndicator.setProgress(1);
            updateDataButton.setDisable(false);
        });
    }

    public void showErrorAlert(String message) {
        runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Смотри, ошибка!");
            alert.setContentText(message);
            alert.showAndWait();
            enableButtonAndSetDoneProgressBar();
        });
    }

}

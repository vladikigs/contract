package com.client.contract.entity;

import javafx.scene.control.CheckBox;
import lombok.Data;
import java.util.Date;

@Data
public class Contract {

    private Date dateOfDrawingUpTheContract;
    private Date dateOfLastUpdate;
    private CheckBox relevance;

    public Contract(Date dateOfDrawingUpTheContract, Date dateOfLastUpdate, Boolean relevance) {
        this.dateOfDrawingUpTheContract = dateOfDrawingUpTheContract;
        this.dateOfLastUpdate = dateOfLastUpdate;
        this.relevance = new CheckBox();
        this.relevance.setSelected(relevance);
        this.relevance.setText(relevance.toString());
        this.relevance.setDisable(true);
    }

}

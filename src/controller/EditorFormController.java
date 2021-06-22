package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class EditorFormController {
    public TextArea txtEditor;
    public TextField txtSearch;
    public AnchorPane pneFind;



    public void mnuItemNew_OnAction(ActionEvent actionEvent) {
        txtEditor.clear();
        txtEditor.requestFocus();
    }

    public void mnuItemExit_OnAction(ActionEvent actionEvent) {

        if(!txtEditor.getText().isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?", ButtonType.YES).show();

        }
    }

    public void mnuItemFind_OnAction(ActionEvent actionEvent) {
    }

    public void mnuItemReplace_OnAction(ActionEvent actionEvent) {
    }

    public void mnuItemSelectAll_OnAction(ActionEvent actionEvent) {
        if(!txtEditor.getText().isEmpty()){
            txtEditor.selectAll();
        }
    }

    public void btnFindUp_OnAction(ActionEvent actionEvent) {
    }

    public void btnFindDown_OnAction(ActionEvent actionEvent) {
    }
}

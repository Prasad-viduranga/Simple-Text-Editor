package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditorFormController {
    Boolean setVisible=false;
    public TextArea txtEditor;
    public TextField txtSearch;
    public AnchorPane pneFind;

    public void initialize(){
        pneFind.setVisible(setVisible);

    }

    public void mnuItemNew_OnAction(ActionEvent actionEvent) {
        txtEditor.clear();
        txtEditor.requestFocus();
    }

    public void mnuItemExit_OnAction(ActionEvent actionEvent) {

        if(!txtEditor.getText().isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?", ButtonType.YES).show();

        }
    }


    Boolean checkText=true;

    public void mnuItemFind_OnAction(ActionEvent actionEvent) {
        setVisible=!setVisible;
        pneFind.setVisible(setVisible);
        String text = txtEditor.getText();
        if(setVisible){
            txtSearch.requestFocus();

            txtSearch.textProperty().addListener(observable -> {

                Pattern regExp = Pattern.compile(txtSearch.getText());
                Matcher matcher = regExp.matcher(text);
                if(matcher.find()){
                    txtEditor.selectRange(matcher.start(),matcher.end());
                    checkText=false;
                }

                while (matcher.find()){
                    System.out.println(matcher.matches());
                }

            });

        }
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
        String pattern = txtSearch.getText();
        String text = txtEditor.getText();

        Pattern regExp = Pattern.compile(pattern);
        Matcher matcher = regExp.matcher(text);
        if(matcher.find()){
            txtEditor.selectRange(matcher.start(),matcher.end());
        }
    }
}

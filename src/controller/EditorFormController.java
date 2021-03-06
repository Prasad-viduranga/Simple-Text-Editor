package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.beans.AppletInitializer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EditorFormController {
    public AnchorPane pneReplace;
    public TextField txtReplaceText;
    public Label lblResultTrue;
    public Label lblResultFalse;
    public MenuItem mnuItemSave;
    Boolean setVisible_pneFind =false;
    Boolean setVisible_pneReplace=false;
    public TextArea txtEditor;
    public TextField txtSearch;
    public AnchorPane pneFind;

    private int countOfreg=0;
    private int index=0;
    private String pattern;
    private String text;
    private List<Integer> searchIndexes=new ArrayList<>();
    private int replaceIndex=0;
    private String copiedText;
    public void initialize(){
        pneFind.setVisible(setVisible_pneFind);
        pneReplace.setVisible(setVisible_pneReplace);

    }

    public void mnuItemNew_OnAction(ActionEvent actionEvent) {
        txtEditor.clear();
        txtEditor.requestFocus();
    }

    public void mnuItemExit_OnAction(ActionEvent actionEvent) {

        if(!txtEditor.getText().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",ButtonType.YES);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                ((Stage) (txtEditor.getScene().getWindow())).close();
            }


        }
    }


    public void mnuItemFind_OnAction(ActionEvent actionEvent) {
        setVisible_pneFind =!setVisible_pneFind;
        pneFind.setVisible(setVisible_pneFind);
        pneReplace.setVisible(false);


        if(setVisible_pneFind){
            txtSearch.requestFocus();

            txtSearch.textProperty().addListener(observable -> {

                index=0;
                countOfreg=0;
                searchIndexes.clear();

                pattern = txtSearch.getText().toLowerCase();
                text = txtEditor.getText().toLowerCase();
try {
    Pattern regExp = Pattern.compile(pattern);
    Matcher matcher = regExp.matcher(text);

    while (matcher.find()) {
        searchIndexes.add(matcher.start());
        searchIndexes.add(matcher.end());
        countOfreg++;
        //System.out.println("find");
    }
    if (!(matcher.find(0))) {
        lblResultFalse.setText("Results 0");
        lblResultTrue.setText("");
        txtEditor.deselect();

    } else {
        txtEditor.selectRange(searchIndexes.get(0), searchIndexes.get(1));
        lblResultTrue.setText("Results " + countOfreg);
        lblResultFalse.setText("");

    }
}catch(PatternSyntaxException e){

                }

            });
        }
    }

    public void btnFindUp_OnAction(ActionEvent actionEvent) {
        if(index>0){

            index=index-2;
            txtEditor.selectRange(searchIndexes.get(index),searchIndexes.get(index+1));
            System.out.println(index);

        }else{
            index=searchIndexes.size()-2;
            txtEditor.selectRange(searchIndexes.get(index),searchIndexes.get(index+1));
        }

    }

    public void btnFindDown_OnAction(ActionEvent actionEvent) {

        if(index<searchIndexes.size()){
            index=index+2;
            txtEditor.selectRange(searchIndexes.get(index),searchIndexes.get(index+1));
            System.out.println(index);

            if (index==searchIndexes.size()-2){
                index=-2;
            }
        }
    }

    public void mnuItemReplaceAll_OnAction(ActionEvent actionEvent) {

        setVisible_pneReplace =!setVisible_pneReplace;
        setVisible_pneFind=setVisible_pneReplace;
        if (setVisible_pneFind){
            setVisible_pneReplace=true;
        }
        pneFind.setVisible(setVisible_pneReplace);
        pneReplace.setVisible(setVisible_pneReplace);

        txtReplaceText.textProperty().addListener(observable -> {
            replaceIndex=0;
        });

        if(setVisible_pneFind){
            txtSearch.requestFocus();

            txtSearch.textProperty().addListener(observable -> {

                index=0;
                countOfreg=0;
                searchIndexes.clear();

                pattern = txtSearch.getText().toLowerCase();
                text = txtEditor.getText().toLowerCase();

                Pattern regExp = Pattern.compile(pattern);
                Matcher matcher = regExp.matcher(text);

                while (matcher.find()){
                    searchIndexes.add(matcher.start());
                    searchIndexes.add(matcher.end());
                    countOfreg++;
                    //System.out.println("find");
                }
                if (!(matcher.find(0))){
                    lblResultFalse.setText("Results 0");
                    lblResultTrue.setText("");
                    txtEditor.deselect();

                }else{
                    txtEditor.selectRange(searchIndexes.get(0), searchIndexes.get(1));
                    lblResultTrue.setText("Results "+countOfreg);
                    lblResultFalse.setText("");

                }


            });
        }

    }

    public void mnuItemReplace_OnAction(ActionEvent actionEvent) {
        setVisible_pneFind =!setVisible_pneFind;
        setVisible_pneReplace =!setVisible_pneReplace;
        pneFind.setVisible(setVisible_pneFind);
        pneReplace.setVisible(setVisible_pneReplace);

        txtReplaceText.textProperty().addListener(observable -> {
            replaceIndex=0;
        });

    }

    public void btnReplace_OnAction(ActionEvent actionEvent) {
            text=txtEditor.getText();
            Pattern regExp = Pattern.compile(pattern);
            Matcher matcher = regExp.matcher(text);
            if (matcher.find()) {
                txtEditor.replaceText(matcher.start(), matcher.end(), txtReplaceText.getText());
            }

    }
    public void btnReplaceAll_OnAction(ActionEvent actionEvent) {

        while (true){
            text=txtEditor.getText();
            Pattern regExp = Pattern.compile(pattern);
            Matcher matcher = regExp.matcher(text);
            if (matcher.find()) {
                txtEditor.replaceText(matcher.start(), matcher.end(), txtReplaceText.getText());
            }else{
                break;
            }
        }
    }

    public void mnuItemSelectAll_OnAction(ActionEvent actionEvent) {
        if(!txtEditor.getText().isEmpty()){
            txtEditor.selectAll();
        }
    }

    public void mnuItemSave_OnAction(ActionEvent actionEvent) {
        txtEditor.textProperty().setValue("advdvd");
    }

    public void mnuItemCut_OnAction(ActionEvent actionEvent) {
    }public void mnuItemCopy_OnAction(ActionEvent actionEvent) {

    }



    public void mnuItemPaste_OnAction(ActionEvent actionEvent) {
        if(txtEditor.getSelectedText().isEmpty()){
            KeyCode enter = KeyCode.ENTER;
        }else {

        }
        System.out.println();
    }
}

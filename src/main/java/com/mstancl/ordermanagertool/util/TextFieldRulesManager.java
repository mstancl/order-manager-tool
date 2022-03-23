package com.mstancl.ordermanagertool.util;

import javafx.scene.control.TextField;

public class TextFieldRulesManager {


    public static void allowOnlyNumbersForInput(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }


}

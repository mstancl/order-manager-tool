package com.mstancl.ordermanagertool.util;

import com.mstancl.ordermanagertool.util.fieldColors.FieldStyle;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TextFieldListeners {


    public static void allowOnlyNumbersForInput(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public static void limitNumberOfCharacters(TextField textField, int numberOfCharacters) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.length() > numberOfCharacters) {
                textField.setText(newValue.substring(0, numberOfCharacters));
            }
        });
    }
    public static void limitNumberOfCharacters(TextArea textArea, int numberOfCharacters) {
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.length() > numberOfCharacters) {
                textArea.setText(newValue.substring(0, numberOfCharacters));
            }
        });
    }


}

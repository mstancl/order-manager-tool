package com.mstancl.ordermanagertool.util.fxml;

import javafx.fxml.FXMLLoader;

public class FXMLoaderManager {


    private static final InheritableThreadLocal<FXMLLoader> fxmLoaderInheritableThreadLocal = new InheritableThreadLocal<>();

    private FXMLoaderManager() {

    }

    public static FXMLLoader getFxmLoader() {
        return fxmLoaderInheritableThreadLocal.get();
    }

    public static void setFxmlLoader(FXMLLoader fxmlLoader) {
        fxmLoaderInheritableThreadLocal.set(fxmlLoader);
    }


}

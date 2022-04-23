module MeanGrade.ui {
    requires json;
    requires MeanGrade.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens ui to javafx.graphics, javafx.fxml;
}

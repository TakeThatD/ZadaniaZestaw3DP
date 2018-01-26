package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML public Button closeButton;
    @FXML public Pane mainPane;
    public boolean up = true;
    public boolean right = true;


    @FXML public void runningButton(MouseEvent event) {
        if ((closeButton.getLayoutX() < (double)180.0) && (right==true)){
            closeButton.setLayoutX(closeButton.getLayoutX()+(double)20.0);
        }
        else if (closeButton.getLayoutX() > (double)0.0) {
            right = false;
            closeButton.setLayoutX(closeButton.getLayoutX()-(double)20.0);
        }
        else {
            right = true;
        }

        if ((closeButton.getLayoutY() < (double)180.0) && (up==true)){
            closeButton.setLayoutY(closeButton.getLayoutY()+(double)20.0);
        }
        else if (closeButton.getLayoutY() > (double)0.0) {
            up = false;
            closeButton.setLayoutY(closeButton.getLayoutY()-(double)20.0);
        }
        else {
            up = true;
        }
    }
}

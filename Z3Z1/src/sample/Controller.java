package sample;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class Controller {

    public TextField inputTextField;
    public TextField outputTextField;

    public RadioButton inputCelcius;
    public RadioButton inputFahrenheit;
    public RadioButton inputKelvin;

    public RadioButton outputCelcius;
    public RadioButton outputFahrenheit;
    public RadioButton outputKelvin;

    public void pressButton (ActionEvent event) {
        float inputTextFieldF = Float.parseFloat(inputTextField.getText());

        /*******          CELCIUS INPUT       *******/
        if (inputCelcius.isSelected()) {
            /** CELCIUS OUTPUT **/
            if (outputCelcius.isSelected()) {
                outputTextField.setText(inputTextField.getText());
            }

            /** FAHRENHEIT OUTPUT **/
            else if (outputFahrenheit.isSelected()) {
                outputTextField.setText(String.valueOf(((9*inputTextFieldF)/5)+32));
            }

            /** KELVIN OUTPUT **/
            else {
                outputTextField.setText(String.valueOf(inputTextFieldF+(float)273.15));
            }
        }

        /*******          FAHRENHEIT INPUT       *******/
        else  if (inputFahrenheit.isSelected()){
            /** CELCIUS OUTPUT **/
            if (outputCelcius.isSelected()) {
               outputTextField.setText(String.valueOf(((inputTextFieldF-(float)32)*5)/9));
            }

            /** FAHRENHEIT OUTPUT **/
            else if (outputFahrenheit.isSelected()) {
                outputTextField.setText(inputTextField.getText());
            }

            /** KELVIN OUTPUT **/
            else {
                outputTextField.setText(String.valueOf((((inputTextFieldF-(float)32)*5)/9)+(float)273.15));
            }
        }

        /*******          KELVIN INPUT       *******/
        else  {
            /** CELCIUS OUTPUT **/
            if (outputCelcius.isSelected()) {
                outputTextField.setText(String.valueOf(inputTextFieldF-(float)273.15));
            }

            /** FAHRENHEIT OUTPUT **/
            else if (outputFahrenheit.isSelected()) {
                outputTextField.setText(String.valueOf(((9*(inputTextFieldF-(float)273.15))/5)+32));
            }

            /** KELVIN OUTPUT **/
            else {
                outputTextField.setText(inputTextField.getText());
            }
        }
    }


}

package blackJack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/*  Krupier dobiera do 17.
    21 oczek daje zwycięstwo, bez ruchu krupiera
    Remis oznacza zwrot stawki
    Black Jack liczony jak normalne zwycięstwo (x2)
 */

public class Game {
    /*********************************************   FXML   **********************************************/

    /*      Grid na stole       */
    @FXML public HBox croupierCards;
    @FXML public HBox playerCards1;

    /*      Informacje          */
    @FXML public TextField playerBidField;
    @FXML public TextField playerCashField;

    /*      Ustawianie stawki   */
    @FXML public Button bidButton50;
    @FXML public Button bidButton100;
    @FXML public Button bidButton200;
    @FXML public Button bidButton500;
    @FXML public Button bidButton1000;
    @FXML public Button bidButton5000;
    @FXML public Button bidDoneButton;

    /*      Sterowanie grą      */
    @FXML public Button hitButton;
    @FXML public Button standButton;
    @FXML public Button doubleDownButton;

    /************************************   Obsługa przycisków    ******************************************/

    /*****  Obsługa przycisków sterowania  *****/
    @FXML public void hitButtonPressed(ActionEvent event) {
        player.addHandValue(table.giveCardFromDeck(playerCards1, player.getCardSlot()));
        if (player.getHandValue()>21) {
            croupierWon("Busted!");
        }
        else if (player.getHandValue()==21){
            playerWon();
        }

        doubleDownButton.setDisable(true);
    }
    @FXML public void standButtonPressed(ActionEvent event) {
        disablePlayButtons();
        croupierTurn();
    }
    @FXML public void doubleDownButtonPressed(ActionEvent event) {
        player.addHandValue(table.giveCardFromDeck(playerCards1, player.getCardSlot()));
        player.addBid(player.getBid()); //Podwojenie stawki

        if (player.getHandValue()>21) {
            croupierWon("Busted!");
        }
        else if (player.getHandValue()==21){
            playerWon();
        }

        disablePlayButtons();
        croupierTurn();
    }


    /***** Obsługa przycisków stawki *****/
    @FXML public void bidButtonPressed50(ActionEvent event) {
        player.addBid(50);
        updateBidField();
    }

    @FXML public void bidButtonPressed100(ActionEvent event) {
        player.addBid(100);
        updateBidField();
    }

    @FXML public void bidButtonPressed200(ActionEvent event) {
        player.addBid(200);
        updateBidField();
    }

    @FXML public void bidButtonPressed500(ActionEvent event) {
        player.addBid(500);
        updateBidField();
    }

    @FXML public void bidButtonPressed1000(ActionEvent event) {
        player.addBid(1000);
        updateBidField();
    }

    @FXML public void bidButtonPressed5000(ActionEvent event) {
        player.addBid(5000);
        updateBidField();
    }


    /***** Przycisk zatwierdzenia stawki / Rozpoczęcia nowej rundy *****/
    @FXML public void bidDoneButtonPressed(ActionEvent event) {
        // Zatwierdzenie stawki
        if (bidDoneButton.getText().equals("Gotowe!")) {
            disableBid();
            firstGive();
            enablePlayButtons();
        }

        // Nowa runda
        else if (bidDoneButton.getText().equals("Następna runda!")){
           newRound();
        }
    }

    /********************************   Przyciski (moduły włącz/wyłącz)   ***********************************/

    /***** Wyłącza możliwość obstawiania *****/
    public void disableBid(){
        bidButton50.setDisable(true);
        bidButton100.setDisable(true);
        bidButton200.setDisable(true);
        bidButton500.setDisable(true);
        bidButton1000.setDisable(true);
        bidButton5000.setDisable(true);
        disableBidDoneButton();
    }

    /***** Włącza możliwość obstawiania *****/
    public void enableBid(){
        bidButton50.setDisable(false);
        bidButton100.setDisable(false);
        bidButton200.setDisable(false);
        bidButton500.setDisable(false);
        bidButton1000.setDisable(false);
        bidButton5000.setDisable(false);
        bidDoneButton.setDisable(false);
        enableBidDoneButton();
    }

    /***** Wyłącza Przycisk zatwierdzenia stawki / Rozpoczęcia nowej rundy *****/
    public void disableBidDoneButton() {
        bidDoneButton.setDisable(true);
        bidDoneButton.setText("");
    }

    /***** Włącza Przycisk zatwierdzenia stawki *****/
    public void enableBidDoneButton() {
        bidDoneButton.setDisable(false);
        bidDoneButton.setText("Gotowe!");
    }

    /***** Włącza Przycisk Rozpoczęcia nowej rundy *****/
    public void enableOkButton(){
        bidDoneButton.setDisable(false);
        bidDoneButton.setText("Następna runda!");
    }

    /***** Wyłącza przyciski sterowania *****/
    public void disablePlayButtons() {
        hitButton.setDisable(true);
        standButton.setDisable(true);
        doubleDownButton.setDisable(true);
    }

    /***** Włącza przyciski sterowania *****/
    public void enablePlayButtons() {
        hitButton.setDisable(false);
        standButton.setDisable(false);
        doubleDownButton.setDisable(false);
    }

    /************************************   Obsługa Gry   ******************************************/

    /***** Wykonuje pierwsze rozdanie (karta dla krupiera, dwie dla gracza) *****/
    public void firstGive(){
        croupier.addHandValue(table.giveCardFromDeck(croupierCards, croupier.getCardSlot()));
        player.addHandValue(table.giveCardFromDeck(playerCards1, player.getCardSlot()));
        player.addHandValue(table.giveCardFromDeck(playerCards1, player.getCardSlot()));

        if (player.getHandValue()==21){
            playerWon("Black Jack!");
        }
    }

    /***** Akcje w zależności od rozstrzygnięcia *****/
    public void croupierWon(String text){
        playerBidField.setText(text);
        roundEnd();
    }

    public void croupierWon() {
        croupierWon("Wygrał krupier!");
    }

    public void playerWon(String text) {
        playerBidField.setText(text);
        player.addCash(player.getBid()*2);
        updateCashField();
        roundEnd();
    }

    public void playerWon() {
        playerWon("Wygrałeś!");
    }

    public void draft() {
        playerBidField.setText("Remis");
        player.addCash(player.getBid());
        updateCashField();
        roundEnd();
    }

    /***** Zabiera wszystkie karty ze stołu *****/
    public void clearCardSlots(){
        table.clearTable(croupierCards);
        table.clearTable(playerCards1);
    }

    /***** Przygotowanie nowej rundy *****/
    public void newRound(){
        enableBid();
        player.resetBid();
        player.resetHandValue();
        player.resetCardSlot();
        croupier.resetHandValue();
        croupier.resetCardSlot();

        clearCardSlots();

        if (player.getCash()==0) {
            player.setCash(1000);
        }

        updateBidField();
    }

    /***** Blokada gry i możliwość rozpoczęcia nowej rundy  ******/
    public void roundEnd(){
        disablePlayButtons();
        enableOkButton();
    }

    /******************** Logika Krupiera ************************/
    public void croupierTurn(){
        while ((croupier.getHandValue()<17) && (croupier.getHandValue()<player.getHandValue())) {
            croupier.addHandValue(table.giveCardFromDeck(croupierCards, croupier.getCardSlot()));
        }

        /******************** Sprawdzanie wyniku *******************/
        if (croupier.getHandValue()>21) {
            playerWon();
        }
        else if (croupier.getHandValue() == player.getHandValue()) {
            draft();
        }
        else if (croupier.getHandValue() > player.getHandValue()) {
            croupierWon();
        }
        else {
            playerWon();
        }
    }

    /***********************************************************************************************/

    /***** Aktualizacja pola Stawka i Portfel *****/
    public void updateBidField(){
        playerBidField.setText(String.valueOf(player.getBid()));
        updateCashField();
    }

    public void updateCashField(){
        playerCashField.setText(String.valueOf(player.getCash()));
    }

    /***** Wykonywane przy starcie *****/
    public void initialize()
    {
        updateBidField();
    }


    private Player croupier = new Player();
    private Player player = new Player();
    private Table table = new Table();

}

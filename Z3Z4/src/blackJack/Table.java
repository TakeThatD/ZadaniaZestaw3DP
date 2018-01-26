package blackJack;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    Klasa odpowiada za tworzenie talii i jej tasowanie, odpowiada na żądanie i wykłada karty na stół,
    jak również usuwa z niego karty po rozgrywce.

    Zmienna packagesCount wyznacza ilość talii które uczestniczą w grze.

    Tworzenie zestawu kart do gry:
    Dodaj do kart nową talię tyle razy ile zostało ustawione (newDeck()) a następnie je posortuj (shuffleDeck())

****** Jedna talia 52 karty ma */

public class Table {
    private List<Card> deck = new ArrayList<>(); //Talia Kart
    private int packagesCount = 4;

    public Table() {

    }

    //***************** Pobiera liczbę używanych talii
    private int getPackagesCount() {
        return packagesCount;
    }

    //***************** Kładzie karty na stół (nie tasowane)
    private void newDeck() {
        for (int i=0; i<getPackagesCount(); i++) {
            for (Card.COLOR color : Card.COLOR.values()) {
                for (Card.FIGURE figure : Card.FIGURE.values()){
                    deck.add(new Card(figure, color));
                }
            }
        }
        shuffleDeck(); //tasowanie
    }

    //***************** Tasuje karty na stole
    private void shuffleDeck() {
        Random random = new Random();
        Card tempCard;
        int tempI, tempJ;

        for (int i=1; i<(8000*getPackagesCount()); i++) {
            tempI=Math.abs((((random.nextInt()%random.nextInt((i*7114)+7)+1)/3214)*random.nextInt(767)))%(getPackagesCount()*52);
            tempJ=Math.abs((((random.nextInt()%random.nextInt((i*1123)+13)+421)/9452)*random.nextInt(1224)))%(getPackagesCount()*52);

            tempCard=deck.get(tempI);
            deck.set(tempI, deck.get(tempJ));
            deck.set(tempJ, tempCard);
        }

    }

    //**************** Pobiera kartę z talii
    private Card takeFromDeck() {
        //*****Jeżeli brak kart, tasowanie
        if (deck.isEmpty()){
            newDeck();
        }

        //*****Pobranie karty z talii
        Card takenCard=deck.get(0);
        deck.remove(0);
        return takenCard;
    }

    //**************** Rozdaje do danej lini kart wybraną kartę na podane miejsce
    //**************** Zwraca wartość karty (1-11)
    public int giveCard(HBox cardsLine, Card cardToAdd, int cardSlot) {
        //********** Foreach dla każdego slotu na kartę
        cardsLine.getChildren().forEach(cardSlotTable -> {
            //****** Jeżeli slot odpowiada slotowi do wstawienia nowej karty
            if (cardSlotTable.getId().equals("c"+String.valueOf(cardSlot))) {
                if (cardSlotTable instanceof StackPane) {
                    //* Przejdź przez wszystkie atrybuty slotu i ustaw zgodnie z kartą
                    ((StackPane) cardSlotTable).getChildren().forEach(cardAttribute -> {

                            /***** Kolor karty *****/
                        if (cardAttribute instanceof Rectangle) {
                            ((Rectangle) cardAttribute).setFill(Color.valueOf("#FFFFFF"));

                            /***** Obrazek *****/
                        } else if (cardAttribute instanceof ImageView) {
                            if (cardToAdd.getColor().equals(Card.COLOR.HEARTS))
                                ((ImageView) cardAttribute).setImage(new Image("file:..\\..\\gfx\\hearts.png"));
                            else if (cardToAdd.getColor().equals(Card.COLOR.CLUBS))
                                ((ImageView) cardAttribute).setImage(new Image("file:..\\..\\gfx\\clubs.png"));
                            else if (cardToAdd.getColor().equals(Card.COLOR.DIAMONDS))
                                ((ImageView) cardAttribute).setImage(new Image("file:..\\..\\gfx\\diamonds.png"));
                            else
                                ((ImageView) cardAttribute).setImage(new Image("file:..\\..\\gfx\\spades.png"));

                            /***** Figura *****/
                        } else if (cardAttribute instanceof Label) {
                            ((Label) cardAttribute).setText(cardToAdd.getFigure().toString());
                        }
                    });
                }
            }
        });
        return cardToAdd.getValue(); //Zwrot wartości dodanej karty
    }

    //**************** Usuwa wszystkie karty z danej linii
    public void clearTable (HBox playerCards) {
        playerCards.getChildren().forEach(cardSlotTable -> {
                if (cardSlotTable instanceof StackPane) {
                    ((StackPane) cardSlotTable).getChildren().forEach(cardAttribute -> {

                    /***** Card Color *****/
                    if (cardAttribute instanceof Rectangle) {
                        ((Rectangle) cardAttribute).setFill(Color.valueOf("#1FBCFF00"));

                        /***** Image *****/
                    } else if (cardAttribute instanceof ImageView) {
                            ((ImageView) cardAttribute).setImage(new Image("file:..\\..\\gfx\\clear.png"));

                        /***** Figure *****/
                    } else if (cardAttribute instanceof Label) {
                        ((Label) cardAttribute).setText("");
                    }
                });
            }
        });
    }

    //********** Dodaje kartę do danej lini kart, pobiera ją z talii
    public int giveCardFromDeck(HBox cardLine, int cardSlot) {
        return giveCard(cardLine, takeFromDeck(), cardSlot);
    }



}

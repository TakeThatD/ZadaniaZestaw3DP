package blackJack;

 /*  Objekt poejdyńczej karty, deklaracja enum KOLORU i FIGURY
     konstruktor automatycznie wyznacza wartość karty w black jacku (1-11)
     Stworzonej karty nie można zmienić.
 */

public class Card {
    public enum COLOR {
        HEARTS, DIAMONDS, CLUBS, SPADES;
    }

    public enum FIGURE {
        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
                                                                            JACK("J"), QUEEN("Q"), KING("K"), ACE("A");
        private final String valueCard;

        FIGURE(String valueCard) {
            this.valueCard = valueCard;
        }

        @Override
        public String toString() {
            return valueCard;
        }
    }

    private FIGURE figure;
    private COLOR color;
    private int value;

    Card( FIGURE figure, COLOR color) {
        this.figure=figure;
        this.color=color;
        if ((figure.toString().equals("J"))||(figure.toString().equals("Q"))||(figure.toString().equals("K")))
            this.value=10;
        else if (figure.toString().equals("A"))
            this.value=11;
        else
            this.value=Integer.valueOf(figure.toString());
    }

    /***** Getters and Setters *****/
    public FIGURE getFigure() {
        return figure;
    }

    public COLOR getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

}

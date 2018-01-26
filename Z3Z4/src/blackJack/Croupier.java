package blackJack;

/*  Przechowuje dane na temat krupiera, minimalna funkcjonalność bez logiki */

public class Croupier {
    private int handValue;
    private int cardSlot;

    Croupier() {
        handValue=0;
        cardSlot=1;
    }

    public void resetCardSlot(){
        this.cardSlot=1;
    }

    public int getCardSlot() {
        if (cardSlot == 7) { cardSlot = 1; }
        return this.cardSlot++;
    }

    public int getHandValue(){
        return this.handValue;
    }

    public void resetHandValue(){
        this.handValue=0;
    }

    public void addHandValue(int handValue){
        this.handValue+=handValue;
    }
}
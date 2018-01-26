package blackJack;

/*  Przechowuje dane na temat gracya, minimalna funkcjonalność bez logiki, roszeżenie krupiera */


public class Player extends Croupier{
    private int Cash;
    private int Bid;

    Player() {
        super();
        Bid =0;
        Cash =1000;
    }

    public int getBid() {
        return Bid;
    }

    public void setBid(int playerBid) {
        this.Bid =playerBid;
    }

    public void resetBid() {
        this.Bid=0;
    }

    public void addBid (int bid) {
        if (getCash() >= bid) {
            setBid(getBid() + bid);
            setCash(getCash() - bid);
        }
    }

    public int getCash() {
        return Cash;
    }

    public void setCash(int playerCash) {
        this.Cash =playerCash;
    }


    public void addCash(int playerCash) {
        this.Cash +=playerCash;
    }
}

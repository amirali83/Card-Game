package Module;

public class Card {
    private String cardName;
    private int cardValue;
    private int duration;

    public Card() {
        this.cardName = "empty";
    }

    public Card(String cardName, int cardValue, int duration) {
        this.cardName = cardName;
        this.cardValue = cardValue;
        this.duration = duration;
    }

    public Card(Card card) {
        this.cardName = card.getCardName();
        this.cardValue = card.getCardValue();
        this.duration = card.getDuration();
    }

    public String getCardName() {return cardName;}
    public int getCardValue() {return cardValue;}
    public int getDuration() {return duration;}

    public void setCardName(String cardName) {this.cardName = cardName;}
    public void setCardValue(int cardValue) {this.cardValue = cardValue;}
    public void setDuration(int duration) {this.duration = duration;}

    @Override
    public Object clone() throws CloneNotSupportedException {
        Card newCard = new Card(this);
        return newCard;
    }

}

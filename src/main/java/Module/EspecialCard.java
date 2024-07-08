package Module;

public class EspecialCard extends Card{
    private String cardExplanation;

    public EspecialCard(String cardName, String cardExplanation, int cardValue, int duration) {
        super(cardName, cardValue, duration);
        this.cardExplanation = cardExplanation;
    }

    public EspecialCard(EspecialCard card) {
        super(card.getCardName(), card.getCardValue(), card.getDuration());
        this.cardExplanation = card.getCardExplanation();
    }

    public String getCardExplanation() {return cardExplanation;}

    @Override
    public Object clone() throws CloneNotSupportedException {
        EspecialCard newCard = new EspecialCard(this);
        return newCard;
    }
}

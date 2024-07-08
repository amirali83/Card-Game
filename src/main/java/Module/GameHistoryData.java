package Module;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

public class GameHistoryData {
    public String index;
    public String date1;
    public String date2;
    public String win;
    public String opponentName;
    public String opponentLevel;
    public String trophy;

    public GameHistoryData(String index, String date1, String date2, String win, String opponentName, String opponentLevel, String trophy) {
        this.index = index;
        this.date1 = date1;
        this.date2 = date2;
        this.win = win;
        this.opponentName = opponentName;
        this.opponentLevel = opponentLevel;
        this.trophy = trophy;
    }
}

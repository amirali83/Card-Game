package Controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class playMenuController {
    public Rectangle timeline00, timeline01, timeline02, timeline03, timeline04, timeline05, timeline06, timeline07, timeline08, timeline09, timeline010, timeline011, timeline012, timeline013, timeline014, timeline015, timeline016, timeline017, timeline018, timeline019, timeline020, timeline021, timeline022, timeline023;
    public Rectangle timeline10, timeline11, timeline12, timeline13, timeline14, timeline15, timeline16, timeline17, timeline18, timeline19, timeline110, timeline111, timeline112, timeline113, timeline114, timeline115, timeline116, timeline117, timeline118, timeline119, timeline120, timeline121, timeline122, timeline123;
    public Rectangle player10, player11, player12, player13, player14, player15;
    public Rectangle player20, player21, player22, player23, player24, player25;

    public Rectangle [][]timelines = new Rectangle[2][23];
    public Rectangle [][]playersdeck = new Rectangle[2][6];

    boolean bringup = false;


    public void bringUp(MouseEvent mouseEvent) {
        if (!bringup) {
            timelines[0][0] = timeline00; timelines[0][1] = timeline01; timelines[0][2] = timeline02; timelines[0][3] = timeline03; timelines[0][4] = timeline04; timelines[0][5] = timeline05; timelines[0][6] = timeline06; timelines[0][7] = timeline07; timelines[0][8] = timeline08; timelines[0][9] = timeline09; timelines[0][10] = timeline010; timelines[0][11] = timeline011; timelines[0][12] = timeline012; timelines[0][13] = timeline013; timelines[0][14] = timeline014; timelines[0][15] = timeline015; timelines[0][16] = timeline016; timelines[0][17] = timeline017; timelines[0][18] = timeline018; timelines[0][19] = timeline019; timelines[0][20] = timeline020; timelines[0][21] = timeline021; timelines[0][22] = timeline022; timelines[0][23] = timeline023;
            timelines[1][0] = timeline10; timelines[1][1] = timeline11; timelines[1][2] = timeline12; timelines[1][3] = timeline13; timelines[1][4] = timeline14; timelines[1][5] = timeline15; timelines[1][6] = timeline16; timelines[1][7] = timeline17; timelines[1][8] = timeline18; timelines[1][9] = timeline19; timelines[1][10] = timeline110; timelines[1][11] = timeline111; timelines[1][12] = timeline112; timelines[1][13] = timeline113; timelines[1][14] = timeline114; timelines[0][15] = timeline015; timelines[0][16] = timeline016; timelines[0][17] = timeline017; timelines[0][18] = timeline018; timelines[0][19] = timeline019; timelines[0][20] = timeline020; timelines[0][21] = timeline021; timelines[0][22] = timeline022; timelines[0][23] = timeline023;
            bringup = true;
        }
    }
}

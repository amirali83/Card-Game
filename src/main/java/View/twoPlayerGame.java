package View;

import Module.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class twoPlayerGame {
    private static User []users = new User[2];
    private static GameCh []chars = new GameCh[2];
    private static Scanner sc;
    private static Card [][]timeLines = new Card[2][21];
    private static Card [][]playersdeck = new Card[2][6];
    private static int []playersDamage = new int[2];
    private static int []playersRound = new int[2];
    private static int inCharge;
    private static int gameStarter;
    private static int cardType1, cardType2;
    private static int cardAD1, cardAD2;
    private static int cardDamage1, cardDamage2;
    private static ArrayList<Integer> []usedEspecial = new ArrayList[2];
    private static boolean inTimeLine;
    private static boolean gameFinished;
    private static int winner;
    private static int []live = new int[2];

    public static int start(User user1, User user2, Scanner sc) {
        twoPlayerGame.sc = sc;
        twoPlayerGame.users[0] = user1;
        twoPlayerGame.users[1] = user2;
        live[0] = user1.getHP();
        live[1] = user2.getHP();

        System.out.println("Select first player character");
        String command = sc.nextLine();
        if (Inputs.SELECT_CHARACTER.getMatcher(command).matches())
            chars[0] = selectCh(Inputs.SELECT_CHARACTER.getMatcher(command));
        else {
            System.out.println("Invalid input");
            twoPlayerGame.start(user1, user2, sc);
            return -1;
        }
        System.out.println("Select second player character");
        command = sc.nextLine();
        if (Inputs.SELECT_CHARACTER.getMatcher(command).matches())
            chars[1] = selectCh(Inputs.SELECT_CHARACTER.getMatcher(command));
        else {
            System.out.println("Invalid input");
            twoPlayerGame.start(user1, user2, sc);
            return -1;
        }

        Random random = new Random();
        int f = Math.abs(random.nextInt()) % 21, s = Math.abs(random.nextInt()) % 21;
        for (int i = 0; i < 21; i++) {
            timeLines[0][i] = new Card();
            timeLines[1][i] = new Card();
            if (i == f)
                timeLines[0][i].setCardName("null");
            if (i == s)
                timeLines[1][i].setCardName("null");
        }
        for (int i = 0; i < 5; i++) {
            f = Math.abs(random.nextInt()) % user1.getCards().size();
            s = Math.abs(random.nextInt()) % user2.getCards().size();
            try {
                playersdeck[0][i] = (Card) user1.getCards().get(f).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            try {
                playersdeck[1][i] = (Card) user2.getCards().get(s).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        playersdeck[0][5] = new Card();
        playersdeck[1][5] = new Card();
        playersDamage[0] = playersDamage[1] = 0;
        playersRound[0] = playersRound[1] = 4;
        usedEspecial[0] = new ArrayList<>();
        usedEspecial[1] = new ArrayList<>();
        inTimeLine = false;
        gameFinished = false;
        drawGrphic();
        return startGame();
    }

    private static int startGame() {
        Random r = new Random();
        inCharge = Math.abs(r.nextInt()) % 2;
        gameStarter = inCharge;
        System.out.println("Player in charge " + (inCharge + 1));
        String command = sc.nextLine();
        while (!gameFinished) {
            if (Inputs.SHOW_CARD_INFORMATION.getMatcher(command).matches()) {
                showCardInformation(Inputs.SHOW_CARD_INFORMATION.getMatcher(command));
            } else if (Inputs.PLACE_CARD_NUMBER.getMatcher(command).matches()) {
                placeCard(Inputs.PLACE_CARD_NUMBER.getMatcher(command));
            } else if (Inputs.HELP.getMatcher(command).matches()){
                help();
            } else
                System.out.println("invalid input");
            if (gameFinished)
                break;
            System.out.println("Player in charge " + (inCharge + 1));
            command = sc.nextLine();
        }
        if (winner == 0) return 1;
        if (winner == 1) return 2;
        return 0;
    }

    private static void help() {
        System.out.println(Inputs.SHOW_CARD_INFORMATION.getString());
        System.out.println(Inputs.PLACE_CARD_NUMBER.getString());
        System.out.println();
    }

    private static void placeCard(Matcher matcher) {
        matcher.find();
        if (!matcher.group("number").matches("[1-5]")) {
            System.out.println("card number invalid");
            return;
        }
        //        if (!matcher.group("index").matches("[0-21]")) {
//            System.out.println("card index invalid");
//            return;
//        }
        int cardIndex = Integer.parseInt(matcher.group("number")) - 1;
        int timelineIndex = Integer.parseInt(matcher.group("index")) - 1;
        if (playersdeck[inCharge][cardIndex].getDuration() != 0) {
            for (int i = 0; i < playersdeck[inCharge][cardIndex].getDuration(); i++) {
                if (timeLines[inCharge][timelineIndex + i] == null) {
                    System.out.println("you cant place your card here");
                    return;
                }
                if (!timeLines[inCharge][timelineIndex + i].getCardName().equals("empty")) {
                    System.out.println("you cant place your card here");
                    return;
                }
            }
            for (int i = 0; i < playersdeck[inCharge][cardIndex].getDuration(); i++)
                timeLines[inCharge][timelineIndex + i] = playersdeck[inCharge][cardIndex];
        }
        else
            handleSpecial((EspecialCard) playersdeck[inCharge][cardIndex]);

        playersRound[inCharge]--;
        playersDamage[0] = playersDamage[1] = 0;
        for (int i = 0; i < 21; i++) {
            checkDamage(i);
            if (timeLines[0][i].getClass().equals(NormalCard.class))
                if (((NormalCard) timeLines[0][i]).getCh().equals(chars[0]))
                    cardDamage1 += (int) (cardDamage1 *= 0.3);

            if (timeLines[1][i].getClass().equals(NormalCard.class))
                if (((NormalCard) timeLines[1][i]).getCh().equals(chars[1]))
                    cardDamage2 += (int) (cardDamage2 *= 0.3);

            if (cardAD1 > cardAD2) playersDamage[0] += cardDamage1;
            else if (cardAD1 < cardAD2) playersDamage[1] += cardDamage2;
        }

        replaceUsedCard(cardIndex);

        drawGrphic();

        //emtiazi
        //completeCard();

        if (gameStarter != inCharge) {
            System.out.println("in timeline");
            inTimeLine = true;
            boolean fin = playTimeLine();
            if (playersRound[gameStarter] == 0 || fin) {
                drawGrphic();
                if (users[0].getHP() > users[1].getHP()) {
                    System.out.println("user1 won");
                    getTrophy(users[0], users[1]);
                }
                else if (users[0].getHP() < users[1].getHP()) {
                    System.out.println("user2 won");
                    getTrophy(users[1], users[0]);
                }
                else {
                    System.out.println("draw");

                }
                users[0].setHP(live[0]);
                users[1].setHP(live[1]);
                gameFinished = true;
            }
            inTimeLine = false;
        }

        if (inCharge == 0) inCharge = 1;
        else inCharge = 0;

    }

    private static void getTrophy(User user1, User user2) {
        user1.setHP(user1.getXP() + 100);
        user2.setHP(user2.getXP() + 100);
        user1.setCoins(user1.getCoins() + 50);
    }

    private static boolean playTimeLine() {
        for (int i = 0; i < 21; i++) {
            checkDamage(i);
            if (cardAD1 > cardAD2) users[1].setHP(users[1].getHP() - cardDamage1);
            else if (cardAD1 < cardAD2) users[0].setHP(users[0].getHP() + cardDamage2);
            if (users[0].getHP() <= 0 || users[1].getHP() <= 0) return true;
        }
        return false;
    }

    private static void replaceUsedCard(int cardIndex) {
        Random r = new Random();
        try {
            playersdeck[inCharge][cardIndex] = (Card) users[inCharge].getCards().get(Math.abs(r.nextInt()) % users[inCharge].getCards().size()).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void checkDamage(int i) {
        if (timeLines[0][i].getCardName().equals("null"))
            cardType1 = -1;
        else if (!timeLines[0][i].getCardName().equals("null") && timeLines[0][i].getClass().equals(NormalCard.class))
            cardType1 = 0;
        else
            cardType1 = 1;

        if (timeLines[1][i].getCardName().equals("null"))
            cardType2 = -1;
        else if (!timeLines[1][i].getCardName().equals("null") && timeLines[1][i].getClass().equals(NormalCard.class))
            cardType2 = 0;
        else
            cardType2 = 1;

        cardDamage1 = cardDamage2 = 0;

        if (cardType1 == 0) {cardAD1 = ((NormalCard) timeLines[0][i]).getCardAttack_Deffence(); cardDamage1 = ((NormalCard) timeLines[0][i]).getPlayerDamage();}
        if (cardType2 == 0) {cardAD2 = ((NormalCard) timeLines[1][i]).getCardAttack_Deffence(); cardDamage2 = ((NormalCard) timeLines[1][i]).getPlayerDamage();}

        if (cardType1 == 1 || cardType1 == -1) cardAD1 = 0;
        if (cardType2 == 1 || cardType2 == -1) cardAD2 = 0;

        handleSpecial(i);
    }

    private static void handleSpecial(int i) {
        if (timeLines[0][i].getCardName().equals("shield"))
            cardAD2 = 0;
        if (timeLines[1][i].getCardName().equals("shield"))
            cardAD1 = 0;

        if (!inTimeLine) {
            if (timeLines[0][i].getCardName().equals("heal"))
                users[0].setHP(users[0].getHP() + 50);
            if (timeLines[1][i].getCardName().equals("heal"))
                users[1].setHP(users[1].getHP() + 50);
        }
    }

    private static void handleSpecial(EspecialCard card) {
        Random r = new Random();
        if (card.getCardName().equals("power buffer")) {
            int in;
            while (true) {
                in = Math.abs(r.nextInt()) % 21;
                if (timeLines[inCharge][in].getClass().equals(NormalCard.class)) {
                    buffCard((NormalCard) timeLines[inCharge][in]);
                    break;
                }
            }
        }

        else if (card.getCardName().equals("hole changer")) {
            int f = -1, s = -1;
            for (int i = 0; i < 21; i++) {
                if (timeLines[0][i].getCardName().equals("null"))
                    f = i;
                if (timeLines[1][i].getCardName().equals("null"))
                    s = i;
            }
            if (f != -1) replaceNull(0, f);
            if (s != -1) replaceNull(1, s);
        }

        else if (card.getCardName().equals("fixer")) {
            for (int i = 0; i < 21; i++)
                if (timeLines[inCharge][i].getCardName().equals("null"))
                    timeLines[inCharge][i].setCardName("empty");
        }

        else if (card.getCardName().equals("reducer")) {
            playersRound[0]--;
            playersRound[1]--;
        }

        else if (card.getCardName().equals("thief")) {
            int in, notInCharge;
            if (inCharge == 0) notInCharge = 1;
            else notInCharge = 0;
            in = Math.abs(r.nextInt()) % 5;
            Card temp = null;
            try {
                temp = (Card) playersdeck[notInCharge][4].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            try {
                playersdeck[notInCharge][4] = (Card) playersdeck[notInCharge][in].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            playersdeck[notInCharge][in] = temp;
            try {
                playersdeck[inCharge][6] = (Card) playersdeck[notInCharge][4].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            playersdeck[notInCharge][4] = new Card();
            usedEspecial[inCharge].add(1);
        }

        else if (card.getCardName().equals("changer")) {
            int in, notInCharge;
            if (inCharge == 0) notInCharge = 1;
            else notInCharge = 0;
            while (true) {
                in = Math.abs(r.nextInt()) % 5;
                if (playersdeck[notInCharge][in].getClass().equals(NormalCard.class)) {
                    deBuffCardDamage((NormalCard) playersdeck[notInCharge][in]);
                    break;
                }
            }
            while (true) {
                in = Math.abs(r.nextInt()) % 5;
                if (playersdeck[notInCharge][in].getClass().equals(NormalCard.class)) {
                    deBuffCardAD((NormalCard) playersdeck[notInCharge][in]);
                    break;
                }
            }
        }

        else if (card.getCardName().equals("copy")) {
            System.out.println("Enter the index: ");
            int in = Integer.parseInt(sc.nextLine()) - 1;
            try {
                playersdeck[inCharge][5] = (Card) playersdeck[inCharge][in].clone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            usedEspecial[inCharge].add(2);
        }

//        else if (card.getCardName().equals("hider")) {
//
//        }
    }

    private static void deBuffCardAD(NormalCard card) {
        int t = (int) (card.getCardAttack_Deffence() * 0.6);
        card.setCardAttack_Deffence(t);
    }

    private static void deBuffCardDamage(NormalCard card) {
        int t = (int) (card.getPlayerDamage() * 0.6);
        card.setPlayerDamage(t);
    }

    private static void replaceNull(int i, int f) {
        boolean flag = true;
        for (int j = 0; j < 21; j++)
            if (timeLines[i][j].getCardName().equals("empty")) {
                flag = false;
                break;
            }
        if (flag) return;
        Random r = new Random();
        int in;
        while (true) {
            in = Math.abs(r.nextInt()) % 21;
            if (timeLines[i][in].getCardName().equals("empty")) {
                timeLines[i][in].setCardName("null");
                timeLines[i][f].setCardName("empty");
                break;
            }
        }
    }


    private static void buffCard(NormalCard card) {
        int t = (int) (card.getCardAttack_Deffence() * 0.5);
        card.setCardAttack_Deffence(card.getCardAttack_Deffence() + t);
        t = (int) (card.getPlayerDamage() * 0.5);
        card.setPlayerDamage(card.getPlayerDamage() + t);
    }

    private static void showCardInformation(Matcher matcher) {
        matcher.find();
        int player = -1, index = -1;
        if (matcher.group("player").equals(users[0].getUsername())) player = 0;
        else if (matcher.group("player").equals(users[1].getUsername())) player = 1;
        else {
            System.out.println("Wrong player");
            System.out.println("try again");
            return;
        }
        if (!(matcher.group("number").equals("1") || matcher.group("number").equals("2") ||
                matcher.group("number").equals("3") || matcher.group("number").equals("4") ||
                matcher.group("number").equals("5"))) {
            System.out.println("Wrong index");
            System.out.println("try again");
            return;
        }
        index = Integer.parseInt(matcher.group("number")) - 1;
        if (playersdeck[player][index].getClass().equals(EspecialCard.class)) {
            System.out.println("Card name: " + playersdeck[player][index].getCardName());
            System.out.println("Card duration: " + ((EspecialCard) playersdeck[player][index]).getDuration());
            System.out.println("Card explanation: " + ((EspecialCard) playersdeck[player][index]).getCardExplanation());
        } else {
            System.out.println("Card name: " + playersdeck[player][index].getCardName());
            System.out.println("Attack/Defence: " + ((NormalCard) playersdeck[player][index]).getCardAttack_Deffence());
            System.out.println("Duration: " + ((NormalCard) playersdeck[player][index]).getDuration());
            System.out.println("Player damage: " + ((NormalCard) playersdeck[player][index]).getPlayerDamage());
        }
        System.out.println();
    }

    private static void drawGrphic() {
        for (int i = 0; i < 2; i++) {
            System.out.println("Player " + (i + 1) + ": ");
            System.out.println("Health: " + users[i].getHP());
            System.out.print(playersRound[i] + " ");
            for (int j = 0; j < 21; j++) {
                if (timeLines[i][j] != null)
                    System.out.print(timeLines[i][j].getCardName());
                else
                    System.out.print("-1");
                System.out.print(" ");
            }
            System.out.println(playersDamage[i]);
            System.out.print("card name: ");
            for (int j = 0; j < 6; j++)
                System.out.print(playersdeck[i][j].getCardName() + " ");
            System.out.println();
            System.out.print("card duration: ");
            for (int j = 0; j < 6; j++)
                System.out.print(playersdeck[i][j].getDuration() + " ");
            System.out.println();
            System.out.print("card damage: ");
            for (int j = 0; j < 6; j++) {
                if (playersdeck[i][j].getClass().equals(NormalCard.class))
                    System.out.print(((NormalCard) playersdeck[i][j]).getPlayerDamage() + " ");
                else
                    System.out.print("- ");
            }
            System.out.println();
            System.out.print("card AD: ");
            for (int j = 0; j < 6; j++) {
                if (playersdeck[i][j].getClass().equals(NormalCard.class))
                    System.out.print(((NormalCard) playersdeck[i][j]).getPlayerDamage() + " ");
                else
                    System.out.print("- ");
            }
            System.out.println();
            System.out.print("card Ch: ");
            for (int j = 0; j < 6; j++) {
                if (playersdeck[i][j].getClass().equals(NormalCard.class)) {
                    if (((NormalCard) playersdeck[i][j]).getCh().equals(GameCh.CHARACTER1))
                        System.out.print("1 ");
                    else if (((NormalCard) playersdeck[i][j]).getCh().equals(GameCh.CHARACTER2))
                        System.out.print("2 ");
                    else if (((NormalCard) playersdeck[i][j]).getCh().equals(GameCh.CHARACTER3))
                        System.out.print("3 ");
                    else if (((NormalCard) playersdeck[i][j]).getCh().equals(GameCh.CHARACTER4))
                        System.out.print("4 ");
                }
                else
                    System.out.print("- ");
            }
            System.out.println();
            System.out.println();
        }
    }

    private static GameCh selectCh(Matcher matcher) {
        matcher.find();
        if (matcher.group("character").matches("character1"))
            return GameCh.CHARACTER1;
        else if (matcher.group("character").matches("character2"))
            return GameCh.CHARACTER2;
        else if (matcher.group("character").matches("character3"))
            return GameCh.CHARACTER3;
        else if (matcher.group("character").matches("character4"))
            return GameCh.CHARACTER4;
        return null;
    }
}
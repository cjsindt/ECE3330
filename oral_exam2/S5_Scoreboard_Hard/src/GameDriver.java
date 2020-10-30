import java.util.Scanner;

public class GameDriver {

    public static void main(String[] args){
//        Team hawk = new Team("Hawkeyes");
//        Team dirt = new Team("Cyclones");
//        Game hyveeCyHawk = new AmericanFootball(dirt, hawk);
//        ScoringMethod touchdown = hyveeCyHawk.getScoringMethods()[0];
//        System.out.println("Hawks: " + hawk.getScore() + " Cyc: " + dirt.getScore());
//        hyveeCyHawk.addScore(touchdown, hawk);
//        System.out.println("Hawks: " + hawk.getScore() + " Cyc: " + dirt.getScore());

        Scanner sc = new Scanner(System.in);
        int gameType = 0;

        Game game;

        System.out.println("Select the type of game:\n1. American Football\n2. Basketball\n3. Football\n4. Hockey\nEnter Choice: ");

        while(gameType < 1 && gameType > 4)
            gameType = sc.nextInt();

        System.out.println("Enter Home Team: ");
        String home = sc.nextLine();
        System.out.println("Enter Away Team: ");
        String away = sc.nextLine();

        if(gameType == 1){
            game = new Football(new Team(home), new Team(away));
        }


    }
}

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
        int gameType;
        String home;
        String away;
        Game game;

        do{
            System.out.print("Select the type of game:\n1. American Football\n2. Basketball\n3. Football\n4. Hockey\nEnter Choice: ");
            gameType = sc.nextInt();
        }while(gameType < 1 || gameType > 4);

        System.out.print("Enter Home Team: ");
        home = sc.next();

        System.out.print("Enter Away Team: ");
        away = sc.next();

        if(gameType == 1){
            game = new AmericanFootball(new Team(home), new Team(away));
        } else if(gameType == 2){
            game = new Basketball(new Team(home), new Team(away));
        } else if(gameType == 3){
            game = new Football(new Team(home), new Team(away));
        } else{
            game = new Hockey(new Team(home), new Team(away));
        }

        while(!game.isGameOver()){
            System.out.println(game.getHomeTeam().getTeamName() + " - " + game.getHomeTeam().getScore() + ", " + game.getAwayTeam().getTeamName() + " - " + game.getAwayTeam().getScore());
            for(int i = 0; i < game.getScoringMethods().length + 1; i++){
                System.out.println();
            }
        }


    }
}

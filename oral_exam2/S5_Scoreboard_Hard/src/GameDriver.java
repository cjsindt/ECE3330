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

        //variables used in the program
        Scanner sc = new Scanner(System.in);
        int input;  //input variable from the user
        String home;    //home team name
        String away;    //away team name
        Game game;  //the game
        int optionsLength = 0;  //different sports have different amounts of options, this is that value

        //take user input until they give a valid input
        do{
            System.out.print("Select the type of game:\n1. American Football\n2. Basketball\n3. Football\n4. Hockey\nEnter Choice: ");
            input = sc.nextInt();
        }while(input < 1 || input > 4);

        System.out.print("Enter Home Team: ");
        home = sc.next();

        System.out.print("Enter Away Team: ");
        away = sc.next();

        //depending on the input, make the specified game
        if(input == 1){
            game = new AmericanFootball(new Team(home), new Team(away));
        } else if(input == 2){
            game = new Basketball(new Team(home), new Team(away));
        } else if(input == 3){
            game = new Football(new Team(home), new Team(away));
        } else{
            game = new Hockey(new Team(home), new Team(away));
        }

        //play the game as long as it isn't over
        while(!game.isGameOver()){
            //print the scores and current period
            System.out.println("\n\n" + game.getHomeTeam().getTeamName() + " - " + game.getHomeTeam().getScore() + ", " + game.getAwayTeam().getTeamName() + " - " + game.getAwayTeam().getScore());
            System.out.println("Current " + game.getPeriodLength() + ": " + game.getPeriod());
            System.out.println("\nMenu:");

            //Display different options for each team depending on the game
            for(int i = 1; i < (game.getScoringMethods().length*2) + 1; i++){
                if(i <= game.getScoringMethods().length){
                    System.out.println(i + ". " + game.getHomeTeam().getTeamName() + " " + game.getScoringMethods()[i-1].getName());
                } else {
                    System.out.println(i + ". " + game.getAwayTeam().getTeamName() + " " + game.getScoringMethods()[i-(game.getScoringMethods().length + 1)].getName());
                }
                if(i == game.getScoringMethods().length*2){
                    System.out.print(i+1 + ". End " + game.getPeriodLength() + "\nEnter Choice: ");
                    optionsLength = i+1;
                }
            }

            //take user input until the user gives a valid input
            do {
                input = sc.nextInt();
            }while(input < 1 || input > optionsLength);

            //do the proper action that the user wants done
            if(input == optionsLength){
                game.endPeriod();
            } else if(input <= game.getScoringMethods().length){ //first half option applies to the home team
                game.getHomeTeam().addScore(game.getScoringMethods()[input-1].getValue());
            } else{ //second half option applies to the away team
                game.getAwayTeam().addScore(game.getScoringMethods()[input -(game.getScoringMethods().length+1)].getValue());
            }
        }

        //game is over, display the winner and scores
        System.out.println("\n\nGame is over.");
        System.out.println(game.getHomeTeam().getTeamName() + " - " + game.getHomeTeam().getScore() + ", " + game.getAwayTeam().getTeamName() + " - " + game.getAwayTeam().getScore());
        System.out.println("Winner: " + game.winner().getTeamName());

    }
}

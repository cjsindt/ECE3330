public class GameDriver {

    public static void main(String[] args){
        Team hawk = new Team("Hawkeyes");
        Team dirt = new Team("Cyclones");
        Game hyveeCyHawk = new AmericanFootball(dirt, hawk);
        ScoringMethod touchdown = hyveeCyHawk.getScoringMethods()[0];
        System.out.println("Hawks: " + hawk.getScore() + " Cyc: " + dirt.getScore());
        hyveeCyHawk.addScore(touchdown, hawk);
        System.out.println("Hawks: " + hawk.getScore() + " Cyc: " + dirt.getScore());
    }
}

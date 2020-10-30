public class Team {
    private String teamName;
    private int score;

    public Team(String name){
        teamName = name;
        score = 0;
    }

    public int getScore(){
        return score;
    }

    public void addScore(int add){
        score += add;
    }

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String name){
        teamName = name;
    }
}

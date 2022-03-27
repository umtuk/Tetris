package Tetris.domain.score.entity;

public class Score {
    private String UserName;
    private double GameEndTime;
    private int Score;
    
    public Score(String new_UserName, double new_GameEndTime, int new_Score){
        this.UserName = new_UserName;
        this.GameEndTime = new_GameEndTime;
        this.Score = new_Score;
    }

    public String Get_UserName(){
        return this.UserName;
    }
    public double Get_GameEndTime(){
        return this.GameEndTime;
    }
    public int Get_Score(){
        return this.Score;
    }

    public void Set_UserName(String new_UserName){
        this.UserName = new_UserName;
    }
    public void Set_GameEndTime(double new_GameEndTime){
        this.GameEndTime = new_GameEndTime;
    }
    public void Set_Score(int new_Score){
        this.Score = new_Score;
    }
}

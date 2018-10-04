package Model;

import java.util.Observable;

public class TicTacToeModel extends Observable {

    private int[] numList;
    private ComputerPlayer comp;
    private final int PLAYERS_TOKEN = 1;
    private final int COMPUTER_TOKEN = 2;
    private final int NEUTRAL_TOKEN = 0;

    public TicTacToeModel(CompPlayer comp){
        this.initializeNumList();
        this.comp = comp;

    }

    private void initializeNumList() {
        numList = new int[9];
        for (int i : numList)
            i = NEUTRAL_TOKEN;
    }

    public void announce(String arg){
        setChanged();
        notifyObservers();
    }

    public void selectedBtn(int btnNum, boolean player){
        if(player)
            numList[btnNum] = PLAYERS_TOKEN;
        else
            numList[btnNum] = COMPUTER_TOKEN;

            announce(":(");
    }


    public void computerTurn(){

        int selected = comp.checkResponse(numList);
        selectedBtn(selected,false);
    }

    public int[] getNmList(){
        return numList;
    }

    public boolean checkWon(int token) {
        for (int i = 0; i <=6;i=i+3){
           if(numList[i] == token && numList[i+2] == token && numList[i+1] == token){
                return true;
            }
        }
        for (int i = 0; i <= 2;i++){
            if(numList[i] == token && numList[i+3] == token && numList[i+6] == token){
                return true;
            }
        }

        if(numList[4] == token && ((numList[0] == token && numList[8] == token  ) || (numList[2] == token && numList[6] == token)))
            return true;
        return false;
    }

    public boolean checkTie(){
        for(int i : numList){
            if(i == NEUTRAL_TOKEN)
                return false;
        }
        return true;
    }

}


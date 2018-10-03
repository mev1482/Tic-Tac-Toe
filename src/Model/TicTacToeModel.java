package Model;

import java.util.Observable;

public class TicTacToeModel extends Observable {

    private int[] numList;
    private ComputerPlayer comp;
    final int PLAYERS_TOKEN = 1;
    final int COMPUTER_TOKEN = 2;

    public TicTacToeModel(CompPlayer comp){
        this.initializeNumList();
        this.comp = comp;

    }

    private void initializeNumList() {
        numList = new int[9];
        for (int i : numList)
            i = 0;
    }

    public void announce(String arg){
        setChanged();
        notifyObservers();
    }

    public void selectedBtn(int btnNum, boolean player){
        if(player)
            numList[btnNum] = 1;
        else
            numList[btnNum] = 2;

            announce(":(");
    }


    public void computerTurn(){

        int selected = comp.checkResponse(numList,PLAYERS_TOKEN);
        if(selected == -1)
            selected = comp.checkResponse(numList,COMPUTER_TOKEN);
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
        for (int i = 0; i <= 3;i++){
            if(numList[i] == token && numList[i+3] == token && numList[i+6] == token){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        CompPlayer c = new CompPlayer();
        int[] k = {1,0,2,0,2,1,1,0,0};
        System.out.println(c.checkResponse(k,1));
    }

}


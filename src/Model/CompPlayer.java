package Model;


public class CompPlayer implements ComputerPlayer {


    final int PLAYER_TOKEN = 1;
    final int COMPUTER_TOKEN = 2;

    @Override
    public int checkResponse(int[] spaces) {
        int response;
        response = check(spaces,COMPUTER_TOKEN);
        if(response != -1)
            return response;
        response = check(spaces,PLAYER_TOKEN);
        if(response != -1)
            return response;
        return findBox(spaces);

    }

    private int check(int[] spaces,int token){
        int response;
        response = checkRows(spaces,token);
        if(response != -1)
            return response;
        response = checkCol(spaces,token);
        if(response != -1)
            return response;
        response = checkDiagnol(spaces,token);
        if(response != -1)
            return response;
        return -1;
    }



    private int checkRows(int[] spaces, int token){
        int response;
        for(int i = 0;i<=6;i=i+3){

            response = check(spaces[i],i,spaces[i+1],i+1,spaces[i+2],i+2,token);
            if(response != -1){
                System.out.println("ROWS CHECKING FOR " + token);
                return response;
            }
        }
        return -1;
    }

    private int checkCol(int[] spaces, int token){
        int response;
        for(int i = 0;i<3;i++){
            response = check(spaces[i],i,spaces[i+3],i+3,spaces[i+6],i+6,token);
            if(response != -1) {
                System.out.println("COLS CHECKING FOR " + token);
                return response;
            }
        }
        return -1;
    }

    private int checkDiagnol(int[] spaces, int token){
        int response;
        response = check(spaces[0],0,spaces[4],4,spaces[8],8,token);
        if(response != -1) {
            System.out.println("DIAG CHECKING FOR " + token);
            return response;
        }
        response = check(spaces[2],2,spaces[4],4,spaces[6],6,token);
        if(response != -1){
            System.out.println("DIAG 2 CHECKING FOR " + token);
        }
        return response;
    }





    private int check(int slot1, int slot1num, int slot2, int slot2num, int slot3,int slot3num, int checkFor) {

        if (slot1 == checkFor && slot2 == checkFor && slot3 == 0 ) {
            return slot3num;
        } else if (slot1 == checkFor && slot3 == checkFor && slot2 == 0) {
            return slot2num;
        } else if (slot2 == checkFor && slot3 == checkFor && slot1 == 0) {
            return slot1num;
        }
        else
            return -1;
    }

    private int findBox(int[] spaces){
        if(spaces[4] == 0)
            return 4;
        if(spaces[2] == 0)
            return 2;
        if(spaces[0] == 0)
            return 0;
        if(spaces[6] == 0)
            return 6;
        if(spaces[8] == 0)
            return 8;
        for(int i = 0;i<spaces.length;i++){
            if(spaces[i] == 0)
                return i;
        }
        return -1;
    }

}

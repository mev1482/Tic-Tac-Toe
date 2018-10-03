package Model;

public class CompPlayer implements ComputerPlayer {

    @Override
    public int checkResponse(int[] spaces, int token) {
        int checkedResponse;
        checkedResponse = checkEach(spaces,3,token);
        if(checkedResponse != -1)
            return checkedResponse;
        checkedResponse = checkEach(spaces, 1, token);
        if(checkedResponse == -1){
            return findBox(spaces);
        }
        return checkedResponse;
    }

    public int checkEach(int[] spaces, int increment, int token) {
        int checkedResponse = -1;
        int colInc = 0;
        if (increment == 1) {
            colInc = 2;
        }
        for (int i = 0; (i+2+(2*colInc)) < spaces.length; i = i + increment) {
            checkedResponse = check(spaces[i],i, spaces[i + 1 + colInc],i+1+colInc, spaces[i + 2 + (2 * colInc)],i+2+(2*colInc), token);
            if (checkedResponse != -1)
                return checkedResponse;
        }
        return checkedResponse;
    }



    public int check(int slot1, int slot1num, int slot2, int slot2num, int slot3,int slot3num, int checkFor) {

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

    public int findBox(int[] spaces){
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

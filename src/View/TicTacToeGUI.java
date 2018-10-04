package View;

import Model.CompPlayer;
import javafx.application.Application;
import Model.TicTacToeModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class TicTacToeGUI extends Application implements Observer {

    private boolean userTurn = true;
    private TicTacToeModel tm;
    private Button[] btnList;
    private final String[] IMAGES = {"view/resources/white_back.png","view/resources/o_image.png","view/resources/x_image.png"};
    private Label lbl;
    private final int BOARD_SIZE = 3;
    private final String PLAYER_TURN = "Select a Box! You Are O!";
    private final String COMPUTER_TURN = "It is the computers turn!!";
    private final String WON = "You won!! Press Play again to play a new game!!";
    private final String LOST = "You Lost!! Press play again to play a new game!!";
    private final String TIE = "A TIE!! Press play again to play a new game!!";


    @Override
    public void init(){
        tm = new TicTacToeModel(new CompPlayer());
        btnList = new Button[9];
        tm.addObserver(this);
        lbl = new Label();
        lbl.setText(PLAYER_TURN);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setScene(makeScene());
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);

    }

    private Scene makeScene() {
        Scene sc = new Scene(makeBorderPane());
        return sc;
    }

    private BorderPane makeBorderPane() {
        BorderPane bp = new BorderPane();
        bp.setCenter(this.makeGridPane());
        bp.setBottom(lbl);
        return bp;
    }

    private GridPane makeGridPane() {
        int count = 0;
        GridPane gp = new GridPane();
        gp.setHgap(3);
        gp.setVgap(3);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Button btn = new Button();
                btn.setMinWidth(115);
                btn.setMinHeight(115);
                btn.setId(""+count);
                btnList[count++] = btn;
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tm.selectedBtn(Integer.valueOf(btn.getId()),true);

                    }
                });
                btn.setPrefSize(100, 100);
                btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                gp.add(btn, j, i);
            }
        }

        return gp;
    }

    public void compTurn(){
        lbl.setText(COMPUTER_TURN);
        this.disableBtns();
    }

    public void playerTurn(){
        System.out.println("PLATER TURN");
        int[] nL = tm.getNmList();
        for (int i = 0; i < nL.length; i++) {
            if(nL[i] == 0) {
                System.out.println("set Disable to false on: " + i);
                btnList[i].setDisable(false);
            }
        }


    }
    @Override
    public void update(Observable o, Object arg) {

        int[] nL = tm.getNmList();
        for (int i = 0; i < nL.length; i++) {
            btnList[i].setGraphic(new ImageView(IMAGES[nL[i]]));
        }
        if(tm.checkTie()) {
            this.tie();
        }
        else if(tm.checkWon(1)) {
            this.won(1);
        }
        else if(tm.checkWon(2)) {
            this.won(2);
        }
        else if (userTurn) {
            compTurn();
            userTurn = false;
            tm.computerTurn();
        } else{
            playerTurn();
            userTurn = true;
        }
    }

    private void won(int i) {
        this.disableBtns();
        if(i == 1)
        lbl.setText(WON);
        else
            lbl.setText(LOST);
    }

    private void tie(){
        this.disableBtns();
        lbl.setText(TIE);
    }

    private void disableBtns(){
        for(Button b: btnList)
            b.setDisable(true);
    }
}

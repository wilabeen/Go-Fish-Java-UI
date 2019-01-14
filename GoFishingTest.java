import java.util.*;
import javax.swing.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.ActionEvent;

public class GoFishingTest extends Application{
   private Player2 player1,player2;
   private Deck2 deck;
   private Hand2 hold;
   private HBox cinematic;
   private Button contin;
   private ArrayList<Button> choice;
   private BorderPane layout;
   private VBox scoreBoard;
   private int turn;
   private int stages;
   private VBox played;
   private HBox chooseCard;
   private Label descriptor;
   
   @Override
   public void start(Stage stage){
      played = new VBox();
      chooseCard = new HBox();
      hold = new Hand2();
      cinematic = new HBox();
      contin = new Button("Continue ->");
      contin.setOnAction(this::continued);
      choice = new ArrayList<Button>(0);
      layout = new BorderPane();
      scoreBoard = new VBox();
      scoreBoard.setAlignment(Pos.CENTER);
      scoreBoard.setStyle("-fx-background-color: white");
      turn = 0;
      stages = 0;
      
      Button starting = new Button("START");
      starting.setAlignment(Pos.CENTER);
      starting.setOnAction(this::starting);
      layout.setCenter(starting);
      layout.setStyle("-fx-background-color: green");
      Scene scene = new Scene(layout,2000,1000);
      stage.setTitle("Go Fish!");
      stage.setScene(scene);
      stage.show();
   }
   public void starting(ActionEvent e){//Start of game asking for player info
      cinematic.getChildren().clear();
      deck = new Deck2();//Creates new deck
      deck.shuffle();//Shuffles deck
      player1 = new Player2(true);//Creates new human player
      for(int i = 0; i < 7; i++)
         deck.deal(player1.getHand());
      Button yes = new Button("YES");//Button for if p2 is human
      yes.setOnAction(this::createHuman);//Create human if yes is pressed
      Button no = new Button("NO");//Button for if p2 is CPU
      no.setOnAction(this::createCPU);//Create cpu if no is pressed
      Label label = new Label("Is player 2 human? ");
      label.setTextFill(Color.WHITE);
      cinematic.getChildren().addAll(label,yes,no);//Ask if p2 is CPU
      cinematic.setAlignment(Pos.CENTER);
      layout.setCenter(cinematic);
      
   }
   public void createHuman(ActionEvent e){
      player2 = new Player2(true);
      stageZero();
   }
   public void createCPU(ActionEvent e){
      player2 = new Player2(false);
      stageZero();
   }
   public void stageZero(){//Sets up screen
      for(int i = 0; i < 7; i++)//Deals 7 cards to p2
         deck.deal(player2.getHand());
      VBox p1 = player1.getGraphic(), p2 = player2.getGraphic();
      played.getChildren().clear();
      played.getChildren().add(p1);
      cinematic.getChildren().clear();
      cinematic.getChildren().add(hold.getGraphic());//Puts requested card at centre stage
      Label p1score = new Label(player1.getName()+": "+player1.getScore());
      Label p2score = new Label(player2.getName()+": "+player2.getScore());
      p1score.setPadding(new Insets(5,5,5,5));
      p2score.setPadding(new Insets(5,5,5,5));
      scoreBoard.getChildren().clear();
      scoreBoard.getChildren().addAll(p1score,p2score);//Score board of player scores
      layout.setTop(p2);//Places waiting player at top
      layout.setBottom(played);//Places current player at bottom
      layout.setRight(deck.getGraphic());//Places the deck on right
      layout.setLeft(scoreBoard);//Places the deck on left
      //Check for any pairs
      checkForPairs(player1);
      checkForPairs(player2);
      stage1();//Begin game
   }
   public void stage1(){//Start of turn
      //Determines playing and waiting players
      Player2 playing = null;
      Player2 queing = null;
      if(turn%2 == 0){
         playing = player1;
         queing = player2;
      }else{
         playing = player2;
         queing = player1;
      }
      if(playing.getHand().getSize() != 0){//If hand is not empty(game finished)
         if(playing.isHuman()){//If player is human
            if(queing.isHuman()){
               String password = JOptionPane.showInputDialog(null,playing.getName()+", enter your password.");//Request password
               while(!password.equals(playing.getPassword()))//Needed to proceed
                  password = JOptionPane.showInputDialog(null,"Incorrect password. "+playing.getName()+", enter your password.");
            }
            playing.getHand().showHand();
            for(int i = 0; i < playing.getHand().getSize(); i++){//Creates buttons to choose card to play
               Button temp = new Button("CHOOSE");
               temp.setMaxWidth(200);temp.setMinWidth(200);
               choice.add(temp);
               choice.get(i).setOnAction(this::choosing);//If button pressed choose corresponding card
               chooseCard.getChildren().add(temp);
            }
            played.getChildren().add(chooseCard);
         }else//If not human
         {compChoose(playing);}//CPU chooses
      }
      else endGame();//End of game
   }
   public void endGame(){//When the game is over, shows winner and asks if want to play again
      if(player1.getScore() > player2.getScore())
         JOptionPane.showMessageDialog(null,player1.getName()+" wins!");//P1 wins
      else
         JOptionPane.showMessageDialog(null,player2.getName()+" wins!");//P2 wins
      cinematic.getChildren().clear();
      turn = 0;//Reset game to 0
      Button newGame = new Button("Play Again?");//For if player wants another game
      newGame.setOnAction(this::starting);//Starts new game
      cinematic.getChildren().add(newGame);
   }
   public void compChoose(Player2 playing){//Computer choosing a card
      Random rn = new Random();
      //Randomly selects card
      int i = rn.nextInt(playing.getHand().getSize());
      Card3 temp = playing.selectCard(i);
      JOptionPane.showMessageDialog(null,player2.getName()+" requests "+temp.getFace());
      hold.add(temp);//Adds card to requested
      hold.showHand();
      cinematic.getChildren().add(contin);
      stages ++;
   }
   public void choosing(ActionEvent e){//Human choosing a card
      Player2 playing = null;
      Player2 queing = null;
      if(turn%2 == 0){
         playing = player1;
         queing = player2;
      }else{
         playing = player2;
         queing = player1;
      }
      for(int i = 0; i < playing.getHand().getSize(); i++){//For all buttons
         if(e.getSource() == choice.get(i))//Chooses card based on corresponding button
            hold.add(playing.selectCard(i));
            hold.showHand();//Displays requested card
      }
      choice.clear();
      chooseCard.getChildren().clear();
      cinematic.getChildren().add(contin);
      stages ++;
   }
   public void continued(ActionEvent e){//Continues events in order
      //Determines who's playing
      Player2 playing = null;
      Player2 queing = null;
      if(turn%2 == 0){
         playing = player1;
         queing = player2;
      }else{
         playing = player2;
         queing = player1;
      }
      
      if(stages == 3){//End of turn
         stages = 0;
         cinematic.getChildren().remove(contin);
         stage1();
      }
      if(stages == 2)//Went fishing
         endOfTurn(playing,queing);
      if(stages == 1)//Selected card
         goFish(playing,queing);
   }
   public void endOfTurn(Player2 playing, Player2 queing){//End of player's turn
      //Checks for pairs in players' hands
      checkForPairs(playing);
      if(playing.getHand().getCards().size() > 0);
         checkForPairs(playing);
      checkForPairs(queing);
      if(queing.getHand().getCards().size() > 0);
         checkForPairs(queing);
      //Sets up for next players turn
      playing.getHand().hideHand();
      layout.setTop(playing.getGraphic());
      played.getChildren().clear();
      played.getChildren().add(queing.getGraphic());
      stages++;
      turn++;
   }
   public void goFish(Player2 playing, Player2 queing){//Checks if other player has pair
      ArrayList<Card3> checked = queing.getHand().getCards();
      Card3 qued = hold.play(0);
      boolean hadPair = false;
      for(int i = 0; i < checked.size(); i++){//For all the other player's cards
         if(qued.equals(checked.get(i))){//If there is a pair
            JOptionPane.showMessageDialog(null,queing.getName()+" has a "+qued.getFace());//Other player has matching card
            //Player gets both cards
            playing.getHand().add(qued);
            playing.getHand().add(queing.selectCard(i));
            i = checked.size();//end loop
            hadPair =true;
         }
      }
      if(!hadPair){//If no pair
         JOptionPane.showMessageDialog(null,"GO FISH!");//Go fish
         //Take back card and card at top of deck
         playing.getHand().add(qued);
         deck.deal(playing.getHand());
      }
      if(playing.isHuman())
         playing.getHand().showHand();
      stages++;
   }
	public void checkForPairs(Player2 player)
	{//Check player's hand for pairs
		ArrayList<Card3> current = player.getHand().getCards();
      
      //Checks every card in hand for pair
      for(int i = 0; i < current.size(); i++){
         for(int j = 0; j < current.size(); j++){
            if(i != j && current.get(i).equals(current.get(j))){
               player.score();
               JOptionPane.showMessageDialog(null,player.getName()+" has a pair of "+current.get(i).getFace()+"s");
               player.selectCard(i);
               if(i>j)
                  player.selectCard(j);
               else
                  player.selectCard(j-1);
               j = current.size();
               i = 0;
            }
         }
      }
      //Updates ScoreBoard
      Label p1score = new Label(player1.getName()+": "+player1.getScore());
      Label p2score = new Label(player2.getName()+": "+player2.getScore());
      p1score.setPadding(new Insets(5,5,5,5));
      p2score.setPadding(new Insets(5,5,5,5));
      scoreBoard.getChildren().clear();
      scoreBoard.getChildren().addAll(p1score,p2score);//Score board of player scores
      if(current.size() == 0)//If hand becomes emptied
         refill(player);//refill hand
	}
   public void refill(Player2 player){//Refills empty hand
      for(int i = 0; i < 7; i++)//Deals 7 cards to the player
         deck.deal(player.getHand());
   }
   public static void main(String[] args) {
       Application.launch(args);
   }
}
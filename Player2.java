import java.util.*;
import javax.swing.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;

public class Player2{
   
   private Hand2 hand;//Player's hand
   private String name;//Player's name
   private String password;
   private boolean isHuman;
   private int score;//Player's score
   private VBox player;
   
   public Player2(boolean c){//Creates a new player
      hand = new Hand2();
      score = 0;
      isHuman = c;
      name = JOptionPane.showInputDialog(null,"Enter player name:");
      if(isHuman){
         password = JOptionPane.showInputDialog(null,"Enter player password:");
      }else{
         password = null;
      }
      player = new VBox();
      Label title = new Label(name);
      title.setTextFill(Color.WHITE);
      player.getChildren().add(title);
      player.getChildren().add(hand.getGraphic());
   }
   
   public String getName(){return name;}//Returns the player's name
   
   public String getPassword(){return password;}//Returns the player's password
   
   public boolean isHuman(){return isHuman;}//Returns whether or not the player is human
   
   public int getScore(){return score;}//Returns the player's score
   
   public Hand2 getHand(){return hand;}//Allows access to the player's hand
   
   public void printHand(){System.out.println(hand);}//Prints the players hand
   
   public void score(){score += 1;}//Increments score
   
   public Card3 selectCard(int input){//Asks the player to select a card from their hand
      return hand.play(input);
   }
   
   public VBox getGraphic(){
      return player;
   }
}
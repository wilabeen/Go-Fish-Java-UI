import java.util.*;
import javafx.scene.layout.*;
public class Hand2{
   
   private ArrayList<Card3> hand;//Cards in the hand
   private HBox handy;
   
   public Hand2(){//Creates a new hand
      hand = new ArrayList<Card3>(0);
      handy = new HBox();
   }
   
   public int getSize(){return hand.size();}//Returns the size of the hand
   
   public void add(Card3 card){//Adds an inputted card to the hand
      hand.add(card);
      handy.getChildren().add(hand.get(hand.size()-1).getBack());
   }
   
   
   
   public Card3 play(int index){//Plays a card the Player asks for (using position of card in hand)
      if(index >= 0 && index < hand.size()){
         Card3 a = hand.get(index);
         hand.remove(a);
         handy.getChildren().remove(index);
         return a;
      }else
         return null;
   }
   
   public HBox getGraphic(){return handy;}
   
   public void showHand(){
      handy.getChildren().clear();
      for(int i = 0; i < hand.size(); i++){
         handy.getChildren().add(hand.get(i).getFront());
      }
   }
   
   public void hideHand(){
      handy.getChildren().clear();
      for(int i = 0; i < hand.size(); i++){
         handy.getChildren().add(hand.get(i).getBack());
      }
   }
   
   public ArrayList<Card3> getCards(){return hand;}
   
   public String toString(){//Prints out the hand
      String out = "Cards contained in hand:\n";
      for(int i = 0; i < hand.size(); i++){out += hand.get(i)+"\n";}
      return out;
   }
}
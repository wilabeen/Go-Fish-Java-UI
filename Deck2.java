import java.util.*;
import javafx.scene.layout.*;

public class Deck2{
   
   private ArrayList<Card3> deck = new ArrayList<Card3>(52);//The 52 cards in the deck
   private HBox front;
   
   public Deck2(){//Creates a new deck
      for(int i = 1; i <= 4; i++){//For all the suits
         for(int j = 2; j <= 14; j++){//For all face values
            deck.add(new Card3(i,j));//Intialize every card in the deck
         }
      }
      front = new HBox(deck.get(0).getBack());
   }
   
   public int size(){return deck.size();}
   
   public void shuffle(){//Shuffles the deck
      Random rn = new Random();//Random to shuffle cards
      for (int i = deck.size()-1; i >= 0; i--){//For all the cards in the deck
         int index = rn.nextInt(deck.size());//Takes a random index in the deck
         Card3 a = deck.get(index);//Takes the card at the random index
         //Switches the random card with the current card
         deck.set(index,deck.get(i));
         deck.set(i,a);
      }
      front.getChildren().clear();
      front.getChildren().add(deck.get(0).getBack());
   }
   
   public void deal(Hand2 h){
      if(deck.size() > 0){//If not all cards have been dealt
         Card3 a = deck.get(0);
         deck.remove(0);
         h.add(a);
      }
      if(!front.getChildren().isEmpty())
      front.getChildren().clear();
      if(deck.size() > 0){
         front.getChildren().add(deck.get(0).getBack());
      }
   }
   
   
   public void dealAmount(Hand2 h, int amnt){//Deals a specific amount of cards to hand
      for(int i = 0; i > amnt; i++){
         if(deck.size() > 0){
            Card3 a = deck.get(0);
            deck.remove(0);
            h.add(a);
         }
      }
      front.getChildren().clear();
      if(deck.size() > 0){
         front.getChildren().add(deck.get(0).getBack());
      }
   }
   public HBox getGraphic(){return front;}
}
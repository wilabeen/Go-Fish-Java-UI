/*CSCI 1101-Assignment 3-Card
Represents a playin card
<Wil Deering> <B00738559> <March 9th, 2018>*/
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.geometry.*;
import javafx.scene.paint.*;

public class Card3{
   
   private String suit;//Card suit
   private String face;//Card face
   private int value;//Face value
   private boolean flipped;
   private GridPane front;
   private Rectangle back;
   
   public Card3(int i, int j){
      switch(i){//Switch to choose the suit based on first parameter
         case 1:
            suit = "Hearts";
            break;
         case 2:
            suit = "Diamonds";
            break;
         case 3:
            suit = "Spades";
            break;
         case 4:
            suit = "Clubs";
            break;
      } switch(j){//Switch to choose the face based on second parameter
         case 2:
            face = "Two";
            break;
         case 3:
            face = "Three";
            break;
         case 4:
            face = "Four";
            break;
         case 5:
            face = "Five";
            break;
         case 6:
            face = "Six";
            break;
         case 7:
            face = "Seven";
            break;
         case 8:
            face = "Eight";
            break;
         case 9:
            face = "Nine";
            break;
         case 10:
            face = "Ten";
            break;
         case 11:
            face = "Jack";
            break;
         case 12:
            face = "Queen";
            break;
         case 13:
            face = "King";
            break;
         case 14:
            face = "Ace";
            break;
      }
      value = j;
      flipped = true;
      front = new GridPane();
      Label suit1 = new Label(suit);suit1.setAlignment(Pos.TOP_LEFT);
      Label suit2 = new Label(suit);suit1.setAlignment(Pos.BOTTOM_RIGHT);
      Label faced = new Label(face);faced.setAlignment(Pos.CENTER);
      back = new Rectangle(150,150,Color.RED);
      front.add(suit1,0,0);
      front.add(faced,1,2);
      front.add(suit2,2,4);
      front.getColumnConstraints().add(new ColumnConstraints(75));
      front.getColumnConstraints().add(new ColumnConstraints(50));
      front.getColumnConstraints().add(new ColumnConstraints(75));
      front.getRowConstraints().add(new RowConstraints(45));
      front.getRowConstraints().add(new RowConstraints(45));
      front.getRowConstraints().add(new RowConstraints(45));
      front.getRowConstraints().add(new RowConstraints(45));
      front.getRowConstraints().add(new RowConstraints(45));
      front.setStyle("-fx-border-color: black");
      front.setStyle("-fx-background-color: white");
      front.setPadding(new Insets(0,0,0,2));
      back.setStyle("-fx-border-color: black");
      front.setMaxHeight(225);front.setMinHeight(225);front.setPrefHeight(225);
   }
   
   public String getFace(){return face;}//Returns the card's face
   
   public int getValue(){return value;}//Returns the face value
   
   public String getSuit(){return suit;}//Returns the card's suit
   
   public String toString(){//Represents the card as a string
      if(flipped)
         return face + " of " + suit;
      return "Hidden Card";
   }
   
   public void flip(){//Flips a card over if it needs to be hidden or shown in a hand
      if(flipped)
         flipped = false;
      else
         flipped = true;
   }
   
   public boolean equals(Card3 e){//Checks to see if the cards have the same value
      if(value == e.value)
         return true;
      else
         return false;
   }
   
   public boolean equalsSuit(Card3 e){//Checks to see if the cards have the same suits
      if(suit.equals(e.suit))
         return true;
      else
         return false;
   }
   
   public boolean greaterThan(Card3 e){//If the card has a higher value than the inputted Card
      if(value > e.value)
         return true;
      else
         return false;
   }
   public GridPane getFront(){return front;}
   public Rectangle getBack(){return back;}
}
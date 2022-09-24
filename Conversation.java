/*
  Author: Janna Gilleman
*/
import java.util.*;

class Conversation {

  public static void main(String[] arguments) {
    
    //Setting up stuff I need for later
    Scanner ask = new Scanner(System.in);                                                   //System.in is a standard input stream and I'm naming my scanner "ask"
    String[] response = {" Say more.", " Talk to me about that.", " Wow", " Why?"};       //makes a new array filled with responses to be used later
    int responseIndex = 0;
    
    //Set up a map (basically dict2.0) where both the keys and the values are strings
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("your", "my");
    map.put("my", "your");
    map.put("I", "you");
    map.put("you", "I");
    map.put("am", "are");
    map.put("me", "you");
    map.put("mine", "yours");
    map.put("yours", "mine");
    map.put(".", "?");

    //My target/replacement bank
    /* NEED TO FIGURE OUT HOW TO DO   you -> I    &   your -> my */
    //String[] target = {"I ", " am", " me ", " my ", "Mine ", ".", " mine ", "I'm"};
    //String[] replacement = {"you ", " are ", " you ", " your ", "Your ",  "?", " yours", "You're"};

    //Intial Chatbot Intro. Ask for number of convo rounds.
    System.out.println("Chatbot: Heya! How long would you like to talk for?\n"
      + "Enter number of rounds:");
    int numRounds = ask.nextInt();          //assigns input to a variable called numRounds which sets the max time the for loop will repeat
    ask.nextLine();                         //clears input buffer
    System.out.println("\nChatbot: Awesome. Well, what's on your mind?");

    //Main part of the program.
    for (int count=0; count < numRounds; count++) {

      //Ask for a sentence from the user
      String str = ask.nextLine();
      String[] words = str.split(" ");

      //Iterate through replacement array. Replace all targets with their replacements.
      for (int x = 0; x < words.length; x++) {
        //str = str.replace(target[x], replacement[x]);
        if(map.containsKey(words[x])) {
          str = str.replace(words[x], (String)map.get(words[x]));       //(string) is saying "treat the value u get from map as a string"
        }

      }


      //Choose a different reponse from my reponse list for every new round.
      if (count>3) { 
        responseIndex = count/response.length;        //this is doing integer division. Note that this number will exceed 3 for convo lengths above 10.
      } else {
        responseIndex = count;
      }

      //Print the crafted response
      System.out.println("Chatbot: " + str + response[responseIndex]);
    }

    //Exit conversation.
    System.out.println("Chatbot: Cool cool cool- Thanks for talkin, my guy; I'm gonna head out.\n");
    ask.close();

  }
}

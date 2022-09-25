/*
  Desc: Conversation.java is basically the main program. It creates and manages the entire chatbot and all its features.
  Author: Janna Gilleman
  Coding Buddies: Ryan (K) Emerson and Chelsea
  Date: 9/24/22
*/
import java.util.*;

class Conversation {

  public static void main(String[] arguments) {
    
    //Setting up stuff I need for later
    Scanner ask = new Scanner(System.in);                                                   //System.in is a standard input stream and I'm naming my scanner "ask"
    String[] response = {" Say more.", " Talk to me about that.", " Wow", " Why?"};       //makes a new array filled with responses to be used later
    int responseIndex = 0;
    //String botResponse = "";
    ArrayList<String> convoLog = new ArrayList<String>();

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

    //Intial Chatbot Intro. Ask for number of convo rounds.
    System.out.println("\n<><><><> WELCOME TO CHATBOT TOBOR <><><><>\n\n" + "Tobor: Heya! How long would you like to talk for?\n"
      + "Enter number of rounds:");
    int numRounds = ask.nextInt();          //assigns input to a variable called numRounds which sets the max time the for loop will repeat
    ask.nextLine();                         //clears input buffer
    System.out.println("\nTobor: Awesome. Well, what's on your mind?");

    //Main part of the program.
    for (int count=0; count < numRounds; count++) {

      //Ask for a sentence from the user
      String str = ask.nextLine();
      String originalInput = str;
      String[] words = str.split(" ");

      //Iterate through all the words in the input string and make all necessary edits. Words.length is how many words are in the sentence.
      for (int x = 0; x < words.length; x++) {
        //If the xth word in our sentence happens to be the same as a key in our map, replace it with the corresponding value.
        if(map.containsKey(words[x])) {
          str = str.replace(words[x], (String)map.get(words[x]));       //(String) is saying "treat the value u get from map as a string"
        }
      }

      //Replace punctuation with a ?
      str = str.replace(".", "?");
      str = str.replace("!", "?");

      //Capitalize: Cut off first letter, capitalize it, and concat it back on.
      String firstLetter = str.substring(0,1);
      String restOf = str.substring(1);
      firstLetter = firstLetter.toUpperCase();
      str = firstLetter + restOf;

      //Choose a different reponse from my reponse list for every new round.
      if (count>3) { 
        responseIndex = count/response.length;        //this is doing integer division. Note that this number will exceed 3 for convo lengths above 10.
      } else {
        responseIndex = count;
      }

      //Print the crafted response
      String botResponse = "Tobor: " + str + response[responseIndex];
      System.out.println(botResponse);

      //Add our edited input sentence to the convoLog
      convoLog.add(originalInput + "\n");
      convoLog.add(botResponse + "\n");
    }

    //Exit conversation. Print convoLog. Close Scanner.
    System.out.println("Tobor: Cool cool cool- Thanks for talkin, my guy; I'm gonna head out.\n" 
      + "By the way, here's a record of everything we said:\n\nCHATBOT LOG:");
    for (int y = 0; y <= (numRounds*2)-1; y++) {
      System.out.print(convoLog.get(y));
    }
    System.out.println("\n<><>Goodbye!<><>");
    ask.close();

  }
}

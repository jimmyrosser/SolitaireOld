import scala.io.StdIn._
import scala.collection.mutable.Stack

object main {
  //Stacks for covered cards
  var coveredStack2 = Stack[Card]()
  var coveredStack3 = Stack[Card]()
  var coveredStack4 = Stack[Card]()
  var coveredStack5 = Stack[Card]()
  var coveredStack6 = Stack[Card]()
  var coveredStack7 = Stack[Card]()
  //List of all coverd card stacks
  var coveredStacks: List[Stack[Card]] = List(coveredStack2, coveredStack3, coveredStack4, coveredStack5, coveredStack6, coveredStack7)

  //Stacks for uncovered cards
  var uncoveredStack1 = Stack[Card]()
  var uncoveredStack2 = Stack[Card]()
  var uncoveredStack3 = Stack[Card]()
  var uncoveredStack4 = Stack[Card]()
  var uncoveredStack5 = Stack[Card]()
  var uncoveredStack6 = Stack[Card]()
  var uncoveredStack7 = Stack[Card]()
  //List of all uncovered card stacks
  var uncoveredStacks: List[Stack[Card]] = List(uncoveredStack1, uncoveredStack2, uncoveredStack3, uncoveredStack4, uncoveredStack5, uncoveredStack6, uncoveredStack7)

  //Stacks for ace cards
  var ace1Stack = Stack[Card]()
  var ace2Stack = Stack[Card]()
  var ace3Stack = Stack[Card]()
  var ace4Stack = Stack[Card]()
  //List of all ace card stacks
  var aceStacks: List[Stack[Card]] = List(ace1Stack, ace2Stack, ace3Stack, ace4Stack)

  //All stacks where playable cards could be/moves could be made
  var allStacks = List[Stack[Card]]()

  var discardStack = Stack[Card]()
  var drawStack = Stack[Card]()
  var cardsToFlip = 1

  //Generate and shuffle the deck, then push it all to a stack
  val deck = Deck.shuffleDeck(Deck.shuffleDeck(Deck.generateDeck))
  var deckStack: Stack[Card] = Stack[Card]()
  deckStack.pushAll(deck)
  var allStacksAndDiscardStack:List[Stack[Card]] = List(uncoveredStack1, uncoveredStack2, uncoveredStack3, uncoveredStack4, uncoveredStack5, uncoveredStack6, uncoveredStack7, 
      ace1Stack, ace2Stack, ace3Stack, ace4Stack, discardStack)

  /*
  //////////////////////////////////////////////
  -------------------- MAIN --------------------
  //////////////////////////////////////////////

  This function deals out the cards to the proper 
  stacks, prints the board, and starts the game

  */
  def main(args: Array[String]): Unit = {
    dealGame()
    updateGame()
    playGame()
  }

  /*
  /////////////////////////////////////////////////////////////
  -------------------- GET TEXT FROM VALUE --------------------
  /////////////////////////////////////////////////////////////

  This function takes an integer card value (1-13) and converts that to
  a string to be used in a sentance

  */
  def getTextFromValue(value: Int): String = {
    if(value == 1) {
      "Ace"
    }
    else if (value == 2) {
      "Two"
    }
    else if (value == 3) {
      "Three"
    }
    else if (value == 4) {
      "Four"
    }
    else if (value == 5) {
      "Five"
    }
    else if (value == 6) {
      "Six"
    }
    else if (value == 7) {
      "Seven"
    }
    else if (value == 8) {
      "Eight"
    }
    else if (value == 9) {
      "Nine"
    }
    else if (value == 10) {
      "Ten"
    }
    else if (value == 11) {
      "Jack"
    }
    else if (value == 12) {
      "Queen"
    }
    else if (value == 13) {
      "King"
    }
    else {
      "ERROR"
    }
  }

  /*
  ////////////////////////////////////////////////////////////////
  -------------------- CONVERT VALUE FOR CARD --------------------
  ////////////////////////////////////////////////////////////////

  This function takes an integer card value (1-13) and converts it to the
  corresponding card value (A, J, Q, K)

  */
  def convertValueForCard(value: Int) = {
    if(value == 1) {
      "A"
    }
    else if (value == 2) {
      "2"
    }
    else if (value == 3) {
      "3"
    }
    else if (value == 4) {
      "4"
    }
    else if (value == 5) {
      "5"
    }
    else if (value == 6) {
      "6"
    }
    else if (value == 7) {
      "7"
    }
    else if (value == 8) {
      "8"
    }
    else if (value == 9) {
      "9"
    }
    else if (value == 10) {
      "10"
    }
    else if (value == 11) {
      "J"
    }
    else if (value == 12) {
      "Q"
    }
    else if (value == 13) {
      "K"
    }
    else {
      "ERROR"
    }
  }

  /*
  /////////////////////////////////////////////////////////////////
  -------------------- CONVERT VALUE FROM CARD --------------------
  /////////////////////////////////////////////////////////////////

  This function takes a string card value (A, 2, J, Q, K, etc) and 
  converts in to the corresponding numerical value (1-13)

  */
  def convertValueFromCard(value: String): Int = {
    if(value.toLowerCase == "a") {
      1
    }
    else if(value.toLowerCase == "1") {
      1
    }
    else if (value.toLowerCase == "2") {
      2
    }
    else if (value.toLowerCase== "3") {
      3
    }
    else if (value.toLowerCase == "4") {
      4
    }
    else if (value.toLowerCase == "5") {
      5
    }
    else if (value.toLowerCase == "6") {
      6
    }
    else if (value.toLowerCase == "7") {
      7
    }
    else if (value.toLowerCase == "8") {
      8
    }
    else if (value.toLowerCase == "9") {
      9
    }
    else if (value.toLowerCase == "10") {
      10
    }
    else if (value.toLowerCase == "j") {
      11
    }
    else if (value.toLowerCase == "q") {
      12
    }
    else if (value.toLowerCase == "k") {
      13
    }
    else {
      -1
    }
  }

  /*
  ///////////////////////////////////////////////////////////////
  -------------------- CONVERT SUIT FOR CARD --------------------
  ///////////////////////////////////////////////////////////////

  This function takes a single character string card suit (s, h, d, or c) and 
  converts in to the corresponding full suit name (spades, hearts, diamonds, or clubs)
  
  */
  def convertSuitForCard(suit: String): Suit.Value = {
    if(suit.toLowerCase == "s") {
      Suit.Spades
    }
    else if(suit.toLowerCase == "h") {
      Suit.Hearts
    }
    else if(suit.toLowerCase == "d") {
      Suit.Diamonds
    }
    else if(suit.toLowerCase == "c") {
      Suit.Clubs
    }
    else {
      Suit.None
    }
  }

  /*
  ///////////////////////////////////////////////////////////////
  -------------------- CONVERT CARD TO ASCII --------------------
  ///////////////////////////////////////////////////////////////

  This function takes a card (in value and suit form) are returns
  an ASCII representation of that card

  */
  def convertCardToASCII(card: Card):String = {
    val value = card.value
    val suit = card.suit
    val shortSuit = suit.toString.head
    if(suit == Suit.Hearts) {
      if(value != 10) {
        return(
        "-------------" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "        |" + "," + 
        "|           |" + "," +
        "|           |" + "," +
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|        " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else if(value == 10) {
        return(
        "-------------" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "       |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|       " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else {
          return("ERROR IN convertCardtoASCII HEARTS ELSE")
      }
    }
    else if(suit == Suit.Diamonds) {
      if(value != 10) {
        return(
        "-------------" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "        |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|  " + shortSuit + "  " + shortSuit + "     |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "     |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|        " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else if(value == 10) {
        return(
        "-------------" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "       |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|  " + shortSuit + "  " + shortSuit + "     |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "     |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|       " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else {
          return("ERROR IN convertCardtoASCII DIAMONDS ELSE")
      }
    }
    else if(suit == Suit.Spades) {
      if (value != 10) {
        return(
        "-------------" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "        |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|        " + shortSuit + "  |" + "," + 
        "|        " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|        " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else if(value == 10) {
        return(
        "-------------" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "       |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|        " + shortSuit + "  |" + "," + 
        "|        " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|       " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else {
          return("ERROR IN convertCardtoASCII SPADES ELSE")
      }
    }
    else if(suit == Suit.Clubs) {
      if(value != 10) {
        return(
        "-------------" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "        |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|        " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else if(value == 10) {
        return(
        "-------------" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "       |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        "|       " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else {
          return("ERROR IN convertCardtoASCII CLUBS ELSE")
      }
    }
    else if(suit == Suit.None) {
      return(
          "-------------" + "," + 
          "|           |" + "," + 
          "|           |" + "," + 
          "|           |" + "," + 
          "|           |" + "," + 
          "|           |" + "," +
          "|           |" + "," +
          "|           |" + "," + 
          "|           |" + "," + 
          "|           |" + "," + 
          "|           |" + "," + 
          "|           |" + "," + 
          "|           |" + "," + 
          "|           |" + "," + 
          "-------------")
    }
    else {
      return("ERROR IN convertCardtoASCII LAST ELSE")
    }
  }

  def convertCardToASCIIWithSize(size: Int): String = {
    if(size > 9) {
      val stringSize = size.toString()
        "-------------" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        //
        "|           |" + "," +
        "|    " + stringSize(0) + " " + stringSize(1) + "    |" + "," + 
        "|           |" + "," + 
        //
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "-------------"
    }
    else {
        "-------------" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," +
        //
        "|           |" + "," +
        "|     " + size + "     |" + "," + 
        "|           |" + "," + 
        //
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "|           |" + "," + 
        "-------------"
    }
  }

  /*
  ////////////////////////////////////////////////////////////////////
  -------------------- CONVERT CARD PILE TO ASCII --------------------
  ////////////////////////////////////////////////////////////////////

  This function converts a whole stack of cards into a single card to be printed,
  allowing the player to see all cards, even cards on top of other cards 
  */
  def convertCardPileToASCII(cardStack: Stack[Card]): String = {
    //reverse the card stack to print cards in correct orders
    val reversedCardStack = cardStack.reverse
    //convert all cards in stack to printable versions
    var cardList = List[List[String]]()
    for(card <- reversedCardStack) {
      cardList = cardList :+ convertCardToASCII(card).split(",").toList
    }
    //reformat that list so they print horizontally
    var reformattedCardList = List[String]()
    reformattedCardList = reformatCardList(cardList)
    //set top of card which will never change
    var cardToPrint = "-------------" + ","
    //loop through all the lines of the cards and only select the ones that have the name of the card
    var index = 0
    var cardLengthIndex = 0
    for(c <- reformattedCardList) {
      if(index > cardList.size && index < cardList.size*2+1 && cardLengthIndex < 14) {
        cardToPrint = cardToPrint + (c.trim + ",")
        cardLengthIndex += 1
        index += 1
      }
      else {
        index += 1
      }
    }
    //add blank spots for all other spaces not taken up by stacked cards
    while(cardLengthIndex < 13) {
      cardToPrint = cardToPrint + "|           |" + ","
      cardLengthIndex += 1
    }
    //add bottom line
    cardToPrint = cardToPrint + ("-------------" + ",")
    //return
    cardToPrint
    
  }

  /*
  ////////////////////////////////////////////////////////////////////
  -------------------- PRINT SINGLE CARD OR STACK --------------------
  ////////////////////////////////////////////////////////////////////

  This function takes a stack of cards and deturmines if the top card should be printed, 
  or if the whole stack should be printed
  
  */
  def printSingleCardOrStack(stack: Stack[Card]): String = {
    if(stack.length > 1) {
      convertCardPileToASCII(stack)
    }
    else if(stack.length == 1) {
      convertCardToASCII(stack.top)
    }
    else {
      "ERROR IN PRINT SINGLE CARD OR STACK"
    }
  }

  /*
  ////////////////////////////////////////////////////
  -------------------- PRINT CARD --------------------
  ////////////////////////////////////////////////////

  This function takes a list of cards (represented as strings) and prints them
  
  */
  def printCard(card: List[String]) = {
    for(i <- card) {
      print(i)
    }
  }

  /*
  /////////////////////////////////////////////////////////
  -------------------- REFORMAT CARD LIST --------------------
  /////////////////////////////////////////////////////////

  This function takes a list of cards or turns it into a list of strings
  so that the cards can be printed horizontally

  */
  def reformatCardList(cards: List[List[String]]):List[String] = {
    var returnLine = List[String]()
    var rowCounter = 0
    //only make rows that are less than cardHeight + 1
    while(rowCounter < 15) {
      cards.foreach(card => returnLine = returnLine :+ (card(rowCounter) + "    "))
      returnLine = returnLine :+ "\n"
      rowCounter += 1
    }
    returnLine
  }

  /*
  //////////////////////////////////////////////////////////
  -------------------- PRINT EMPTY CARD --------------------
  //////////////////////////////////////////////////////////

  This function prints a placeholder card with no value to it
  Used to represent where cards can go (ace spots and discard spot)

  */
  def printEmptyCard():String = {
    "-------------" + "," + 
    "|           |" + "," + 
    "|           |" + "," + 
    "|           |" + "," + 
    "|           |" + "," +
    "|           |" + "," +
    "|           |" + "," + 
    "|           |" + "," + 
    "|           |" + "," + 
    "|           |" + "," + 
    "|           |" + "," + 
    "|           |" + "," + 
    "|           |" + "," + 
    "|           |" + "," + 
    "-------------"
  }

  /*
  //////////////////////////////////////////////////////////
  -------------------- PRINT BLANK CARD --------------------
  //////////////////////////////////////////////////////////

  This function creates a blank space the size of a card
  Used for spacing out elements

  */
  def printBlankCard():String = {
    "             " + "," + 
    "             " + "," + 
    "             " + "," + 
    "             " + "," + 
    "             " + "," +
    "             " + "," +
    "             " + "," + 
    "             " + "," + 
    "             " + "," + 
    "             " + "," + 
    "             " + "," + 
    "             " + "," + 
    "             " + "," + 
    "             " + "," + 
    "             "
  }

  /*
  /////////////////////////////////////////////////////////////////////
  -------------------- CONVERT STACK SIZE TO ASCII --------------------
  /////////////////////////////////////////////////////////////////////

  This function takes a size of a stacdk and converts it to an ASCII representation 
  to be printed above the cards that have cards underneath them

  */
  def convertStackSizeToASCII(size: Int): String = {
    if (size == 0) {
      "             "
    }
    else if(size == 1) {
      "*            "
    }
    else if(size == 2) {
      "**           "
    }
    else if(size == 3) {
      "***          "
    }
    else if(size == 4) {
      "****         "
    }
    else if(size == 5) {
      "*****        "
    }
    else if(size == 6) {
      "******       "
    }
    else if(size == 7) {
      "*******      "
    }
    else if(size == 8) {
      "********     "
    }
    else if(size == 9) {
      "*********    "
    }
    else if(size == 10) {
      "**********   "
    }
    else if(size == 11) {
      "***********  "
    }
    else if(size == 12) {
      "************ "
    }
    else if(size == 13) {
      "*************"
    }
    else {
      "ERROR"
    }
  }

  /*
  ////////////////////////////////////////////////////
  -------------------- PRINT GAME --------------------
  ////////////////////////////////////////////////////

  This function prints the current state of the game

  */
  def printGame() = {

    //Makes a list of the ace card spots above the main section of the game. Adds the top card to the list and adds an empty card if there are no cards in the stack
    var aceCardList = List[List[String]]()
    aceCardList = aceCardList :+ convertCardToASCII(ace1Stack.top).split(",").toList
    aceCardList = aceCardList :+ convertCardToASCII(ace2Stack.top).split(",").toList
    aceCardList = aceCardList :+ convertCardToASCII(ace3Stack.top).split(",").toList
    aceCardList = aceCardList :+ convertCardToASCII(ace4Stack.top).split(",").toList
    //Call reformatCardList to arrange the cards horizontally
    var aceCards = reformatCardList(aceCardList)
    //Print the cards
    printCard(aceCards)

    //Space seperation between ace section and main section
    println()
    println()
    println()

    //Makes a list of the solitaire spots in the main section of the game. Adds the top card to the list and adds an empty card if there are no cards in the stack
    var cardList = List[List[String]]()
    if(uncoveredStack1.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ printSingleCardOrStack(uncoveredStack1).split(",").toList
    }
    if(uncoveredStack2.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      println("             " + "    " + convertStackSizeToASCII(coveredStack2.size) 
            + "    " + convertStackSizeToASCII(coveredStack3.size)
            + "    " + convertStackSizeToASCII(coveredStack4.size)
            + "    " + convertStackSizeToASCII(coveredStack5.size)
            + "    " + convertStackSizeToASCII(coveredStack6.size)
            + "    " + convertStackSizeToASCII(coveredStack7.size))
      cardList = cardList :+ printSingleCardOrStack(uncoveredStack2).split(",").toList
    }
    if(uncoveredStack3.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ printSingleCardOrStack(uncoveredStack3).split(",").toList
    }
    if(uncoveredStack4.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ printSingleCardOrStack(uncoveredStack4).split(",").toList
    }
    if(uncoveredStack5.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ printSingleCardOrStack(uncoveredStack5).split(",").toList
    }
    if(uncoveredStack6.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ printSingleCardOrStack(uncoveredStack6).split(",").toList
    }
    if(uncoveredStack7.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ printSingleCardOrStack(uncoveredStack7).split(",").toList
    }
    //Call reformatCardList to arrange the cards horizontally
    var cards = reformatCardList(cardList)
    //Print the cards
    for(i <- cards) {
      print(i)
    }

    //Makes space between the main section and the draw section
    println()
    println()
    println()

    //Makes a list of the discard deck and draw deck below the main section of the game with blank cards for spacing. 
    //Adds the top card to the list and adds an empty card if there are no cards in the stack
    var drawCardsList = List[List[String]]()
    drawCardsList = 
      drawCardsList :+ printBlankCard().split(",").toList 
        :+ printBlankCard().split(",").toList 
        :+ printBlankCard().split(",").toList 
        :+ printBlankCard().split(",").toList 
        :+ printBlankCard().split(",").toList
    if(discardStack.isEmpty) {
      drawCardsList = drawCardsList :+ printEmptyCard().split(",").toList
    }
    else {
      drawCardsList = drawCardsList :+ convertCardToASCII(discardStack.top).split(",").toList
    }
    drawCardsList = drawCardsList :+ convertCardToASCIIWithSize(drawStack.size).split(",").toList
    //call reformatCardList to arrange the cards horizontally
    var drawCards = reformatCardList(drawCardsList)
    //Print the cards
    if(deckStack.size < 14) {
      print("                                                                                                      ")
      println(convertStackSizeToASCII(deckStack.size))
    }
    printCard(drawCards)
  }

  /*
  ///////////////////////////////////////////////////
  -------------------- DEAL GAME --------------------
  ///////////////////////////////////////////////////

  This function deals cards out like one would in solitaire

  */
  def dealGame() = {
      //add 1 card to first uncovered stack, add 6 cards to next 6 coverd stacks
      uncoveredStack1.push(deckStack.pop())
      for(i <- 0 to 5) {
        coveredStacks(i).push(deckStack.pop())
      }

      //add 1 card to second uncoverd stack, add 5 cards to next 5 covered stacks
      uncoveredStack2.push(deckStack.pop())
      for(i <- 1 to 5) {
        coveredStacks(i).push(deckStack.pop())
      }

      //add 1 card to third uncoverd stack, add 4 cards to next 4 covered stacks
      uncoveredStack3.push(deckStack.pop())
      for(i <- 2 to 5) {
        coveredStacks(i).push(deckStack.pop())
      }

      //add 1 card to fourth uncoverd stack, add 3 cards to next 3 covered stacks
      uncoveredStack4.push(deckStack.pop())
      for(i <- 3 to 5) {
        coveredStacks(i).push(deckStack.pop())
      }
      //add 1 card to fifth uncoverd stack, add 2 cards to next 2 covered stacks
      uncoveredStack5.push(deckStack.pop())
      for(i <- 4 to 5) {
        coveredStacks(i).push(deckStack.pop())
      }
      //add 1 card to sixth uncoverd stack, add 1 cards to next 1 covered stacks
      uncoveredStack6.push(deckStack.pop())
      coveredStack7.push(deckStack.pop())
      //add 1 card to seventh uncoverd stack, add rest of cards to draw stack
      uncoveredStack7.push(deckStack.pop())
      
      //pushes the rest of the deck to the draw pile
      drawStack.pushAll(deckStack)

      //update lists of stacks to reflect the delt cards
      rebuildStacks()
  }

  /*
  ////////////////////////////////////////////////////////
  -------------------- REBUILD STACKS --------------------
  ////////////////////////////////////////////////////////

  This function rebuilds the lists of stacks after stacks have been updated

  */
  def rebuildStacks(): Unit = {
    uncoveredStacks = List(uncoveredStack1, uncoveredStack2, uncoveredStack3, uncoveredStack4, uncoveredStack5, uncoveredStack6, uncoveredStack7)
    coveredStacks = List(coveredStack2, coveredStack3, coveredStack4, coveredStack5, coveredStack6, coveredStack7)
    aceStacks =  List(ace1Stack, ace2Stack, ace3Stack, ace4Stack)
    allStacks = uncoveredStacks ++ aceStacks
    allStacksAndDiscardStack = List(uncoveredStack1, uncoveredStack2, uncoveredStack3, uncoveredStack4, uncoveredStack5, uncoveredStack6, uncoveredStack7, 
      ace1Stack, ace2Stack, ace3Stack, ace4Stack, discardStack)
  }

  /*
  ///////////////////////////////////////////////////
  -------------------- PLAY GAME --------------------
  ///////////////////////////////////////////////////

  This function begins the game by asking the user their name, 
  setting game options, and takes user input

  */
  def playGame() = {
    //var to stop program when user wants to
    var running = true

    //prompt user for name
    println("What is your name?")

    //read in name
    var name = readLine()
    println(s"Hello, $name! \nWelcome to Solitaire!")
    //prompt for play style
    println("Would you like to flip 1 card at a time or three cards at a time? (1 or 3)")
    var flipInput = readLine()
    println("Commands:")
    println("draw -             draws as card from the draw deck and places it face up in the discard deck to be used")
    println("move __ to __ -    allows you to move a card (KD, AS, 6C, JH, etc) to a specific pile (A1, S4, A3, S7, etc.) \n \t Example: \"move AS to A3\" moves the ace of spades (if it is in play) to the third ace pile. \n \t Example: \"move KD to S4\" moves the king of diamonds (if it is in play) to the fourth solitaire pile.")
    println("moveall __ to __ - allows you to move a card (KD, AS, 6C, JH, etc) and any subsequent cards in the pile to a specific pile (A1, S4, A3, S7, etc.) \n \t Example: \"move 10D to S4\" moves the 10 of diamonds (if it is in play) and any cards placed on top of the 10 of diamonds to the fourth solitaire pile. \n")
    println("q -                quit the game")
    
    //set play style
    if(flipInput == "3") {
      cardsToFlip = 3
      println("Flipping 3 cards at a time.")
    }
    else if(flipInput == "1") {
      cardsToFlip = 1
      println("Flipping 1 card at a time.")
    }
    else if(flipInput != "1") {
      println("Invalid input. Flipping 1 card at a time.")
    }
    //handle input during game
    while(running) {
      var input = readLine()
      handleInput(input)
      //exits the game
      if(input.toLowerCase == "q") {
        println(s"Goodbye $name!")
        running = false
      }
      if(ace1Stack.size == 14 && ace2Stack.size == 14 && ace3Stack.size == 14 && ace4Stack.size == 14) {
        println("Congratulations! You won Solitaire!")
        running = false
      }
    }
  }

  /*
  /////////////////////////////////////////////////////
  -------------------- UPDATE GAME --------------------
  /////////////////////////////////////////////////////

  This function updates the game by updating the stacks, 
  rebuilding the lists of stacks, and reprinting the board

  */
  def updateGame(): Unit = {
    fixStacksAfterMove()
    rebuildStacks()
    printGame()
  }

  /*
  ///////////////////////////////////////////////////////////////
  -------------------- FIX STACKS AFTER MOVE --------------------
  ///////////////////////////////////////////////////////////////

  This function checks if a stack is empty and pushes a blank card if it is to prevent errors,
  it also checks if there are covered cards that need to be flipped; 
  if there are, they get popped and pushed to the uncovered stack

  */
  def fixStacksAfterMove() = {
    var card: Card = null
    if(uncoveredStack1.isEmpty) {
      uncoveredStack1.push(Card(-1, Suit.None))
    }

    if(uncoveredStack2.isEmpty && coveredStack2.isEmpty) {
      uncoveredStack2.push(Card(-1, Suit.None))
    }
    else if(uncoveredStack2.isEmpty && !(coveredStack2.isEmpty)) {
      card = coveredStack2.pop()
      uncoveredStack2.push(card)
    }

    if(uncoveredStack3.isEmpty && coveredStack3.isEmpty) {
      uncoveredStack3.push(Card(-1, Suit.None))
    }
    else if(uncoveredStack3.isEmpty && !(coveredStack3.isEmpty)) {
      card = coveredStack3.pop()
      uncoveredStack3.push(card)
    }

    if(uncoveredStack4.isEmpty && coveredStack4.isEmpty) {
      uncoveredStack4.push(Card(-1, Suit.None))
    }
    else if(uncoveredStack4.isEmpty && !(coveredStack4.isEmpty)) {
      card = coveredStack4.pop()
      uncoveredStack4.push(card)
    }

    if(uncoveredStack5.isEmpty && coveredStack5.isEmpty) {
      uncoveredStack5.push(Card(-1, Suit.None))
    }
    else if(uncoveredStack5.isEmpty && !(coveredStack5.isEmpty)) {
      card = coveredStack5.pop()
      uncoveredStack5.push(card)
    }

    if(uncoveredStack6.isEmpty && coveredStack6.isEmpty) {
      uncoveredStack6.push(Card(-1, Suit.None))
    }
    else if(uncoveredStack6.isEmpty && !(coveredStack6.isEmpty)) {
      card = coveredStack6.pop()
      uncoveredStack6.push(card)
    }

    if(uncoveredStack7.isEmpty && coveredStack7.isEmpty) {
      uncoveredStack7.push(Card(-1, Suit.None))
    }
    else if(uncoveredStack7.isEmpty && !(coveredStack7.isEmpty)) {
      card = coveredStack7.pop()
      uncoveredStack7.push(card)
    }

    if(ace1Stack.isEmpty) {
      ace1Stack.push(Card(-1, Suit.None))
    }
    if(ace2Stack.isEmpty) {
      ace2Stack.push(Card(-1, Suit.None))
    }
    if(ace3Stack.isEmpty) {
      ace3Stack.push(Card(-1, Suit.None))
    }
    if(ace4Stack.isEmpty) {
      ace4Stack.push(Card(-1, Suit.None))
    }
  }

  /*
  //////////////////////////////////////////////////////////
  -------------------- FIND CARD ON TOP --------------------
  //////////////////////////////////////////////////////////

  This function locates a card using its value and suit and returns 
  a (boolean, int) tuple of if the card was found, and what stack it is 
  located in in allStacksAndDiscardStack

  */
  def findCardOnTop(value: Int, suit: String): (Boolean, Int) = {
    //instantiate variables for counting and returning
    var foundAndNum = (false, -1)
    var counter = 0
    //go through all 13 stacks
    while(counter < 12) {
      if(!allStacksAndDiscardStack(counter).isEmpty) {
        //set current top card
        val current = allStacksAndDiscardStack(counter).top
        //if the card matches the values apssed in, set tuple to reflect that and kills the loop
        if(current.value == value && current.suit == convertSuitForCard(suit)) {
          foundAndNum = (true, counter)
          counter = 12
        }
        //Go to next stack
        else {
          counter += 1
        }
      }
      else {
        counter += 1
      }
    }
    //return tuple
    foundAndNum
  }

  /*
  ////////////////////////////////////////////////////////////////
  -------------------- FIND CARD IN UNCOVERED --------------------
  ////////////////////////////////////////////////////////////////

  This function takes card in value, suit format and finds it in 
  all the uncovered stacks and returns a (boolean, int) tuple of 
  if the card was found, and what stack it is located in in 
  allStacksAndDiscardStack
  
  UNUSED

  */
  def findCardInUncovered(value: Int, suit: String): (Boolean, Int) = {
    //instantiate variables for counting and returning
    var foundAndNum = (false, -1)
    var counter = 0
    //go through all 13 stacks
    while(counter < 12) {
      if(!uncoveredStacks(counter).isEmpty) {
        //set current top card
        val current = uncoveredStacks(counter).top
        //if the card matches the values apssed in, set tuple to reflect that and kills the loop
        if(current.value == value && current.suit == convertSuitForCard(suit)) {
          foundAndNum = (true, counter)
          counter = 12
        }
        //Go to next stack
        else {
          counter += 1
        }
      }
      else {
        counter += 1
      }
    }
    //return tuple
    foundAndNum
  }
  
  /*
  //////////////////////////////////////////////////////////////////////////////
  -------------------- FIND CARD AND GET STACK IN UNCOVERED --------------------
  //////////////////////////////////////////////////////////////////////////////

  This function takes a card and returns the stack of uncovered cards that 
  contains that card

  UNUSED

  */
  def findCardAndGetStackInUncovered(card: Card): Stack[Card] = {
    var tmpStack = Stack[Card]()
    for(stack <- uncoveredStacks) {
      if(stack.contains(card)) {
        var tmpCard = stack.pop
        tmpStack.push(tmpCard)
        if (tmpStack.top == card) {
          return(tmpStack)
        }
      }
      else {
        tmpStack
      }
    }
    tmpStack
  }

  /*
  ///////////////////////////////////////////////////////
  -------------------- VALIDATE CARD --------------------
  ///////////////////////////////////////////////////////

  This function validates that a value, suit combination 
  representation of a card passed in is a valid card 

  */
  def validateCard(value: Int, suit: String): Boolean = {
    var isCard = true
    if(value < 0 || value > 14) {
      isCard = false
    }
    if(suit.toLowerCase != "s" && suit.toLowerCase != "c" && suit.toLowerCase != "d" && suit.toLowerCase != "h") {
      isCard = false
    }
    isCard
  }

  /*
  ////////////////////////////////////////////////////////
  -------------------- VALIDATE STACK --------------------
  ////////////////////////////////////////////////////////

  This function validates that a category, value combination 
  representation of a stack passed in is a valid stack

  */
  def validateStack(category: String, value: Int):Boolean = {
    if((category.toLowerCase == "a" || category.toLowerCase == "s") && (value > 0 && value < 8)) {
      true
    }
    else {
      false
    }
  }

  /*
  /////////////////////////////////////////////////////////////////
  -------------------- GET STACK FROM TOP CARD --------------------
  /////////////////////////////////////////////////////////////////

  This function takes a card and returns the stack that that card is in

  */
  def getStackFromCard(targetCard: Card): Stack[Card] = {
    var stackToReturn = Stack[Card]()
    for(stack <- allStacks) {
      for(card <- stack) {
        if(card.value == targetCard.value && card.suit == targetCard.suit) {
          stackToReturn = stack
        }
      }
    }
    stackToReturn

  }

  /*
  ////////////////////////////////////////////////////
  -------------------- CHECK MOVE --------------------
  ////////////////////////////////////////////////////

  This function checks if 2 cards can be placed together based on their value, suit, and location

  */
  def checkMove(card1: Card, card2: Card, location: String): Boolean = {
    if(location.toLowerCase == "s") {
      if(card2.value == card1.value + 1) {
        if((card1.suit == Suit.Hearts || card1.suit == Suit.Diamonds) && (card2.suit == Suit.Spades || card2.suit == Suit.Clubs)) {
          true
        }
        else if((card1.suit == Suit.Spades || card1.suit == Suit.Clubs) && (card2.suit == Suit.Hearts || card2.suit == Suit.Diamonds)) {
          true
        }
        else {
          false
        }
      }
      else if(card2.suit == Suit.None) {
          true
        }
      else {
        false
      }
    }
    else if (location.toLowerCase == "a") {
      if(card1.value == card2.value + 1) {
        if(card1.suit == card2.suit) {
          true
        }
        else {
          false
        }
      }
      else if(card2.suit == Suit.None) {
        true
      }
      else {
        false
      }
    }
    else {
      println("ERROR IN CHECKMOVE")
      false
    }
  }

  /*
  ///////////////////////////////////////////////////////////
  -------------------- POP CORRECT STACK --------------------
  ///////////////////////////////////////////////////////////

  This function pops the correct stack based off a given index and returns that card

  */
  def popCorrectStack(currentCardIndex: Int): Card = {
    var card: Card = null
    if(currentCardIndex == 0) {
      card = uncoveredStack1.pop
    }
    else if (currentCardIndex == 1) {
      card = uncoveredStack2.pop
    }
    else if (currentCardIndex == 2) {
      card = uncoveredStack3.pop
    }
    else if (currentCardIndex == 3) {
      card = uncoveredStack4.pop
    }
    else if (currentCardIndex == 4) {
      card = uncoveredStack5.pop
    }
    else if (currentCardIndex == 5) {
      card = uncoveredStack6.pop
    }
    else if (currentCardIndex == 6) {
      card = uncoveredStack7.pop
    }
    else if (currentCardIndex == 7) {
      card = ace1Stack.pop
    }
    else if (currentCardIndex == 8) {
      card = ace2Stack.pop
    }
    else if (currentCardIndex == 9) {
      card = ace3Stack.pop
    }
    else if (currentCardIndex == 10) {
      card = ace4Stack.pop
    }
    else if (currentCardIndex == 11) {
      card = discardStack.pop
    }
    else {
      println("ERROR IN POPCORRECTSTACK LAST ELSE")
    }
    card
  }

  /*
  ///////////////////////////////////////////////////
  -------------------- MOVE CARD --------------------
  ///////////////////////////////////////////////////

  This function moves a card from its current position to a new stack

  */
  def moveCard(cardToMove: Card, currentCardIndex: Int, newLocRow: String, newLocCol: Int) = {
    var card: Card = null
    //Push current card onto correct new stack (solitaire row)
    if (newLocRow.toLowerCase == "s") {
      val targetStack = uncoveredStacks(newLocCol-1)
      if(checkMove(Card(cardToMove.value, cardToMove.suit), targetStack.top, "s")) {
        if(cardToMove.value == 13) {
          handleKingToEmptyStack(newLocCol)
          card = cardToMove
          popCorrectStack(currentCardIndex)
        }
        else if(currentCardIndex != -1) {
          card = popCorrectStack(currentCardIndex)
        }
        else {
          card = cardToMove
        }
        targetStack.push(card)
      }
      else {
        println("Invalid move, please try again: moveCard push new card")
      }
    }
    else if (newLocRow.toLowerCase == "a") {
      val targetStack = aceStacks(newLocCol-1)
      if(checkMove(Card(cardToMove.value, cardToMove.suit), targetStack.top, "a")) {
          if(currentCardIndex != -1) {
            card = popCorrectStack(currentCardIndex)
          }
          else {
            card = cardToMove
          }
          targetStack.push(card)
      }
      else {
        println("Invalid move, please try again: moveCard push new card")
      }
    }
  }
  //steps of moving
  //check size of stack where card is (need card and stack)
  //if size > 1, moveCardStack (getting all cards below and including that card)
  //else, call regular move function


  /*
  /////////////////////////////////////////////////////////////
  -------------------- MOVE MULTIPLE CARDS --------------------
  /////////////////////////////////////////////////////////////

  This function takes a main card to move, the current stack 
  that it is in (which contains the other cards to be moved 
  with it), and the stack to move to and moves the cards.

  */
  def moveMultipleCards(mainCardToMove: Card, currentStack: Stack[Card], stackToMoveTo: Stack[Card]) = {
    if(currentStack.size > 1) {
      val cardsSelected = getSubsetOfCardStack(mainCardToMove, currentStack)
      stackToMoveTo.pushAll(cardsSelected)
    }
    else {
      println("ERROR IN MOVE MULTIPLE CARDS")
    }
  }


  /*
  //////////////////////////////////////////////////////////////////
  -------------------- GET SUBSET OF CARD STACK --------------------
  //////////////////////////////////////////////////////////////////

  This function takes a target card that needs to be moved and the 
  current stack it is in and returns a stack of all cards subsequent 
  (including) the target card.

  */
  def getSubsetOfCardStack(targetCard: Card, currentStack: Stack[Card]): Stack[Card] = {
    val tmpStack = Stack[Card]()
    var card = Card(-1, Suit.None)
    while(currentStack.top.value != targetCard.value) {
      card = currentStack.pop()
      tmpStack.push(card)
    }
    card = currentStack.pop()
    tmpStack.push(card)
    tmpStack
  }

  /*
  ////////////////////////////////////////////////////////////////////
  -------------------- HANDLE KING TO EMPTY STACK --------------------
  ////////////////////////////////////////////////////////////////////

  This function check to see if the stack that a king is being moved 
  to has a blank card on it. Ifit does, it pops the blank card in 
  perparation for the king to be pushed.

  */
  def handleKingToEmptyStack(newLocCol: Int) = {
    val targetStack = uncoveredStacks(newLocCol-1)
    if(targetStack.isEmpty) {
      println("ERROR 1 IN HANDLE KING TO EMPTY STACK")
    }
    else if(checkIfTopIsBlank(targetStack)) {
      targetStack.pop()
    }
    else {
      println("Error 2 in handleKingToEmptyStack")
    }

  }

  /*
  ///////////////////////////////////////////////////////////////
  -------------------- CHECK IF TOP IS BLANK --------------------
  ///////////////////////////////////////////////////////////////

  This function takes a stack of cards and returns a boolean of 
  if the top card is a blank card.

  */
  def checkIfTopIsBlank(stack: Stack[Card]):Boolean = {
    if(stack.top.value == -1 && stack.top.suit == Suit.None) {
      true
    }
    else {
      println("top card of stack is not empty")
      false
    }
  }
  
  /*
  //////////////////////////////////////////////////////
  -------------------- HANDLE INPUT --------------------
  //////////////////////////////////////////////////////

  This function moves handles all commands from the user

  */
  def handleInput(line: String) = {
    if(line == "hello") {
      println("hola")
    }
    else if(line.toLowerCase == "help") {
      println("Commands:")
      println("draw -             draws as card from the draw deck and places it face up in the discard deck to be used")
      println("move __ to __ -    allows you to move a card (KD, AS, 6C, JH, etc) to a specific pile (A1, S4, A3, S7, etc.) \n \t Example: \"move AS to A3\" moves the ace of spades (if it is in play) to the third ace pile. \n \t Example: \"move KD to S4\" moves the king of diamonds (if it is in play) to the fourth solitaire pile.")
      println("moveall __ to __ - allows you to move a card (KD, AS, 6C, JH, etc) and any subsequent cards in the pile to a specific pile (A1, S4, A3, S7, etc.) \n \t Example: \"move 10D to S4\" moves the 10 of diamonds (if it is in play) and any cards placed on top of the 10 of diamonds to the fourth solitaire pile. \n")
      println("q -                quit the game")
    }
    //draws a card from the deck and adds it face up to the discard pile
    else if(line.toLowerCase == "draw")
    {
      //check if the deck stack is empty, if so, push the discard stack to the deck stack, revcerse it, and clear the discard stack
      if(drawStack.isEmpty) {
        drawStack.pushAll(discardStack).reverse
        discardStack.clear()
        updateGame()
      }
      else if(cardsToFlip == 3 && drawStack.length >= 3) {
        var card1 = drawStack.pop
        discardStack.push(card1)
        var card2 = drawStack.pop
        discardStack.push(card2)
        var card3 = drawStack.pop
        discardStack.push(card3)
        updateGame()
      }
      else if(cardsToFlip == 3 && drawStack.length == 2) {
        var card1 = drawStack.pop
        discardStack.push(card1)
        var card2 = drawStack.pop
        discardStack.push(card2)
        updateGame()
      }
      else if(cardsToFlip == 3 && drawStack.length == 1) {
        var card1 = drawStack.pop
        discardStack.push(card1)
        updateGame()
      }
      //handle drawing a card and placing it face up in the discard pile
      //println("Discard Stack Length: " + discardStack.size)
      updateGame()
    }
    else if (line.split(" ").length < 4 ) {
      println("That is an invalid command. Type \"help\" for a list and description of valid commands")
    }
    //handles moving a card by calling multiple functions to convert command to usable data
    else if (line.toLowerCase().contains("moveall")) {
      val lineSubstring = line.split(" ")
      val cardToMove = lineSubstring(1)
      var cardToMoveSuit = ""
      var cardToMoveValue = -1
      var currentStack = Stack[Card]()
      val stackToMoveToString = lineSubstring(3)
      var stackToMoveToRow = ""
      var stackToMoveToColumn = -1
      var stackToMoveTo = Stack[Card]()
      var validCommand = false

      if(cardToMove.size == 3 && (validateCard(convertValueFromCard(cardToMove(0).toString + cardToMove(1).toString), cardToMove(2).toString))) {
        cardToMoveValue = convertValueFromCard(cardToMove(0).toString + cardToMove(1).toString)
        cardToMoveSuit = cardToMove(2).toString
        currentStack = getStackFromCard(Card(cardToMoveValue, convertSuitForCard(cardToMoveSuit)))
        stackToMoveToColumn = convertValueFromCard(stackToMoveToString(1).toString)
        stackToMoveTo = uncoveredStacks(stackToMoveToColumn-1)
        validCommand = true
      }
      else if ((validateCard(convertValueFromCard(cardToMove(0).toString), cardToMove(1).toString))) {
        cardToMoveValue = convertValueFromCard(cardToMove(0).toString)
        cardToMoveSuit = cardToMove(1).toString
        currentStack = getStackFromCard(Card(cardToMoveValue, convertSuitForCard(cardToMoveSuit)))
        stackToMoveToColumn = convertValueFromCard(stackToMoveToString(1).toString)
        stackToMoveTo = uncoveredStacks(stackToMoveToColumn-1)
        if(cardToMoveValue == 13 && checkIfTopIsBlank(stackToMoveTo)) {
          stackToMoveTo.pop
        }
        validCommand = true
      }
      if (validCommand) {
        moveMultipleCards(Card(cardToMoveValue, convertSuitForCard(cardToMoveSuit)), currentStack, stackToMoveTo)
        updateGame()
      }
      updateGame()
    }
    else if(line.toLowerCase().contains("move")) {
      val lineSubstring = line.split(" ")
      val cardToMove = lineSubstring(1)
      var cardToMoveSuit = ""
      var cardToMoveValue = -1
      val stackToMoveTo = lineSubstring(3)
      var stackToMoveToRow = ""
      var stackToMoveToColumn = -1
      var validCommand = false

      //If the card is a 10
      if(cardToMove.size == 3 
        && (validateCard(convertValueFromCard(cardToMove(0).toString + cardToMove(1).toString), cardToMove(2).toString))
        && (validateStack(stackToMoveTo(0).toString, convertValueFromCard(stackToMoveTo(1).toString))))
        {
          cardToMoveValue = convertValueFromCard(cardToMove(0).toString + cardToMove(1).toString)
          cardToMoveSuit = cardToMove(2).toString
          stackToMoveToRow = stackToMoveTo(0).toString
          stackToMoveToColumn = convertValueFromCard(stackToMoveTo(1).toString)
          validCommand = true
        }
      //If the card is anything except a 10  
      else if ((validateCard(convertValueFromCard(cardToMove(0).toString), cardToMove(1).toString)) 
        && (validateStack(stackToMoveTo(0).toString, convertValueFromCard(stackToMoveTo(1).toString)))) 
        {
          cardToMoveValue = convertValueFromCard(cardToMove(0).toString)
          cardToMoveSuit = cardToMove(1).toString
          stackToMoveToRow = stackToMoveTo(0).toString
          stackToMoveToColumn = convertValueFromCard(stackToMoveTo(1).toString)
          validCommand = true
        }
      if (validCommand) {
        //check if card is actually in play
        val isCardInPlay = findCardOnTop(cardToMoveValue, cardToMoveSuit)
        val currentLoc = isCardInPlay._2
        if(isCardInPlay._1) {
          moveCard(Card(cardToMoveValue, convertSuitForCard(cardToMoveSuit)), currentLoc, stackToMoveToRow, stackToMoveToColumn)
          updateGame()
        }
        else {
          println("That card is not available")
        }
      }

      //Add check for making sure this is a valid card
    }
    //Error catching
    else {
      println("That is an invalid command. Type \"help\" for a list and description of valid commands")
    }
  }
}
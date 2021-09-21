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
  val deck = Deck.shuffleDeck(Deck.generateDeck)
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
    println("CFFC value: " + value)
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
      println("inside king if part")
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
        "_____________" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "        |" + "," + 
        "|           |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|           |" + "," + 
        "|        " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else if(value == 10) {
        return(
        "_____________" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "       |" + "," + 
        "|           |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
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
        "_____________" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "        |" + "," + 
        "|           |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "     |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "     |" + "," + 
        "|           |" + "," + 
        "|        " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else if(value == 10) {
        return(
        "_____________" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "       |" + "," + 
        "|           |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "     |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "     " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "     |" + "," + 
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
        "_____________" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "        |" + "," + 
        "|           |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|        " + shortSuit + "  |" + "," + 
        "|        " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|           |" + "," + 
        "|        " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else if(value == 10) {
        return(
        "_____________" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "       |" + "," + 
        "|           |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|        " + shortSuit + "  |" + "," + 
        "|        " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
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
        "_____________" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "        |" + "," + 
        "|           |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|           |" + "," + 
        "|        " + convertValueForCard(value) + shortSuit + " |" + "," + 
        "-------------")
      }
      else if(value == 10) {
        return(
        "_____________" + "," + 
        "| " + convertValueForCard(value) + shortSuit + "       |" + "," + 
        "|           |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "        |" + "," + 
        "|  " + shortSuit + "  " + shortSuit + "  " + shortSuit + "  |" + "," + 
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
          "_____________" + "," + 
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

  def convertCardPileToASCII(cardStack: Stack[Card]) = {
    //convert every card
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
    while(rowCounter < 13) {
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
    "_____________" + "," + 
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
    "             "
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
    /*if(ace1Stack.isEmpty) {
      aceCardList = aceCardList :+ printEmptyCard().split(",").toList
    }
    else {
      aceCardList = aceCardList :+ convertCardToASCII(ace1Stack.top).split(",").toList
    }
    if(ace2Stack.isEmpty) {
      aceCardList = aceCardList :+ printEmptyCard().split(",").toList
    }
    else {
      aceCardList = aceCardList :+ convertCardToASCII(ace2Stack.top).split(",").toList
    }
    if(ace3Stack.isEmpty) {
      aceCardList = aceCardList :+ printEmptyCard().split(",").toList
    }
    else {
      aceCardList = aceCardList :+ convertCardToASCII(ace3Stack.top).split(",").toList
    }
    if(ace4Stack.isEmpty) {
      aceCardList = aceCardList :+ printEmptyCard().split(",").toList
    }
    else {
      aceCardList = aceCardList :+ convertCardToASCII(ace4Stack.top).split(",").toList
    }*/
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
    println()
    println()

    //Makes a list of the solitaire spots in the main section of the game. Adds the top card to the list and adds an empty card if there are no cards in the stack
    var cardList = List[List[String]]()
    if(uncoveredStack1.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ convertCardToASCII(uncoveredStack1.top).split(",").toList
    }
    if(uncoveredStack2.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ convertCardToASCII(uncoveredStack2.top).split(",").toList
    }
    if(uncoveredStack3.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ convertCardToASCII(uncoveredStack3.top).split(",").toList
    }
    if(uncoveredStack4.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ convertCardToASCII(uncoveredStack4.top).split(",").toList
    }
    if(uncoveredStack5.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ convertCardToASCII(uncoveredStack5.top).split(",").toList
    }
    if(uncoveredStack6.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ convertCardToASCII(uncoveredStack6.top).split(",").toList
    }
    if(uncoveredStack7.isEmpty) {
      cardList = cardList :+ printEmptyCard().split(",").toList
    }
    else {
      cardList = cardList :+ convertCardToASCII(uncoveredStack7.top).split(",").toList
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

    //Makes a list of the discard deck and draw deck below the main section of the game with blank cards for spacing. Adds the top card to the list and adds an empty card if there are no cards in the stack
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
    drawCardsList = drawCardsList :+ printEmptyCard().split(",").toList
    //call reformatCardList to arrange the cards horizontally
    var drawCards = reformatCardList(drawCardsList)
    //Print the cards
    printCard(drawCards)
  }

  /*
  ///////////////////////////////////////////////////
  -------------------- DEAL GAME --------------------
  ///////////////////////////////////////////////////

  This function deals cards out like one would in solitaire

  */
  def dealGame() = {
      println("adding cards to stacks")
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
    
    //set play style
    if(flipInput == "3") {
      cardsToFlip = 3
    }
    else if(flipInput != "1") {
      println("Error with flip count handling")
    }
    println("Flip Count: " + cardsToFlip)
    //handle input during game
    while(running) {
      var input = readLine()
      handleInput(input)
      //exits the game
      if(input.toLowerCase == "q") {
        println(s"Goodbye $name!")
        running = false
      }
    }
  }

  /*
  /////////////////////////////////////////////////////
  -------------------- UPDATE GAME --------------------
  /////////////////////////////////////////////////////

  This function updates the game by rebuilding the stacks and reprinting the board

  */
  def updateGame(): Unit = {
    fixStacksAfterMove()
    rebuildStacks()
    printGame()
  }

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
  ///////////////////////////////////////////////////
  -------------------- FIND CARD --------------------
  ///////////////////////////////////////////////////

  This function locates a card usning its value and suit and returns 
  a boolean, int tuple of if the card was found, and what stack it is 
  located in in allStacksAndDiscardStack

  */
  def findCard(value: Int, suit: String): (Boolean, Int) = {
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

  UNUSED

  */
  def getStackFromTopCard(category: String, value: Int): Stack[Card] = {
    var returnStack = Stack[Card]()
    if(category.toLowerCase() == "s") {
      for(i <- 0 to 6) {
        if(i == value) {
          returnStack = uncoveredStacks(i)
        }
        else {
          returnStack = Stack[Card]()
        }
      }
    }
    else if (category.toLowerCase == "a") {
      for(i <- 0 to 3) {
        if(i == value) {
          returnStack = aceStacks(i)
        }
        else {
          returnStack = Stack[Card]()
        }
      }
    }
    else {
      returnStack = Stack[Card]()
    }
    returnStack
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
    //checkMove(Card(cardToMove.value, cardToMove.suit), Card())
    var card: Card = null
    //Push current card onto correct new stack (solitaire row)
    if (newLocRow.toLowerCase == "s") {
      if(newLocCol == 1) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + uncoveredStack1.top)
        println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
        if(checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s")) {
          println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
          card = popCorrectStack(currentCardIndex)
          uncoveredStack1.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
      else if(newLocCol == 2) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + uncoveredStack2.top)
        println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
        if(checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack2.top, "s")) {
          println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
          card = popCorrectStack(currentCardIndex)
          uncoveredStack2.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
      else if(newLocCol == 3) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + uncoveredStack3.top)
        println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
        if(checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack3.top, "s")) {
          println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
          card = popCorrectStack(currentCardIndex)
          uncoveredStack3.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
      else if(newLocCol == 4) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + uncoveredStack4.top)
        println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
        if(checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack4.top, "s")) {
          println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
          card = popCorrectStack(currentCardIndex)
          uncoveredStack4.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
      else if(newLocCol == 5) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + uncoveredStack5.top)
        println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
        if(checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack5.top, "s")) {
          println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
          card = popCorrectStack(currentCardIndex)
          uncoveredStack5.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
      else if(newLocCol == 6) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + uncoveredStack6.top)
        println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
        if(checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack6.top, "s")) {
          println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
          card = popCorrectStack(currentCardIndex)
          uncoveredStack6.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
      else if(newLocCol == 7) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + uncoveredStack7.top)
        println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
        if(checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack7.top, "s")) {
          println("checkMove: " + checkMove(Card(cardToMove.value, cardToMove.suit), uncoveredStack1.top, "s"))
          card = popCorrectStack(currentCardIndex)
          uncoveredStack7.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
    }
    //Push current card onto correct new stack (ace row)
    else if (newLocRow.toLowerCase == "a") {
      if(newLocCol == 1) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + ace1Stack.top.value + " " + ace1Stack.top.suit)
        println("First ace")
        if(checkMove(Card(cardToMove.value, cardToMove.suit), ace1Stack.top, "a")) {
          card = popCorrectStack(currentCardIndex)
          ace1Stack.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
      else if(newLocCol == 2) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + ace2Stack.top.value + " " + ace2Stack.top.suit)
        println("Second ace")
        if(checkMove(Card(cardToMove.value, cardToMove.suit), ace2Stack.top, "a")) {
          card = popCorrectStack(currentCardIndex)
          ace2Stack.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
      else if(newLocCol == 3) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + ace3Stack.top.value + " " + ace3Stack.top.suit)
        println("Third ace")
        if(checkMove(Card(cardToMove.value, cardToMove.suit), ace3Stack.top, "a")) {
          card = popCorrectStack(currentCardIndex)
          ace3Stack.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
      else if(newLocCol == 4) {
        println("MoveCard cardToMoveValue: " + cardToMove.value)
        println("MoveCard cardToMoveSuit: " + cardToMove.suit)
        println("stacktoMoveToTopCard: " + ace4Stack.top.value + " " + ace4Stack.top.suit)
        println("Fourth ace")
        if(checkMove(Card(cardToMove.value, cardToMove.suit), ace4Stack.top, "a")) {
          card = popCorrectStack(currentCardIndex)
          ace4Stack.push(card)
        }
        else {
          println("Invalid move, please try again: moveCard push new card")
        }
      }
    }
    else {
      println("Invalid move, please try again: incorrect locRow")
    }
  }


  //Commands
  /*
    draw: draws a new card from the deck and places it face up in the discard deck to be used
    move VS to (AX(1,2,3,4) or SX(1,2,3,4,5,6,7)): moves a specific card to the top of a different stack
    undo: undoes the last move made
  */
  
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
      println("draw - draws as card from the draw deck and places it face up in the discard deck to be used")
      println("move __ to __ - allows you to move a card (KD, AS, 6C, JH, etc) to a specific pile (A1, S4, A3, S7, etc. \n \t Example: \"move AS to A3\" moves the ace of spades (if it is in play) to the third ace pile. \n \t Example: \"move KD to S4\" moves the king of diamonds (if it is in play) to the fourth solitaire pile.")
      println("q - quit the game")
    }
    //draws a card from the deck and adds it face up to the discard pile
    else if(line.toLowerCase == "draw")
    {
      if(drawStack.isEmpty) {
        drawStack.pushAll(discardStack)
        discardStack.push(drawStack.pop)
        updateGame()
      }
      discardStack.push(drawStack.pop())
      updateGame()
    }
    //handles moving a card by calling multiple functions to convert command to usable data
    else if(line.toLowerCase().contains("move")) {
      val lineSubstring = line.split(" ")
      val cardToMove = lineSubstring(1)
      var cardToMoveSuit = ""
      var cardToMoveValue = -1
      val stackToMoveTo = lineSubstring(3)
      var stackToMoveToRow = ""
      var stackToMoveToColumn = -1
      var validCommand = false

      if(cardToMove.size == 3 
        && (validateCard(convertValueFromCard(cardToMove(0).toString + cardToMove(1).toString), cardToMove(2).toString))
        && (validateStack(stackToMoveTo(0).toString, convertValueFromCard(stackToMoveTo(1).toString))))
        {
          cardToMoveValue = convertValueFromCard(cardToMove(0).toString + cardToMove(1).toString)
          cardToMoveSuit = cardToMove(2).toString
          stackToMoveToRow = stackToMoveTo(0).toString
          stackToMoveToColumn = convertValueFromCard(stackToMoveTo(1).toString)
          validCommand = true
          println("first")
        }
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
        //checkMove(Card(cardToMoveValue, convertSuitForCard(cardToMoveSuit)), Card())
        //check if card is actually in play
        val isCardInPlay = findCard(cardToMoveValue, cardToMoveSuit)
        val currentLoc = isCardInPlay._2
        if(isCardInPlay._1) {
          moveCard(Card(cardToMoveValue, convertSuitForCard(cardToMoveSuit)), currentLoc, stackToMoveToRow, stackToMoveToColumn)
          updateGame()
        }
        else {
          println("That card is not available")
        }
        //println("card in play: " + isCardInPlay._1 + " loc: " + isCardInPlay._2)
      }

      //Add check for making sure this is a valid card
    }
    //Error catching
    else {
      println("That is an invalid command. Type \"help\" for a list and description of valid commands")
    }
  }

  //TODO: Write function that consistantly checks if an empty uncovered stack has cards corresponding in the covered stack.
    //If it does, pop from covered and push to covered
    //If it does not, push blank card to uncovered

  //TODO: Write function that checks if a card placement is a logically valid move with suit and value comparisons
    //Check to make sure only ace cards can be placed into the ace piles if they are empty
    //Ace piles MUST be same suit and count up, Solitaire piles must be alternating suits and count down
    //Only kings can be placed in blank solitaire slots
}

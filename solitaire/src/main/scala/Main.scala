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
  val deck = /*Deck.shuffleDeck*/(Deck.generateDeck)
  var deckStack: Stack[Card] = Stack[Card]()
  deckStack.pushAll(deck)

  @main
  def mainFunction: Unit = {
    /*println("generated deck")
    var testCardList = List[List[String]]()
    for(i <- deck) {
      testCardList = testCardList :+ convertCardtoASCII(i).split(",").toList
    }
    var testCards = reformatCardList(testCardList)
    for(i <- testCards) {
      print(i)
    }*/
    //printCard(testCards)

    dealGame()
    /*println("First stack: " + uncoveredStack1.top.value + " of " + uncoveredStack1.top.suit)
    println("Second stack: " + uncoveredStack2.top.value + " of " + uncoveredStack2.top.suit)
    println("Third stack: " + uncoveredStack3.top.value + " of " + uncoveredStack3.top.suit)
    println("Fourth stack: " + uncoveredStack4.top.value + " of " + uncoveredStack4.top.suit)
    println("Fifth stack: " + uncoveredStack5.top.value + " of " + uncoveredStack5.top.suit)
    println("Sixth stack: " + uncoveredStack6.top.value + " of " + uncoveredStack6.top.suit)
    println("Seventh stack: " + uncoveredStack7.top.value + " of " + uncoveredStack7.top.suit)
    */
    printGame()
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

  This function takes an integer card value (1-13) and converts ut to the
  corresponding card value (ace, jack, queen king)

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
  ////////////////////////////////////////////////////
  -------------------- PRINT CARD --------------------
  ////////////////////////////////////////////////////

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
    else {
      return("ERROR IN convertCardtoASCII LAST ELSE")
    }
  }

  def printCard(card: List[String]) = {
    for(i <- card) {
      println(i)
    }
  }

  /*
  /////////////////////////////////////////////////////////
  -------------------- PRINT CARD LIST --------------------
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
  ////////////////////////////////////////////////////////////////
  -------------------- PRINT GAME START STATE --------------------
  ////////////////////////////////////////////////////////////////

  This function prints the beginning state of the game

  */
  def printGame() = {

    //Makes a list of the ace spots above the main section of the game
    var aceCardList = List[List[String]]()
    if(ace1Stack.isEmpty) {
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
    }
    var aceCards = reformatCardList(aceCardList)
    for(i <- aceCards) {
      print(i)
    }

    //Space seperation between ace section and main section
    println()
    println()
    println()
    println()
    println()

    //Test cards to check printing functionality
    /*val card1:List[String] = convertCardtoASCII(deck(0)).split(",").toList
    val card2:List[String] = convertCardtoASCII(deck(1)).split(",").toList
    val card3:List[String] = convertCardtoASCII(deck(2)).split(",").toList
    val card4:List[String] = convertCardtoASCII(deck(3)).split(",").toList
    val card5:List[String] = convertCardtoASCII(deck(4)).split(",").toList
    val card6:List[String] = convertCardtoASCII(deck(5)).split(",").toList
    val card7:List[String] = convertCardtoASCII(deck(6)).split(",").toList*/

    //Get cards from stacks and print them
    var cardList:List[List[String]] = List(
        convertCardToASCII(uncoveredStack1.top).split(",").toList, 
        convertCardToASCII(uncoveredStack2.top).split(",").toList, 
        convertCardToASCII(uncoveredStack3.top).split(",").toList, 
        convertCardToASCII(uncoveredStack4.top).split(",").toList, 
        convertCardToASCII(uncoveredStack5.top).split(",").toList, 
        convertCardToASCII(uncoveredStack6.top).split(",").toList, 
        convertCardToASCII(uncoveredStack7.top).split(",").toList)
    var cards = reformatCardList(cardList)
    for(i <- cards) {
      print(i)
    }
    /*for(i <- 1 to 7) {
      cardList = cardList :+ convertCardtoASCII(coveredStacks(i).top.value, coveredStacks(i).top.suit).split(",").toList
    }
    var cards = reformatCardList(cardList)
    for(i <- cards) {
      print(i)
    }*/


    //Makes space between the main section and the draw section
    println()
    println()
    println()

    //Makes a list for the discard spot and the draw spot 
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
    var drawCards = reformatCardList(drawCardsList)
    for(i <- drawCards) {
      print(i)
    }
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
      drawStack.pushAll(deckStack)
      println("drawStack size: " + drawStack.size)
      allStacks = uncoveredStacks ++ aceStacks
  }

  def playGame() = {
    var running = true
    println("What is you name?")
    var name = readLine()
    println(s"Hello, $name! \nWelcome to Solitaire")
    val cardLoc = findCard(1, "s")
    println("card location: " + cardLoc)
    println("Would you like to flip 1 card at a time or three cards at a time? (1 or 3)")
    var flipInput = readLine()
    if(flipInput == "3") {
      cardsToFlip = 3
    }
    else if(flipInput != "1") {
      println("Error with flip count handling")
    }
    println("Flip Count: " + cardsToFlip)
    while(running) {
      var input = readLine()
      handleInput(input)
      if(input.toLowerCase == "q") {
        println(s"Goodbye $name!")
        running = false
      }
    }
  }

  /*def findCard(value: Int, suit: String): Int = {
    //Check solitaire stacks
    var found = false
    var uncoveredStackCounter = 0
    for(stack <- uncoveredStacks) {
      if(value == stack.top.value && suit == stack.top.suit.toString) {
        return(uncoveredStackCounter)
        found = true
      }
      uncoveredStackCounter += 1
    }

    var aceStackCounter = 0
    if(!found) {
      for (stack <- aceStacks) {
        if(value == stack.top.value && suit == stack.top.suit.toString) {
          return(aceStackCounter)
          found = true
        }
        else {

        }
        aceStackCounter += 1
        aceStackCounter
      }
    }
    else {
      return(uncoveredStackCounter)
    }
  }*/

  def findCard(value: Int, suit: String): (Boolean, Int) = {
    var foundAndNum = (false, -1)
    var counter = 0
    while(counter < 11) {
      println("allstacks length: " + allStacks.size)
      val current = allStacks(counter).top
      if(current.value == value && current.suit.toString.toLowerCase == suit) {
        foundAndNum = (true, counter)
        counter = 11
      }
      else {
        counter += 1
      }
    }
    foundAndNum
  }

  def validatecard(value: Int, suit: String): Boolean = {
    var isCard = true
    if(value < 0 || value > 14) {
      isCard = false
    }
    if(suit.toLowerCase != "s" || suit.toLowerCase != "c" || suit.toLowerCase != "d" || suit.toLowerCase != "h") {
      isCard = false
    }
    isCard
  }


  //Commands
  /*
    draw: draws a new card from the deck and places it face up in the discard deck to be used
    move VS to (AX(1,2,3,4) or SX(1,2,3,4,5,6,7)): moves a specific card to the top of a different stack
    undo: undoes the last move made
  */
  def handleInput(line: String) = {
    if(line == "hello") {
      println("hola")
    }
    else if(line.toLowerCase == "draw")
    {
      discardStack.push(drawStack.pop())
      println("popped and pushed")
      printGame()
    }
    else if(line.toLowerCase().contains("move")) {
      val lineSubstring = line.split(" ")
      val cardToMove = lineSubstring(1)
      var cardToMoveSuit = ""
      var cardToMoveValue = ""
      val stackToMoveTo = lineSubstring(3)
      var stackToMoveToRow = ""
      var stackToMoveToColumn = ""
      //Add check for making sure this is a valid card
      //split into 2 parts, check length of string to determine if it is a 10
      if(cardToMove.size == 3) {
        cardToMoveValue = cardToMove(0) + cardToMove(1).toString
        cardToMoveSuit = cardToMove(2).toString
      }
      else {
        cardToMoveValue = cardToMove(0).toString
        cardToMoveSuit = cardToMove(1).toString
      }
      stackToMoveToRow = stackToMoveTo(0).toString
      stackToMoveToColumn = stackToMoveTo(1).toString

      //Add check for making sure this is a valid card

    }
    else {
      println("That is an invalid command. Type \"help\" for a list and description of valid commands")
    }
  }
  //TODO: make a move checker that verifys that a move is valid before it is made
  //TODO: maken a function that finds a card and determines if it is on top


  //def msg = "I was compiled by Scala 3. :)"
}

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
  var ace1 = Stack[Card]()
  var ace2 = Stack[Card]()
  var ace3 = Stack[Card]()
  var ace4 = Stack[Card]()
  //List of all ace card stacks
  var aceStacks: List[Stack[Card]] = List(ace1, ace2, ace3, ace4)

  var discard = Stack[Card]()
  var draw = Stack[Card]()

  @main
  def mainFunction: Unit = {
    val deck = Deck.generateDeck
    var deckStack: Stack[Card] = Stack[Card]()
    deckStack.pushAll(deck)
    println("generated deck")
    var testCardList = List[List[String]]()
    for(i <- deck) {
      testCardList = testCardList :+ convertCardtoASCII(i).split(",").toList
    }
    var testCards = reformatCardList(testCardList)
    for(i <- testCards) {
      print(i)
    }
    //printCard(testCards)

    dealGame(deckStack)
    println("First stack: " + uncoveredStack1.top.value + " of " + uncoveredStack1.top.suit)
    println("Second stack: " + uncoveredStack2.top.value + " of " + uncoveredStack2.top.suit)
    println("Third stack: " + uncoveredStack3.top.value + " of " + uncoveredStack3.top.suit)
    println("Fourth stack: " + uncoveredStack4.top.value + " of " + uncoveredStack4.top.suit)
    println("Fifth stack: " + uncoveredStack5.top.value + " of " + uncoveredStack5.top.suit)
    println("Sixth stack: " + uncoveredStack6.top.value + " of " + uncoveredStack6.top.suit)
    println("Seventh stack: " + uncoveredStack7.top.value + " of " + uncoveredStack7.top.suit)
    //printGameStartState(deck)
    //playGame()
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
  def convertCardtoASCII(card: Card):String = {
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
  def printGameStartState(deck: List[Card]) = {

    //Makes a list of the ace spots above the main section of the game
    var emptyCardList = List[List[String]]()
    for(i <- 1 to 4) {
      emptyCardList = emptyCardList :+ printEmptyCard().split(",").toList
    }
    var emptyCards = reformatCardList(emptyCardList)
    for(i <- emptyCards) {
      print(i)
    }

    //Space seperation between ace section and main section
    println()
    println()
    println()
    println()
    println()

    //Test cards to check printing functionality
    val card1:List[String] = convertCardtoASCII(deck(0)).split(",").toList
    val card2:List[String] = convertCardtoASCII(deck(1)).split(",").toList
    val card3:List[String] = convertCardtoASCII(deck(2)).split(",").toList
    val card4:List[String] = convertCardtoASCII(deck(3)).split(",").toList
    val card5:List[String] = convertCardtoASCII(deck(4)).split(",").toList
    val card6:List[String] = convertCardtoASCII(deck(5)).split(",").toList
    val card7:List[String] = convertCardtoASCII(deck(6)).split(",").toList

    //Get cards from stacks and print them
    var cardList:List[List[String]] = List(card1, card2, card3, card4, card5, card6, card7)
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
        :+ printEmptyCard().split(",").toList 
        :+ convertCardtoASCII(deck.head).split(",").toList
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
  def dealGame(deck: Stack[Card]) = {
      println("adding cards to stacks")
      //add 1 card to first uncovered stack, add 6 cards to next 6 coverd stacks
      uncoveredStack1.push(deck.pop())
      for(i <- 0 to 5) {
        coveredStacks(i).push(deck.pop())
      }

      //add 1 card to second uncoverd stack, add 5 cards to next 5 covered stacks
      uncoveredStack2.push(deck.pop())
      for(i <- 1 to 5) {
        coveredStacks(i).push(deck.pop())
      }

      //add 1 card to third uncoverd stack, add 4 cards to next 4 covered stacks
      uncoveredStack3.push(deck.pop())
      for(i <- 2 to 5) {
        coveredStacks(i).push(deck.pop())
      }

      //add 1 card to fourth uncoverd stack, add 3 cards to next 3 covered stacks
      uncoveredStack4.push(deck.pop())
      for(i <- 3 to 5) {
        coveredStacks(i).push(deck.pop())
      }
      //add 1 card to fifth uncoverd stack, add 2 cards to next 2 covered stacks
      uncoveredStack5.push(deck.pop())
      for(i <- 4 to 5) {
        coveredStacks(i).push(deck.pop())
      }
      //add 1 card to sixth uncoverd stack, add 1 cards to next 1 covered stacks
      uncoveredStack6.push(deck.pop())
      coveredStack7.push(deck.pop())
      //add 1 card to seventh uncoverd stack, add rest of cards to draw stack
      uncoveredStack7.push(deck.pop())
  }

  def getDealtCards() = {
    var deck: List[List[String]] = List[List[String]]()
  }

  def playGame() = {
    var running = true
    println("What is you name?")
    var name = readLine()
    println(s"Hello, $name!")
    while(running) {
      var input = readLine()
      if(input.toLowerCase == "q") {
        println(s"Goodbye $name!")
        running = false
      }
    }
  }


  //def msg = "I was compiled by Scala 3. :)"
}

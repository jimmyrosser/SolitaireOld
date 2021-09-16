import scala.collection.mutable.Stack

class GameState {
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

    var discardStack = Stack[Card]()
    var drawStack = Stack[Card]()
    var cardsToFlip = 1

    //Generate and shuffle the deck, then push it all to a stack
    val deck = Deck.shuffleDeck(Deck.generateDeck)
    var deckStack: Stack[Card] = Stack[Card]()
    deckStack.pushAll(deck)

    //Accessor methods
    /*def coveredStack2: Stack[Card] = {
        coveredStack2
    }
    def coveredStack2: Stack[Card] = {
        coveredStack2
    }
    def coveredStack2: Stack[Card] = {
        coveredStack2
    }
    def coveredStack2: Stack[Card] = {
        coveredStack2
    }
    def coveredStack2: Stack[Card] = {
        coveredStack2
    }
    def coveredStack2: Stack[Card] = {
        coveredStack2
    }
    def coveredStacks: List[Stack[Card]] = {
        coveredStacks
    }

    def uncoveredStack1: Stack[Card] = {
        uncoveredStack1
    }
    def uncoveredStack2: Stack[Card] = {
        uncoveredStack2
    }
    def uncoveredStack3: Stack[Card] = {
        uncoveredStack3
    }
    def uncoveredStack4: Stack[Card] = {
        uncoveredStack4
    }
    def uncoveredStack5: Stack[Card] = {
        uncoveredStack5
    }
    def uncoveredStack6: Stack[Card] = {
        uncoveredStack6
    }
    def uncoveredStack7: Stack[Card] = {
        uncoveredStack7
    }
    def uncoveredStacks: List[Stack[Card]] = {
        uncoveredStack7
    }

    def ace1Stack: Stack[Card] = {
        ace1Stack
    }
    def ace2Stack: Stack[Card] = {
        ace2Stack
    }
    def ace3Stack: Stack[Card] = {
        ace3Stack
    }
    def ace4Stack: Stack[Card] = {
        ace4Stack
    }
    def aceStacks: List[Stack[Card]] = {
        aceStacks
    }

    def discardStack: Stack[Card] = {
        discardStack
    }
    def drawStack: Stack[Card] = {
        drawStack
    }

    def deckStack: Stack[Card] = {
        deckStack
    }

    def cardsToFlip: Int = {
        cardsToFlip
    }*/
        
}

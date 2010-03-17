from sexpr.sexpr import *

class Interrogator:
    """
    A mock connection that joins every game as a 
    spectator and creates game logs.
    """
    def __init__(self, game):
        self.messages = [] #A list of messages this player has received
        self.user = "Interrogator"
        self.screenName = "Interrogator"
        self.game = game
	self.stateList = []
	self.diceList = []

	#Larger Die Rule
	self.stateList.append([0, 0, 2, 0, 0, 0,-5, 2, 0, 2, 0, 2, 0,
                                 -5, 2,-4, 0, 0, 0, 2, 1, 2, 0, 0,-1, 0])
	self.diceList.append([6,4,0,0])

	#Avoidance 2
	self.stateList.append([0, 2, 0,-1, 0,-2,-1, 0, 0, 0, 0, 0, 0,
				  0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 2, 2, 0])
	self.diceList.append([6,4,0,0])

    def writeSExpr(self, message):
        pass

    def nextQuestion(self):
	if (len(self.stateList) == 0):
	    self.game.declareWinner(self.game.players[0])
	else:
	    self.game.myBoard.points = self.stateList.pop()[:]
	    newDice = self.diceList.pop()[:]
	    self.game.myBoard.rollDice()
	    while (self.game.myBoard.dice != newDice):
		self.game.myBoard.rollDice()


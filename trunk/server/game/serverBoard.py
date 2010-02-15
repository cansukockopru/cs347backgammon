import functools
import gameObject
import math
import random
GameObject = gameObject.GameObject


class ServerBoard(GameObject):
    """
    The backgammon board
    """

    def __init__(self, game):
        GameObject.__init__(self, game)
	self.points = [0]*26
	self.dice = [0]*4

    def toList(self):
        list = GameObject.toList(self)
	list.extend(self.dice)
        list.extend(self.points)
        return list

    def talk(self, message):
	self.game.animations.append(['talk', self.id, message])
	return True

    def nextTurn(self):
        GameObject.nextTurn(self)
	#Force the use of all unused dice
	movingPlayer = self.game.getPlayerIndex(self.game.turn)
        direction = 2*movingPlayer - 1
	i = 0
	while (i < 4 and self.dice[i] > 0):
	    success = False
	    start = 25-25*movingPlayer
	    stop = 26*movingPlayer - 1
	    for fromPoint in xrange(start, stop, direction):
		toPoint = fromPoint + direction*self.dice[i]
		success = success or self.move(fromPoint,toPoint)==True
		success = success or self.bearOff(fromPoint)==True
		if success:
		    break
	    if not success:
		i += 1

	self.rollDice()

    def rollDice(self):
	self.dice = [random.randint(1,6), random.randint(1,6)]
	if self.dice[0] == self.dice[1]:
	    self.dice = self.dice*2
	else:
	    self.dice.extend([0]*2)

    def move(self, fromPoint, toPoint):
	movingPlayer = self.game.getPlayerIndex(self.game.turn)
	direction = 2*movingPlayer - 1
	if (toPoint-fromPoint) * direction < 0:
	    return "Tried to move the wrong direction"
	if fromPoint == toPoint:
	    return "Tried to move nowhere"
	if fromPoint < 0 or fromPoint > 25 or toPoint < 1 or toPoint > 24:
	    return "Referred to a point that does not exist."
	if int(abs(fromPoint-toPoint)) not in self.dice:
	    return "Move does not match any die"
	if self.points[fromPoint] * direction < 1:
	    return "You have no checkers to move from there"
	if self.points[toPoint] * direction < -1:
	    return "Your opponent has two or more checkers at destination."
	if abs(self.points[25-25*movingPlayer]) > 0 and \
	    fromPoint != 25-25*movingPlayer:
	    return "You have to clear your bar before making other moves"
	#Allow the move
	if self.points[toPoint] * direction == -1:
	    #The opponent is hit
	    self.points[25*movingPlayer] += self.points[toPoint]
	    self.points[toPoint] = 0
	self.points[toPoint] += direction
	self.points[fromPoint] -= direction
	self.dice.remove(int(abs(fromPoint-toPoint)))
	self.dice.append(0)
	return True

    def bearOff(self, fromPoint):
	movingPlayer = self.game.getPlayerIndex(self.game.turn)
        direction = 2*movingPlayer - 1
	lastChecker = 0#distance of current player's furthest checker from home
	for i in xrange(25):
	    distance = i + movingPlayer * (25 - 2 * i)
	    if direction * self.points[i] > 0 and distance > lastChecker:
		lastChecker = distance

        if fromPoint < 1 or fromPoint > 24:
            return "Referred to a point that does not exist."
	if lastChecker > 6:
	    return "Everything must be on your home board before bearing off"
	dieToUse = -1
	distance = fromPoint + movingPlayer * (25- 2*i)
	if distance in self.dice:
	    dieToUse = distance
	if distance == lastChecker and max(self.dice) > distance:
	    dieToUse = int(max(self.dice))
	if dieToUse == -1:
            return "The dice prevent this bear off"
        if self.points[fromPoint] * direction < 1:
            return "You have no checkers to move from there"
        if abs(self.points[25-25*movingPlayer]) > 0 and \
            fromPoint != 25-25*movingPlayer:
            return "You have to clear your bar before making other moves"
        #Allow the move
        self.game.turn.score += 1
        self.points[fromPoint] -= direction
        self.dice.remove(dieToUse)
        self.dice.append(0)
        return True


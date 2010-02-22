import game.base as base
import unittest
import collections
import match
import matchUtils

Match = match.Match

def string_exception(e):
    return ', '.join(str(n) for n in [e.__class__, e])

class TestBaseObjects(unittest.TestCase):

    def setUp(self):
        self.world = base.RectangularGameWorld(10, 10)

    def test_build_world(self):
        self.assertFalse(self.world == None)
        self.assertFalse(self.world.area == None)

    def test_lookup(self):
	k = self.world
	self.assertTrue(isinstance(k.area, collections.defaultdict))
        try:
            k.area[1]
        except Exception, e:
            self.assertTrue(isinstance(e, TypeError), string_exception(e))
        try:
            k.area[(1,2,3)]
        except Exception, e:
            self.assertTrue(isinstance(e, TypeError), string_exception(e))
        try:
            k.area[('a',1)]
        except Exception, e:
            self.assertTrue(isinstance(e, TypeError), string_exception(e))
        try:
            k.area[(11,10)]
        except Exception, e:
            self.assertTrue(isinstance(e, IndexError), string_exception(e))
        e = 10
        try:
            k.area[(0,0)].append(e)
            self.assertTrue(e in k.area[(0,0)])
        except Exception, e:
            self.fail()

class MockPlayer(object):
    """
    An object to receive messages as if it were a player.  This class will
    be used to test the game code without having to rely on all the
    networking code.
    """
    def __init__(self):
        self.messages = [] #A list of messages this player has received
        self.user = "Username"
	self.screenName = "Screenname"
	self.ID = 0

    def writeSExpr(self, message):
        self.messages.append(message)

    def last(self):
        return self.messages[len(self.messages) - 1]


class TestMatchStart(unittest.TestCase):
    def setUp(self):
        self.game = Match(7000, None)
        self.players = [MockPlayer(), MockPlayer()]
        self.game.declareWinner = lambda self: None

    def test_join_game(self):
        """
        Tests Match.addPlayer and Match.nextTurn
        """
        self.assertEqual(self.game.players, [])
        self.game.addPlayer(self.players[0])
        self.assertNotEqual(True, self.game.start())
        self.assertEqual([self.players[0]], self.game.players)
        self.game.addPlayer(self.players[1])
        self.assertEqual(self.players, self.game.players)
        self.assertNotEqual(True, self.game.addPlayer(MockPlayer()))
        self.assertEqual(True, self.game.start())
	if (self.game.myBoard.dice[0] > self.game.myBoard.dice[1]):
            self.assertEqual(self.game.turn, self.players[0])
	    self.game.nextTurn()
        self.assertEqual(self.game.turn, self.players[1])
        self.game.nextTurn()
        self.assertEqual(self.game.turn, self.players[0])

class MockGame(object):
    def __init__(self):
        self.objects = {}

    @matchUtils.requireReferences(str, None, int)
    def wrappedFunction(self, a, b, c):
	return True

class TestRequireReferences(unittest.TestCase):
    def setUp(self):
	self.game = MockGame()
	self.game.objects[0] = "Hello"
	self.game.objects[1] = 3.14159
	self.game.objects[2] = 42

    def test_require_refs(self):
	self.assertEqual(True, self.game.wrappedFunction(0, 1, 2))
	self.assertNotEqual(True, self.game.wrappedFunction(0, 2, 1))
	self.assertNotEqual(True, self.game.wrappedFunction(0, 1, 3))
	self.assertEqual(True, self.game.wrappedFunction(0, 83, 2))

class TestGameLogic(unittest.TestCase):
    def setUp(self):
	self.game = Match(7001, None)
	self.players = [MockPlayer(), MockPlayer(), MockPlayer()]
	self.players[0].screenName = "Bob"
	self.players[1].screenName = "Tod"
	self.players[2].screenName = "Joe"
	self.game.addPlayer(self.players[0], "player")
	self.game.addPlayer(self.players[1], "player")
	self.game.addPlayer(self.players[2], "spectator")


    def test_status_messages(self):
	self.game.start()
	expected = ['ident', [[0, 'Username', 'Bob', 'player'], \
			[1, 'Username', 'Tod', 'player'], \
			[-1, 'Scribe', 'Scribe', 'spectator'], \
			[-1, 'Username', 'Joe', 'spectator']], 7001, 0]
	self.assertEqual(self.players[0].messages[0], expected)
	dice = self.game.myBoard.dice
	expected = ["status", ["game", self.game.turnNum, 0, 0] , \
	    ['ServerBoard', [0, dice[0], dice[1], 0, 0, \
	    0, 2, 0, 0, 0, 0, -5, 0, -3, 0, 0, 0, 5, \
	    -5, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0, -2, 0]]]
	self.assertEqual(expected, self.game.status())

    def test_talk(self):
	self.game.start()
	taunt1 = "Neener-neener."
	self.assertEqual(True, self.game.talk(taunt1))
	expected = ["animations", ["talk", 0, taunt1]]
	self.assertEqual(self.game.animations, expected)

    def test_move(self):
	self.game.start()
	self.game.turn = self.players[0]
	self.game.myBoard.dice = [1, 2, 0, 0]
	self.assertEqual(True, self.game.move(24,23))
	self.assertEqual(True, self.game.move(24,22))
	self.game.nextTurn()
	self.assertEqual(-1, self.game.myBoard.points[23])
	self.assertEqual(-1, self.game.myBoard.points[22])

    def test_auto_move(self):
	self.game.start()
        self.game.turn = self.players[0]
        self.game.myBoard.dice = [1, 2, 0, 0]
        self.game.nextTurn()
        self.assertEqual(-1, self.game.myBoard.points[23])
        self.assertEqual(-1, self.game.myBoard.points[22])

    def test_bear_off(self):
	self.game.start()
	for i in xrange(len(self.game.myBoard.points)):
	    self.game.myBoard.points[i] = 0
	self.game.myBoard.points[19] = 1
	self.game.turn = self.players[1]
	self.game.myBoard.dice = [6,0,0,0]
	self.assertEqual(True, self.game.bearOff(19))

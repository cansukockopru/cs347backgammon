package cs347.backgammon.core.game.players;

/**
 * Contains the identification information about a player.
 */
public class PlayerInfo
{
	private PlayerID playerID;
	private String name;
	private PlayerType playerType;

	// Time remaining
	// Wins/losses?

	/**
	 * Initializes the player name with a blank and everything else as null. 
	 */
	public PlayerInfo()
	{
		name = "";
	}
	
	/**
	 * Copy constructor.
	 * @param toClone The PlayerInfo to copy.
	 */
	public PlayerInfo(PlayerInfo toClone)
	{
		playerID = toClone.playerID;
		playerType = toClone.playerType;
		//Deep copy, not shallow
		name = new String(toClone.name);
	}

	/**
	 * Get the identification enumeration for the player.
	 * @return The player ID.
	 */
	public PlayerID getPlayerID()
	{
		return playerID;
	}

	/**
	 * Set the player ID for this player.
	 * @param playerID The new player ID for this player.
	 */
	public void setPlayerID(PlayerID playerID)
	{
		this.playerID = playerID;
	}

	/**
	 * Retrieve this player's name.  This value appears on the game GUI.
	 * @return The name of this player.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of this player.  This value will appear on the game GUI.
	 * @param name The new name value for this player.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Is this player a local GUI operator or a remote client?
	 * @return The type of player associated with this PlayerInfo.
	 */
	public PlayerType getPlayerType()
	{
		return playerType;
	}

	/**
	 * Set the type of player associated with this PlayerInfo.
	 * @param playerType The type of player associated with this PlayerInfo.
	 */
	public void setPlayerType(PlayerType playerType)
	{
		this.playerType = playerType;
	}

}

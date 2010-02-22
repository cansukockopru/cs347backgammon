///The class implementing gameplay logic.
class AI extends BaseAI
{
  public String username()
  {
    return "Shell AI";
  }
  public String password()
  {
    return "password";
  }
  public boolean run()
  {
    serverBoards[0].move(0,0);
    serverBoards[0].bearOff(0);
    return true;
  }

  public void init() {}
}

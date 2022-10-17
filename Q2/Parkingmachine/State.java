public class State{
  String name;
  double balance;
  double exchange;
  int completion;
  
  public State(String pName, double pBalance, double pExchange, int pCompletion){
    this.name = pName;
    this.balance = pBalance;
    this.exchange = pExchange;
    this.completion = pCompletion;
  }
}
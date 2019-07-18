public class Model{
  public int start(String input){
    if(input.length()==0) return 0;
    int pos = 0;
    if(input.contains("(")){
      pos = input.indexOf("(") - 1;
      if(pos == -1){
        pos = findRB(input)+1;
        if(pos>=input.length()){
          return start(input.substring(1,input.length()-1));
        }
      }
    }else {
      pos = getPos(input);
    }
    if (pos==0) return Integer.parseInt(input);
    return eval(input.substring(0,pos),input.charAt(pos),input.substring(pos+1,input.length()));
  }

  public int findRB(String in){
    int stack = 0;
    for(int i = in.indexOf("(");i<in.length();i++){
      if(in.charAt(i)=='(') stack++;
      if(in.charAt(i)==')') stack--;
      if(stack==0) return i;
    }
    return 0;
  }

  public int getPos(String in){
    if(in.contains("*")){
      return in.indexOf("*");
    }else if(in.contains("/")){
      return in.indexOf("/");
    }else if(in.contains("+")){
      return in.indexOf("+");
    }else if(in.contains("-")){
      return in.indexOf("-");
    }else{
      return 0;
    }
  }

  public int eval(String a,char op, String b){
    System.out.println("EVAL |"+a+"|"+op+"|"+b);
    switch(op){
      case '+': return start(a)+start(b);
      case '-': System.out.println("MINUS");
                return start(a)-start(b);
      case '*': return start(a)*start(b);
      default: return start(a)/start(b);
    }
  }
}

public class Rotor {

    
  private String rotorValues;
  private char startChar;
      
  public Rotor(String v, char c){
      this.rotorValues = new String(v);
      this.startChar = c;
      while(!this.rotate());            
  }
  
  public boolean rotate(){
      //retrieve last char
      String last = rotorValues.substring(rotorValues.length()-1);

      //move last char to front of string
      rotorValues = last.concat(rotorValues.substring(0, rotorValues.length()-1));

      //check: full rotation completed?
      if(last.charAt(0) == startChar){
          return true;
      }

      return false; 
      
  }
  
  public int indexOf(char c){
      int index = -1;

      //loop through rotorValues str until target char is found
      for(int i = 0; i < rotorValues.length(); i++){
          if(rotorValues.charAt(i) == c){
              index = i;
              break;
          }
      }

      return index;
  }

  public char charAt(int idx){
      //% to handle edge cases (idx < 0 || idx > rotorValues.length)
      return rotorValues.charAt(idx%rotorValues.length());
  }
}
  

public class Enigma{

  private String rotorInit[] = {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
      "#EJOTYCHMRWAFKPUZDINSXBGLQV",
      "#BDFHJLNPRTVXZACEGIKMOQSUWY",
      "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
      "#TGOWHLIFMCSZYRVXQABUPEJKND"};


  private Rotor rotors[];
      
  public Enigma(int id1, int id2, int id3, String start){

      rotors = new Rotor[3];
      rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0));
      rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1));
      rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2));
      
  }

  public String decrypt(String message){        
      //TODO
      String decrypt = "";
      char[] toDecrypt = message.toCharArray();
      for(int i = 0; i < toDecrypt.length; i++){
          //retrieve idx of target char in outer rotor
          int idx = rotors[2].indexOf(toDecrypt[i]);
          //retrieve char at same index in middle rotor
          char correspond = rotors[1].charAt(idx);
          //retrieve idx of that char in outer rotor
          idx = rotors[2].indexOf(correspond);
          //retrieve char at that idx in inner rotor
          correspond = rotors[0].charAt(idx);
          //add decrypted char to decrypted string
          decrypt = decrypt.concat(Character.toString(correspond));
          this.rotate();
      }
      return decrypt;
  }
  
  public String encrypt(String message){
      String encrypt = ""; 
      char[] toEncrypt = message.toCharArray();
      for(int i = 0; i < toEncrypt.length; i++){
          //retrieve idx of target char in inner rotor
          int idx = rotors[0].indexOf(toEncrypt[i]);
          //retrieve char at same index in outer rotor
          char correspond = rotors[2].charAt(idx);
          //retrieve idx of that char in middle rotor
          idx = rotors[1].indexOf(correspond);
          //retrieve char at that idx in outer rotor
          correspond = rotors[2].charAt(idx);
          //add encrypted char to encrypted string
          encrypt = encrypt.concat(Character.toString(correspond));
          this.rotate();
      }
      return encrypt;
  }

  private void rotate(){
      if(rotors[0].rotate()){
          if(rotors[1].rotate()){
              rotors[2].rotate();
          }
      }
  }
  
}

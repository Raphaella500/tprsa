/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciphertextsecurity;

import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class Ciphertextsecurity {

    /**
     * @param args the command line arguments1
     
     */
    public static int[] H  = {6,5,2,7,4,1,3,0};
    public static int[] PI = {4,6,0,2,7,3,1,5};
    public static int [] P = {2,0,1,3};
    
    public static void main(String[] args) {
        
      System.out.print("PROGRAMME DE CHIFFREMENT DECHIFFREMENT DE MBUYAMBA \n");

      System.out.print("AUCUNE CLE POUR LE CHIFFREMENT OU DECHIFFREMENT N'A ETE RENSEIGNE, VEUILLEEZ ENTRER UNE CLE SVP \n");
      
      String key = saisie();
      String sk[]=souscles(key);
      System.out.println("la première sous-clé est: " + sk[0]+ " et la deuxième sous-clé est "+ sk[1]);
      
      
      System.out.print("BIENVENU DANS VOTRE SIMILATEUR DE CHIFFREMENT ET DECHIFFREMENT \n");
      
      boolean test = true;
      
      while(test==true){
          System.out.print("Que souhaitez vous faire ? Chiffrer (1) dechiffrer (0). \n");
          Scanner console = new Scanner (System.in);
          int avis = console.nextInt();
          if (avis ==1){
            String cypher=chiffrement(sk);
            System.out.println("le message chiffré est : " + cypher);
            }
          else if (avis ==0){
            String decypher=chiffrement(sk);
            System.out.println("le message déchiffré est : " + decypher );
            }
          else{
            System.out.println("Choix incorrect" );
             }
          System.out.print("Souhaitez vous continuer ? Tapez 1 si oui sinon n'importe quel autre chiffre \n");
          int avis2 = console.nextInt();
          if (avis2 !=1){
              System.out.print("MERCI D'AVOIR UTILISE NOTRE PROGRAMME, BYE A LA PROCHAINE \n");
              test = false;
          }
        
      }
      
     
      
      
          
        
    }
    public static String saisie(){
        String key = "";
        Scanner console = new Scanner (System.in);
        for(int i = 1; i<= 8; i++){
            boolean test=false;
            int c = 0;
            while (test==false){
                System.out.println("entrer l'element numéro "+ i);
                c = console.nextInt();
                test = (c==0)||(c==1);
                if (test==false){
                    System.out.println("Il faut saisir 0 ou 1 seulement s'il vous plait !");
                }  
            }
            key = key + c;
            
        }
        return key;
    }
    
    public static String permutation(String bin,int P[]){

       String new_bin="";
       for(int i=0 ; i < P.length;i++){
           new_bin += bin.charAt(P[i]);
       }
       return new_bin;
    }
    
    public static int[] inversepermutation(int[] P){
        int[] P_1 = new int[P.length];
        for (int j=0; j<P.length; j++){
            for (int i=0;i<P.length;i++){
                if(j==P[i]){
                    P_1[j]=i;
                }
            }
        }
        return P_1;
    }
    
    public static String Ouexclusif(String chaine1, String chaine2){
        String chaine="";

        for (int i = 0 ; i<= chaine1.length()-1; i++){
           if(chaine1.charAt(i)==chaine2.charAt(i)){
               chaine+="0";
           } 
           else{
               chaine+="1";
           }
        
       
    }
         return chaine;
    }
        
    public static String Etlogique (String chaine1, String chaine2){
         String chaine="";
        for (int i = 0 ; i<= chaine1.length()-1; i++){
           if(chaine1.charAt(i)==chaine2.charAt(i)){
               if (chaine1.charAt(i)=='1'){
                chaine+="1";
               }
               else{
                chaine+="0";
               }
           } 
           else{
               chaine+="0";
           }
       
    }
         return chaine;
    }
    
    
    public static String Oulogique (String chaine1, String chaine2){
         String chaine="";
        for (int i = 0 ; i<= chaine1.length()-1; i++){
           if(chaine1.charAt(i)==chaine2.charAt(i)){
               if (chaine1.charAt(i)=='0'){
                chaine+="0";
               }
               else{
                chaine+="1";  
               }
           } 
           else{
               chaine+="1";
           }
    }
         return chaine;
    }
   
    
     public static String[] souscles (String key){
       
        key=permutation(key,H);
        String k1 = key.substring(0,4);
        String k2 = key.substring(4);
        String sk1 = "";
        String sk2 = "";
        sk1=Ouexclusif(k1,k2);
        sk2=Etlogique(k1,k2);
        String[] subkey = new String[2];
        subkey[0]=sk1;
        subkey[1]=sk2;
        return subkey;
    }
     
    public static String chiffrement(String[] sk){
        System.out.print("Saisissez svp le bloc de 8 bits à chiffrer: \n");
        String N=saisie();
        N=permutation(N,PI);
        String G0=N.substring(0,4);
        String D0=N.substring(4);
       
        // Premier round
        String D1=permutation(G0,P);
        D1= Ouexclusif(D1,sk[0]);
        String G1=Oulogique(G0,sk[0]);
        G1=Ouexclusif(D0,G1);
        
        // Deuxième round
        String D2=permutation(G1,P);
        D2= Ouexclusif(D2,sk[1]);
        String G2=Oulogique(G1,sk[1]);
        G2=Ouexclusif(D1,G2);
        
        String cypher = G2+D2;
        
        int[] P_1 = inversepermutation(PI);
        cypher = permutation(cypher,P_1);
        
        return cypher;
        
    }
    public static String dechiffrement ( String []sk){
        
       String C =saisie(); 
       C = permutation(C,PI);
       int[] P_1 = inversepermutation(P);
       String G2 = C.substring(0,4);
       String D2 = C.substring(4);
       // Premier round
       String G1= Ouexclusif(D2, sk[1]);
       G1 = permutation (G1, P_1);
      String D1 = Oulogique(G1, sk [1]);
      D1 = Ouexclusif(D1, G2);
      //Deuxième round
      String G0 = Ouexclusif(D1, sk[0]);
      G0 = permutation(G0, P_1);
      String D0 =Oulogique(G0, sk[0]);
      D0 = Ouexclusif(G1, D0);
      String decypher = G0 + D0;
      int[] PI_1 = inversepermutation (PI);
      decypher = permutation (decypher, PI_1);
      return decypher ;
    }
    }
    


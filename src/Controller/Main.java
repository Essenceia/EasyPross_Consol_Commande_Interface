package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String args[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Commnade_Line cl;
        String s , cl_args[];
        int exit = 0;
        cl = new Commnade_Line();
	// write your code here
        while(exit == 0){
           try{
               System.out.println("Please enter commande :");
               s =br.readLine();
               cl_args = s.split(" ");

               if( cl_args.length > 0) {

                   switch (cl_args[0]) {
                       case "-o":
                       case "--opcode":
                           System.out.println("Opcode");
                           cl.opcode(cl_args);
                           break;
                       case "-h":
                       case "--help":
                           cl.help();
                           break;
                       case "-q": case "--quit":exit =1 ;break;
                       default:
                           System.out.println("Unknown commande type :" +
                                   "-h or --help for help and -o or --opcode to enter an opcode");
                   }
               }else{
                   sleep(200);
               }


           }catch (IOException e ){
               e.printStackTrace();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        }


    }

}

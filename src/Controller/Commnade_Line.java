package Controller;

import java.util.Vector;

public class Commnade_Line {
    public final int OPCODE_INDEX = 1;
    public final int MODULE_NAME = 2;
    private API_IHM api;
    public Commnade_Line(){
        help();
        api = new API_IHM();

    }
    public void help(){
        String s="\033[0;33m";
        s+="List of commands :\n";
        s+= "'-q' exit , usage :[-q] or [--quit]\n";
        s+= "'-h' help , usage : [-h] or [--help]\n";
        s+= "'1' load module , usage : [-o 1 <ModuleName>]\n";
        s+= "'2' simulate for 1 tick, usage : [-o 2]\n";
        s+= "'3' get data on wire, usage : [-o 3 <id> <id> <id> ...]\n";
        s+= "'4' set data on wire , usage : [-o 4 <id>:<data>,<id>:<data>,<id>:<data>, ...]\n";
        s+= "'5' reset data of graph to default values , usage : [-o 5]\n";
        s+= "'6' get absolute path to register file , usage : [-o 6 <id>]\n";
        s+="\n\nNote:<data> is represented in it's boolean for with MSB first and all bit seperated by a '.'\n";
        s+="example:\n0.0.1.1\nrepresents a binary 2 in a 4 bit wide wire\n\n";
        s+="Note: API commande line interface must be launshed \033[0;31m BEFORE \033[0;33m the simulator\n";
        s+="\033[0m";
        System.out.println(s);
    }
    public void opcode(String[] args){
        int opcode;
        Vector<Integer> nvids;
        Vector<Data_Tuple> nvdata;
        try {
            opcode= Integer.parseInt(args[OPCODE_INDEX]);
            System.out.println("opcode recived ::"+opcode);
            switch (opcode){
                case 1:api.APISender(opcode,"",0,args[MODULE_NAME],"",null, null);
                break;
                case 2: api.APISender(opcode,"",0,"","",null,null);
                break;
                case 5:
                    //reset
                    api.APISender(opcode,"",0,"","",null,null);
                break;
                case 3 ://ask for new values of ids
                    nvids = new Vector<>();
                    for (int i = 2 ; i < args.length; i ++){
                        nvids.add( Integer.parseInt(args[i]));
                    }
                    api.APISender(opcode,"",0,"","",nvids,null);

                    break;
                case 4:
                    //set data
                    nvdata = new Vector<>();
                    String tuples[] = args[2].split(",");
                    System.out.println("Args 2 "+args[2]+" split :");
                    for (String s :tuples
                         ) {
                        System.out.println("- "+s);
                    }
                    String pairs[];
                    for( int i = 0; i < tuples.length; i ++){
                        pairs = tuples[i].split(":");
                        if(pairs.length==2) {
                            nvdata.add(new Data_Tuple(pairs[0], pairs[1]));
                        }else{
                            System.out.println("Error :: expecting 2 ellements sperated by : gotten "+args[i]);
                        }
                        api.APISender(opcode,"",0, "","",null,nvdata);
                    }
                    break;

                case 6:
                    //ask file path
                    api.APISender(opcode,"",Integer.parseInt(args[2]), "","",null,null);
                    break;
                    default:api.APISender(opcode,"",0,"","",null,null);
                    break;
            }
        } catch (NumberFormatException e) {
            System.err.println("Argument opcode" + args[OPCODE_INDEX] + " must be an integer.");
            System.exit(1);
        }
    }
}

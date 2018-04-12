package Controller;


import java.util.Vector;


public class Helper_Data_Handler {
    /**
     * Parse_to_String
     * @param ids
     * @param newValues
     * @return
     */
    public static String Parse_to_String(Vector<Integer> ids, Vector<String> newValues ){
        String retVal = "";
        int maxit = Math.min(ids.size(),newValues.size());
        for(int i = 0 ; i < maxit; i ++){
            retVal+= ids.toString()+":"+newValues;
            if(i != maxit-1)retVal+=",";
        }
        return retVal;
    }
    public static String Parse_to_String(Vector<Data_Tuple> dataVector){
        String retVal = "";
        Data_Tuple current;
        int dataSize = dataVector.size();

        for (int i = 0; i < dataSize; i++) {

            current = dataVector.get(i);
            retVal+= current.getId().toString();
            retVal+=":";
            retVal+=current.getStringValues();
            if(i != dataSize-1)retVal+=",";

        }
        return retVal;
    }

    /**
     * parseFromStrin
     *
     * This function is used to parse the data from a recived message
     * example:: <id>:<data>,<id>:<data>,<id>:<data>
     * @param recMsg
     * @return
     */
    public static Vector<Data_Tuple> parseIdAndDataFromString(String recMsg){
        Vector<Data_Tuple> retVec = new Vector<>();
        String inddata[];
        System.out.println("Extracting data from ::"+recMsg);
        String datatuples[] = recMsg.split(",");
        for (String tuple:datatuples
             ) {
            inddata = tuple.split(":");
            if(inddata.length==2) {
                retVec.add(new Data_Tuple(inddata[0], inddata[1]));
            }else{
                System.out.println("Error :: expected ellements after split 2 gotten "+inddata.length);
            }
        }
        return retVec;
    }
    public static String creatIdString(Vector<Integer> Idwire){
        String nvmsg="";
        for (int i = 0; i < Idwire.size(); i++) {
            nvmsg+= color3(Idwire.get(i).toString());
            if(i!= Idwire.size() - 1)nvmsg+=",";
        }
        return nvmsg;
    }
    public static Vector<Integer> parseIdString(String idString) {
        Vector<Integer> retId = new Vector<>();
        String nvmsg[] = idString.split(",");
        for (String id : nvmsg
                ) {
            retId.add(Integer.parseInt(id));
        }
        return retId;
    }
    public static void printDataTupleArray(Vector<Data_Tuple> toprint){
        String val= "Values recived :\n";
        for (Data_Tuple data:toprint
             ) {
            val+= "id "+data.getId()+"::"+color2(data.getStringValues())+" \n";
        }
    }
    public static String color2(String s){
        return "\033[0;36m"+s+"\033[0;33m";
    }
    public static String color3(String s){
        return "\033[0;32m"+s+"\033[0;33m";
    }

    /**
     * Print to RED
     * @param s
     * @return
     */
    public static String color1(String s){
        return "\033[0;31m"+s+"\033[0;33m";
    }
    public static void colreset(){
        System.out.print("\033[0;33m");
    }
}

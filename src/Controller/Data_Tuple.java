package Controller;

import java.util.Vector;

public class Data_Tuple{
    private Integer id;
    private String stringValues;
    private Vector<Boolean> boolValues;
    public Data_Tuple(Integer id, String newValue){
        this.id = id;
        this.stringValues = newValue;
        boolValues = new Vector<>();
        stringToBoolVector();
    }
    public Data_Tuple(String id, String newValue){
        this.id = Integer.parseInt(id);
        this.stringValues = newValue;
        boolValues = new Vector<>();
        stringToBoolVector();
    }
    public Data_Tuple(Integer id, Vector<Boolean> newValue){
        this.id = id;
        this.boolValues = newValue;
        boolVectorToString();
    }
    private void boolVectorToString(){
        this.stringValues="";
        int boolsize = this.boolValues.size();
        for (int i = 0; i <boolsize ; i++) {
            if(this.boolValues.get(i) ==Boolean.TRUE){
                this.stringValues+="1";
            }else{
                this.stringValues+="0";
            }
            if(i != boolsize -1 )this.stringValues+= ".";
        }
        System.out.println("boolVectorToString value :"+this.stringValues);


    }
    private void stringToBoolVector(){
        String boolvalues[] = this.stringValues.split("\\.");
        for (String bool: boolvalues
                ) {
            if(bool.equals("0")){
                this.boolValues.add(false);
            }else this.boolValues.add(true);
        }
    }

    public Vector<Boolean> getBoolValues() {
        return boolValues;
    }

    public Integer getId() {
        return id;
    }

    public String getStringValues() {
        return stringValues;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralkaunixml;

import java.util.ArrayList;

/**
 *
 * @author rsys
 */
public class StringTree {
    String name;
    boolean isValue = false;
    ArrayList<StringTree> stringList = new ArrayList<>(); 
    
    public StringTree(String name){
        this.name = name;
    }
    
    public void setIsValue(boolean is){
        isValue = is;
    }
    
    public boolean getIsValue(){
        return isValue;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<StringTree> getStringList(){
        return stringList;
    }
    
    public void setStringList( ArrayList<StringTree> list){
       stringList  = list;
    }
}

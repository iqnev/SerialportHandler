package com.fortranbg.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TempTest {

  public static void main(String[] args) {
    
    SerialportHandler sHandler = SerialportHandler.getSingleton();
    
    
    
    ArrayList<String> ports = sHandler.listSerialPorts();
    
    ports.forEach(s-> System.out.println(s));
   
    
    
    try {
      sHandler.openPort("/dev/cu.Bluetooth-Incoming-Port");
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}

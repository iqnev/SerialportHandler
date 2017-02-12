package com.fortranbg.serial.handle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HowToUse {

  public static void main(String[] args) {
    
    //Gets an instance
    SerialportHandler sHandler = SerialportHandler.getSingleton();
 
    //Gets all ports
    ArrayList<String> ports = sHandler.listSerialPorts();
    
    //Prints the ports
    ports.forEach(s-> System.out.println(s));
   
    try {
      
      //Opens the port by name
      sHandler.openPort("/dev/cu.Bluetooth-Incoming-Port");
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

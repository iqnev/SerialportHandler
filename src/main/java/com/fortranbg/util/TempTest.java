package com.fortranbg.util;

import java.util.ArrayList;
import java.util.Arrays;

public class TempTest {

  public static void main(String[] args) {
    ArrayList<String> ports = SerialportHandler.listSerialPorts();
    
    ports.forEach(s-> System.out.println(s));
  }

}

/**
 * Copyright (c) 2017 Ivelin Yanev <bgfortran@gmail.com>.
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at your option) any later version. 
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * 
 */
package com.fortranbg.serial.handle.testing;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fortranbg.serial.handle.SerialportHandler;


/**
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since  2017 
 *
 */
public class SerialportHandlerTest {

  private static SerialportHandler sHandler;
  
  @BeforeClass
  public static void init() {
    sHandler = SerialportHandler.getSingleton();
    
    assertNotNull("The serial instance is NULL", sHandler);
  }
  
  @Test
  public void testListSerialPorts() {
    ArrayList<String> ports = sHandler.listSerialPorts(); 
    
    assertNotNull("The list with serial ports is NULL", ports);
    assertFalse("The list with serial ports is empty", ports.isEmpty());
  }
  
  @Test
  public void testConnect() {
    assertTrue("This is test case", true);
  
  }
  
  @Test
  public void testDisconnect() {
    //TODO
  }
  
  @Test
  public void testCheckListSerialPorts() {
    //TODO
  }
  
  @Test
  public void testReadBlocked() {
    
  }
  
  
}

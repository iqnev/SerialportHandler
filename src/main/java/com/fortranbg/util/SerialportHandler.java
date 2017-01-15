/**
 * Copyright (c) 2017 Ivelin Yanev <>.
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
package com.fortranbg.util;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;



/**
 * @author Ivelin Yanev
 * @since  2017 
 *
 */
public final class SerialportHandler {
  
  /**
   * An explicit constructor.
   */
  private SerialportHandler() {
    throw new AssertionError();
  }
  
  /**
   * 
   * @return
   */
  public static ArrayList<String> listSerialPorts() {
    Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();
    
    ArrayList<String> ports = new ArrayList<String>();
    while (portEnum.hasMoreElements()) {
        CommPortIdentifier port = (CommPortIdentifier) portEnum.nextElement();
        if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
          ports.add(port.getName());
        }
    }
    return ports;
  }
  
}

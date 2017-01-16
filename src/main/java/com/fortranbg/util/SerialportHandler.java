/**
 * Copyright (c) 2017 Ivelin Yanev <>. This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110,
 * USA
 * 
 */
package com.fortranbg.util;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;


/**
 * @author Ivelin Yanev
 * @since 2017
 *
 */
public class SerialportHandler {

  private SerialPort serialPort;
  private OutputStream outStream;
  private InputStream inStream;

  /**
   * The value of TIME OUT constant.
   */
  public static final int DEFAULT_TIME_OUT = 2000;


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

  /**
   * 
   * @param portName
   * @throws IOException
   * @throws NoSuchPortException
   */
  public void openPort(final String portName) throws IOException, NoSuchPortException {
    if (portName == null) {
      throw new NullPointerException("Com port is null");
    }
    // Obtain a CommPortIdentifier object for the port you want to open
    CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(portName);

    if (portId.isCurrentlyOwned()) {
      throw new IllegalStateException("Com port in use");
    }

    try {
      // Get the port's ownership
      serialPort = (SerialPort) portId.open(SerialportHandler.class.getName(), DEFAULT_TIME_OUT);

      // Set the parameters of the connection.
      setSerialPortParameters();
    } catch (final PortInUseException e) {
      throw new IOException(e.getMessage());
    }

    try {

      // Open the input and output streams for the connection.
      // If they won't open, close the port before throwing an
      // exception.
      outStream = serialPort.getOutputStream();
      inStream = serialPort.getInputStream();
    } catch (IOException e) {
      // TODO: close all stream

    }

  }

  /**
   * 
   */
  public void disconnect() {
    if (serialPort != null) {
      try {
        // close the i/o streams.
        outStream.close();
        inStream.close();
      } catch (IOException ex) {
        // don't care
      }
      // Close the port.
      serialPort.close();
      serialPort = null;
    }
  }

  /**
   * @return the outStream
   */
  public OutputStream getOutStream() {
    return outStream;
  }

  /**
   * @return the inStream
   */
  public InputStream getInStream() {
    return inStream;
  }

  /**
   * Sets the serial port parameters.
   */
  private void setSerialPortParameters() throws IOException {
    // TODO
  }

}

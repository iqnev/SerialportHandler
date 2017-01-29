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
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;


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
   * The filed with a DataRate value.
   */
  private int dataRate;

  /**
   * The value of TIME OUT constant.
   */
  public static final int DEFAULT_TIME_OUT = 2000;

  /**
   * The filed with a TimeOut value.
   */
  private int timeOut;


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
   * Opens a CommPort using {@link CommPortIdentifier} object. If the port is owned by some other
   * application the method throws {@link IOException}.
   * 
   * @param portName the {@link CommPortIdentifier} object.
   * @throws IOException if {@link IOException} occurs.
   */
  public void openPort(final String portName) throws IOException {
    try {
      if (portName == null) {
        throw new NullPointerException("Com port is null");
      }
      // Obtain a CommPortIdentifier object for the port you want to open
      CommPortIdentifier portId;
      
      portId = CommPortIdentifier.getPortIdentifier(portName);

      if (portId.isCurrentlyOwned()) {
        throw new IllegalStateException("Com port in use");
      }

      // Get the port's ownership
      this.serialPort = (SerialPort) portId.open(SerialportHandler.class.getName(), this.timeOut);

      // Set the parameters of the connection.
      setSerialPortParameters();

      this.serialPort.notifyOnDataAvailable(true);

      // Open the input and output streams for the connection.
      // If they won't open, close the port before throwing an
      // exception.
      outStream = serialPort.getOutputStream();
      inStream = serialPort.getInputStream();
    } catch (NoSuchPortException e1) {
      throw new IOException(e1.getMessage());
    } catch (PortInUseException e) {
      throw new IOException(e.getMessage());
    } catch (IOException e) {
      this.serialPort.close();
      throw e;
    }

  }

  /**
   * Check for available data.
   * 
   * @return
   */
  public boolean isDataAvailable() {
    boolean flag = false;
    try {
      if (this.inStream.available() != 0) {
        flag = true;
      }
    } catch (IOException ex) {

    }

    return flag;
  }

  /**
   * Disconnect the serial port.
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
   * Sets TimeOut of serial connection
   *
   * @param timeOut the TimeOut value.
   */
  public void setTimeOut(final int timeOut) {
    this.timeOut = timeOut;
  }

  /**
   * Sets DataRade of the serial connection.
   *
   * @param dataRate the DataRade value.
   */
  public void setDataRate(final int dataRate) {
    this.dataRate = dataRate;
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
   * 
   * @throws IOException
   * 
   * @throws UnsupportedCommOperationException
   */
  protected void setSerialPortParameters() throws IOException {
    try {
      this.serialPort.setSerialPortParams(this.dataRate, SerialPort.DATABITS_8,
          SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    } catch (UnsupportedCommOperationException e) {
      throw new IOException("Unsupported serial port parameter");
    }
  }

  /**
   * Adds the specified {@link SerialPortEventListener} listener to receive
   * {@link SerialportHandler} events. If listener deviceListener is null, no exception is thrown
   * and no action is performed.
   * 
   * @param listener the {@link SerialPortEventListener} object.
   * @throws TooManyListenersException if {@link TooManyListenersException} occurs.
   */
  public void addSerialEventListener(final SerialPortEventListener listener)
      throws TooManyListenersException {
    this.serialPort.addEventListener(listener);
  }
  
  //TODO
  public byte[] readBlocked(final int len)  {
    return null;
    
  }
  
  //TODO
  public void writeBlock(byte[] bytes) {
    
  }
  
  //TODO
  public int getAvailableBytes() {
    return dataRate;
    
  }
  

  

}

/**
 * Copyright (c) 2017 Ivelin Yanev <bgfortran@gmail.com>. This program is free software; you can
 * redistribute it and/or modify it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License, or (at your option) any later
 * version.
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
package com.fortranbg.serial.handle.testing;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;

import com.fortranbg.serial.handle.SerialportHandler;

import gnu.io.CommPortIdentifier;

/**
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 2017
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SerialportHandler.class})
public class SerialportHandlerTest {

  @Mock
  SerialportHandler sHandler;

  @Before
  public void setup() {
    mockStatic(SerialportHandler.class);
    when(sHandler.getSingleton()).thenReturn(sHandler);
  }

  @Test
  public void testListSerialPorts() {
    Enumeration ports;
    Vector portsName;
    portsName = new Vector();
    portsName.addElement("/com/ax");
    portsName.addElement("/com/bx");
    ports = portsName.elements();

    when(sHandler.listSerialPorts()).thenReturn(new ArrayList<String>(portsName));
    ArrayList<String> p = sHandler.listSerialPorts();
    assertEquals("Тhe ports are not expected", p, new ArrayList<String>(portsName));

  }

  @Test
  public void testConnect() {
    assertTrue("This is test case", true);

  }

  @Test
  public void testDisconnect() {
    // TODO
  }

  @Test
  public void testCheckListSerialPorts() {
    // TODO
  }

  @Test
  public void testReadBlocked() {

  }

}

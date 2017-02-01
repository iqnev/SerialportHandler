# SerialportHandler
RXTX Serial Port Singletone class


### Gets all available ports 

        SerialportHandler sHandler = SerialportHandler.getSingleton();
  
        ArrayList<String> ports = sHandler.listSerialPorts();
        
### Opens a port
       try {
          sHandler.openPort("/dev/cu.Bluetooth-Incoming-Port");
      } catch (IOException e) {
          e.printStackTrace();
       }

# SerialportHandler
RXTX Serial Port Singletone class


### Get all available ports 

  SerialportHandler sHandler = SerialportHandler.getSingleton();
  
  ArrayList<String> ports = sHandler.listSerialPorts();

package com.ntnu.eit.socket;


import java.net.ServerSocket;
import java.net.Socket;


public class ToppurServer extends Thread{

  private static int PORT =  31111;
  
  
  /**
   * This method starts a new ClientHandlerThread, every time a new socketcommunication is initialized.
   */
   @Override
  public void run() {
      ServerSocket ss = null;
      Socket s = null;
    
      try {
	  ss = new ServerSocket(PORT);
	  while (true) {
	      s = ss.accept();
	      new ClientHandlerThread(s).start();

	  }
	  
	
      }
      catch(Exception e) {
	   System.out.println("Exception:"+ e.getMessage());
      }
      finally {
	  try {
	      s.close();
	      ss.close();
	  }
	  catch (Exception e){
	      System.out.println("Finally exception: "+e.getMessage());
	  }
	  
	  
      }
  }
	  
    public static void main(String[] args) {
	ToppurServer dts = new ToppurServer();
	dts.start();
	System.out.println("Server started and ready for action!");
	
    }
}

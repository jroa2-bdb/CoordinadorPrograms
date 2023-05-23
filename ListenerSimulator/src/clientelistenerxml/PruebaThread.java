/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clientelistenerxml;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DARIAS3
 */
public class PruebaThread {

    public static void main(String [] args){

      int iscMaxCalls=10;
      ThreadGroup tg = new ThreadGroup ("subgroup 1");

      ArrayList<ThreadTest> threadArray = new ArrayList<ThreadTest>();

      System.out.println("fecha1: "+new Date());

        for (int i = 0; i < iscMaxCalls; i++) {
            ThreadTest threadTest = new ThreadTest(tg, "thread-"+i);
            threadTest.start();
            //seteo
            threadArray.add(threadTest);
        }

     

      while(tg.activeCount ()>0){
      }

      System.out.println("fecha2: "+new Date());
      
    }

}

class ThreadTest extends Thread{

    public ThreadTest(ThreadGroup group, String name) {
        super(group, name);
    }

    private String request;
    
    private String response;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    @Override
    public void run(){

        request="request de prueba...";

        try {
            sleep(7000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        response="respuesta correcta!";
    }

    

}

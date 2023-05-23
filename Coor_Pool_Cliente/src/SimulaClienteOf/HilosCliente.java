package SimulaClienteOf;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;

import java.net.Socket;

public class HilosCliente extends Thread{
   
   Socket s; 
   int cantidad;
    long tini, tfin, ttotal;
   
    public HilosCliente(Socket s, int cantidad){
        this.s=s;
        this.cantidad=cantidad;
        try{
        DataInputStream entrada = new DataInputStream(s.getInputStream());
         
            System.out.println("lectura 1");
                short leng = entrada.readShort();
            //if(leng !=16946){
                System.out.println("lectura 2 " + leng);
                byte[] trama = new byte[leng];
                System.out.println("lectura 3");
                entrada.read(trama);
                //entrada.read(trama);
                            
                System.out.println("lectura 4");
            System.out.println(new String(trama));//}
           
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void run(){
        tini=System.currentTimeMillis();
        DataOutputStream mensaje;

        DataInputStream entrada;

        //creamos el flujo de datos por el que se enviara un mensaje
        try{
        
        String men = null;
        //enviamos el mensaje
        File file = new File("Transaccion");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        while ((men = reader.readLine()) != null){
        int i=0;
            while(i<cantidad){
                mensaje = new DataOutputStream(s.getOutputStream());
                entrada = new DataInputStream(s.getInputStream());
                byte []datos = men.getBytes();
                System.out.println(datos.length);
                mensaje.writeShort(datos.length);
                mensaje.write(datos,0,datos.length-2);
                mensaje.flush();
                
                System.out.println("lectura 1");
                    short leng = entrada.readShort();
                System.out.println("Primer shor " +leng);
                    //leng = entrada.readShort();
                //if(leng !=16946){
                    System.out.println("lectura 2 " + leng);
                    byte[] trama = new byte[leng];
                    System.out.println("lectura 3");
                    entrada.read(trama);
                    //entrada.read(trama);
                                
                    System.out.println("lectura 4");
                System.out.println(new String(trama)+""+ s.getPort());//}
                i++;
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        tfin=System.currentTimeMillis();
        ttotal=tfin-tini;
        System.out.println("tiempo en mili "+ttotal+" de la oficina: "+ s.getPort());
    }
}

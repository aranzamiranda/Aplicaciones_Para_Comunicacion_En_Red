/* Práctica 1: Construcción de un servicio de transferencia de archivos utilizando sockets orientados a conexión.
 * Aranza Miranda Montellano
 * 3CM3
 * Aplicaciones para Comunicaciones en Red
 */
package practica1;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
/**
 *
 * @author chiky
 */
public class ServidorArchivos {
    ServerSocket servidor;
    Socket cliente;
    InputStream entrada;
    OutputStream salida;
    DataInputStream datosentrada;
    DataOutputStream datossalida;
    // para la escritura del archivo
    FileOutputStream escrituraarchivo;
    
    public ServidorArchivos(){
        try{
            servidor = new ServerSocket(1028);
            System.out.println("Servidor activo en el puerto 1028");
                cliente = servidor.accept();
                entrada = cliente.getInputStream();
                salida = cliente.getOutputStream();
                datosentrada = new DataInputStream(entrada);
                datossalida = new DataOutputStream(salida);
                // leer el tamaño del archivo que mando el cliente
                long size = datosentrada.readLong();
                System.out.println("Tamaño: "+size);
                escrituraarchivo = new FileOutputStream("C:\\Users\\chiky\\OneDrive\\Documentos\\NetBeansProjects\\Practica1\\archivosSubidos\\archivoServidor.txt");
                int bytes=0;
                int o = (int)size; //esta variable almacena el casteo del tamaño del archivo
                byte buffer[]=new byte[o];
                while(size >0 && (bytes=datosentrada.read(buffer, 0, buffer.length))!=-1){
                    // escribir en archivo del lado del servidor
                    escrituraarchivo.write(buffer,0,bytes);// da salida al archivo "local"
                    size -= bytes;
                    System.out.println("Tamaño restado: "+size);
                }
                escrituraarchivo.close();
                cliente.close();
                
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public static void main(String args[]){
        ServidorArchivos se = new ServidorArchivos();
    }
    
}

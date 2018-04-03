package file;

import domain.Vehicle;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco y Maria
 */
public class VehicleFile {
    //atributos
    public RandomAccessFile randomAccessFile;
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize;//tamanno del registro
    private String myFilePath;//ruta
    
    //constructor
    public VehicleFile(File file) throws IOException{
        //almaceno la ruta
        myFilePath = file.getPath();
        
        //indico el tamanno m'aximo
        this.regSize = 140;
        
        //una validaci'on sencilla
        if(file.exists() && !file.isFile()){
            throw new IOException(file.getName() + " is an invalid file");
        }else{
            //crear la nueva instancia de RAF
            randomAccessFile = new RandomAccessFile(file, "rw");
            
            //necesitamos indicar cuantos registros tiene el archivo
            this.regsQuantity = 
                    (int)Math.ceil((double)randomAccessFile.length() / (double)regSize);
        }
    }//end method
    
    //MUY IMPORTANTE cerrar nuestros archivos
    public void close() throws IOException{
        randomAccessFile.close();
        
    }
    
    //indicar la cantidad de registros de nuestro archivo
    public int fileSize(){
        return this.regsQuantity;
    }
    
    //insertar un nuevo registro en una posici'on espec'ifica
    public boolean putValue(int position, Vehicle vechile) throws IOException{
        //primero: verificar que sea v'alida la inserci'on
        if(position < 0 && position > this.regsQuantity){
            System.err.println("1001 - Record position is out of bounds");
            return false;
        }else{
            if(vechile.sizeInBytes() > this.regSize){
                System.err.println("1002 - Record size is out of bounds");
                return false;
            }else{
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(vechile.getName());
                randomAccessFile.writeInt(vechile.getYear());
                randomAccessFile.writeFloat(vechile.getMileage());
                randomAccessFile.writeBoolean(vechile.isAmerican());
                randomAccessFile.writeInt(vechile.getSerie());
                return true;
            }
        }
    }//end method
    
    //insertar al final del archivo
    public boolean addEndRecord(Vehicle vechile) throws IOException{
        boolean success = putValue(this.regsQuantity, vechile);
        if(success){
            ++this.regsQuantity;
        }
        return success;
    }
    
    //obtener un estudiante
    public Vehicle getVechile(int position) throws IOException{
        //validar la posici'on
        if(position >= 0 && position <= this.regsQuantity){
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);
            
            //llevamos a cabo la lectura
            Vehicle vechileTemp = new Vehicle();
            vechileTemp.setName(randomAccessFile.readUTF());
            vechileTemp.setYear(randomAccessFile.readInt());
            vechileTemp.setMileage(randomAccessFile.readFloat());
            vechileTemp.setAmerican(randomAccessFile.readBoolean());
            vechileTemp.setSerie(randomAccessFile.readInt());
            
            if(vechileTemp.getName().equalsIgnoreCase("deleted")){
                return null;
            }else{
                return vechileTemp;
            }
        }else{
            System.err.println("1003 - position is out of bounds");
            return null;
        }
    }//end method


    public List<Vehicle> getAllVehicles() throws IOException {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for (int i = 0; i < regsQuantity; i++) {
            Vehicle temp = getVechile(i);
            if (temp != null) {
                vehicles.add(temp);
            } // if
        } // for

        return vehicles;

    } // getAllVehicles
       
  

    public boolean deleteVehicle(int serie) throws IOException {
        Vehicle vehicle;
        // busqueda del registro a eliminar
        for (int i = 0; i < regsQuantity; i++) {
            vehicle = this.getVechile(i);
            if (vehicle != null) {
                if (vehicle.getSerie()== serie) {
                    vehicle.setName("deleted");
                    return putValue(i, vehicle);
                } // if
            }
        } // for
        return false;
    } // deleteVehicle

}//end class

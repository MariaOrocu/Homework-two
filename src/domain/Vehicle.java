package domain;

/**
 *
 * @author Marco y Maria
 */
public class Vehicle {

    //Atributos
    private String name;
    private int year;
    private float mileage;
    private boolean american;
    private int serie;

    //constructores
    public Vehicle() {
        this.name = "";
        this.year = 0;
        this.mileage = 0;
        this.american = false;
        this.serie = 0;
    }

    public Vehicle(String name, int year, float mileage, boolean american, int serie) {

        this.name = name;
        this.year = year;
        this.mileage = mileage;
        this.american = american;
        this.serie = serie;
    }

    //métodos accesores
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public boolean isAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Nombre: " + name
                + "\t Año: " + year
                + "\t Kilometrage: " + mileage
                + "\t Americano: " + american
                + "\t Serie: " + serie;
    }

    //tamaño de las variables
    public int sizeInBytes() {
        return this.getName().length() * 2 + 4 + 4 + 2 + 4;
    }

}//end class


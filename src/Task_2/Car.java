package Task_2;

class Car {
    private int id;
    private String brandOfCar;
    private String modelOfCar;
    private int yearOfCar;
    private String colorOfCar;
    private double priceOfCar;
    private int registrationNumberOfCar;

    //region  Constructor "Car"
    public Car(int id, String brandOfCar, String modelOfCar, int yearOfCar, String colorOfCar, double priceOfCar, int registrationNumberOfCar) {
        this.id = id;
        this.brandOfCar = brandOfCar;
        this.modelOfCar = modelOfCar;
        this.yearOfCar = yearOfCar;
        this.colorOfCar = colorOfCar;
        this.priceOfCar = priceOfCar;
        this.registrationNumberOfCar = registrationNumberOfCar;
    }
    //endregion

    //region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandOfCar() {
        return brandOfCar;
    }

    public void setBrandOfCar(String brandOfCar) {
        this.brandOfCar = brandOfCar;
    }

    public String getModelOfCar() {
        return modelOfCar;
    }

    public void setModelOfCar(String modelOfCar) {
        this.modelOfCar = modelOfCar;
    }

    public int getYearOfCar() {
        return yearOfCar;
    }

    public void setYearOfCar(int yearOfCar) {
        this.yearOfCar = yearOfCar;
    }

    public String getColorOfCar() {
        return colorOfCar;
    }

    public void setColorOfCar(String colorOfCar) {
        this.colorOfCar = colorOfCar;
    }

    public double getPriceOfCar() {
        return priceOfCar;
    }

    public void setPriceOfCar(int priceOfCar) {
        this.priceOfCar = priceOfCar;
    }

    public int getRegistrationNumberOfCar() {
        return registrationNumberOfCar;
    }

    public void setRegistrationNumberOfCar(int registrationNumberOfCar) {
        this.registrationNumberOfCar = registrationNumberOfCar;
    }
    //endregion

    //region toString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("Mid=").append(id);
        sb.append(", brand='").append(brandOfCar).append('\'');
        sb.append(", model='").append(modelOfCar).append('\'');
        sb.append(", year=").append(yearOfCar);
        sb.append(", color='").append(colorOfCar).append('\'');
        sb.append(", price=").append(priceOfCar);
        sb.append(", registerNumber=").append(registrationNumberOfCar);
        sb.append('}');
        return sb.toString();
    }
    //endregion
}
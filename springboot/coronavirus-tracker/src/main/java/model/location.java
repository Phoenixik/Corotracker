package model;

public class location {


    private String state;
    private String Country;
    private int latestconfirmedcases;
    private int diffdaycases;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getLatestconfirmedcases() {
        return latestconfirmedcases;
    }

    public void setLatestconfirmedcases(int latestconfirmedcases) {
        this.latestconfirmedcases = latestconfirmedcases;
    }

    public int getDiffdaycases() {
        return diffdaycases;
    }

    public void setDiffdaycases(int diffdaycases) {
        this.diffdaycases = diffdaycases;
    }

    @Override
    public String toString() {
        return "location{" +
                "state='" + state + '\'' +
                ", Country='" + Country + '\'' +
                ", latestconfirmedcases=" + latestconfirmedcases +
                ", diffdaycases=" + diffdaycases +
                '}';
    }
}

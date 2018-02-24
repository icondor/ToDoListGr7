package ir;

public class IR {
    private String intrebare;
    private String raspuns;

    public IR(String intrebare, String raspuns) {
        this.intrebare = intrebare;
        this.raspuns = raspuns;
    }

    public IR() {}

    public String getIntrebare() {
        return intrebare;
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    public String getRaspuns() {
        return raspuns;
    }

    public void setRaspuns(String raspuns) {
        this.raspuns = raspuns;
    }


}
package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Guess {
    private String name;
    private String celular;
    private Genre genre;
    private Date date;
    private String direction;
    private boolean terminos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isTerminos() {
        return terminos;
    }

    public void setTerminos(boolean terminos) {
        this.terminos = terminos;
    }

    @Override
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return getName() + ";" + getCelular()  + ";" + getGenre() + ";" +
                sdf.format(this.date) + ";"+ getDirection() +
                ";" + (isTerminos() ? "1" : "0");
    }
}

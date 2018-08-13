/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Örs
 */
public class Tagok {
    
    private int idtag;
    private String vnev;
    private String knev;
    private String email;

    public Tagok() {
    }

    public Tagok(int idtag, String vnev, String knev, String email) {
        this.idtag = idtag;
        this.vnev = vnev;
        this.knev = knev;
        this.email = email;
    }

    public Tagok(String vnev, String knev, String email) {
        this.vnev = vnev;
        this.knev = knev;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: "+idtag+" Név: " + vnev +" "+ knev + ", Email: " + email;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdtag() {
        return idtag;
    }

    public void setIdtag(int idtag) {
        this.idtag = idtag;
    }

    public String getVnev() {
        return vnev;
    }

    public void setVnev(String vnev) {
        this.vnev = vnev;
    }

    public String getKnev() {
        return knev;
    }

    public void setKnev(String knev) {
        this.knev = knev;
    }
    
    
}

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
public class Konyvek {
    
    private Tagok tag;
    private int id;
    private int idtag;
    private String cim;
    private String szerzoknev;
    private String szerzovnev;
    private int oldalszam;

    public Konyvek() {
    }

    public Konyvek(int id, int idtag, String cim, String szerzoknev, String szerzovnev, int oldalszam) {
        this.id = id;
        this.idtag = idtag;
        this.cim = cim;
        this.szerzoknev = szerzoknev;
        this.szerzovnev = szerzovnev;
        this.oldalszam = oldalszam;
    }

    public Konyvek(int idtag, String cim, String szerzoknev, String szerzovnev, int oldalszam) {
        this.idtag = idtag;
        this.cim = cim;
        this.szerzoknev = szerzoknev;
        this.szerzovnev = szerzovnev;
        this.oldalszam = oldalszam;
    }

    public Konyvek(String cim, String szerzoknev, String szerzovnev, int oldalszam) {
        this.cim = cim;
        this.szerzoknev = szerzoknev;
        this.szerzovnev = szerzovnev;
        this.oldalszam = oldalszam;
    }

    public Konyvek(int id, int idtag) {
        this.id = id;
        this.idtag = idtag;
    }

    public Konyvek(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return "Kivette: " +tag.getVnev()+" "+tag.getKnev() 
                + ", Szerző: " + szerzovnev + szerzoknev + ", Könyv címe: " + cim + ", Oldalszam: " + oldalszam;
    }

//    @Override
//    public String toString() {
//        return "Szerző: " + szerzovnev +" "+ szerzoknev + ", Könyv címe: " + cim + ", Oldalszam: " + oldalszam;
//    }
    
    
    public int getOldalszam() {
        return oldalszam;
    }

    public void setOldalszam(int oldalszam) {
        this.oldalszam = oldalszam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtag() {
        return idtag;
    }

    public void setIdtag(int idtag) {
        this.idtag = idtag;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getSzerzoknev() {
        return szerzoknev;
    }

    public void setSzerzoknev(String szerzoknev) {
        this.szerzoknev = szerzoknev;
    }

    public String getSzerzovnev() {
        return szerzovnev;
    }

    public void setSzerzovnev(String szerzovnev) {
        this.szerzovnev = szerzovnev;
    }

    public Tagok getTag() {
        return tag;
    }

    public void setTag(Tagok tag) {
        this.tag = tag;
    }
    
}

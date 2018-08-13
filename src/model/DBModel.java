/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Örs
 */
public class DBModel implements IModel {

    private Connection conn;
    private PreparedStatement getKonyv;
    private PreparedStatement getAllTag;
    private PreparedStatement getAllKonyv;
    private PreparedStatement getTagokKivettKonyv;
    private PreparedStatement addKonyv;
    private PreparedStatement updateKonyv;
    private PreparedStatement removeKonyv;
    private PreparedStatement getTag;
    private PreparedStatement addTag;
    private PreparedStatement updateTag;
    private PreparedStatement removeTag;
    private PreparedStatement takeOutaBook;
    private PreparedStatement getBookLeftInALibrary;

    public DBModel(Connection conn) throws SQLException {
        this.conn = conn;

        getKonyv
                = conn.prepareStatement("SELECT * FROM konyvek WHERE id = ?");
        getAllKonyv
                = conn.prepareStatement("SELECT * FROM konyvek");
        getAllTag
                = conn.prepareStatement("SELECT * FROM tagok WHERE idtag > 0");
        getTagokKivettKonyv
                = conn.prepareStatement("SELECT * FROM konyvek WHERE idtag = ?");
        addKonyv
                = conn.prepareStatement("INSERT INTO konyvek(idtag, cim, szerzoknev, szerzovnev, oldalszam) VALUES (?, ?, ?, ?, ?)");
        updateKonyv
                = conn.prepareStatement("UPDATE konyvek SET idtag = ?, cim = ?, szerzoknev = ?, szerzovnev = ?, oldalszam = ? WHERE id = ?");
        removeKonyv
                = conn.prepareStatement("DELETE FROM konyvek WHERE id = ?");
        getTag
                = conn.prepareStatement("SELECT * FROM tagok WHERE idtag = ?");
        addTag
                = conn.prepareStatement("INSERT INTO tagok(idtag, vnev, knev, email) VALUES(?, ?, ?, ?)");
        updateTag
                = conn.prepareStatement("UPDATE tagok SET vnev = ?, knev = ?, email = ? WHERE idtag = ?");
        removeTag
                = conn.prepareStatement("DELETE FROM tagok WHERE idtag = ?");
        takeOutaBook
                = conn.prepareStatement("UPDATE konyvek SET idtag= ? WHERE id= ?");
        getBookLeftInALibrary 
                = conn.prepareStatement("SELECT * FROM konyvek WHERE idtag = 0");
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }

    @Override
    public Konyvek getKonyv(int id) throws SQLException {
        /*Ez a Tagokat tároló Lista lesz*/
        List<Tagok> tagok = getAllTag();

        /*Ide másolom be a Tagokat. IDTag a külső kulcs*/
        Map<Integer, Tagok> mapTagok = new HashMap<>();
        for (Tagok t : tagok) {
            mapTagok.put(t.getIdtag(), t);
        }

        getKonyv.setInt(1, id);

        ResultSet rs = getKonyv.executeQuery();
        Konyvek konyv = null;

        if (rs.next()) {
            konyv = new Konyvek(
                    rs.getInt("idtag"),
                    rs.getString("cim"),
                    rs.getString("szerzoknev"),
                    rs.getString("szerzovnev"),
                    rs.getInt("oldalszam"));

        }
        konyv.setTag(mapTagok.get(rs.getInt("idtag")));

        rs.close();
        return konyv;
    }

    @Override
    public List<Konyvek> getAllKonyv() throws SQLException {
        List<Konyvek> konyvek = new ArrayList<>();
        
        /*Ez a Tagokat tároló Lista lesz*/
        List<Tagok> tagok = getAllTag();

        /*Ide másolom be a Tagokat. IDTag a külső kulcs*/
        Map<Integer, Tagok> mapTagok = new HashMap<>();
        for (Tagok t : tagok) {
            mapTagok.put(t.getIdtag(), t);
        }

        ResultSet rs = getAllKonyv.executeQuery();
        Konyvek konyv = null;

        while (rs.next()) {
            konyv = new Konyvek(
                    rs.getInt("id"),
                    rs.getInt("idtag"),
                    rs.getString("cim"),
                    rs.getString("szerzoknev"),
                    rs.getString("szerzovnev"),
                    rs.getInt("oldalszam"));
            konyv.setTag(mapTagok.get(rs.getInt("idtag")));
            konyvek.add(konyv);
        }

        rs.close();
        return konyvek;
    }

    @Override
    public List<Konyvek> getTagokKivettKonyv(int idtag) throws SQLException {
        List<Tagok> tagok = getAllTag();

        /*Ide másolom be a Tagokat. IDTag a külső kulcs*/
        Map<Integer, Tagok> mapTagok = new HashMap<>();
        for (Tagok t : tagok) {
            mapTagok.put(t.getIdtag(), t);
        }

        List<Konyvek> konyvek = new ArrayList<>();

        getTagokKivettKonyv.setInt(1, idtag);
        ResultSet rs = getTagokKivettKonyv.executeQuery();
        Konyvek konyv = null;

        while (rs.next()) {
            konyv = new Konyvek(
                    rs.getInt("idtag"),
                    rs.getString("cim"),
                    rs.getString("szerzoknev"),
                    rs.getString("szerzovnev"),
                    rs.getInt("oldalszam"));
            konyv.setTag(mapTagok.get(rs.getInt("idtag")));
            konyvek.add(konyv);
        }

        //konyv.setTag(mapTagok.get(rs.getInt("idtag")));
        rs.close();
        return konyvek;
    }

    @Override
    public void addKonyv(Konyvek konyv) throws SQLException {
        addKonyv.setInt(1, konyv.getIdtag());
        addKonyv.setString(2, konyv.getCim());
        addKonyv.setString(3, konyv.getSzerzoknev());
        addKonyv.setString(4, konyv.getSzerzovnev());
        addKonyv.setInt(5, konyv.getOldalszam());

        addKonyv.executeUpdate();
    }

    @Override
    public void updateKony(Konyvek konyv) throws SQLException {
        updateKonyv.setInt(1, konyv.getIdtag());
        updateKonyv.setString(2, konyv.getCim());
        updateKonyv.setString(3, konyv.getSzerzoknev());
        updateKonyv.setString(4, konyv.getSzerzovnev());
        updateKonyv.setInt(5, konyv.getOldalszam());
        updateKonyv.setInt(6, konyv.getId());

        updateKonyv.executeUpdate();
    }

    @Override
    public void removeKonyv(Konyvek konyv) throws SQLException {
        removeKonyv.setInt(1, konyv.getId());

        removeKonyv.executeUpdate();
    }

    @Override
    public Tagok getTag(int id) throws SQLException {
        getTag.setInt(1, id);

        ResultSet rs = getTag.executeQuery();
        Tagok tag = null;

        if (rs.next()) {
            tag = new Tagok(
                    rs.getInt("idtag"),
                    rs.getString("vnev"),
                    rs.getString("knev"),
                    rs.getString("email"));
        }

        rs.close();
        return tag;
    }

    @Override
    public List<Tagok> getAllTag() throws SQLException {
        List<Tagok> tagok = new ArrayList<>();

        ResultSet rs = getAllTag.executeQuery();

        while (rs.next()) {
            Tagok tag = new Tagok(
                    rs.getInt("idtag"),
                    rs.getString("vnev"),
                    rs.getString("knev"),
                    rs.getString("email"));
            tagok.add(tag);
        }

        rs.close();

        return tagok;
    }

    @Override
    public void addTag(Tagok tag) throws SQLException {
        addTag.setInt(1, tag.getIdtag());
        addTag.setString(2, tag.getVnev());
        addTag.setString(3, tag.getKnev());
        addTag.setString(4, tag.getEmail());

        addTag.executeUpdate();
    }

    @Override
    public void updateTag(Tagok tag) throws SQLException {
        updateTag.setString(1, tag.getVnev());
        updateTag.setString(2, tag.getKnev());
        updateTag.setString(3, tag.getEmail());
        updateTag.setInt(4, tag.getIdtag());

        updateTag.executeUpdate();
    }

    @Override
    public void removeTag(Tagok tag) throws SQLException {
        removeTag.setInt(1, tag.getIdtag());

        removeTag.executeUpdate();
    }

    @Override
    public void takeOutaBook(Konyvek konyv) throws SQLException {

        takeOutaBook.setInt(1, konyv.getIdtag());
        takeOutaBook.setInt(2, konyv.getId());

        takeOutaBook.executeUpdate();
    }

    @Override
    public List<Konyvek> getBookLeftInALibrary() throws SQLException {
        List<Konyvek> konyvek = new ArrayList();
        
        ResultSet rs = getBookLeftInALibrary.executeQuery();
        Konyvek konyv = null;
        
        while (rs.next()) {
            konyv = new Konyvek(
                    rs.getInt("idtag"),
                    rs.getString("cim"),
                    rs.getString("szerzoknev"),
                    rs.getString("szerzovnev"),
                    rs.getInt("oldalszam"));
            konyvek.add(konyv);
        }
        
        rs.close();
        return konyvek;
    }

}

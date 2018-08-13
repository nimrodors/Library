/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Örs
 */
public interface IModel {
    
    void close() throws SQLException;
    Konyvek getKonyv(int id) throws SQLException;
    List<Konyvek> getAllKonyv() throws SQLException;
    List<Konyvek> getBookLeftInALibrary () throws SQLException;

    /**
     *
     * @param idtag
     * @return
     * @throws SQLException
     */
    
    List<Konyvek> getTagokKivettKonyv(int idtag) throws SQLException;
    void addKonyv(Konyvek konyv) throws SQLException;
    void updateKony(Konyvek konyv) throws SQLException;
    void removeKonyv (Konyvek konyv) throws SQLException;
    void takeOutaBook (Konyvek konyv) throws SQLException;
    
    Tagok getTag(int id) throws SQLException;
    List<Tagok> getAllTag() throws SQLException;
    void addTag(Tagok tag) throws SQLException;
    void updateTag(Tagok tag) throws SQLException;
    void removeTag(Tagok tag) throws SQLException;
}

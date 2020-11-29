/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

/**
 *
 * @author Ernestine
 */
class DBName {
  String id,nom;

    public DBName(String nom) {
        this.nom = nom;
    }
    public DBName(String id,String nom) {
        this.id = id;
        this.nom = nom;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
  
}

package com.idrisso4.money;

public class NewMoney {
    private String nom;
    private String date;
    private String prix;
    private int id;

    public NewMoney() {
    }

    public NewMoney(int id,String nom, String prix,String date) {
        this.nom = nom;
        this.date = date;
        this.prix = prix;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getprix() {
        return prix;
    }
    public void setprix(String prix) {
        this.prix = prix;
    }

    public int getid() {
        return id; }
    public void setid(int id) {
        this.id = id; }

    public String getdate() {
        return date; }
    public void setdate(String date) {
        this.date = date; }
}

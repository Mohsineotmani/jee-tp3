package org.mo.tp2.model;

import java.math.BigDecimal;

public class Produit {
    private Long id;
    private String nom;
    private String description;
    private BigDecimal prix;
    private int stock;

    public Produit() {
    }

    public Produit(Long id, String nom, String description, BigDecimal prix, int stock) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
    }
    // TODO : getters et setters pour tous les champs
    // (indispensables : l'EL ${produit.nom} appelle getNom())


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                '}';
    }
}
package org.example.agence.classes;

public class AdresseAgence {
    private String pays;
    private String ville;
    private String rue;
    private String numero;
    private String Codepostal;
    private String lieuDit;

    public AdresseAgence(String pays, String ville, String rue, String numero, String codepostal, String lieuDit) {
        this.pays = pays;
        this.ville = ville;
        this.rue = rue;
        this.numero = numero;
        this.Codepostal = codepostal;
        this.lieuDit = lieuDit;
    }

    public AdresseAgence(){}

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodepostal() {
        return Codepostal;
    }

    public void setCodepostal(String codepostal) {
        Codepostal = codepostal;
    }

    public String getLieuDit() {
        return lieuDit;
    }

    public void setLieuDit(String lieuDit) {
        this.lieuDit = lieuDit;
    }

    @Override
    public String toString() {
        return ""+this.numero+" "+this.rue+" "+this.lieuDit+" "+this.Codepostal+" "+this.ville+" "+this.pays;
    }

    @Override
    public boolean equals(Object p) {
        if (this == p)
            return true;
        if (p == null || getClass() != p.getClass())
            return false;
        AdresseAgence adr = (AdresseAgence) p;
        return adr.getNumero().equals(this.numero)
                && adr.getRue().equals(this.rue)
                && adr.getLieuDit().equals(this.lieuDit)
                && adr.getVille().equals(this.ville)
                && adr.getPays().equals(this.pays)
                && adr.getCodepostal().equals(this.Codepostal);
    }
}
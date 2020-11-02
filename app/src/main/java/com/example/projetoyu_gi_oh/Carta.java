package com.example.projetoyu_gi_oh;

public class Carta {
    private String cartaNome, cartaDescricao;
    private int cartaATK, cartaDEF;

    public Carta() {
    }

    public Carta(String cartaNome, String cartaDescricao, int cartaATK, int cartaDEF) {
        this.cartaNome = cartaNome;
        this.cartaDescricao = cartaDescricao;
        this.cartaATK = cartaATK;
        this.cartaDEF = cartaDEF;
    }

    public String getCartaNome() {
        return cartaNome;
    }

    public void setCartaNome(String cartaNome) {
        this.cartaNome = cartaNome;
    }

    public String getCartaDescricao() {
        return cartaDescricao;
    }

    public void setCartaDescricao(String cartaDescricao) {
        this.cartaDescricao = cartaDescricao;
    }

    public int getCartaATK() {
        return cartaATK;
    }

    public void setCartaATK(int cartaATK) {
        this.cartaATK = cartaATK;
    }

    public int getCartaDEF() {
        return cartaDEF;
    }

    public void setCartaDEF(int cartaDEF) {
        this.cartaDEF = cartaDEF;
    }

    @Override
    public String toString() {
        return
                "Nome da Carta='" + cartaNome + "\n" +
                "Descricao='" + cartaDescricao + "\n" +
                "ATK=" + cartaATK + "\n" +
                "DEF=" + cartaDEF + "\n\n"
                ;
    }
}

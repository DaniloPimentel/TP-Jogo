/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author strudel
 */
public class Resposta {

    private final int servico;
    private final String corpo;

    public Resposta(int servico, String corpo) {
        this.servico = servico;
        this.corpo = corpo;
    }

    public String encode() {
        return this.servico + "&&&" + this.corpo;
    }

    public int getServico() {
        return servico;
    }

    public String getCorpo() {
        return corpo;
    }
}

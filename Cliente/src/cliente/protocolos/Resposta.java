package cliente.protocolos;

public class Resposta {

    private int servico;
    private String corpo;

    public Resposta(String resposta) throws RespostaMalFormadaException {

        if (resposta.contains("&&&")) {

            String[] partes = resposta.split("&&&");

            if (partes.length == 1 || partes.length == 2) {
                try {
                    this.servico = Integer.parseInt(resposta.split("&&&")[0]);
                } catch (NumberFormatException ex) {
                    throw new RespostaMalFormadaException("Serviço inválido.");
                }

                if (partes.length == 2) {
                    this.corpo = partes[1];
                } else {
                    this.corpo = "";
                }
            } else {
                throw new RespostaMalFormadaException("Mal uso do regex primário");
            }

        } else {
            throw new RespostaMalFormadaException("Não possui regex primário");
        }

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

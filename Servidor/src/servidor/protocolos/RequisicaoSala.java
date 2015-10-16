package servidor.protocolos;

public class RequisicaoSala {

    private final int id;
    private final int servico;
    private final String corpo;

    public RequisicaoSala(String requisicao) throws RequisicaoMalFormadaException {

        if (requisicao.contains("###")) {

            String[] partes = requisicao.split("###");

            if (partes.length >= 3) {
                try {
                    this.id = Integer.parseInt(requisicao.split("###")[0]);
                } catch (NumberFormatException ex) {
                    throw new RequisicaoMalFormadaException("Id da sala inválido.");
                }

                try {
                    this.servico = Integer.parseInt(requisicao.split("###")[1]);
                } catch (NumberFormatException ex) {
                    throw new RequisicaoMalFormadaException("Serviço inválido.");
                }

                if (partes.length == 3) {
                    this.corpo = partes[2];
                } else {
                    this.corpo = "";
                }

            } else {
                throw new RequisicaoMalFormadaException("Mal uso do regex secundário");
            }

        } else {
            throw new RequisicaoMalFormadaException("Não possui regex secundário");
        }
    }

    public String encode() {
        return this.servico + "###" + this.corpo;
    }

    public int getServico() {
        return servico;
    }

    public String getCorpo() {
        return corpo;
    }

    public int getId() {
        return id;
    }

}

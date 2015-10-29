package cliente.protocolos;

public class Mensagem {

    private int id1;
    private int id2;
    private String mensagem;

    public Mensagem(int id1, int id2, String mensagem) {
        this.id1 = id1;
        this.id2 = id2;
        this.mensagem = mensagem;
    }

    public Mensagem(String corpo) throws MensagemMalFormadaException {

        if (corpo.contains("###")) {

            String[] partes = corpo.split("###");

            if (partes.length == 3) {

                try {
                    id1 = Integer.parseInt(partes[0]);
                    id2 = Integer.parseInt(partes[1]);
                    mensagem = partes[2];
                } catch (NumberFormatException ex) {
                    throw new MensagemMalFormadaException("Id do destinatário ou do remetente inválido");
                }

            } else {
                throw new MensagemMalFormadaException("Mal uso do regex secundário");
            }

        } else {
            throw new MensagemMalFormadaException("Não possui regex secundário");
        }

    }

    public String encode() {
        return id1 + "###" + id2 + "###" + mensagem;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public String getMensagem() {
        return mensagem;
    }

}

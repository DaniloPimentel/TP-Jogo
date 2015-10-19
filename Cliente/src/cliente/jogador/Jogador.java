package cliente.jogador;

public class Jogador {

    private Integer id;
    private String apelido;
    private int avatarCamisa = 1;
    private int avatarCalca = 1;

    public Jogador() {
    }

    public Jogador(Integer id, String apelido) {
        this.id = id;
        this.apelido = apelido;
    }

    public Jogador(Integer id, String apelido, int avatarCamisa, int avataCalca) {
        this.id = id;
        this.apelido = apelido;
        this.avatarCalca = avatarCalca;
        this.avatarCamisa = avatarCamisa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getAvatarCamisa() {
        return avatarCamisa;
    }

    public void setAvatarCamisa(int avatarCamisa) {
        this.avatarCamisa = avatarCamisa;
    }

    public int getAvatarCalca() {
        return avatarCalca;
    }

    public void setAvatarCalca(int avatarCalca) {
        this.avatarCalca = avatarCalca;
    }

}

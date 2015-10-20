package cliente.janelas;

import cliente.Cliente;
import cliente.protocolos.Requisicao;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

public class ConfigInicial extends javax.swing.JFrame {
    
    Cliente cliente;

    public ConfigInicial(Cliente cliente) {
        this.cliente = cliente;
        initComponents();
        ( (CardLayout) this.PainelPrincipal.getLayout()).show(PainelPrincipal, "conexao");
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelPrincipal = new javax.swing.JPanel();
        PainelEscolherApelido = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        InputApelido = new javax.swing.JTextField();
        BtConfirmar = new javax.swing.JButton();
        MsgEscolherApelido = new javax.swing.JLabel();
        PainelConexao = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        InputServidor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        InputPorta = new javax.swing.JTextField();
        BtConectar = new javax.swing.JButton();
        MsgConexao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configurações Iniciais");
        setResizable(false);

        PainelPrincipal.setLayout(new java.awt.CardLayout());

        PainelEscolherApelido.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setText("Apelido");

        BtConfirmar.setText("Confirmar");
        BtConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtConfirmarActionPerformed(evt);
            }
        });

        MsgEscolherApelido.setText("Entre com um apelido");

        javax.swing.GroupLayout PainelEscolherApelidoLayout = new javax.swing.GroupLayout(PainelEscolherApelido);
        PainelEscolherApelido.setLayout(PainelEscolherApelidoLayout);
        PainelEscolherApelidoLayout.setHorizontalGroup(
            PainelEscolherApelidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEscolherApelidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelEscolherApelidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InputApelido)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelEscolherApelidoLayout.createSequentialGroup()
                        .addComponent(MsgEscolherApelido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(BtConfirmar))
                    .addGroup(PainelEscolherApelidoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PainelEscolherApelidoLayout.setVerticalGroup(
            PainelEscolherApelidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEscolherApelidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InputApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(PainelEscolherApelidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtConfirmar)
                    .addComponent(MsgEscolherApelido))
                .addContainerGap())
        );

        PainelPrincipal.add(PainelEscolherApelido, "apelido");

        PainelConexao.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Servidor: ");

        jLabel2.setText("Porta: ");

        BtConectar.setText("Conectar");
        BtConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtConectarActionPerformed(evt);
            }
        });

        MsgConexao.setText("Entre com os parâmetros de um servidor");

        javax.swing.GroupLayout PainelConexaoLayout = new javax.swing.GroupLayout(PainelConexao);
        PainelConexao.setLayout(PainelConexaoLayout);
        PainelConexaoLayout.setHorizontalGroup(
            PainelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelConexaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelConexaoLayout.createSequentialGroup()
                        .addComponent(MsgConexao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(BtConectar))
                    .addGroup(PainelConexaoLayout.createSequentialGroup()
                        .addGroup(PainelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InputPorta)
                            .addComponent(InputServidor))))
                .addContainerGap())
        );
        PainelConexaoLayout.setVerticalGroup(
            PainelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelConexaoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(PainelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(InputServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(InputPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(PainelConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtConectar)
                    .addComponent(MsgConexao))
                .addContainerGap())
        );

        PainelPrincipal.add(PainelConexao, "conexao");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(PainelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(PainelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtConectarActionPerformed
        String endereco = this.InputServidor.getText();
        String porta = this.InputPorta.getText();

        try {

            int porta2 = Integer.parseInt(porta);

            this.cliente.setSocket(new Socket(endereco, porta2));

            this.cliente.getRecebedor().start();
            
            ( (CardLayout) this.PainelPrincipal.getLayout()).show(PainelPrincipal, "apelido");

        } catch (NumberFormatException ex) {
            this.MsgConexao.setForeground(Color.red);
            this.MsgConexao.setText("Porta inválida");
        } catch (IOException ex) {
            this.MsgConexao.setForeground(Color.red);
            this.MsgConexao.setText("Não foi possível se conectar ao servidor");
        }
    }//GEN-LAST:event_BtConectarActionPerformed

    private void BtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtConfirmarActionPerformed
        
        String apelido = this.InputApelido.getText();
        
        try{
            
            this.cliente.enviar(new Requisicao(Cliente.SERVICO_OLA, apelido));
            
            while(cliente.getId() == null) {
                this.MsgEscolherApelido.setForeground(Color.black);
                this.MsgEscolherApelido.setText("Esperando resposta do servidor");
            }
            
            if(cliente.getId() <= 0){
                this.MsgEscolherApelido.setForeground(Color.red);
                this.MsgEscolherApelido.setText("Apelido inválido. Escolha outro.");
            } else {
                this.cliente.getJanela().setVisible(true);
                this.dispose();
            }
            
        } catch(IOException ex) {
            this.MsgEscolherApelido.setForeground(Color.red);
            this.MsgEscolherApelido.setText("Não foi possível se conectar ao servidor.Tente novamente.");
        }
        
    }//GEN-LAST:event_BtConfirmarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtConectar;
    private javax.swing.JButton BtConfirmar;
    private javax.swing.JTextField InputApelido;
    private javax.swing.JTextField InputPorta;
    private javax.swing.JTextField InputServidor;
    private javax.swing.JLabel MsgConexao;
    private javax.swing.JLabel MsgEscolherApelido;
    private javax.swing.JPanel PainelConexao;
    private javax.swing.JPanel PainelEscolherApelido;
    private javax.swing.JPanel PainelPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}

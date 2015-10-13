/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import cliente.Cliente;
import cliente.Requisicao;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author strudel
 */
public class Janela extends javax.swing.JFrame {
    
    Cliente cliente;

    public Janela(Cliente c) {
        initComponents();
        this.setVisible(true);
        
        ((CardLayout) Content.getLayout()).show(Content, "config");
        
        this.cliente = c;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TelaBase = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Content = new javax.swing.JPanel();
        TelaConfig = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ServidorEnderecoInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        PortaServidorInput = new javax.swing.JTextField();
        BtConectarServidor = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ApelidoInput = new javax.swing.JTextField();
        BtEscolherApelido = new javax.swing.JButton();
        BtConfigContinuar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TelaBase.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Header.jpg"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img1.jpg"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img2.jpg"))); // NOI18N

        Content.setLayout(new java.awt.CardLayout());

        TelaConfig.setBackground(new java.awt.Color(0, 0, 0));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Configuração do Servidor");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Endereço: ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Porta:");

        BtConectarServidor.setText("Conectar");
        BtConectarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtConectarServidorActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Apelido: ");

        ApelidoInput.setEditable(false);

        BtEscolherApelido.setText("Escolher");
        BtEscolherApelido.setEnabled(false);
        BtEscolherApelido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtEscolherApelidoActionPerformed(evt);
            }
        });

        BtConfigContinuar.setText("Continuar");
        BtConfigContinuar.setEnabled(false);

        javax.swing.GroupLayout TelaConfigLayout = new javax.swing.GroupLayout(TelaConfig);
        TelaConfig.setLayout(TelaConfigLayout);
        TelaConfigLayout.setHorizontalGroup(
            TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaConfigLayout.createSequentialGroup()
                .addGroup(TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaConfigLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel4))
                    .addGroup(TelaConfigLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtConectarServidor)
                            .addComponent(BtEscolherApelido)
                            .addGroup(TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(BtConfigContinuar)
                                .addGroup(TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(PortaServidorInput, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                    .addComponent(ServidorEnderecoInput, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                    .addComponent(ApelidoInput))))))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        TelaConfigLayout.setVerticalGroup(
            TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ServidorEnderecoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PortaServidorInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtConectarServidor)
                .addGap(24, 24, 24)
                .addGroup(TelaConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ApelidoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtEscolherApelido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(BtConfigContinuar)
                .addGap(28, 28, 28))
        );

        Content.add(TelaConfig, "config");

        javax.swing.GroupLayout TelaBaseLayout = new javax.swing.GroupLayout(TelaBase);
        TelaBase.setLayout(TelaBaseLayout);
        TelaBaseLayout.setHorizontalGroup(
            TelaBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaBaseLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TelaBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3))
        );
        TelaBaseLayout.setVerticalGroup(
            TelaBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TelaBaseLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(TelaBaseLayout.createSequentialGroup()
                .addGroup(TelaBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TelaBaseLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(TelaBaseLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Content, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TelaBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelaBase, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtConectarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtConectarServidorActionPerformed

        this.ApelidoInput.setEditable(false);
        this.BtEscolherApelido.setEnabled(false);
        this.BtConfigContinuar.setEnabled(false);

        String endereco = this.ServidorEnderecoInput.getText();
        String porta = this.PortaServidorInput.getText();

        try{

            int porta2 = Integer.parseInt(porta);

            this.cliente.socket = new Socket(endereco, porta2);

            this.ApelidoInput.setEditable(true);
            this.BtEscolherApelido.setEnabled(true);

        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(TelaConfig, "Porta inválida!", "Erro ao conectar servidor", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(TelaConfig, "Não foi possível se conectar ao servidor. Cheque o endereço e a porta.", "Erro ao conectar servidor", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtConectarServidorActionPerformed

    private void BtEscolherApelidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtEscolherApelidoActionPerformed

        String apelido = this.ApelidoInput.getText();

        try {

            this.cliente.enviar(new Requisicao(Cliente.SERVICO_OLA, apelido));

            this.BtConfigContinuar.setEnabled(true);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(TelaConfig, Cliente.ERRO_SERVICO_OLA, "Erro ao escolher apelido", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtEscolherApelidoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ApelidoInput;
    private javax.swing.JButton BtConectarServidor;
    private javax.swing.JButton BtConfigContinuar;
    private javax.swing.JButton BtEscolherApelido;
    private javax.swing.JPanel Content;
    private javax.swing.JTextField PortaServidorInput;
    private javax.swing.JTextField ServidorEnderecoInput;
    private javax.swing.JPanel TelaBase;
    private javax.swing.JPanel TelaConfig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}

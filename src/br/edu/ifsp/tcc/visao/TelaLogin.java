/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.visao;

import br.edu.ifsp.tcc.controle.ControleFuncionario;
import br.edu.ifsp.tcc.modelo.Funcionario;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author Programar
 */
public class TelaLogin extends javax.swing.JFrame {

    /**
     * Creates new form TelaLogin
     */
    private ControleFuncionario controleFuncionario = new ControleFuncionario();
    
    public TelaLogin() {
        initComponents();
       // criarUsuarioInicial();

    }

       /**
     * Este Método verifica quais campos estão preenchidos
     * @return 
     */
    public boolean existCamposVazio() {
        if (txtInicioLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo LOGIN deve ser Prenchido", "Atenção", JOptionPane.ERROR_MESSAGE);
            txtInicioLogin.requestFocus();
            return true;
        }

        if (String.valueOf(txtSenha.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo SENHA deve ser Prenchido", "Atenção", JOptionPane.ERROR_MESSAGE);
            txtSenha.requestFocus();
            return true;
        }
        return false;

    }

    /**
     * *
     * ESTE METODO VERIFICA SE O USUARIO TEM LOGIN E SENHA CADASTRADOS
     */
    public void verificaUsuario() {
        Funcionario funcionario = null;
        String login = txtInicioLogin.getText();
        String senha = String.valueOf(txtSenha.getPassword());
        try {
            funcionario = controleFuncionario.confirmaLoginSenha(login, senha);
        } catch (NoResultException ex) {
            JOptionPane.showMessageDialog(null, "Funcionario não encontrado!!! verifique login e senha!", "Atenção", JOptionPane.ERROR_MESSAGE);
            txtInicioLogin.setText("");
            txtSenha.setText("");
            txtInicioLogin.requestFocus();
        }
        // verificando o usuario
        if (funcionario != null) {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            this.dispose();
            telaPrincipal.setFuncionario(funcionario);/// manda para o principal
            telaPrincipal.setVisible(true);
        }

    }
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelUsuario = new javax.swing.JLabel();
        txtInicioLogin = new javax.swing.JTextField();
        jLabelSenha = new javax.swing.JLabel();
        jButtonAcessar = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        jButtonSair = new javax.swing.JButton();
        jLabelFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login e Senha");

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(285, 213));
        jPanel1.setLayout(null);

        jLabelUsuario.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsuario.setText("Usuário");
        jPanel1.add(jLabelUsuario);
        jLabelUsuario.setBounds(10, 10, 56, 19);

        txtInicioLogin.setToolTipText("Digite seu usuário");
        txtInicioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInicioLoginActionPerformed(evt);
            }
        });
        jPanel1.add(txtInicioLogin);
        txtInicioLogin.setBounds(10, 40, 290, 30);

        jLabelSenha.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabelSenha.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSenha.setText("Senha");
        jPanel1.add(jLabelSenha);
        jLabelSenha.setBounds(10, 70, 70, 19);

        jButtonAcessar.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButtonAcessar.setText("Acessar");
        jButtonAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcessarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAcessar);
        jButtonAcessar.setBounds(70, 140, 90, 30);

        txtSenha.setToolTipText("Digite sua senha");
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        jPanel1.add(txtSenha);
        txtSenha.setBounds(10, 100, 290, 30);

        jButtonSair.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSair);
        jButtonSair.setBounds(180, 140, 70, 30);

        jLabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fundoLogin.png"))); // NOI18N
        jPanel1.add(jLabelFundo);
        jLabelFundo.setBounds(0, 0, 311, 195);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(324, 216));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void txtInicioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInicioLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInicioLoginActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcessarActionPerformed

        if (!existCamposVazio()) {
            verificaUsuario();

        }

    }//GEN-LAST:event_jButtonAcessarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAcessar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabelFundo;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtInicioLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}

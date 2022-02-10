/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.visao;

import br.edu.ifsp.tcc.controle.ControleFaccao;
import br.edu.ifsp.tcc.modelo.Faccao;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author macel
 */
public class CadastroFaccao extends javax.swing.JDialog {

    private ControleFaccao controle = new ControleFaccao();
    private List<Faccao> listFaccoes = new ArrayList();
    Faccao f = new Faccao();

    public CadastroFaccao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarLista();
        atualizaTabela();
        TamanhoColuna();
        trataEdicao(false);
    }

    private void trataEdicao(boolean editando) {
        btnCancelar.setEnabled(editando);
        btnSalvar.setEnabled(editando);
        btnAlterar.setEnabled(!editando);
        btnNovo.setEnabled(!editando);
        txtNome.setEditable(editando);
        txtSiglaFaccao.setEditable(editando);
        //btnPesquisar.setEditable(editando);
        //  txtLocalizar.setEditable(editando);
        jTableFaccao.setEnabled(!editando);
    }

    public void TamanhoColuna() {
        jTableFaccao.getColumnModel().getColumn(0).setMaxWidth(920);
        jTableFaccao.getColumnModel().getColumn(1).setMaxWidth(150);
        jTableFaccao.setAutoCreateRowSorter(true); // ordena ao clicar na coluna
        JTableHeader jth = jTableFaccao.getTableHeader();
        jth.setFont(new Font("Arial Unicod MS", Font.BOLD, 14));
    }

    public void atualizaTabela() {
        DefaultTableModel dtm = (DefaultTableModel) jTableFaccao.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serva para apagar as linhas "é muito rapido"
        for (Faccao f : listFaccoes) {
            dtm.addRow(new Object[]{f.getNome(), f.getSiglaFaccao()}); // Object é um vetor ou coleção com ele não pecisa converter
        }
    }

    public void carregarLista() {
        listFaccoes = controle.buscar();
    }

    public void limparCampos() {
        txtNome.setText("");
        txtLocalizar.setText("");
        txtSiglaFaccao.setText("");

    }

    public void pesquisar() {
        listFaccoes.clear();//limpa lista
        DefaultTableModel dtm = (DefaultTableModel) jTableFaccao.getModel();
        dtm.setNumRows(0);//LIMPA A TABELA
        String str = "";
        str = txtLocalizar.getText();
        if (jcbPesquisarFaccao.getSelectedItem().toString().equals("Nome")) {
            listFaccoes.addAll(controle.listarNome(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        if (jcbPesquisarFaccao.getSelectedItem().toString().equals("Sigla")) {
            listFaccoes.addAll(controle.listarSigla(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        txtLocalizar.requestFocus();
        atualizaTabela();//PREENCHE COM OS NOVOS DADOS
    }

    public void vincularCampos() {
        Faccao f = listFaccoes.get(jTableFaccao.getSelectedRow());// pega a linha da tabela
        txtNome.setText(f.getNome());
        txtSiglaFaccao.setText(f.getSiglaFaccao());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelSentenciado = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtSiglaFaccao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        espaco = new javax.swing.JLabel();
        painelTabela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFaccao = new javax.swing.JTable();
        txtLocalizar = new javax.swing.JTextField();
        jcbPesquisarFaccao = new javax.swing.JComboBox<>();
        painelAcoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        painelSentenciado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Facção", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N
        painelSentenciado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel10.setText("Nome:");
        painelSentenciado.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        txtNome.setFont(txtNome.getFont().deriveFont(txtNome.getFont().getSize()+3f));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 640, -1));

        txtSiglaFaccao.setFont(txtSiglaFaccao.getFont().deriveFont(txtSiglaFaccao.getFont().getSize()+3f));
        txtSiglaFaccao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSiglaFaccaoActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtSiglaFaccao, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 70, -1));

        jLabel9.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel9.setText("Sigla:");
        painelSentenciado.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, -1, -1));
        painelSentenciado.add(espaco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, 20));

        painelTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Presidios por:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jTableFaccao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Sigla"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFaccao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFaccaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFaccao);

        txtLocalizar.setFont(txtLocalizar.getFont().deriveFont(txtLocalizar.getFont().getSize()+3f));
        txtLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocalizarActionPerformed(evt);
            }
        });
        txtLocalizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalizarKeyReleased(evt);
            }
        });

        jcbPesquisarFaccao.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jcbPesquisarFaccao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Sigla" }));
        jcbPesquisarFaccao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPesquisarFaccaoActionPerformed(evt);
            }
        });
        jcbPesquisarFaccao.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jcbPesquisarFaccaoPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout painelTabelaLayout = new javax.swing.GroupLayout(painelTabela);
        painelTabela.setLayout(painelTabelaLayout);
        painelTabelaLayout.setHorizontalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                    .addGroup(painelTabelaLayout.createSequentialGroup()
                        .addComponent(jcbPesquisarFaccao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        painelTabelaLayout.setVerticalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTabelaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbPesquisarFaccao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );

        painelAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18))); // NOI18N

        btnNovo.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/adicionar.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/atualizar.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelAcoesLayout = new javax.swing.GroupLayout(painelAcoes);
        painelAcoes.setLayout(painelAcoesLayout);
        painelAcoesLayout.setHorizontalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(71, 71, 71)
                .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(58, 58, 58)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(76, 76, 76)
                .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(51, 51, 51))
        );
        painelAcoesLayout.setVerticalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnNovo)
                    .addComponent(btnAlterar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painelAcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelSentenciado, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(13, Short.MAX_VALUE)
                    .addComponent(painelSentenciado, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(406, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtSiglaFaccaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSiglaFaccaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSiglaFaccaoActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        limparCampos();
        f = new Faccao();
        trataEdicao(true);
        txtNome.requestFocus();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        if (jTableFaccao.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para alterar", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            f = listFaccoes.get(jTableFaccao.getSelectedRow());
            trataEdicao(true);
            txtNome.requestFocus();
        }
        carregarLista();
        atualizaTabela();


    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        trataEdicao(false);
        limparCampos();
        carregarLista();
        atualizaTabela();
        txtLocalizar.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (!txtNome.getText().isEmpty()) {
            if (!txtSiglaFaccao.getText().isEmpty()) {
                f.setNome(txtNome.getText().trim());
                f.setSiglaFaccao(txtSiglaFaccao.getText().trim());
                controle.alterar(f);//salvar o orçamento
                carregarLista();
                atualizaTabela();
                limparCampos();
                trataEdicao(false);
            } else {
                JOptionPane.showMessageDialog(null, "Digite a Sigla da Facção!", "Alerta", JOptionPane.WARNING_MESSAGE);
                txtSiglaFaccao.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Digite o Nome da Facção!", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocus();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalizarActionPerformed

    private void jTableFaccaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFaccaoMouseClicked
        // TODO add your handling code here:
        vincularCampos();
    }//GEN-LAST:event_jTableFaccaoMouseClicked

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void jcbPesquisarFaccaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPesquisarFaccaoActionPerformed

        // TODO add your handling code here:
        System.out.println("oi");
        txtLocalizar.setEnabled(true);
        txtLocalizar.requestFocus();
    }//GEN-LAST:event_jcbPesquisarFaccaoActionPerformed

    private void jcbPesquisarFaccaoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jcbPesquisarFaccaoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbPesquisarFaccaoPropertyChange

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
            java.util.logging.Logger.getLogger(CadastroFaccao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroFaccao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroFaccao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroFaccao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroFaccao dialog = new CadastroFaccao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel espaco;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFaccao;
    private javax.swing.JComboBox<String> jcbPesquisarFaccao;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelSentenciado;
    private javax.swing.JPanel painelTabela;
    private javax.swing.JTextField txtLocalizar;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSiglaFaccao;
    // End of variables declaration//GEN-END:variables
}
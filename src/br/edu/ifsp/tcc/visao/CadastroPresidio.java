/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.visao;

import br.edu.ifsp.tcc.controle.ControleCoordenadoria;
import br.edu.ifsp.tcc.controle.ControlePresidio;
import br.edu.ifsp.tcc.modelo.Coordenadoria;
import br.edu.ifsp.tcc.modelo.Presidio;
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
public class CadastroPresidio extends javax.swing.JDialog {

    private ControlePresidio controlePresidio = new ControlePresidio();
    private ControleCoordenadoria controleCoordenadoria = new ControleCoordenadoria();
    private List<Presidio> listPresidios = new ArrayList();
    private List<Coordenadoria> listCoordenadoria = new ArrayList();
    Presidio f = new Presidio();
    //private Coordenadoria coordenadoria;

    public CadastroPresidio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarLista();
        atualizaTabela();
        TamanhoColuna();
        trataEdicao(false);
        for (Coordenadoria p : listCoordenadoria) {
            jComboBoxSiglaCoordenadoria.addItem(p.getSigla());
           }
        btnNovo.requestFocus();
    }

    private void trataEdicao(boolean editando) {
        btnCancelar.setEnabled(editando);
        btnSalvar.setEnabled(editando);
        btnAlterar.setEnabled(!editando);
        btnNovo.setEnabled(!editando);
        txtNome.setEditable(editando);
        txtSigla.setEditable(editando);
        txtCidade.setEditable(editando);
        jComboBoxRegime.setEditable(editando);
        jcbPesquisarPresidio.setEditable(editando);
        // txtLocalizar.setEditable(editando);
        jTablePresidio.setEnabled(!editando);
    }

    public void TamanhoColuna() {
        jTablePresidio.getColumnModel().getColumn(0).setMaxWidth(100);
        jTablePresidio.getColumnModel().getColumn(1).setMaxWidth(350);
        jTablePresidio.getColumnModel().getColumn(2).setMaxWidth(350);
        jTablePresidio.getColumnModel().getColumn(3).setMaxWidth(100);
        jTablePresidio.getColumnModel().getColumn(4).setMaxWidth(150);
        jTablePresidio.setAutoCreateRowSorter(true); // ordena ao clicar na coluna
        JTableHeader jth = jTablePresidio.getTableHeader();
        jth.setFont(new Font("Arial Unicod MS", Font.BOLD, 14));
        jTablePresidio.setRowHeight(14);
    }

    public void atualizaTabela() {
        DefaultTableModel dtm = (DefaultTableModel) jTablePresidio.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serva para apagar as linhas "é muito rapido"
        for (Presidio f : listPresidio) {
            dtm.addRow(new Object[]{f.getSigla(), f.getNome(), f.getCidade(), f.getRegime(), f.getCoordenadoria().getSigla()}); // Object é um vetor ou coleção com ele não pecisa converter
        }
    }

    public void carregarLista() {
        listPresidio = controlePresidio.buscar();
        listCoordenadoria = controleCoordenadoria.buscar();
    }

    public void limparCampos() {
        txtNome.setText("");
        txtSigla.setText("");
        txtCidade.setText("");
        jComboBoxRegime.addItem("");
        txtLocalizar.setText("");
        jComboBoxRegime.setSelectedIndex(0);
        jComboBoxSiglaCoordenadoria.setSelectedIndex(0);
    }

    public void pesquisar() {
        listPresidio.clear();//limpa lista
        DefaultTableModel dtm = (DefaultTableModel) jTablePresidio.getModel();
        dtm.setNumRows(0);//LIMPA A TABELA
        String str = "";
        str = txtLocalizar.getText();
        if (jcbPesquisarPresidio.getSelectedItem().toString().equals("Sigla")) {
            listPresidio.addAll(controlePresidio.listarSigla(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        if (jcbPesquisarPresidio.getSelectedItem().toString().equals("Nome")) {
            listPresidio.addAll(controlePresidio.listarNome(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        if (jcbPesquisarPresidio.getSelectedItem().toString().equals("Cidade")) {
            listPresidio.addAll(controlePresidio.listarCidade(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        txtLocalizar.requestFocus();
        atualizaTabela();//PREENCHE COM OS NOVOS DADOS
    }

    /**
     * Creates new form FormularioCela
     */
    public void vincularCampos() {
        Presidio f = listPresidio.get(jTablePresidio.getSelectedRow());// pega a linha da tabela
        txtNome.setText(f.getNome());
        txtSigla.setText(f.getSigla());
        txtCidade.setText(f.getCidade());
        jComboBoxSiglaCoordenadoria.setSelectedItem(f.getCoordenadoria().getSigla()); // pega a bebida do comboBox
        jComboBoxRegime.setSelectedItem(f.getRegime()); // pega a bebida do comboBox

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PersistenciaPU").createEntityManager();
        queryPresidio = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("from Presidio p order by p.nome");
        listPresidio = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(queryPresidio.getResultList());
        queryCoordenadoria = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("from Coordenadoria f order by f.sigla");
        listaSiglaCoordenadoria = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(queryCoordenadoria.getResultList());
        painelSentenciado = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSigla = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxSiglaCoordenadoria = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxRegime = new javax.swing.JComboBox<>();
        painelAcoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePresidio = new javax.swing.JTable();
        jcbPesquisarPresidio = new javax.swing.JComboBox<>();
        txtLocalizar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastraro de Presidio");

        painelSentenciado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Presídio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N
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
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });
        painelSentenciado.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 640, -1));

        jLabel9.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel9.setText("Sigla Presídio:");
        painelSentenciado.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, -1, -1));

        txtSigla.setFont(txtSigla.getFont().deriveFont(txtSigla.getFont().getSize()+3f));
        txtSigla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSiglaActionPerformed(evt);
            }
        });
        txtSigla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSiglaKeyPressed(evt);
            }
        });
        painelSentenciado.add(txtSigla, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, 190, -1));

        jLabel15.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel15.setText("Cidade:");
        painelSentenciado.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        txtCidade.setFont(txtCidade.getFont().deriveFont(txtCidade.getFont().getSize()+3f));
        txtCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeActionPerformed(evt);
            }
        });
        txtCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCidadeKeyPressed(evt);
            }
        });
        painelSentenciado.add(txtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 370, -1));

        jLabel11.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel11.setText("Sigla Cordenadoria:");
        painelSentenciado.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, -1, -1));

        jComboBoxSiglaCoordenadoria.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jComboBoxSiglaCoordenadoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxSiglaCoordenadoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSiglaCoordenadoriaActionPerformed(evt);
            }
        });
        jComboBoxSiglaCoordenadoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxSiglaCoordenadoriaKeyPressed(evt);
            }
        });
        painelSentenciado.add(jComboBoxSiglaCoordenadoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 180, -1));

        jLabel16.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel16.setText("Regime:");
        painelSentenciado.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 80, -1, -1));

        jComboBoxRegime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxRegime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha um Regime", "Fechado", "Semi-Aberto", "Inexistente" }));
        jComboBoxRegime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxRegimeKeyPressed(evt);
            }
        });
        painelSentenciado.add(jComboBoxRegime, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 80, 180, -1));

        painelAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18))); // NOI18N

        btnNovo.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/adicionar.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        btnNovo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNovoKeyPressed(evt);
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
        btnAlterar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAlterarKeyPressed(evt);
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
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
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
        btnSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSalvarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout painelAcoesLayout = new javax.swing.GroupLayout(painelAcoes);
        painelAcoes.setLayout(painelAcoesLayout);
        painelAcoesLayout.setHorizontalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        painelAcoesLayout.setVerticalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo)
                    .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAlterar)
                        .addComponent(btnCancelar)
                        .addComponent(btnSalvar)))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jButtonPesquisar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Presidios por:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jTablePresidio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sigla Presidio", "Nome", "Cidade", "Regime", "Coordenadoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePresidio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePresidioMouseClicked(evt);
            }
        });
        jTablePresidio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTablePresidioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePresidio);

        jcbPesquisarPresidio.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jcbPesquisarPresidio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sigla", "Nome", "Cidade" }));
        jcbPesquisarPresidio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPesquisarPresidioActionPerformed(evt);
            }
        });
        jcbPesquisarPresidio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcbPesquisarPresidioKeyPressed(evt);
            }
        });

        txtLocalizar.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        txtLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocalizarActionPerformed(evt);
            }
        });
        txtLocalizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLocalizarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalizarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jButtonPesquisarLayout = new javax.swing.GroupLayout(jButtonPesquisar);
        jButtonPesquisar.setLayout(jButtonPesquisarLayout);
        jButtonPesquisarLayout.setHorizontalGroup(
            jButtonPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPesquisarLayout.createSequentialGroup()
                .addGroup(jButtonPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jButtonPesquisarLayout.createSequentialGroup()
                        .addComponent(jcbPesquisarPresidio, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLocalizar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE))
                .addContainerGap())
        );
        jButtonPesquisarLayout.setVerticalGroup(
            jButtonPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPesquisarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jButtonPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbPesquisarPresidio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(painelSentenciado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelSentenciado, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtSiglaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSiglaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSiglaActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        f = new Presidio();
        limparCampos();
        trataEdicao(true);
        txtNome.requestFocus();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        if (jTablePresidio.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para alterar", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            f = listPresidio.get(jTablePresidio.getSelectedRow());
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
        limparCampos();
        txtLocalizar.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if (!txtNome.getText().equals("")) {
            if (!txtSigla.getText().equals("")) {
                if (!txtCidade.getText().equals("")) {
                    if (jComboBoxSiglaCoordenadoria.getSelectedIndex() != 0) {
                        if (jComboBoxRegime.getSelectedIndex() != 0) {
                            f.setNome(txtNome.getText().trim());
                            f.setSigla(txtSigla.getText().trim());
                            f.setCidade(txtCidade.getText().trim());
                            f.setRegime(String.valueOf(jComboBoxRegime.getSelectedItem()));
                            f.setCoordenadoria(listCoordenadoria.get(jComboBoxSiglaCoordenadoria.getSelectedIndex() - 1));
                            controlePresidio.alterar(f);//salvar o orçamento
                            carregarLista();
                            atualizaTabela();
                            limparCampos();
                            trataEdicao(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Adicione o Regime!", "Alerta", JOptionPane.WARNING_MESSAGE);
                            jComboBoxRegime.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Informe  a Sigla da Coordenadoria!", "Alerta", JOptionPane.WARNING_MESSAGE);
                        jComboBoxSiglaCoordenadoria.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Informe a Cidade!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    txtCidade.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha a Sigla!", "Alerta", JOptionPane.WARNING_MESSAGE);
                txtSigla.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Digite o Nome!", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocus();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeActionPerformed

    private void jTablePresidioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePresidioMouseClicked
        // TODO add your handling code here:
        vincularCampos();
    }//GEN-LAST:event_jTablePresidioMouseClicked

    private void jComboBoxSiglaCoordenadoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSiglaCoordenadoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSiglaCoordenadoriaActionPerformed

    private void jcbPesquisarPresidioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPesquisarPresidioActionPerformed
        // TODO add your handling code here:
        txtLocalizar.setEnabled(true);
        txtLocalizar.requestFocus();
    }//GEN-LAST:event_jcbPesquisarPresidioActionPerformed

    private void txtLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalizarActionPerformed

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtSigla.requestFocus();
        }
    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtSiglaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSiglaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtCidade.requestFocus();
        }
    }//GEN-LAST:event_txtSiglaKeyPressed

    private void txtCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCidadeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jComboBoxSiglaCoordenadoria.requestFocus();
        }
    }//GEN-LAST:event_txtCidadeKeyPressed

    private void jComboBoxSiglaCoordenadoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxSiglaCoordenadoriaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jComboBoxRegime.requestFocus();
        }
    }//GEN-LAST:event_jComboBoxSiglaCoordenadoriaKeyPressed

    private void jComboBoxRegimeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxRegimeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jcbPesquisarPresidio.requestFocus();
        }
    }//GEN-LAST:event_jComboBoxRegimeKeyPressed

    private void jcbPesquisarPresidioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbPesquisarPresidioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtLocalizar.requestFocus();
        }
    }//GEN-LAST:event_jcbPesquisarPresidioKeyPressed

    private void btnNovoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNovoKeyPressed
        // TODO add your handling code here:if(evt.getKeyCode() == evt.VK_ENTER){
        if (evt.getKeyCode() == evt.VK_ENTER) {
            f = new Presidio();
            limparCampos();
            trataEdicao(true);
            txtNome.requestFocus();
        }
    }//GEN-LAST:event_btnNovoKeyPressed

    private void btnAlterarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAlterarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (jTablePresidio.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para alterar", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {
                f = listPresidio.get(jTablePresidio.getSelectedRow());
                trataEdicao(true);
                txtNome.requestFocus();
            }
            carregarLista();
            atualizaTabela();
        }
    }//GEN-LAST:event_btnAlterarKeyPressed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            trataEdicao(false);
            limparCampos();
            carregarLista();
            atualizaTabela();
            limparCampos();
            txtLocalizar.setEnabled(false);
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void btnSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalvarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (!txtNome.getText().equals("")) {
                if (!txtSigla.getText().equals("")) {
                    if (!txtCidade.getText().equals("")) {
                        if (jComboBoxSiglaCoordenadoria.getSelectedIndex() != 0) {
                            if (jComboBoxRegime.getSelectedIndex() != 0) {
                                f.setNome(txtNome.getText().trim());
                                f.setSigla(txtSigla.getText().trim());
                                f.setCidade(txtCidade.getText().trim());
                                f.setRegime(String.valueOf(jComboBoxRegime.getSelectedItem()));
                                f.setCoordenadoria(listCoordenadoria.get(jComboBoxSiglaCoordenadoria.getSelectedIndex() - 1));
                                controlePresidio.alterar(f);//salvar o orçamento
                                carregarLista();
                                atualizaTabela();
                                limparCampos();
                                trataEdicao(false);
                            } else {
                                JOptionPane.showMessageDialog(null, "Adicione o Regime!", "Alerta", JOptionPane.WARNING_MESSAGE);
                                jComboBoxRegime.requestFocus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Informe  a Sigla da Coordenadoria!", "Alerta", JOptionPane.WARNING_MESSAGE);
                            jComboBoxSiglaCoordenadoria.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Informe a Cidade!", "Alerta", JOptionPane.WARNING_MESSAGE);
                        txtCidade.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha a Sigla!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    txtSigla.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite o Nome!", "Alerta", JOptionPane.WARNING_MESSAGE);
                txtNome.requestFocus();
            }
        }
    }//GEN-LAST:event_btnSalvarKeyPressed

    private void jTablePresidioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePresidioKeyPressed
        // TODO add your handling code here:
        vincularCampos();
    }//GEN-LAST:event_jTablePresidioKeyPressed

    private void txtLocalizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnAlterar.requestFocus();
        }                                     
    }//GEN-LAST:event_txtLocalizarKeyPressed

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
            java.util.logging.Logger.getLogger(CadastroPresidio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroPresidio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroPresidio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroPresidio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                CadastroPresidio dialog = new CadastroPresidio(new javax.swing.JFrame(), true);
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
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JPanel jButtonPesquisar;
    private javax.swing.JComboBox<String> jComboBoxRegime;
    private javax.swing.JComboBox<String> jComboBoxSiglaCoordenadoria;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePresidio;
    private javax.swing.JComboBox<String> jcbPesquisarPresidio;
    private java.util.List<Presidio> listPresidio;
    private java.util.List<Coordenadoria> listaSiglaCoordenadoria;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelSentenciado;
    private javax.persistence.Query queryCoordenadoria;
    private javax.persistence.Query queryPresidio;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtLocalizar;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSigla;
    // End of variables declaration//GEN-END:variables
}

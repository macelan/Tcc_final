/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.visao;

import br.edu.ifsp.tcc.controle.ControleFaccao;
import br.edu.ifsp.tcc.controle.ControleSentenciado;
import br.edu.ifsp.tcc.modelo.Faccao;
import br.edu.ifsp.tcc.modelo.Sentenciado;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author macel
 */
public class CadastroSentenciado extends javax.swing.JDialog {

    private ControleSentenciado controleSentenciado = new ControleSentenciado();
    private ControleFaccao controleFaccoes = new ControleFaccao();
    private List<Sentenciado> listaSentenciados = new ArrayList();
    private List<Faccao> listaFaccao = new ArrayList<>();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // o mes tem que ser maiusculo
    Sentenciado f = new Sentenciado();

    public CadastroSentenciado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarLista();
        atualizaTabela();
        TamanhoColuna();
        trataEdicao(false);
        for(Faccao f:listaFaccao){
        jComboBoxSiglaFaccao.addItem(f.getSiglaFaccao());
        }
        btnNovo.requestFocus();
    }

    private void trataEdicao(boolean editando) {
        btnCancelar.setEnabled(editando);
        btnSalvar.setEnabled(editando);
        btnAlterar.setEnabled(!editando);
        btnNovo.setEnabled(!editando);
        txtRg.setEditable(editando);
        jDateChooserDataNascimento.setEnabled(editando);
        txtNome.setEditable(editando);
        txtMatricula.setEditable(editando);
        txtAliase.setEditable(editando);
        jcbPesquisarSentenciado.setEditable(editando);
        //txtLocalizar.setEditable(editando);
        jTableSentenciado.setEnabled(!editando);
    }

    public void TamanhoColuna() {
        jTableSentenciado.getColumnModel().getColumn(0).setMaxWidth(100);
        jTableSentenciado.getColumnModel().getColumn(1).setMaxWidth(300);
        jTableSentenciado.getColumnModel().getColumn(2).setMaxWidth(70);
        jTableSentenciado.getColumnModel().getColumn(3).setMaxWidth(180);
        jTableSentenciado.getColumnModel().getColumn(4).setMaxWidth(180);
        jTableSentenciado.getColumnModel().getColumn(5).setMaxWidth(180);
        jTableSentenciado.getColumnModel().getColumn(6).setMaxWidth(70);
        jTableSentenciado.setAutoCreateRowSorter(true); // ordena ao clicar na coluna
        JTableHeader jth = jTableSentenciado.getTableHeader();
        jth.setFont(new Font("Arial Unicod MS", Font.BOLD, 14));
    }

    public void atualizaTabela() {
        DefaultTableModel dtm = (DefaultTableModel) jTableSentenciado.getModel();
        dtm.setNumRows(0);//apagando todas as linhas
        for (Sentenciado f : listaSentenciados) {
            dtm.addRow(new Object[]{f.getMatricula(),
                f.getNome(),
                f.getFaccao(),
                df.format(f.getDataNascimento()),
                f.getQuantidadeSindicancia(),
                f.getQuantidadeObservacao(),
                (f.getAtivo()? "Ativo": "Inativo")});
        }

    }
 
    public void carregarLista() {
        listaSentenciados = controleSentenciado.buscar();
        listaFaccao =  controleFaccoes.buscar();
    }

    public void limparCampos() {
        txtLocalizar.setText("");
        txtRg.setText("");
        jDateChooserDataNascimento.setDate(null);
        txtNome.setText("");
        txtAliase.setText("");
        txtMatricula.setText("");
        jComboBoxSiglaFaccao.setSelectedIndex(0);

    }

    public void pesquisar() {
        listaSentenciados.clear();//limpa lista
        DefaultTableModel dtm = (DefaultTableModel) jTableSentenciado.getModel();
        dtm.setNumRows(0);//LIMPA A TABELA
        String str = "";
        str = txtLocalizar.getText();
        if (jcbPesquisarSentenciado.getSelectedItem().toString().equals("Nome")) {
            listaSentenciados.addAll(controleSentenciado.listarNome(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        if (jcbPesquisarSentenciado.getSelectedItem().toString().equals("Matricula")) {
            listaSentenciados.addAll(controleSentenciado.listarMatricula(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        txtLocalizar.requestFocus();
        atualizaTabela();//PREENCHE COM OS NOVOS DADOS
    }
    

    public void vincularCampos() {
      
        Sentenciado f = listaSentenciados.get(jTableSentenciado.getSelectedRow());// pega a linha da tabela
        txtMatricula.setText(f.getMatricula());
        txtNome.setText(f.getNome());
        txtRg.setText(f.getRg());
        jComboBoxSiglaFaccao.setSelectedItem(f.getFaccao().getSiglaFaccao()); // pega a facção do comboBox
        jDateChooserDataNascimento.setDate(f.getDataNascimento());
        txtAliase.setText(f.getAliases());
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
        queryFaccao = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("from Faccao f order by f.siglaFaccao");
        listFaccao = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(queryFaccao.getResultList());
        painelSentenciado = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtAliase = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        espaco = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JFormattedTextField();
        txtRg = new javax.swing.JFormattedTextField();
        jComboBoxSiglaFaccao = new javax.swing.JComboBox<>();
        jDateChooserDataNascimento = new com.toedter.calendar.JDateChooser();
        painelAcoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        painelTabela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSentenciado = new javax.swing.JTable();
        txtLocalizar = new javax.swing.JTextField();
        jcbPesquisarSentenciado = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Sentenciado");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        painelSentenciado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Sentenciado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N
        painelSentenciado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel8.setText("Matricula:");
        painelSentenciado.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel10.setText("Nome:");
        painelSentenciado.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        txtNome.setFont(txtNome.getFont().deriveFont(txtNome.getFont().getSize()+3f));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 430, -1));

        jLabel12.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel12.setText("Data de Nascimento:");
        painelSentenciado.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel7.setText("Apelidos:");
        painelSentenciado.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel16.setText("Facção:");
        painelSentenciado.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 60, -1));

        txtAliase.setFont(txtAliase.getFont().deriveFont(txtAliase.getFont().getSize()+3f));
        txtAliase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAliaseActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtAliase, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 330, -1));

        jLabel9.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel9.setText("Rg:");
        painelSentenciado.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, -1, -1));
        painelSentenciado.add(espaco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, 20));

        try {
            txtMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMatriculaFocusLost(evt);
            }
        });
        txtMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatriculaKeyReleased(evt);
            }
        });
        painelSentenciado.add(txtMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 130, -1));

        try {
            txtRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtRg.setMinimumSize(new java.awt.Dimension(6, 23));
        txtRg.setPreferredSize(new java.awt.Dimension(109, 23));
        txtRg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRgActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtRg, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 140, -1));

        jComboBoxSiglaFaccao.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jComboBoxSiglaFaccao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar" }));
        jComboBoxSiglaFaccao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSiglaFaccaoActionPerformed(evt);
            }
        });
        painelSentenciado.add(jComboBoxSiglaFaccao, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 70, 130, -1));
        painelSentenciado.add(jDateChooserDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 160, -1));

        getContentPane().add(painelSentenciado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1020, 110));

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
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        painelAcoesLayout.setVerticalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo)
                    .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar)
                        .addComponent(btnSalvar)
                        .addComponent(btnAlterar)))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        getContentPane().add(painelAcoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 1010, -1));

        painelTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Sentenciados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jTableSentenciado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nome", "Facção", "D. de Nascimento", "Qtd. de Sindicância", "Qtd. de Observação", "Ativo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSentenciado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSentenciadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableSentenciado);
        if (jTableSentenciado.getColumnModel().getColumnCount() > 0) {
            jTableSentenciado.getColumnModel().getColumn(0).setResizable(false);
            jTableSentenciado.getColumnModel().getColumn(1).setResizable(false);
            jTableSentenciado.getColumnModel().getColumn(2).setResizable(false);
            jTableSentenciado.getColumnModel().getColumn(3).setResizable(false);
            jTableSentenciado.getColumnModel().getColumn(4).setResizable(false);
            jTableSentenciado.getColumnModel().getColumn(5).setResizable(false);
            jTableSentenciado.getColumnModel().getColumn(6).setResizable(false);
        }

        txtLocalizar.setFont(txtLocalizar.getFont().deriveFont(txtLocalizar.getFont().getSize()+3f));
        txtLocalizar.setEnabled(false);
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

        jcbPesquisarSentenciado.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jcbPesquisarSentenciado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Matricula" }));
        jcbPesquisarSentenciado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbPesquisarSentenciadoMouseClicked(evt);
            }
        });
        jcbPesquisarSentenciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPesquisarSentenciadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelTabelaLayout = new javax.swing.GroupLayout(painelTabela);
        painelTabela.setLayout(painelTabelaLayout);
        painelTabelaLayout.setHorizontalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelTabelaLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(painelTabelaLayout.createSequentialGroup()
                        .addComponent(jcbPesquisarSentenciado, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLocalizar))))
        );
        painelTabelaLayout.setVerticalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTabelaLayout.createSequentialGroup()
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbPesquisarSentenciado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(painelTabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1020, 340));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtAliaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAliaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAliaseActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        f = new Sentenciado();
        trataEdicao(true);
        txtMatricula.requestFocus();
        limparCampos();
        
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        if (jTableSentenciado.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para alterar", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            f = listaSentenciados.get(jTableSentenciado.getSelectedRow());
            trataEdicao(true);
            txtMatricula.requestFocus();
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
      if (!txtMatricula.getText().equals("   .   - ")) {
            if (!txtNome.getText().equals("")) {
                if (!txtRg.getText().equals("  .   .   - ")) {
                    if (jComboBoxSiglaFaccao.getSelectedIndex() != 0) {
                        if (jDateChooserDataNascimento.getDate() != null) {
                            if (!txtAliase.getText().equals("")) {
                                    f.setMatricula(txtMatricula.getText());
                                    f.setNome(txtNome.getText().trim());
                                    f.setRg(txtRg.getText());
                                    f.setFaccao(listFaccao.get(jComboBoxSiglaFaccao.getSelectedIndex()-1)); // cast
                                    f.setDataNascimento(jDateChooserDataNascimento.getDate());
                                    f.setAliases(txtAliase.getText().trim());
                                    f.setDataCadastro(new Date());
                                    f.setAtivo(true);
                                    f.setQuantidadeObservacao(0);
                                    f.setQuantidadeSindicancia(0);
                                    controleSentenciado.alterar(f);//salvar o orçamento
                                    carregarLista();
                                    atualizaTabela();
                                    limparCampos();
                                    trataEdicao(false);
                                } else {
                                JOptionPane.showMessageDialog(null, "Informe o Apelido!", "Alerta", JOptionPane.WARNING_MESSAGE);
                                txtAliase.requestFocus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Selecione a Data de Nascimento!", "Alerta", JOptionPane.WARNING_MESSAGE);
                            jDateChooserDataNascimento.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione a Sigla da Facção", "Alerta", JOptionPane.WARNING_MESSAGE);
                        jComboBoxSiglaFaccao.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Informe o RG!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    txtRg.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Informe o Nome!", "Alerta", JOptionPane.WARNING_MESSAGE);
                txtNome.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Informe a Matricula!", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtMatricula.requestFocus();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalizarActionPerformed

    private void jTableSentenciadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSentenciadoMouseClicked
        // TODO add your handling code here:
        limparCampos();
        vincularCampos();
    }//GEN-LAST:event_jTableSentenciadoMouseClicked

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void txtRgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRgActionPerformed

    private void jcbPesquisarSentenciadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbPesquisarSentenciadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbPesquisarSentenciadoMouseClicked

    private void jcbPesquisarSentenciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPesquisarSentenciadoActionPerformed
        // TODO add your handling code here:
        txtLocalizar.setEnabled(true);
        txtLocalizar.requestFocus();
    }//GEN-LAST:event_jcbPesquisarSentenciadoActionPerformed

    private void jComboBoxSiglaFaccaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSiglaFaccaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSiglaFaccaoActionPerformed

    private void txtMatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatriculaKeyReleased
        // TODO add your handling code here:

        
    }//GEN-LAST:event_txtMatriculaKeyReleased
    // Executa quando sair do foco
    private void txtMatriculaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatriculaFocusLost
        // TODO add your handling code here:
//        String retorno = controleSentenciado.consultarMatricula(txtMatricula.getSelectedText());
//        if(retorno == null){
//        JOptionPane.showMessageDialog(null, "Matricula ja Existe.");
//        txtMatricula.setText("");
//        txtMatricula.requestFocus();
//        }
    }//GEN-LAST:event_txtMatriculaFocusLost

    private void btnNovoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNovoKeyPressed
        // TODO add your handling code here:
            if (evt.getKeyCode() == evt.VK_ENTER) {
            f = new Sentenciado();
            limparCampos();
            trataEdicao(true);
            txtMatricula.requestFocus();
        }
    }//GEN-LAST:event_btnNovoKeyPressed

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
            java.util.logging.Logger.getLogger(CadastroSentenciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroSentenciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroSentenciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroSentenciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroSentenciado dialog = new CadastroSentenciado(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel espaco;
    private javax.swing.JComboBox<String> jComboBoxSiglaFaccao;
    private com.toedter.calendar.JDateChooser jDateChooserDataNascimento;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSentenciado;
    private javax.swing.JComboBox<String> jcbPesquisarSentenciado;
    private java.util.List<Faccao> listFaccao;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelSentenciado;
    private javax.swing.JPanel painelTabela;
    private javax.persistence.Query queryFaccao;
    private javax.swing.JTextField txtAliase;
    private javax.swing.JTextField txtLocalizar;
    private javax.swing.JFormattedTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtRg;
    // End of variables declaration//GEN-END:variables
}

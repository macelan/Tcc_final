/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.visao;

import br.edu.ifsp.tcc.controle.ControleFuncionario;
import br.edu.ifsp.tcc.controle.ControlePresidio;
import br.edu.ifsp.tcc.modelo.Funcionario;
import br.edu.ifsp.tcc.modelo.Presidio;
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
public class CadastroFuncionario extends javax.swing.JDialog {

    private ControleFuncionario controleFuncinario = new ControleFuncionario();
    private ControlePresidio controlePresidio = new ControlePresidio();
    private List<Funcionario> listaFuncionarios = new ArrayList();
    private List<Presidio> listaPresidio = new ArrayList();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // o mes tem que ser maiusculo
    Funcionario f = new Funcionario();
    boolean controleAlterar =  false;

    public CadastroFuncionario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarLista();
        atualizaTabela();
        TamanhoColuna();
        trataEdicao(false);
        buscaListaSiglaPresidio();
    }

    private void buscaListaSiglaPresidio() {
        for (Presidio p : listaPresidio) {
            jComboBoxSiglaPresidio.addItem(p.getSigla());
        }
    }

    private void trataEdicao(boolean editando) {
        btnCancelar.setEnabled(editando);
        btnSalvar.setEnabled(editando);
        btnAlterar.setEnabled(!editando);
        btnNovo.setEnabled(!editando);
        txtRgFuncionario.setEditable(editando);
        jDateChooserDataNascimento.setEnabled(editando);
        txtLogin.setEditable(editando);
        txtSenha.setEditable(editando);
        jcbPesquisarFuncionario.setEditable(editando);
        jComboBoxTipoUsuario.setEditable(editando);
        //txtLocalizar.setEditable(editando);
        jTableFuncionario.setEnabled(!editando);
    }

    public void TamanhoColuna() {
        jTableFuncionario.getColumnModel().getColumn(0).setMaxWidth(350);
        jTableFuncionario.getColumnModel().getColumn(1).setMaxWidth(100);
        jTableFuncionario.getColumnModel().getColumn(2).setMaxWidth(150);
        jTableFuncionario.getColumnModel().getColumn(3).setMaxWidth(350);
        jTableFuncionario.getColumnModel().getColumn(4).setMaxWidth(100);
        jTableFuncionario.getColumnModel().getColumn(5).setMaxWidth(100);
        jTableFuncionario.setAutoCreateRowSorter(true); // ordena ao clicar na coluna
        JTableHeader jth = jTableFuncionario.getTableHeader();
        jth.setFont(new Font("Arial Unicod MS", Font.BOLD, 14));
    }

    public void atualizaTabela() {
        DefaultTableModel dtm = (DefaultTableModel) jTableFuncionario.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serva para apagar as linhas "é muito rapido"
        for (Funcionario f : listaFuncionarios) {
            dtm.addRow(new Object[]{f.getNome(), f.getRg(), df.format(f.getDataNascimento()), f.getPresidio().getNome(), f.getTipoUsuario(), (f.getAtivo() ? "Ativo" : "Inativo")}); // Object é um vetor ou coleção com ele não pecisa converter
        }
    }

    public void carregarLista() {
        listaFuncionarios = controleFuncinario.buscar();
        listaPresidio = controlePresidio.buscar();
    }

    public void limparCampos() {
        txtLocalizar.setText("");
        txtNome.setText("");
        jDateChooserDataNascimento.setDate(null);
        txtLogin.setText("");
        txtSenha.setText("");
        txtRgFuncionario.setText("");
        jComboBoxSiglaPresidio.setSelectedIndex(0);
        jComboBoxTipoUsuario.setSelectedIndex(0);

    }

    public void pesquisar() {
        listaFuncionarios.clear();//limpa lista
        DefaultTableModel dtm = (DefaultTableModel) jTableFuncionario.getModel();
        dtm.setNumRows(0);//LIMPA A TABELA
        String str = "";
        str = txtLocalizar.getText();
        if (jcbPesquisarFuncionario.getSelectedItem().toString().equals("RG")) {
            listaFuncionarios.addAll(controleFuncinario.listarRg(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        if (jcbPesquisarFuncionario.getSelectedItem().toString().equals("Nome")) {
            listaFuncionarios.addAll(controleFuncinario.listarNome(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        if (jcbPesquisarFuncionario.getSelectedItem().toString().equals("Presidio")) {
            listaFuncionarios.addAll(controleFuncinario.listarPresidio(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        txtLocalizar.requestFocus();
        atualizaTabela();//PREENCHE COM OS NOVOS DADOS
    }

    public void vincularCampos() {
        Funcionario f = listaFuncionarios.get(jTableFuncionario.getSelectedRow());// pega a linha da tabela
        txtLogin.setText(f.getUsuario());
        txtSenha.setText(f.getSenha());
        txtNome.setText(f.getNome());
        jDateChooserDataNascimento.setDate(f.getDataNascimento());
        txtRgFuncionario.setText(f.getRg());
        jComboBoxSiglaPresidio.setSelectedItem(f.getPresidio().getSigla()); // pega a presidio do comboBox
        jComboBoxTipoUsuario.setSelectedItem(f.getTipoUsuario()); // pega o usuário do comboBox
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
        queryPresidioFuncionario = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("from Presidio p order by p.sigla");
        listPresidioFuncionario = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(queryPresidioFuncionario.getResultList());
        painelSentenciado = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        espaco = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jDateChooserDataNascimento = new com.toedter.calendar.JDateChooser();
        jComboBoxSiglaPresidio = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxTipoUsuario = new javax.swing.JComboBox<>();
        txtRgFuncionario = new javax.swing.JTextField();
        painelTabela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFuncionario = new javax.swing.JTable();
        txtLocalizar = new javax.swing.JTextField();
        jcbPesquisarFuncionario = new javax.swing.JComboBox<>();
        painelAcoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Funcionario");

        painelSentenciado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Funcionario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N
        painelSentenciado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel9.setText("Rg:");
        painelSentenciado.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel11.setText("Nome:");
        painelSentenciado.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        txtNome.setFont(txtNome.getFont().deriveFont(txtNome.getFont().getSize()+3f));
        txtNome.setMaximumSize(new java.awt.Dimension(6, 20));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 300, -1));

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel10.setText("Tipo :");
        painelSentenciado.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, -1, -1));

        txtLogin.setFont(txtLogin.getFont().deriveFont(txtLogin.getFont().getSize()+3f));
        txtLogin.setMaximumSize(new java.awt.Dimension(6, 20));
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 150, -1));

        jLabel12.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel12.setText("Data de Nascimento:");
        painelSentenciado.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel15.setText("Senha:");
        painelSentenciado.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel16.setText("Login:");
        painelSentenciado.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 60, -1));
        painelSentenciado.add(espaco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, 20));

        txtSenha.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtSenha.setText("jPasswordField3");
        txtSenha.setMaximumSize(new java.awt.Dimension(6, 20));
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 160, -1));

        jDateChooserDataNascimento.setMaximumSize(new java.awt.Dimension(6, 20));
        painelSentenciado.add(jDateChooserDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 240, -1));

        jComboBoxSiglaPresidio.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jComboBoxSiglaPresidio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxSiglaPresidio.setMaximumSize(new java.awt.Dimension(6, 20));
        painelSentenciado.add(jComboBoxSiglaPresidio, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 110, -1));

        jLabel13.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel13.setText("Sigla Presidio Lotado:");
        painelSentenciado.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, -1, -1));

        jComboBoxTipoUsuario.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jComboBoxTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Usuário", "Administrador" }));
        jComboBoxTipoUsuario.setMaximumSize(new java.awt.Dimension(6, 20));
        painelSentenciado.add(jComboBoxTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 70, 110, -1));
        painelSentenciado.add(txtRgFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 150, -1));

        painelTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Funcionários por:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jTableFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Rg", "D. Nascimento", "Nome Presidio", "Tipo", "Ativo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFuncionario);
        if (jTableFuncionario.getColumnModel().getColumnCount() > 0) {
            jTableFuncionario.getColumnModel().getColumn(0).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(1).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(2).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(3).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(4).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(5).setResizable(false);
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

        jcbPesquisarFuncionario.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jcbPesquisarFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "RG", "Presidio" }));
        jcbPesquisarFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbPesquisarFuncionarioMouseClicked(evt);
            }
        });
        jcbPesquisarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPesquisarFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelTabelaLayout = new javax.swing.GroupLayout(painelTabela);
        painelTabela.setLayout(painelTabelaLayout);
        painelTabelaLayout.setHorizontalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelTabelaLayout.createSequentialGroup()
                        .addComponent(jcbPesquisarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLocalizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelTabelaLayout.setVerticalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTabelaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbPesquisarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
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
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelSentenciado, javax.swing.GroupLayout.PREFERRED_SIZE, 1077, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(painelSentenciado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        f = new Funcionario();
        trataEdicao(true);
        txtRgFuncionario.requestFocus();
        limparCampos();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
      
        if (jTableFuncionario.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para alterar", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            controleAlterar = true;
            f = listaFuncionarios.get(jTableFuncionario.getSelectedRow());
            trataEdicao(true);
            txtRgFuncionario.requestFocus();
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
        JOptionPane.showMessageDialog(this, "OPERAÇÃO CANCELADA", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        txtLocalizar.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        boolean ret = controleFuncinario.confirmaRg(txtRgFuncionario.getText());//Recebe true se existir
        boolean retLogin = controleFuncinario.confirmaLogin(txtLogin.getText());//Recebe true se existir
        if (!txtRgFuncionario.getText().equals("")) {
            if (!txtNome.getText().equals("")) {
                if (jDateChooserDataNascimento.getDate() != null) {
                    if (!txtLogin.getText().isEmpty()) {
                        if (!txtSenha.getText().isEmpty()) {
                            if (jComboBoxSiglaPresidio.getSelectedIndex() != 0) {
                                if (jComboBoxTipoUsuario.getSelectedIndex() != 0) {
                                    if ((!ret )|| (controleAlterar)) {
                                        if ((!retLogin) || (controleAlterar)) {
                                          
                                            f.setDataNascimento(jDateChooserDataNascimento.getDate());
                                            f.setNome(txtNome.getText().trim());
                                            f.setRg(txtRgFuncionario.getText().trim());
                                            f.setUsuario(txtLogin.getText().trim());
                                            f.setSenha(txtSenha.getText().trim());
                                            f.setDataCadastro(new Date());
                                            f.setPresidio(listaPresidio.get(jComboBoxSiglaPresidio.getSelectedIndex() - 1));
                                            f.setTipoUsuario(String.valueOf(jComboBoxTipoUsuario.getSelectedItem()));
                                            f.setAtivo(true);
                                            if(controleAlterar){
                                            controleFuncinario.alterar(f);//salvar o orçamento
                                            }else
                                            controleFuncinario.inserir(f);
                                            if(controleAlterar){
                                            controleAlterar = false;
                                            }
                                            carregarLista();
                                            atualizaTabela();
                                            limparCampos();
                                            trataEdicao(false);
                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Login digitado já existe! Escolha outro novamente", "Alerta", JOptionPane.WARNING_MESSAGE);
                                            txtLogin.setText("");
                                            txtLogin.requestFocus();
                                        }

                                    } else {
                                        JOptionPane.showMessageDialog(null, "RG digitado já existe! Digite novamente", "Alerta", JOptionPane.WARNING_MESSAGE);
                                        txtRgFuncionario.setText("");
                                        txtRgFuncionario.requestFocus();
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Selecione o Usuário!", "Alerta", JOptionPane.WARNING_MESSAGE);
                                    jComboBoxTipoUsuario.requestFocus();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Selecione o Presidio!", "Alerta", JOptionPane.WARNING_MESSAGE);
                                jComboBoxSiglaPresidio.requestFocus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Adicione o Senha!", "Alerta", JOptionPane.WARNING_MESSAGE);
                            txtSenha.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Informe o login!", "Alerta", JOptionPane.WARNING_MESSAGE);
                        txtLogin.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Informe a data de Nascimento!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    jDateChooserDataNascimento.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha o Nome!", "Alerta", JOptionPane.WARNING_MESSAGE);
                txtNome.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha o RG!", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtRgFuncionario.requestFocus();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalizarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtLocalizarActionPerformed

    private void jTableFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFuncionarioMouseClicked
        // TODO add your handling code here:
        limparCampos();
        vincularCampos();
    }//GEN-LAST:event_jTableFuncionarioMouseClicked

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void jcbPesquisarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPesquisarFuncionarioActionPerformed
        // TODO add your handling code here:
        txtLocalizar.setEnabled(true);
        txtLocalizar.requestFocus();
    }//GEN-LAST:event_jcbPesquisarFuncionarioActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void jcbPesquisarFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbPesquisarFuncionarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbPesquisarFuncionarioMouseClicked

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
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroFuncionario dialog = new CadastroFuncionario(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBoxSiglaPresidio;
    private javax.swing.JComboBox<String> jComboBoxTipoUsuario;
    private com.toedter.calendar.JDateChooser jDateChooserDataNascimento;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFuncionario;
    private javax.swing.JComboBox<String> jcbPesquisarFuncionario;
    private java.util.List<Presidio> listPresidioFuncionario;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelSentenciado;
    private javax.swing.JPanel painelTabela;
    private javax.persistence.Query queryPresidioFuncionario;
    private javax.swing.JTextField txtLocalizar;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRgFuncionario;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}

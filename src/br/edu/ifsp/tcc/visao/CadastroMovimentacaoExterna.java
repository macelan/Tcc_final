/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.visao;

import br.edu.ifsp.tcc.controle.ControleCela;
import br.edu.ifsp.tcc.controle.ControleFaccao;
import br.edu.ifsp.tcc.controle.ControleMovimentacaoExterna;
import br.edu.ifsp.tcc.controle.ControleMovimentacaoInterna;
import br.edu.ifsp.tcc.controle.ControlePavilhao;
import br.edu.ifsp.tcc.controle.ControleSentenciado;
import br.edu.ifsp.tcc.modelo.Cela;
import br.edu.ifsp.tcc.modelo.MovimentacaoExterna;
import br.edu.ifsp.tcc.modelo.MovimentacaoInterna;
import br.edu.ifsp.tcc.modelo.Pavilhao;
import br.edu.ifsp.tcc.modelo.Presidio;
import br.edu.ifsp.tcc.modelo.Sentenciado;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
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
public class CadastroMovimentacaoExterna extends javax.swing.JDialog {

    private ControleMovimentacaoExterna controleME = new ControleMovimentacaoExterna();
    private ControleMovimentacaoInterna controleMI = new ControleMovimentacaoInterna();
    private ControleFaccao controleFaccoes = new ControleFaccao();
    private ControleCela controleCelas = new ControleCela();
    private ControlePavilhao controlePavilhaos = new ControlePavilhao();
    private ControleSentenciado controleSentenciados = new ControleSentenciado();

    private List<MovimentacaoExterna> listMovimentacoesExternas = new ArrayList();
    private List<Pavilhao> listPavilhao = new ArrayList();
    private List<Cela> listCela = new ArrayList();

    MovimentacaoInterna mi = new MovimentacaoInterna(); // new quando vou criar
    MovimentacaoExterna me = new MovimentacaoExterna();

    private Sentenciado sentenciado;// pega um objeto ja preenchido
    private Presidio presidio;

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // o mes tem que ser maiusculo
    DateFormat dfh = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // o mes tem que ser maiusculo

    public CadastroMovimentacaoExterna(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarLista();
        atualizaTabela();
        TamanhoColuna();
        trataEdicao(false);
        carregarInsercaoCelaPavilhao(false);
        carregarComboBoxPavilhao();
    }

    private void carregarComboBoxPavilhao() {
        for (Pavilhao p : listPavilhao) {
            jComboBoxSiglaPavilhao.addItem(p.getSigla());// vou criar um toSting para pegar a sigla
        }
        //    listPavilhao.addAll(controlePavilhaos.buscar());
    }

    /**
     * Parametro recebe uma sigla e retorna o objeto pavilhao correpondente
     *
     * @param sigla
     * @return
     */
    public Pavilhao localizaPavilhao(String sigla) {
        for (Pavilhao p : listPavilhao) { // localizando ja na lista carregada do for anterior
            if (p.getSigla().equals(sigla)) {
                return p;
            }
        }
        return null;
    }

    private void trataEdicao(boolean editando) {
        btnCancelar.setEnabled(editando);
        btnSalvar.setEnabled(editando);
        btnNovo.setEnabled(!editando);
        jDateChooserDataMovimentacaoExterna.setEnabled(editando);
        txtAdicionaSiglaPresidio.setEnabled(editando);
        txtAdicionaMatriculaSentenciado.setEnabled(editando);
        jComboBoxTipo.setEnabled(editando);
        jComboBoxMotivo.setEnabled(editando);
        jButtonAdicionaSiglaPresidio.setEnabled(editando);
        jButtonAdicionaSentenciado.setEnabled(editando);
        //txtLocalizarObsercacao.setEditable(editando);
        jTableMovimentacaoExterna.setEnabled(!editando);
    }

    /**
     * Este metodo controla a visibilidade dos botões de inserir cela e pavilhao
     * ativo = true !ativo = false
     *
     * @param ativo
     */
    public void carregarInsercaoCelaPavilhao(boolean ativo) {
        jLabelpavilhao.setVisible(ativo);
        jComboBoxSiglaPavilhao.setVisible(ativo);
        jLabelNumero.setVisible(ativo);
        jComboBoxCelaNumero.setVisible(ativo);
    }

    public void TamanhoColuna() {
        jTableMovimentacaoExterna.getColumnModel().getColumn(0).setMaxWidth(200);
        jTableMovimentacaoExterna.getColumnModel().getColumn(1).setMaxWidth(200);
        jTableMovimentacaoExterna.getColumnModel().getColumn(2).setMaxWidth(250);
        jTableMovimentacaoExterna.getColumnModel().getColumn(3).setMaxWidth(70);
        jTableMovimentacaoExterna.getColumnModel().getColumn(4).setMaxWidth(425);
        jTableMovimentacaoExterna.setAutoCreateRowSorter(true); // ordena ao clicar na coluna
        JTableHeader jth = jTableMovimentacaoExterna.getTableHeader();
        jth.setFont(new Font("Arial Unicod MS", Font.BOLD, 14));
    }

    public void atualizaTabela() {
        DefaultTableModel dtm = (DefaultTableModel) jTableMovimentacaoExterna.getModel();
        dtm.setNumRows(0);//apagando todas as linhas
        for (MovimentacaoExterna f : listMovimentacoesExternas) {
            dtm.addRow(new Object[]{f.getSentenciado().getMatricula(), df.format(f.getDataRegistro()), dfh.format(f.getDataRegistro()), f.getTipoMe(), f.getPresidio().getSigla()});
        }
    }

    public void carregarLista() {
        listMovimentacoesExternas = controleME.buscar();
        listPavilhao = controlePavilhaos.buscar();

    }

    public void limparCampos() {
        jDateChooserDataMovimentacaoExterna.setDate(null);
        jComboBoxTipo.setSelectedIndex(0);
        jComboBoxMotivo.setSelectedIndex(0);
        jComboBoxSiglaPavilhao.setSelectedIndex(0);
        jComboBoxMotivo.setSelectedIndex(0);
        txtAdicionaSiglaPresidio.setText("");
        txtAdicionaMatriculaSentenciado.setText("");
        //  txtAdicionaSiglaPresidio.setText("");
        txtLocalizar.setText("");
    }

    public void pesquisar() {
        listMovimentacoesExternas.clear();//limpa lista
        DefaultTableModel dtm = (DefaultTableModel) jTableMovimentacaoExterna.getModel();
        dtm.setNumRows(0);//LIMPA A TABELA
        String str = "";
        str = txtLocalizar.getText();
        if (jButtonPesquisar.getSelectedItem().toString().equals("Sigla Presidio")) {
            listMovimentacoesExternas.addAll(controleME.listarPresidio(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        if (jButtonPesquisar.getSelectedItem().toString().equals("Matricula")) {
            listMovimentacoesExternas.addAll(controleME.listarMatricula(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        txtLocalizar.requestFocus();
        atualizaTabela();//PREENCHE COM OS NOVOS DADOS
    }

    public void vincularCampos() {
        MovimentacaoExterna f = listMovimentacoesExternas.get(jTableMovimentacaoExterna.getSelectedRow());// pega a linha da tabela
        jDateChooserDataMovimentacaoExterna.setDate(f.getDataRegistro());
        jComboBoxTipo.setSelectedItem(f.getTipoMe());
        txtAdicionaSiglaPresidio.setText(f.getPresidio().getSigla());
        txtAdicionaMatriculaSentenciado.setText(f.getSentenciado().getMatricula());
//        if (jComboBoxTipo.setSelectedItem(me.getTipo().equals("IN")) {
//            carregarInsercaoCelaPavilhao(true);
//            jComboBoxSiglaPavilhao.setSelectedItem(p.getPavilhao().getSigla()); // pega a presidio do comboBox
        //          jComboBoxCelaNumero.setSelectedItem(c.getNumeroCela ; // pega o usuário do comboBox

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
        queryPresidio = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("from Presidio p order by p.sigla");
        listPresidio = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(queryPresidio.getResultList());
        painelSentenciado = new javax.swing.JPanel();
        espaco = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jDateChooserDataMovimentacaoExterna = new com.toedter.calendar.JDateChooser();
        jButtonAdicionaSiglaPresidio = new javax.swing.JButton();
        txtAdicionaSiglaPresidio = new javax.swing.JTextField();
        jButtonAdicionaSentenciado = new javax.swing.JButton();
        txtAdicionaMatriculaSentenciado = new javax.swing.JTextField();
        jLabelpavilhao = new javax.swing.JLabel();
        jComboBoxSiglaPavilhao = new javax.swing.JComboBox();
        jLabelNumero = new javax.swing.JLabel();
        jComboBoxCelaNumero = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jComboBoxMotivo = new javax.swing.JComboBox<>();
        painelAcoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        painelTabela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMovimentacaoExterna = new javax.swing.JTable();
        txtLocalizar = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Movimentação Externa");

        painelSentenciado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Movimentação Externa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N
        painelSentenciado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        painelSentenciado.add(espaco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, 20));

        jLabel13.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel13.setText("Data:");
        painelSentenciado.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel16.setText("Tipo:");
        painelSentenciado.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 40, -1));

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE", "EX", "IN" }));
        jComboBoxTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTipoItemStateChanged(evt);
            }
        });
        jComboBoxTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxTipoMouseClicked(evt);
            }
        });
        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });
        painelSentenciado.add(jComboBoxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 110, -1));
        painelSentenciado.add(jDateChooserDataMovimentacaoExterna, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 270, -1));

        jButtonAdicionaSiglaPresidio.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jButtonAdicionaSiglaPresidio.setText("Sigla Presidio:");
        jButtonAdicionaSiglaPresidio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionaSiglaPresidioActionPerformed(evt);
            }
        });
        painelSentenciado.add(jButtonAdicionaSiglaPresidio, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, -1, 20));

        txtAdicionaSiglaPresidio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdicionaSiglaPresidioActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtAdicionaSiglaPresidio, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 170, 20));

        jButtonAdicionaSentenciado.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jButtonAdicionaSentenciado.setText("Adicionar Sentenciado:");
        jButtonAdicionaSentenciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionaSentenciadoActionPerformed(evt);
            }
        });
        painelSentenciado.add(jButtonAdicionaSentenciado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        txtAdicionaMatriculaSentenciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdicionaMatriculaSentenciadoActionPerformed(evt);
            }
        });
        painelSentenciado.add(txtAdicionaMatriculaSentenciado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 160, 20));

        jLabelpavilhao.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabelpavilhao.setText("Sigla Pavilhão:");
        painelSentenciado.add(jLabelpavilhao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jComboBoxSiglaPavilhao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSiglaPavilhaoItemStateChanged(evt);
            }
        });
        jComboBoxSiglaPavilhao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSiglaPavilhaoActionPerformed(evt);
            }
        });
        painelSentenciado.add(jComboBoxSiglaPavilhao, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 130, -1));

        jLabelNumero.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabelNumero.setText("Cela Numero:");
        painelSentenciado.add(jLabelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        painelSentenciado.add(jComboBoxCelaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 110, -1));

        jLabel17.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel17.setText("Motivo:");
        painelSentenciado.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 60, -1));

        jComboBoxMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE" }));
        painelSentenciado.add(jComboBoxMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 110, -1));

        painelAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18))); // NOI18N

        btnNovo.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/adicionar.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
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
                .addGap(113, 113, 113)
                .addComponent(btnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(99, 99, 99)
                .addComponent(btnSalvar)
                .addGap(56, 56, 56))
        );
        painelAcoesLayout.setVerticalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        painelTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Movimentações Externas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jTableMovimentacaoExterna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Data da Movimentação", "Data do Registro", "Tipo", "Sigla do Presídio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMovimentacaoExterna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMovimentacaoExternaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMovimentacaoExterna);
        if (jTableMovimentacaoExterna.getColumnModel().getColumnCount() > 0) {
            jTableMovimentacaoExterna.getColumnModel().getColumn(0).setResizable(false);
            jTableMovimentacaoExterna.getColumnModel().getColumn(1).setResizable(false);
            jTableMovimentacaoExterna.getColumnModel().getColumn(2).setResizable(false);
            jTableMovimentacaoExterna.getColumnModel().getColumn(3).setResizable(false);
            jTableMovimentacaoExterna.getColumnModel().getColumn(4).setResizable(false);
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

        jButtonPesquisar.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jButtonPesquisar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sigla Presidio", "Matricula" }));
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelTabelaLayout = new javax.swing.GroupLayout(painelTabela);
        painelTabela.setLayout(painelTabelaLayout);
        painelTabelaLayout.setHorizontalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelTabelaLayout.createSequentialGroup()
                        .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(txtLocalizar)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        painelTabelaLayout.setVerticalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTabelaLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelTabela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelAcoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelSentenciado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelSentenciado, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        me = new MovimentacaoExterna();
        trataEdicao(true);
        limparCampos();
        jDateChooserDataMovimentacaoExterna.requestFocus();
    }//GEN-LAST:event_btnNovoActionPerformed

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
    /**
     * função recebe um evento e verifica se campos estão preenchidos e salva
     * informações no banco
     *
     * @param evt
     */
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (!txtAdicionaMatriculaSentenciado.getText().equals("")) {
            if (!txtAdicionaSiglaPresidio.getText().equals("")) {
                if (jDateChooserDataMovimentacaoExterna.getDate() != null) {
                    if (jComboBoxTipo.getSelectedIndex() != 0) {
                        if (jComboBoxMotivo.getSelectedIndex() != 0) {
                            if ((sentenciado.getAtivo().equals(false) && (jComboBoxTipo.getSelectedItem().equals("IN"))) || ((sentenciado.getAtivo().equals(true)) && (jComboBoxTipo.getSelectedItem().equals("EX")))) {// se estiver iniativo e IN ou ativo e EX
                                Cela ce = new Cela();
                                Pavilhao pa = new Pavilhao();
                                me.setDataMovimentacao(jDateChooserDataMovimentacaoExterna.getDate());
                                me.setDataRegistro(new Date());
                                me.setSentenciado(sentenciado);
                                me.setPresidio(presidio);
                                me.setTipoMe(String.valueOf(jComboBoxTipo.getSelectedItem()));
                                me.setPresidio(presidio);
                                mi.setMotivo(String.valueOf(jComboBoxMotivo.getSelectedItem()));
                                mi.setDataMovimentacao(jDateChooserDataMovimentacaoExterna.getDate());
                                mi.setDataRegistro(new Date());
                                mi.setSentenciado(sentenciado);
                                if (jComboBoxTipo.getSelectedItem().equals("IN")) {
                                    Integer parametroCela = (Integer.parseInt(String.valueOf(jComboBoxCelaNumero.getSelectedItem()))); // fez automatico , isto é, colocou o String
                                    String parametroSiglaPavilhao = String.valueOf(jComboBoxSiglaPavilhao.getSelectedItem()); //pega  o texto seleciona e converte em String e 
                                    Pavilhao p = localizaPavilhao(parametroSiglaPavilhao);//pega a Sring sigla e devolve o objeto pavilhão
                                    ce = (controleCelas.confirmaInsercaoCela(parametroCela, p.getId()));
                                    System.out.println("Numero da cela " + ce.getNumeroCela() + "||" + "Numero da pavilhao " + ce.getPavilhao().getId());
                                    //mi.setCela(ce);
                                    mi.setTipoMi("AL");
                                    sentenciado.setAtivo(true);
                                    mi.setCela(ce);
                                } else {
                                    ce.setId(474);
                                    mi.setTipoMi("DS");
                                    sentenciado.setAtivo(false);//salvar no Sentenciado
                                    mi.setCela(ce);
                                }

                                controleME.inserir(me);//salvar na Movimentação Externa
                                controleMI.inserir(mi);//salvar na Movimentação Interna
                                controleSentenciados.alterar(sentenciado);

                                carregarLista();
                                atualizaTabela();
                                limparCampos();
                                trataEdicao(false);

                            } else {
                                if (jComboBoxTipo.getSelectedItem().equals("IN")) {
                                    JOptionPane.showMessageDialog(null, "Este Sentenciado ja esta na Unidade Prisional!", "Por favor. Verifique a Matricula.", JOptionPane.WARNING_MESSAGE);
                                    txtAdicionaMatriculaSentenciado.setText("");
                                    txtAdicionaMatriculaSentenciado.requestFocus();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Este Sentenciado não esta na Unidade Prisional!", "Selecione Novamente ou Cadastre o mesmo caso necessário.", JOptionPane.WARNING_MESSAGE);
                                }

                                txtAdicionaMatriculaSentenciado.setText("");
                                txtAdicionaMatriculaSentenciado.requestFocus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Selecione o MOtivo da Movimentação!", "Alerta", JOptionPane.WARNING_MESSAGE);
                            jComboBoxMotivo.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione o Tipo de Movimentação!", "Alerta", JOptionPane.WARNING_MESSAGE);
                        jComboBoxTipo.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione a Data da Movimentação!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    jDateChooserDataMovimentacaoExterna.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Adicione a Presidio!", "Alerta", JOptionPane.WARNING_MESSAGE);
                txtAdicionaSiglaPresidio.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Adicione a Matricula do Sentenciado!", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtAdicionaMatriculaSentenciado.requestFocus();

        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jTableMovimentacaoExternaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMovimentacaoExternaMouseClicked
        // TODO add your handling code here:
        vincularCampos();
        jDateChooserDataMovimentacaoExterna.requestFocus();
    }//GEN-LAST:event_jTableMovimentacaoExternaMouseClicked

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoActionPerformed

    private void jComboBoxTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTipoItemStateChanged
        jComboBoxMotivo.removeAllItems();
        if (jComboBoxTipo.getSelectedItem().equals("EX")) {
            // carregarComboBoxMotivoEX();
            jComboBoxMotivo.addItem(String.valueOf("SELECIONE"));
            jComboBoxMotivo.addItem(String.valueOf("ER"));
            jComboBoxMotivo.addItem(String.valueOf("LB"));
            jComboBoxMotivo.addItem(String.valueOf("FA"));
            jComboBoxMotivo.addItem(String.valueOf("FU"));
            jComboBoxMotivo.addItem(String.valueOf("ERR"));
        } else if (jComboBoxTipo.getSelectedItem().equals("IN")) {
            // carregarComboBoxMotivoIN();
            jComboBoxMotivo.addItem(String.valueOf("SELECIONE"));
            jComboBoxMotivo.addItem(String.valueOf("IR"));
            jComboBoxMotivo.addItem(String.valueOf("REC"));
            jComboBoxMotivo.addItem(String.valueOf("ERR"));

        } else if (jComboBoxTipo.getSelectedItem().equals("SELECIONE")) {
            jComboBoxMotivo.addItem(String.valueOf("SELECIONE"));
        }

        if (jComboBoxTipo.getSelectedItem().equals("IN")) {
            carregarInsercaoCelaPavilhao(true);
        }else {
            carregarInsercaoCelaPavilhao(false);
        }
    }//GEN-LAST:event_jComboBoxTipoItemStateChanged

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        // TODO add your handling code here:
        txtLocalizar.setEnabled(true);
        txtLocalizar.requestFocus();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonAdicionaSiglaPresidioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionaSiglaPresidioActionPerformed
        PesquisarPresidio ppres = new PesquisarPresidio(null, true); /// posso passar usuário
        ppres.setVisible(true);
        presidio = ppres.getPresidio();
        txtAdicionaSiglaPresidio.setText(presidio.getSigla());
    }//GEN-LAST:event_jButtonAdicionaSiglaPresidioActionPerformed

    private void txtAdicionaSiglaPresidioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdicionaSiglaPresidioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdicionaSiglaPresidioActionPerformed

    private void jButtonAdicionaSentenciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionaSentenciadoActionPerformed
        // TODO add your handling code here:
        PesquisarSentenciado psent = new PesquisarSentenciado(null, true); /// posso passar usuário
        psent.setVisible(true);
        sentenciado = psent.getSentenciado();
        txtAdicionaMatriculaSentenciado.setText(sentenciado.getMatricula());
    }//GEN-LAST:event_jButtonAdicionaSentenciadoActionPerformed

    private void txtAdicionaMatriculaSentenciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdicionaMatriculaSentenciadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdicionaMatriculaSentenciadoActionPerformed

    private void jComboBoxSiglaPavilhaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSiglaPavilhaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSiglaPavilhaoActionPerformed

    private void jComboBoxTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxTipoMouseClicked
        // TODO add your handling code here:
        if (jComboBoxTipo.getSelectedItem().toString().equals("IN")) {
            carregarInsercaoCelaPavilhao(true);
        }
    }//GEN-LAST:event_jComboBoxTipoMouseClicked
    /**
     * Evento para preencher combo de cela
     *
     * @param evt
     */
    private void jComboBoxSiglaPavilhaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxSiglaPavilhaoItemStateChanged
        // TODO add your handling code here:
        int index = jComboBoxSiglaPavilhao.getSelectedIndex();
        listCela.clear();
        jComboBoxCelaNumero.removeAllItems();
        Pavilhao selecionado = listPavilhao.get(index);
        listCela.addAll(selecionado.getCelas());
        for (Cela c : listCela) {
            jComboBoxCelaNumero.addItem(String.valueOf(c.getNumeroCela()));
        }
    }//GEN-LAST:event_jComboBoxSiglaPavilhaoItemStateChanged

    private void txtLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalizarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroMovimentacaoExterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacaoExterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacaoExterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacaoExterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                CadastroMovimentacaoExterna dialog = new CadastroMovimentacaoExterna(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JLabel espaco;
    private javax.swing.JButton jButtonAdicionaSentenciado;
    private javax.swing.JButton jButtonAdicionaSiglaPresidio;
    private javax.swing.JComboBox<String> jButtonPesquisar;
    private javax.swing.JComboBox jComboBoxCelaNumero;
    private javax.swing.JComboBox<String> jComboBoxMotivo;
    private javax.swing.JComboBox jComboBoxSiglaPavilhao;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private com.toedter.calendar.JDateChooser jDateChooserDataMovimentacaoExterna;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelpavilhao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMovimentacaoExterna;
    private java.util.List<Presidio> listPresidio;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelSentenciado;
    private javax.swing.JPanel painelTabela;
    private javax.persistence.Query queryPresidio;
    private javax.swing.JTextField txtAdicionaMatriculaSentenciado;
    private javax.swing.JTextField txtAdicionaSiglaPresidio;
    private javax.swing.JTextField txtLocalizar;
    // End of variables declaration//GEN-END:variables
}

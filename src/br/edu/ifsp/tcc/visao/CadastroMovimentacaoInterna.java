/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.visao;

import br.edu.ifsp.tcc.controle.ControleCela;
import br.edu.ifsp.tcc.controle.ControleMovimentacaoInterna;
import br.edu.ifsp.tcc.controle.ControlePavilhao;
import br.edu.ifsp.tcc.modelo.Cela;
import br.edu.ifsp.tcc.modelo.MovimentacaoInterna;
import br.edu.ifsp.tcc.modelo.Pavilhao;
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
public class CadastroMovimentacaoInterna extends javax.swing.JDialog {

    private ControleMovimentacaoInterna controleMI = new ControleMovimentacaoInterna();
    private ControlePavilhao controlePavilhao = new ControlePavilhao(); // JA TENHO PAVILHAO NA CELA
    private ControleCela controleCela = new ControleCela();

    private List<MovimentacaoInterna> listaMovimentacoesInternas = new ArrayList();
    // private List<Pavilhao> listaPavilhao = new ArrayList(); // JA TENHO PAVILHAO NA CELA
    private List<Cela> listaCelas = new ArrayList();
    private List<Pavilhao> listPavilhao = new ArrayList();

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // o mes tem que ser maiusculo
    DateFormat dfh = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // o mes tem que ser maiusculo

    MovimentacaoInterna f = new MovimentacaoInterna();
    Cela c = new Cela();
    Sentenciado s = new Sentenciado();
    private Sentenciado sentenciado;

    public CadastroMovimentacaoInterna(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarLista();
        atualizaTabela();
        TamanhoColuna();
        trataEdicao(false);
        carregarComboBoxPavilhao();
        txtLocalizar.requestFocus();

    }

    private void carregarComboBoxPavilhao() {
        for (Pavilhao p : listPavilhao) {
            jComboBoxPavilhao.addItem(p.getSigla());// vou criar um toSting para pegar a sigla

        }

    }
    
    private void trataEdicao(boolean editando) {
        btnCancelar.setEnabled(editando);
        btnSalvar.setEnabled(editando);
        btnNovo.setEnabled(!editando);
        jDateChooserDataMovimentacaoInterna.setEnabled(editando);
        jComboBoxMotivo.setEnabled(editando);
        jComboBoxPavilhao.setEnabled(editando);
        jComboBoxCelaNumero.setEnabled(editando);
        jTableMovimentacaoInterna.setEnabled(!editando);
    }

    public void TamanhoColuna() {
        jTableMovimentacaoInterna.getColumnModel().getColumn(0).setMaxWidth(300);
        jTableMovimentacaoInterna.getColumnModel().getColumn(1).setMaxWidth(400);
        jTableMovimentacaoInterna.getColumnModel().getColumn(2).setMaxWidth(150);
        jTableMovimentacaoInterna.getColumnModel().getColumn(3).setMaxWidth(400);
        jTableMovimentacaoInterna.getColumnModel().getColumn(4).setMaxWidth(350);
        jTableMovimentacaoInterna.setAutoCreateRowSorter(true); // ordena ao clicar na coluna
        JTableHeader jth = jTableMovimentacaoInterna.getTableHeader();
        jth.setFont(new Font("Arial Unicod MS", Font.BOLD, 14));
    }

    public void atualizaTabela() {
        DefaultTableModel dtm = (DefaultTableModel) jTableMovimentacaoInterna.getModel();
        dtm.setNumRows(0);//apagando todas as linhas
        for (MovimentacaoInterna f : listaMovimentacoesInternas) {
            dtm.addRow(new Object[]{f.getSentenciado().getMatricula(),
                                    dfh.format(f.getDataMovimentacao()),
                                    f.getTipoMi(),
                                    (f.getCela().getId()==474)? "Removido do Presídio":f.getCela().getPavilhao().getNome(), // colocar 474
                                    (f.getCela().getId()==474)? "Removido do Presídio": f.getCela().getNumeroCela()});  // colocar 474
        }

    }

    public void carregarLista() {
        listaMovimentacoesInternas = controleMI.buscar();
        listPavilhao = controlePavilhao.buscar();
    }

    public void limparCampos() {
        jDateChooserDataMovimentacaoInterna.setDate(null);
        jComboBoxMotivo.setSelectedIndex(0);
        jComboBoxPavilhao.setSelectedIndex(0);
        jComboBoxCelaNumero.setSelectedIndex(0);
        //   txtLotacao.setText("");
        txtLocalizar.setText("");
    }

    public void pesquisar() {
        listaMovimentacoesInternas.clear();//limpa lista
        DefaultTableModel dtm = (DefaultTableModel) jTableMovimentacaoInterna.getModel();
        dtm.setNumRows(0);//LIMPA A TABELA
        String str = "";
        str = txtLocalizar.getText();
        if (jButtonPesquisar.getSelectedItem().toString().equals("Pesquisa por Pavilhao")) {
            listaMovimentacoesInternas.addAll(controleMI.listarPavilhao(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        if (jButtonPesquisar.getSelectedItem().toString().equals("Pesquisa por Matricula")) {
            listaMovimentacoesInternas.addAll(controleMI.listarMatricula(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
        }
        txtLocalizar.requestFocus();
        atualizaTabela();//PREENCHE COM OS NOVOS DADOS
    }

    public void vincularCampos() {
        MovimentacaoInterna f = listaMovimentacoesInternas.get(jTableMovimentacaoInterna.getSelectedRow());// pega a linha da tabela
        this.sentenciado = f.getSentenciado(); // pega o sentenciado do f
        jDateChooserDataMovimentacaoInterna.setDate(f.getDataMovimentacao());
        jComboBoxMotivo.setSelectedItem(f.getTipoMi());
//        jComboBoxPavilhao.setSelectedItem(f.getCela().getPavilhao().getSigla());
//        jComboBoxCelaNumero.setSelectedItem(f.getCela().getNumeroCela());
        //txtLotacao.setSelected(f.getCela().getLotacao());

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
        espaco = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxMotivo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxPavilhao = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxCelaNumero = new javax.swing.JComboBox<>();
        jDateChooserDataMovimentacaoInterna = new com.toedter.calendar.JDateChooser();
        painelAcoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        painelTabela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMovimentacaoInterna = new javax.swing.JTable();
        txtLocalizar = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Movimentação Interna");

        painelSentenciado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Movimentação Interna", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N
        painelSentenciado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        painelSentenciado.add(espaco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, 20));

        jLabel13.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel13.setText("Data:");
        painelSentenciado.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel16.setText("Motivo:");
        painelSentenciado.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 60, -1));

        jComboBoxMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE", "SD", "CD", "CA", "SE" }));
        jComboBoxMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMotivoActionPerformed(evt);
            }
        });
        painelSentenciado.add(jComboBoxMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 130, -1));

        jLabel9.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel9.setText("Sigla Pavilhão:");
        painelSentenciado.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        jComboBoxPavilhao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR" }));
        jComboBoxPavilhao.setToolTipText("");
        jComboBoxPavilhao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxPavilhaoItemStateChanged(evt);
            }
        });
        jComboBoxPavilhao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPavilhaoActionPerformed(evt);
            }
        });
        painelSentenciado.add(jComboBoxPavilhao, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 100, -1));

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel10.setText("Cela Numero:");
        painelSentenciado.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, -1, -1));

        jComboBoxCelaNumero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONAR" }));
        painelSentenciado.add(jComboBoxCelaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 130, -1));
        painelSentenciado.add(jDateChooserDataMovimentacaoInterna, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 220, -1));

        painelAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18))); // NOI18N

        btnNovo.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/adicionar.png"))); // NOI18N
        btnNovo.setText("Nova");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        btnNovo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnNovoKeyReleased(evt);
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
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(btnCancelar)
                .addGap(129, 129, 129)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
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

        painelTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de Inclusões", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jTableMovimentacaoInterna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sentenciado", "Data da Movimentação", "Tipo", "Pavilhão", "Número da Cela"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMovimentacaoInterna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMovimentacaoInternaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMovimentacaoInterna);
        if (jTableMovimentacaoInterna.getColumnModel().getColumnCount() > 0) {
            jTableMovimentacaoInterna.getColumnModel().getColumn(0).setResizable(false);
            jTableMovimentacaoInterna.getColumnModel().getColumn(1).setResizable(false);
            jTableMovimentacaoInterna.getColumnModel().getColumn(2).setResizable(false);
            jTableMovimentacaoInterna.getColumnModel().getColumn(3).setResizable(false);
            jTableMovimentacaoInterna.getColumnModel().getColumn(4).setResizable(false);
        }

        txtLocalizar.setFont(txtLocalizar.getFont().deriveFont(txtLocalizar.getFont().getSize()+3f));
        txtLocalizar.setEnabled(false);
        txtLocalizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalizarKeyReleased(evt);
            }
        });

        jButtonPesquisar.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jButtonPesquisar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pesquisa por Matricula", "Pesquisa por Pavilhao" }));
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelTabelaLayout.createSequentialGroup()
                        .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLocalizar)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        painelTabelaLayout.setVerticalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTabelaLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(painelSentenciado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelSentenciado, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        txtLocalizar.setEnabled(true);
        txtLocalizar.requestFocus();
        f = new MovimentacaoInterna();
//        if (jTableMovimentacaoInterna.getSelectedRow() < 0) {
//            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para movimentar", "Atenção", JOptionPane.WARNING_MESSAGE);
//        } else {
//            MovimentacaoInterna f = listaMovimentacoesInternas.get(jTableMovimentacaoInterna.getSelectedRow());
//            trataEdicao(true);
//            limparCampos();
//            jDateChooserDataMovimentacaoInterna.requestFocus();
//
//        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        trataEdicao(false);
        limparCampos();
        carregarLista();
        atualizaTabela();
        limparCampos();
        txtLocalizar.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

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

    /**
     * função recebe um evento e verifica se campos estão preenchidos e salva
     * informações no banco
     *
     * @param evt
     */

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (jDateChooserDataMovimentacaoInterna.getDate() != null) {
            if (jComboBoxMotivo.getSelectedIndex() != 0) {
                if (jComboBoxPavilhao.getSelectedIndex() != 0) {
                    if (jComboBoxCelaNumero.getSelectedIndex() != -1) {
                        if (sentenciado.getAtivo()) {
                            f.setDataRegistro(new Date());
                            f.setDataMovimentacao(jDateChooserDataMovimentacaoInterna.getDate());
                            f.setTipoMi("MC");
                            f.setMotivo(String.valueOf(jComboBoxMotivo.getSelectedItem()));
                            int parametroCela = (Integer.parseInt((String.valueOf(jComboBoxCelaNumero.getSelectedItem())))); // fez automatico , isto é, colocou o String
                            String parametroPavilhao = String.valueOf(jComboBoxPavilhao.getSelectedItem()); // fez automatico , isto é, colocou o String
                            Pavilhao p = localizaPavilhao(parametroPavilhao);//pega a String sigla e devolve o objeto pavilhão
                            c = (controleCela.confirmaInsercaoCela(parametroCela, p.getId()));
                            f.setCela(c);
                            f.setSentenciado(sentenciado); // sentenciado tem que vir de algum lugar preciso acertar isto
                            controleMI.alterar(f);//salvar o orçamento
                            carregarLista();
                            atualizaTabela();
                            limparCampos();
                            trataEdicao(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Este Sentenciado não se encontra nesta Unidade Prisional!", "Favor Incluir Sentenciado", JOptionPane.WARNING_MESSAGE);
                            jDateChooserDataMovimentacaoInterna.setDate(null);
                            jDateChooserDataMovimentacaoInterna.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione o Número da Cela!", "Alerta", JOptionPane.WARNING_MESSAGE);
                        jComboBoxCelaNumero.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione a Pavilhão!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    jComboBoxPavilhao.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione o Tipo de Movimentação!", "Alerta", JOptionPane.WARNING_MESSAGE);
                jComboBoxMotivo.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione a data da Movimentação!", "Alerta", JOptionPane.WARNING_MESSAGE);
            jDateChooserDataMovimentacaoInterna.requestFocus();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jTableMovimentacaoInternaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMovimentacaoInternaMouseClicked
        // TODO add your handling code here:
        vincularCampos();
        trataEdicao(true);
        limparCampos();
        jDateChooserDataMovimentacaoInterna.requestFocus();

    }//GEN-LAST:event_jTableMovimentacaoInternaMouseClicked

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        // TODO add your handling code here:
        txtLocalizar.setEnabled(true);
        txtLocalizar.requestFocus();
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void btnNovoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNovoKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_btnNovoKeyReleased

    private void jComboBoxPavilhaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPavilhaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPavilhaoActionPerformed

    private void jComboBoxPavilhaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxPavilhaoItemStateChanged
        // TODO add your handling code here:
        if (!jComboBoxPavilhao.getSelectedItem().equals("SELECIONAR")) {//MUDEI O TESTE PARA COMPARAR O TEXTO EM VEZ DO INDICE
            int index = jComboBoxPavilhao.getSelectedIndex();
            listaCelas.clear();
            jComboBoxCelaNumero.removeAllItems();
            
            Pavilhao selecionado = listPavilhao.get(index - 1);
            listaCelas.addAll(selecionado.getCelas());
            for (Cela c : listaCelas) {
                jComboBoxCelaNumero.addItem(String.valueOf(c.getNumeroCela()));
            }
        } else {
            jComboBoxCelaNumero.removeAllItems();//APAGO TODO O COMBOBOX 
            jComboBoxCelaNumero.addItem("SELECIONAR");//DEPOIS DE APAGADO ESCREVE SELECIONAR NO COMBO
        }
    }//GEN-LAST:event_jComboBoxPavilhaoItemStateChanged

    private void jComboBoxMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMotivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMotivoActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroMovimentacaoInterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacaoInterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacaoInterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroMovimentacaoInterna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                CadastroMovimentacaoInterna dialog = new CadastroMovimentacaoInterna(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel espaco;
    private javax.swing.JComboBox<String> jButtonPesquisar;
    private javax.swing.JComboBox<String> jComboBoxCelaNumero;
    private javax.swing.JComboBox<String> jComboBoxMotivo;
    private javax.swing.JComboBox<String> jComboBoxPavilhao;
    private com.toedter.calendar.JDateChooser jDateChooserDataMovimentacaoInterna;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMovimentacaoInterna;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelSentenciado;
    private javax.swing.JPanel painelTabela;
    private javax.swing.JTextField txtLocalizar;
    // End of variables declaration//GEN-END:variables
}

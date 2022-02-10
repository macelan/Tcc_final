/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.visao;

import br.edu.ifsp.tcc.controle.ControleFuncionario;
import br.edu.ifsp.tcc.controle.ControleFuncionarioDaSindicancia;
import br.edu.ifsp.tcc.controle.ControleSentenciado;
import br.edu.ifsp.tcc.controle.ControleSentenciadoDaSindicancia;
import br.edu.ifsp.tcc.controle.ControleSindicancia;
import br.edu.ifsp.tcc.modelo.Funcionario;
import br.edu.ifsp.tcc.modelo.Sentenciado;
import br.edu.ifsp.tcc.modelo.Sindicancia;
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
public class CadastroSindicancia extends javax.swing.JDialog {

    // Instanciando Sindicancia
    Sindicancia f = new Sindicancia();
    Funcionario fu = new Funcionario();
    Sentenciado se = new Sentenciado();
    // controles
    private ControleSindicancia controlesindicancia = new ControleSindicancia();
    private ControleFuncionario controlefuncionario = new ControleFuncionario();
    private ControleSentenciado controlesentenciado = new ControleSentenciado();
    private ControleSentenciadoDaSindicancia controleSentenciadoSindicancia = new ControleSentenciadoDaSindicancia();
    private ControleFuncionarioDaSindicancia controleFuncionarioSindicancia = new ControleFuncionarioDaSindicancia();

    //Listas
    private List<Sindicancia> listasindicancias = new ArrayList();
    private List<Funcionario> listafuncionarios = new ArrayList();
    private List<Sentenciado> listasentenciados = new ArrayList();
    private List<ControleFuncionarioDaSindicancia> listaFuncionarioSindicancia = new ArrayList();
    private List<ControleSentenciadoDaSindicancia> listaSentenciadosSindicancia = new ArrayList();

    //adequando a data
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // o mes tem que ser maiusculo
    DateFormat dfh = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // o mes tem que ser maiusculo

    /**
     * Creates new form FormSindicancia
     */
    public CadastroSindicancia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarListas();
        carregarListaSindicancia();
        atualizarTodasTabelas();
        atualizaTabelaSindicancia();
//        TamanhoColunaSentenciado();
//        TamanhoColunaFuncionario();
//        TamanhoColunaSindicancia();
        trataEdicao(false);
//        // coloquei aqui para carregar como falso e não mostrar
        carregarBotoesPesquisaPorPeriodo(false);
    }

    private void trataEdicao(boolean editando) {
        btnCancelar.setEnabled(editando);
        btnSalvar.setEnabled(editando);
        btnAlterar.setEnabled(!editando);
        btnExcluir.setEnabled(!editando);
        btnNovo.setEnabled(!editando);
        btAdicionaSentenciadoSindicancia.setEnabled(editando);
        btAdicionaFuncionarioSindicancia.setEnabled(editando);
        txtAreaSindicancia.setEditable(editando);
        jDateChooserDataSindicancia.setEnabled(editando);
//      jButtonPesquisaSindicancia.setEditable(editando);
        tbFuncionario.setEnabled(!editando);
        tbSentenciado.setEnabled(!editando);
    }

    public void TamanhoColunaSentenciado() {
        tbSentenciado.getColumnModel().getColumn(0).setMaxWidth(100);
        tbSentenciado.getColumnModel().getColumn(1).setMaxWidth(350);
        tbSentenciado.getColumnModel().getColumn(2).setMaxWidth(100);
        tbSentenciado.getColumnModel().getColumn(3).setMaxWidth(100);
        tbSentenciado.setAutoCreateRowSorter(true); // ordena ao clicar na coluna
        JTableHeader jth = tbSentenciado.getTableHeader();
        jth.setFont(new Font("Arial Unicod MS", Font.BOLD, 14));
    }

    public void TamanhoColunaFuncionario() {
        tbFuncionario.getColumnModel().getColumn(0).setMaxWidth(350);
        tbFuncionario.getColumnModel().getColumn(1).setMaxWidth(100);
        tbFuncionario.setAutoCreateRowSorter(true); // ordena ao clicar na coluna
        JTableHeader jth = tbFuncionario.getTableHeader();
        jth.setFont(new Font("Arial Unicod MS", Font.BOLD, 14));
    }

    public void TamanhoColunaSindicancia() {
        tbSindicancia.getColumnModel().getColumn(0).setMaxWidth(150);
        tbSindicancia.getColumnModel().getColumn(1).setMaxWidth(150);
        tbSindicancia.getColumnModel().getColumn(2).setMaxWidth(750);
        tbSindicancia.setAutoCreateRowSorter(true); // ordena ao clicar na coluna
        JTableHeader jth = tbSindicancia.getTableHeader();
        jth.setFont(new Font("Arial Unicod MS", Font.BOLD, 14));
        tbSindicancia.setRowHeight(14);

    }

    // TABELAS DOS ITENS DO ORÇAMENTO
    public void atualizaTabelaSentenciado() {
        DefaultTableModel dtm = (DefaultTableModel) tbSentenciado.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serve para apagar as linhas "é muito rapido"
        for (Sentenciado se : listasentenciados) {
            dtm.addRow(new Object[]{se.getMatricula(), se.getNome(), se.getAliases(), se.getFaccao()}); // Object é um vetor ou coleção com ele não pecisa converter
        }

    }

    public void atualizaTabelaFuncionario() {
        DefaultTableModel dtm = (DefaultTableModel) tbFuncionario.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serva para apagar as linhas "é muito rapido"
        for (Funcionario fu : listafuncionarios) {
            dtm.addRow(new Object[]{fu.getNome(), fu.getRg()}); // Object é um vetor ou coleção com ele não pecisa converter
        }

    }

    /**
     * Recebe um objeto sindicancia selecionado e mostra os Sentenciados ligados
     * a esta Sindicancia
     *
     * @param sind
     */
    public void atualizaTabelaSentenciadoComParametro(Sindicancia sind) {
        System.err.println(sind.getSentenciados());
        DefaultTableModel dtm = (DefaultTableModel) tbSentenciado.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serve para apagar as linhas "é muito rapido"
        for (Sentenciado se : sind.getSentenciados()) {
            dtm.addRow(new Object[]{se.getMatricula(), se.getNome(), se.getAliases(), se.getFaccao()}); // Object é um vetor ou coleção com ele não pecisa converter
        }

    }

    /**
     * Recebe um objeto sindicancia selecionado e mostra os Funcionarios ligados
     * a esta Sindicancia
     *
     * @param sind
     */
    public void atualizaTabelaFuncionarioComParametro(Sindicancia sind) {
        System.err.println(sind.getFuncionarios().toString());
        DefaultTableModel dtm = (DefaultTableModel) tbFuncionario.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serva para apagar as linhas "é muito rapido"
        for (Funcionario fu : sind.getFuncionarios()) {
            dtm.addRow(new Object[]{fu.getNome(), fu.getRg()}); // Object é um vetor ou coleção com ele não pecisa converter
        }

    }

    public void atualizaTabelaSindicancia() {
        DefaultTableModel dtm = (DefaultTableModel) tbSindicancia.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serve para apagar as linhas "é muito rapido"
        for (Sindicancia f : listasindicancias) {
            dtm.addRow(new Object[]{df.format(f.getDataOcorrencia()), dfh.format(f.getDataRegistro()), f.getRelato()});
        }

    }

    public void carregarListaFuncionario() {
        listafuncionarios = controlefuncionario.buscar();
    }

    public void carregarListaSentenciado() {
        listasentenciados = controlesentenciado.buscar();
    }

    public void carregarListaSindicancia() {
        listasindicancias = controlesindicancia.buscar();
    }

    public void atualizarTodasTabelas() {
        //atualizaTabelaSentenciado();
       // atualizaTabelaFuncionario();
        carregarListaSindicancia();
     }

    public void carregarListas() {
        carregarListaFuncionario();
        carregarListaSentenciado();
        carregarListaSindicancia();
    }

    public void limparCampos() {
        jDateChooserDataSindicancia.setDate(null);
        txtLocalizar.setText("");
        txtAreaSindicancia.setText("");
        DefaultTableModel dtmsent = (DefaultTableModel) tbSentenciado.getModel(); //esta linha limpar minhas tabelas apos salvar
        dtmsent.setNumRows(0);//LIMPA A TABELA
        DefaultTableModel dtmfunc = (DefaultTableModel) tbFuncionario.getModel();
        dtmfunc.setNumRows(0);//LIMPA A TABELA
    }

    /**
     * Neste metodo trato as dadas que vem da tela para transformar em padrão
     * compativel com o banco
     *
     * @param data
     * @return
     * @throws ParseException
     */
//  public String formataDataRetornaBD(String data) throws ParseException{
//        //veja este metodo....ele recebe uma String como dia/mes/ano e retorna ano/mes/dia
//        Date d = df.parse(data);
//        df = new SimpleDateFormat("yyyy-MM-dd");
//        String s = df.format(d);
//        return s;
//    }
    /**
     * Este metodo controla a visibilidade dos botões de data para pesquisa
     * ativo = true !ativo = false
     *
     * @param ativo
     */
    public void carregarBotoesPesquisaPorPeriodo(boolean ativo) {
        btBuscaPeriodoInicial.setVisible(ativo);
        jDateChooserInicial.setVisible(ativo);
        btBuscaPeriodoFinal.setVisible(ativo);
        jDateChooserFinal.setVisible(ativo);
        jButtonBuscarPeriodo.setVisible(ativo);
    }

    /**
     * Este Metodo "pesquisarPorRalato" realiza uma pesquisa por relatos
     */
    // pegando a data do banco para mostrar no seuJDateChooser
    // seuJDateChooser.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(suaDataFromDataBase));
    // pegando do seuJDateChooser
    // Date date = new Date(); 
    // DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    // System.out.println(df.format(date));
    public void pesquisarPorRalato() {
        DateFormat dfp = new SimpleDateFormat("yyyy-MM-dd");
        listasindicancias.clear();//limpa lista
        // listafuncionarios.clear();//limpa lista
        // listasentenciados.clear();//limpa lista
        DefaultTableModel dtmsind = (DefaultTableModel) tbSindicancia.getModel();
        dtmsind.setNumRows(0);// LIMPA A TABELA
        DefaultTableModel dtmsent = (DefaultTableModel) tbSentenciado.getModel();
        dtmsent.setNumRows(0);// LIMPA A TABELA
        DefaultTableModel dtmfunc = (DefaultTableModel) tbFuncionario.getModel();
        dtmfunc.setNumRows(0);// LIMPA A TABELA
        String str = "";
        str = txtLocalizar.getText();
        if (jcbPesquisar.getSelectedItem().toString().equals("Período")) {
            txtLocalizar.setVisible(false);
            carregarBotoesPesquisaPorPeriodo(true);
            if (jDateChooserInicial.equals("") && jDateChooserFinal.equals("")) {
                JOptionPane.showMessageDialog(this, "Escolha uma data!!");
                jDateChooserInicial.requestFocus();
            } else {
                listasindicancias.addAll(controlesindicancia.listarSindicanciaPorDataRegistro(jDateChooserInicial.getDate(), jDateChooserFinal.getDate()));
                 atualizaTabelaSindicancia();
            }
        }

            if (jcbPesquisar.getSelectedItem().toString().equals("Relato")) {
                carregarBotoesPesquisaPorPeriodo(false);
                txtLocalizar.setVisible(true);
                listasindicancias.addAll(controlesindicancia.listarSindicanciaRelato(str));
                atualizaTabelaSindicancia();
                // listafuncionarios.addAll(controlefuncionario.listarNome(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
                // listasentenciados.addAll(controlesentenciado.listarMatricula(str));//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA RELEASE
            }
            txtLocalizar.requestFocus();
        }
     

    public void vincularCampos() {
        Sindicancia f = listasindicancias.get(tbSindicancia.getSelectedRow());// pega a linha da tabela
        jDateChooserDataSindicancia.setDate(f.getDataOcorrencia());
        txtAreaSindicancia.setText(f.getRelato());
        atualizaTabelaFuncionarioComParametro(f);
        atualizaTabelaSentenciadoComParametro(f);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelOcorrencia = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jDateChooserDataSindicancia = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaSindicancia = new javax.swing.JTextArea();
        painelParticipantes = new javax.swing.JPanel();
        jPanelIntSentenc = new javax.swing.JPanel();
        btAdicionaSentenciadoSindicancia = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSentenciado = new javax.swing.JTable();
        jPanelIntFuncio = new javax.swing.JPanel();
        btAdicionaFuncionarioSindicancia = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbFuncionario = new javax.swing.JTable();
        painelTabela = new javax.swing.JPanel();
        txtLocalizar = new javax.swing.JTextField();
        jcbPesquisar = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbSindicancia = new javax.swing.JTable();
        btBuscaPeriodoInicial = new javax.swing.JButton();
        btBuscaPeriodoFinal = new javax.swing.JButton();
        jDateChooserInicial = new com.toedter.calendar.JDateChooser();
        jDateChooserFinal = new com.toedter.calendar.JDateChooser();
        jButtonBuscarPeriodo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        painelAcoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Sindicancia");

        painelOcorrencia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Sindicância", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel10.setText("Data da Sindicancia:");

        jLabel13.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel13.setText("Ocorrência:");

        txtAreaSindicancia.setColumns(20);
        txtAreaSindicancia.setRows(5);
        jScrollPane2.setViewportView(txtAreaSindicancia);

        painelParticipantes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Participantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 51, 51))); // NOI18N

        jPanelIntSentenc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btAdicionaSentenciadoSindicancia.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btAdicionaSentenciadoSindicancia.setText("Adicionar Sentenciado");
        btAdicionaSentenciadoSindicancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionaSentenciadoSindicanciaActionPerformed(evt);
            }
        });

        tbSentenciado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nome", "Apelido", "Facção"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSentenciado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSentenciadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSentenciado);

        javax.swing.GroupLayout jPanelIntSentencLayout = new javax.swing.GroupLayout(jPanelIntSentenc);
        jPanelIntSentenc.setLayout(jPanelIntSentencLayout);
        jPanelIntSentencLayout.setHorizontalGroup(
            jPanelIntSentencLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIntSentencLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAdicionaSentenciadoSindicancia)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelIntSentencLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelIntSentencLayout.setVerticalGroup(
            jPanelIntSentencLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIntSentencLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAdicionaSentenciadoSindicancia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelIntFuncio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btAdicionaFuncionarioSindicancia.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btAdicionaFuncionarioSindicancia.setText("Adicionar Funcionário");
        btAdicionaFuncionarioSindicancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionaFuncionarioSindicanciaActionPerformed(evt);
            }
        });

        tbFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "R.G"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbFuncionario);
        if (tbFuncionario.getColumnModel().getColumnCount() > 0) {
            tbFuncionario.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanelIntFuncioLayout = new javax.swing.GroupLayout(jPanelIntFuncio);
        jPanelIntFuncio.setLayout(jPanelIntFuncioLayout);
        jPanelIntFuncioLayout.setHorizontalGroup(
            jPanelIntFuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIntFuncioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelIntFuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(jPanelIntFuncioLayout.createSequentialGroup()
                        .addComponent(btAdicionaFuncionarioSindicancia)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelIntFuncioLayout.setVerticalGroup(
            jPanelIntFuncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelIntFuncioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAdicionaFuncionarioSindicancia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout painelParticipantesLayout = new javax.swing.GroupLayout(painelParticipantes);
        painelParticipantes.setLayout(painelParticipantesLayout);
        painelParticipantesLayout.setHorizontalGroup(
            painelParticipantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelParticipantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelIntSentenc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelIntFuncio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelParticipantesLayout.setVerticalGroup(
            painelParticipantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelParticipantesLayout.createSequentialGroup()
                .addGroup(painelParticipantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelIntSentenc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelIntFuncio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        painelTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa Sindicancia por:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        txtLocalizar.setFont(txtLocalizar.getFont().deriveFont(txtLocalizar.getFont().getSize()+3f));
        txtLocalizar.setEnabled(false);
        txtLocalizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalizarKeyReleased(evt);
            }
        });

        jcbPesquisar.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jcbPesquisar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Relato", "Período" }));
        jcbPesquisar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbPesquisarItemStateChanged(evt);
            }
        });
        jcbPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPesquisarActionPerformed(evt);
            }
        });

        tbSindicancia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data do Ocorrido", "Data do Registro", "Relato"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSindicancia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSindicanciaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbSindicancia);
        if (tbSindicancia.getColumnModel().getColumnCount() > 0) {
            tbSindicancia.getColumnModel().getColumn(0).setResizable(false);
            tbSindicancia.getColumnModel().getColumn(1).setResizable(false);
            tbSindicancia.getColumnModel().getColumn(2).setResizable(false);
        }

        btBuscaPeriodoInicial.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btBuscaPeriodoInicial.setText("Periodo Inicial");
        btBuscaPeriodoInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscaPeriodoInicialActionPerformed(evt);
            }
        });

        btBuscaPeriodoFinal.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btBuscaPeriodoFinal.setText("Periodo Final");
        btBuscaPeriodoFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscaPeriodoFinalActionPerformed(evt);
            }
        });

        jButtonBuscarPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/localizar20.png"))); // NOI18N
        jButtonBuscarPeriodo.setToolTipText("Click para Pesquisar");
        jButtonBuscarPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarPeriodoActionPerformed(evt);
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
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(painelTabelaLayout.createSequentialGroup()
                        .addComponent(jcbPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btBuscaPeriodoInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btBuscaPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBuscarPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))))
        );
        painelTabelaLayout.setVerticalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTabelaLayout.createSequentialGroup()
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btBuscaPeriodoFinal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btBuscaPeriodoInicial)
                        .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserFinal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooserInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBuscarPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout painelOcorrenciaLayout = new javax.swing.GroupLayout(painelOcorrencia);
        painelOcorrencia.setLayout(painelOcorrenciaLayout);
        painelOcorrenciaLayout.setHorizontalGroup(
            painelOcorrenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelOcorrenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelOcorrenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelOcorrenciaLayout.createSequentialGroup()
                        .addGroup(painelOcorrenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10))
                        .addGap(9, 9, 9)
                        .addComponent(jDateChooserDataSindicancia, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(661, 661, 661))
                    .addComponent(painelTabela, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelParticipantes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelOcorrenciaLayout.setVerticalGroup(
            painelOcorrenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelOcorrenciaLayout.createSequentialGroup()
                .addGroup(painelOcorrenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelOcorrenciaLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(painelOcorrenciaLayout.createSequentialGroup()
                        .addComponent(jDateChooserDataSindicancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel13)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelParticipantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        getContentPane().add(painelOcorrencia, java.awt.BorderLayout.NORTH);

        painelAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

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

        btnExcluir.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/deletar.png"))); // NOI18N
        btnExcluir.setText("Excluir Sindicancia");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelAcoesLayout = new javax.swing.GroupLayout(painelAcoes);
        painelAcoes.setLayout(painelAcoesLayout);
        painelAcoesLayout.setHorizontalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1072, Short.MAX_VALUE)
            .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(painelAcoesLayout.createSequentialGroup()
                    .addGap(0, 34, Short.MAX_VALUE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnExcluir)
                    .addGap(0, 19, Short.MAX_VALUE)))
        );
        painelAcoesLayout.setVerticalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
            .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(painelAcoesLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnNovo)
                        .addComponent(btnAlterar)
                        .addComponent(btnCancelar)
                        .addComponent(btnSalvar)
                        .addComponent(btnExcluir))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        f = new Sindicancia();
        limparCampos();
        trataEdicao(true);
        txtLocalizar.setEnabled(false);
        jDateChooserDataSindicancia.requestFocus();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        if (tbSindicancia.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para alterar", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            f = listasindicancias.get(tbSindicancia.getSelectedRow());
//            f = listasentenciados.get(tbSentenciado.getSelectedRow());
//            f = listafuncionarios.get(tbFuncionario.getSelectedRow());
            // Duvida os dados do funcionario e sentenciado grava junto
            trataEdicao(true);
            jDateChooserDataSindicancia.requestFocus();
            carregarListas();
            atualizarTodasTabelas();
        }

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        trataEdicao(false);
        limparCampos();
        carregarListas();
        atualizarTodasTabelas();
//        limparCampos();
        JOptionPane.showMessageDialog(this, "OPERAÇÃO CANCELADA", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        txtLocalizar.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed
    /**
     */
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (jDateChooserDataSindicancia.getDate() != null) {
            if (!txtAreaSindicancia.getText().equals("")) {
                if (!listafuncionarios.isEmpty()) {
                    if (!listasentenciados.isEmpty()) {
                        f.setDataRegistro(new Date());
                        f.setDataOcorrencia(jDateChooserDataSindicancia.getDate());
                        f.setRelato(txtAreaSindicancia.getText().trim());
                        f.setFuncionarios(listafuncionarios);
                        f.setSentenciados(listasentenciados);
                        controlesindicancia.alterar(f);//salvar o orçamento
                        limparCampos();
                        trataEdicao(false);
                        listafuncionarios.clear();//limpa lista
                        listasentenciados.clear();//limpa lista
                        carregarListaSindicancia();
                        atualizaTabelaSindicancia();
                    } else {
                        JOptionPane.showMessageDialog(null, "Escolha um Sentenciado!", "Alerta", JOptionPane.WARNING_MESSAGE);
                        btAdicionaFuncionarioSindicancia.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Escolha um funcionario!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    btAdicionaSentenciadoSindicancia.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha um Breve relato da Sindicancia!", "Alerta", JOptionPane.WARNING_MESSAGE);
                txtAreaSindicancia.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione a data dos Fatos!", "Alerta", JOptionPane.WARNING_MESSAGE);
            jDateChooserDataSindicancia.requestFocus();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (tbSindicancia.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para excluir", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (JOptionPane.showConfirmDialog(null, "Confirma exclusão da sindicancia?",
                    "Exclusão da Sindicancia", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {
                Sindicancia f = listasindicancias.get(tbSindicancia.getSelectedRow());
                controlesindicancia.remover(f);
                carregarListas();
                atualizarTodasTabelas();
                atualizaTabelaSindicancia();
                limparCampos();
            }
        }
//        carregarListas();
//        atualizarTodasTabelas();
//        limparCampos();
        
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tbSentenciadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSentenciadoMouseClicked
        // TODO add your handling code here:
        vincularCampos();
    }//GEN-LAST:event_tbSentenciadoMouseClicked

    private void btAdicionaSentenciadoSindicanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionaSentenciadoSindicanciaActionPerformed
        // TODO add your handling code here:
        listasentenciados.clear();
        PesquisarSentenciado tela = new PesquisarSentenciado(null, true);
        tela.setLocationRelativeTo(this);
        tela.setSindicancia(f);// passando a sindicancia para a tela de pesquisa de sentenciado para ser persistir
        tela.setVisible(true);
        listasentenciados.addAll(tela.getListaSentenciadosSelecionados());
        System.out.println(listasentenciados);
        atualizaTabelaSentenciado();
    }//GEN-LAST:event_btAdicionaSentenciadoSindicanciaActionPerformed

    private void btAdicionaFuncionarioSindicanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionaFuncionarioSindicanciaActionPerformed
        // TODO add your handling code here:
        listafuncionarios.clear();
        PesquisarFuncionario tela = new PesquisarFuncionario(null, true); // abrir tela de pesquisa
        tela.setLocationRelativeTo(this);
        tela.setSindicancia(f);// passando a sindicancia para a tela de pesquisa de funcionariopara ser persistido
        tela.setVisible(true);
        listafuncionarios.addAll(tela.getListaFuncionariosSelecionados());
        atualizaTabelaFuncionario();
    }//GEN-LAST:event_btAdicionaFuncionarioSindicanciaActionPerformed

    private void tbSindicanciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSindicanciaMouseClicked
        // TODO add your handling code here:
        vincularCampos();
    }//GEN-LAST:event_tbSindicanciaMouseClicked

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // TODO add your handling code here:
        pesquisarPorRalato();
    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void jcbPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPesquisarActionPerformed
        // TODO add your handling code here:
        txtLocalizar.setEnabled(true);
        txtLocalizar.requestFocus();
    }//GEN-LAST:event_jcbPesquisarActionPerformed

    private void btBuscaPeriodoInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscaPeriodoInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btBuscaPeriodoInicialActionPerformed

    private void btBuscaPeriodoFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscaPeriodoFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btBuscaPeriodoFinalActionPerformed

    private void jcbPesquisarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbPesquisarItemStateChanged
        // TODO add your handling code here:
        pesquisarPorRalato();
    }//GEN-LAST:event_jcbPesquisarItemStateChanged

    private void jButtonBuscarPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarPeriodoActionPerformed
        // TODO add your handling code here:
        pesquisarPorRalato();

    }//GEN-LAST:event_jButtonBuscarPeriodoActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroSindicancia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroSindicancia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroSindicancia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroSindicancia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroSindicancia dialog = new CadastroSindicancia(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btAdicionaFuncionarioSindicancia;
    private javax.swing.JButton btAdicionaSentenciadoSindicancia;
    private javax.swing.JButton btBuscaPeriodoFinal;
    private javax.swing.JButton btBuscaPeriodoInicial;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButtonBuscarPeriodo;
    private com.toedter.calendar.JDateChooser jDateChooserDataSindicancia;
    private com.toedter.calendar.JDateChooser jDateChooserFinal;
    private com.toedter.calendar.JDateChooser jDateChooserInicial;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelIntFuncio;
    private javax.swing.JPanel jPanelIntSentenc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> jcbPesquisar;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelOcorrencia;
    private javax.swing.JPanel painelParticipantes;
    private javax.swing.JPanel painelTabela;
    private javax.swing.JTable tbFuncionario;
    private javax.swing.JTable tbSentenciado;
    private javax.swing.JTable tbSindicancia;
    private javax.swing.JTextArea txtAreaSindicancia;
    private javax.swing.JTextField txtLocalizar;
    // End of variables declaration//GEN-END:variables
}

package br.senai.sp.jandira.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import br.senai.sp.jandira.model.Aluno;
import br.senai.sp.jandira.model.Periodo;
import br.senai.sp.jandira.repository.AlunoRepository;

public class FrameCadastroAluno extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;

	private int posicao;

	public FrameCadastroAluno() {

		// exemplo de como usar um array

//		int[] numeros =new int[5];
//		numeros[0] = 8;
//		numeros[1] = 4;
//		numeros[2] = 36;
//		numeros[3] = 9;
//		numeros[4] = 90;
//		
//		int[] numbers = {45, 54 ,32, 12 ,32 ,656,12};
//		
		String[] dias = { "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado" };
//		
//		for(String dia : diasDaSemana) {
//			
//			System.out.println(dia);
//			
//		}

		setTitle("Cadastro de Alunos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(10, 11, 52, 14);
		contentPane.add(lblMatricula);

		txtMatricula = new JTextField();
		txtMatricula.setBounds(72, 8, 141, 20);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(72, 39, 141, 20);
		contentPane.add(txtNome);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 42, 36, 14);
		contentPane.add(lblNome);

		JLabel lblPeriodo = new JLabel("Periodo:");
		lblPeriodo.setBounds(10, 75, 52, 14);
		contentPane.add(lblPeriodo);

		JButton btnSalvarAluno = new JButton("Salvar Aluno");
		btnSalvarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalvarAluno.setBounds(10, 111, 203, 43);
		contentPane.add(btnSalvarAluno);

		JComboBox comboPeriodo = new JComboBox();
		DefaultComboBoxModel<String> modelPeriodo = new DefaultComboBoxModel<String>();

		for (Periodo periodo : Periodo.values()) {

			modelPeriodo.addElement(periodo.getDescricao());

		}

		comboPeriodo.setModel(modelPeriodo);
		comboPeriodo.setBounds(69, 70, 144, 22);
		contentPane.add(comboPeriodo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 39, 168, 211);
		contentPane.add(scrollPane);

		JList listAlunos = new JList();
		DefaultListModel<String> listaModel = new DefaultListModel<String>();
		listAlunos.setModel(listaModel);
		scrollPane.setViewportView(listAlunos);

		JLabel lblListaAlunos = new JLabel("Lista de alunos:");

		lblListaAlunos.setBounds(258, 11, 111, 14);
		contentPane.add(lblListaAlunos);

		JButton btnExibirAlunos = new JButton("Exibir alunos");
		btnExibirAlunos.setBounds(10, 165, 203, 43);
		contentPane.add(btnExibirAlunos);

		AlunoRepository turma = new AlunoRepository(3);

		btnSalvarAluno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Aluno aluno = new Aluno();
				aluno.setMatricula(txtMatricula.getText());
				aluno.setNome(txtNome.getText());
				aluno.setPeriodo(periodoSelecionado(comboPeriodo.getSelectedIndex()));

				turma.gravar(aluno, posicao);
				posicao++;

				listaModel.addElement(aluno.getNome());
				
				if (posicao == turma.getTamanhoTurma()) {
					
					btnSalvarAluno.setEnabled(false);
					JOptionPane.showMessageDialog(null, "a turma já encheu!","Turma cheia", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});

		btnExibirAlunos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (Aluno aluno : turma.listarTodos()) {
					System.out.println(aluno.getMatricula() + " - " + aluno.getNome()+" - "+ aluno.getPeriodo().getDescricao());

				}

			}
		});
		
		listAlunos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = listAlunos.getSelectedIndex();
				
				Aluno aluno = turma.listarAluno(i);
				
				txtMatricula.setText(aluno.getMatricula());
				txtNome.setText(aluno.getNome());
				comboPeriodo.setSelectedIndex(aluno.getPeriodo().ordinal());
				
			}
		});
		
	}

	private Periodo periodoSelecionado(int periodoSelecionado) {

		if (periodoSelecionado == 0) {
			return Periodo.MANHA;
		} else if (periodoSelecionado == 1) {
			return Periodo.TARDE;
		} else if (periodoSelecionado == 2) {
			return Periodo.NOITE;
		} else {
			return Periodo.JORGE;
		}
	}
}

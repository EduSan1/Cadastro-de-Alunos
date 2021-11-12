package br.senai.sp.jandira.repository;

import br.senai.sp.jandira.model.Aluno;

public class AlunoRepository {
	
	private Aluno[] turma;
	
	public AlunoRepository() {
		
		turma = new Aluno[32];
		
	}
	
	public AlunoRepository(int NumeroDeAlunos) {
		
		turma = new Aluno[NumeroDeAlunos];
		
	}
	
	public void gravar(Aluno aluno, int NumeroDoAluno) {
		
		turma[NumeroDoAluno] = aluno;
		
	}
	
	public Aluno listarAluno(int posicao) {
		
		return turma[posicao];
		
	}
	
	public Aluno[] listarTodos() {
		
		return turma;
		
	}
	
	public int getTamanhoTurma() {
		
		return turma.length;
		
	}

}
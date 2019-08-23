package model.entity.lista01;

import java.util.List;

public class Gerente extends Funcionario{
	
	private int cdGerencia;
	private String nmGerencia;
	private double comissao;
	private List<Operacional> operacionais;
	private String sigDiretoria;
	
	

	public Gerente(String nome, String cpf, String sexo, int idade, double salBruto, double descIR, double descInss,
			double salBase, double salLiquido, int cdGerencia, String nmGerencia, double comissao,
			List<Operacional> operacionais, String sigDiretoria) {
		super(nome, cpf, sexo, idade, salBruto, descIR, descInss, salBase, salLiquido);
		this.cdGerencia = cdGerencia;
		this.nmGerencia = nmGerencia;
		this.comissao = comissao;
		this.operacionais = operacionais;
		this.sigDiretoria = sigDiretoria;
	}
	
	

	public Gerente() {
		// TODO Auto-generated constructor stub
	}



	public String getSigDiretoria() {
		return sigDiretoria;
	}



	public void setSigDiretoria(String sigDiretoria) {
		this.sigDiretoria = sigDiretoria;
	}



	public int getCdGerencia() {
		return cdGerencia;
	}

	public void setCdGerencia(int cdGerencia) {
		this.cdGerencia = cdGerencia;
	}

	public String getNmGerencia() {
		return nmGerencia;
	}

	public void setNmGerencia(String nmGerencia) {
		this.nmGerencia = nmGerencia;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	public List<Operacional> getOperacionais() {
		return operacionais;
	}

	public void setOperacionais(List<Operacional> operacionais) {
		this.operacionais = operacionais;
	}

	@Override
	public double calculaSalarioFinal() {
		this.salLiquido = this.getSalBase() * ((this.getSalBase() /100) * 90) + this.getComissao();
		return salLiquido;
	}

	@Override
	public double calculaDescontoIR() {
		// TODO Auto-generated method stub
		return super.calculaDescontoIR();
	}

	@Override
	public double calculaDescontoINSS() {
		// TODO Auto-generated method stub
		return super.calculaDescontoINSS();
	}

	@Override
	public double calculaSalBase() {
		// TODO Auto-generated method stub
		return super.calculaSalBase();
	}
	
	

}

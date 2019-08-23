package model.entity.lista01;

import java.util.List;

public class Diretoria extends Funcionario {
	
	private int cdDiretoria;
	private String sigDiretoria;
	private double comissao;
	private List<Gerente> gerentes;
	
	public Diretoria(String nome, String cpf, String sexo, int idade, double salBruto, double descIR, double descInss,
			double salBase, double salLiquido, int cdDiretoria, String sigDiretoria, double comissao) {
		super(nome, cpf, sexo, idade, salBruto, descIR, descInss, salBase, salLiquido);
		this.cdDiretoria = cdDiretoria;
		this.sigDiretoria = sigDiretoria;
		this.comissao = comissao;
	}

	public int getCdDiretoria() {
		return cdDiretoria;
	}

	public void setCdDiretoria(int cdDiretoria) {
		this.cdDiretoria = cdDiretoria;
	}

	public String getSigDiretoria() {
		return sigDiretoria;
	}

	public void setSigDiretoria(String sigDiretoria) {
		this.sigDiretoria = sigDiretoria;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	public List<Gerente> getGerentes() {
		return gerentes;
	}

	public void setGerentes(List<Gerente> gerentes) {
		this.gerentes = gerentes;
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

	@Override
	public double calculaSalarioFinal() {
		this.salLiquido = 3 * this.getSalBase() + this.getComissao();
		return salLiquido;
	}

	
	
	
}

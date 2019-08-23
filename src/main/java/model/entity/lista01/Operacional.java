package model.entity.lista01;



public class Operacional extends Funcionario {

	private int cdOperacional;
	private String nmGerencia;

	
	public Operacional(String nome, String cpf, String sexo, int idade, double salBruto, double descIR, double descInss,
			double salBase, double salLiquido, int cdOperacional, String nmGerencia) {
		super(nome, cpf, sexo, idade, salBruto, descIR, descInss, salBase, salLiquido);
		this.cdOperacional = cdOperacional;
		this.nmGerencia = nmGerencia;
	}

	


	public Operacional() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getCdOperacional() {
		return cdOperacional;
	}




	public void setCdOperacional(int cdOperacional) {
		this.cdOperacional = cdOperacional;
	}


	public String getNmGerencia() {
		return nmGerencia;
	}



	public void setNmGerencia(String nmGerencia) {
		this.nmGerencia = nmGerencia;
	}




	@Override
	public double calculaSalarioFinal() {
		this.salLiquido = this.getSalBase() * ((this.getSalBase() / 100) * 85);
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

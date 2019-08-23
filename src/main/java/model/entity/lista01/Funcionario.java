package model.entity.lista01;

public abstract class Funcionario {
	
	
	protected String nome;
	protected String cpf;
	protected String sexo;
	protected int idade;
	protected double salBruto;
	protected double descIR;
	protected double descInss;
	protected double salBase;
	protected double salLiquido;
	
	public Funcionario() {}

	public Funcionario(String nome, String cpf, String sexo, int idade, double salBruto, double descIR, double descInss,
			double salBase, double salLiquido) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.idade = idade;
		this.salBruto = salBruto;
		this.descIR = descIR;
		this.descInss = descInss;
		this.salBase = salBase;
		this.salLiquido = salLiquido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getSalBruto() {
		return salBruto;
	}

	public void setSalBruto(double salBruto) {
		this.salBruto = salBruto;
	}

	public double getDescIR() {
		return descIR;
	}

	public void setDescIR(double descIR) {
		this.descIR = calculaDescontoIR();
	}

	public double getDescInss() {
		return descInss;
	}

	public void setDescInss(double descInss) {
		this.descInss = calculaDescontoINSS();
	}

	public double getSalBase() {
		return salBase;
	}

	public void setSalBase(double salBase) {
		this.salBase = calculaSalBase();
	}
	

	public double getSalLiquido() {
		return salLiquido;
	}

	public void setSalLiquido(double salLiquido) {
		this.salLiquido = calculaSalarioFinal();
	}

	public double calculaDescontoIR() {
		
		if (this.getSalBruto() < 1800.00) {
			descIR = 0;
		}
		else if (this.getSalBruto() > 1800.00 && this.getSalBruto() < 3000.00) {
			descIR = this.getSalBruto() - ((this.getSalBruto() / 100) * 10);
		}
		else {
			descIR = this.getSalBruto() - ((this.getSalBruto() / 100) * 12);
		}
		return descIR;
	}
	
	public double calculaDescontoINSS() {
		
		if (this.getIdade() < 45) {
			descInss = this.getSalBruto() - ((this.getSalBruto()/ 100) * 12);
			
		}
		else {
			descInss = this.getSalBruto() - ((this.getSalBruto() / 100) * 8);
		}
		
		return descInss;
	}
	
	public double calculaSalBase() {
		this.salBase = this.getSalBruto() - (this.descInss + this.descIR);
		return salBase;
	}
	
	public abstract double calculaSalarioFinal();

}

package fag.objetos;

public class Produto {

	private String nome;
	private double preco;
	private int estoque;
	
	public Produto() {
	}

	public Produto(String nome, double preco, int estoque) {
		setNome(nome);
		setPreco(preco);
		setEstoque(estoque);
	}
	
	//GettersInicio-----------------------------------------------------------------//
	
	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public int getEstoque() {
		return estoque;
	}
	
	//GettersFim--------------------------------------------------------------------//
	
	//SettersInicio-----------------------------------------------------------------//
	
	public void setNome(String nome) {
		if(nome != null && !nome.isBlank()) {
		this.nome = nome;
		}
	}
	
	public void setPreco(double preco) {
		if(preco>=0) {
		this.preco = preco;
		}
	}
	
	public void setEstoque(int estoque) {
		if(estoque>=0) {
		this.estoque = estoque;
		}
	}
	
	//SettersFim--------------------------------------------------------------------//
	
	public void mostrarEstoque() {
		System.out.printf("Produto: %s a R$%.2f, estoque: %d\n", nome, preco, estoque);
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", preco=" + preco + ", estoque=" + estoque + "]";
	}
	
}
package fag.objetos;

public class Nota {
	
	private double valor;
	private int quantidade;
	private double total;

	public Nota() {
	}

	public Nota(double valor, int quantidade) {
		setValor(valor);
		setQuantidade(quantidade);
		setTotal(valor,quantidade);
	}
	
	//GettersInicio-----------------------------------------------------------------//
	
		public double getValor() {
			return valor;
		}

		public int getQuantidade() {
			return quantidade;
		}

		public double getTotal() {
			return total;
		}
		
		//GettersFim--------------------------------------------------------------------//
	
		//SettersInicio-----------------------------------------------------------------//
		
		public void setValor(double valor) {
			if(valor>0) {
				this.valor=valor;
			}
		}
		
		public void setQuantidade(int quantidade) {
			if(quantidade>=0) {
			this.quantidade = quantidade;
			}
		}
		
		public void setTotal(double valor, int quantidade) {
			this.total=valor*quantidade;
		}
		
		//SettersFim--------------------------------------------------------------------//
		
		public void mostrarNotas() {
			System.out.printf("Notas de R$%.2f: %d, totalizando: %.2f.\n", valor, quantidade, total);
		}
		
		public void escolherNotas() {
			System.out.printf("Cédula de R$%.2f: %d\n", valor, quantidade);
		}
		
}

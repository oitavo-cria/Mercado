package fag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fag.objetos.Nota;
import fag.objetos.Produto;

			public class Principal {
			
				static Scanner scan = new Scanner(System.in);
				static List<Nota> caixa = new ArrayList<>();
				static List<Nota> carteira = new ArrayList<>();
				static List<Produto> estoque = new ArrayList<>();
				static List<Produto> carrinho = new ArrayList<>();
				
				public static void main(String[] args) {
					iniciarCarteira();
					popularCaixa();
					//mostrarCarteira();
					popularEstoque();
					mostrarMenu();
				}
				
				public static void iniciarCarteira() {
					Nota nota200 = new Nota(200, 0);
					Nota nota100 = new Nota(100, 0);
					Nota nota50 = new Nota(50, 0);
					Nota nota20 = new Nota(20, 0);
					Nota nota10 = new Nota(10, 0);
					Nota nota5 = new Nota(5, 0);
					Nota nota2 = new Nota(2, 0);
					Nota nota1 = new Nota(1, 0);
					
					carteira.add(nota200);
					carteira.add(nota100);
					carteira.add(nota50);
					carteira.add(nota20);
					carteira.add(nota10);
					carteira.add(nota5);
					carteira.add(nota2);
					carteira.add(nota1);
				}
			
				public static void popularCaixa() {
					Nota nota200 = new Nota(200, 0);
					Nota nota100 = new Nota(100, 0);
					Nota nota50 = new Nota(50, 5);
					Nota nota20 = new Nota(20, 0);
					Nota nota10 = new Nota(10, 5);
					Nota nota5 = new Nota(5, 5);
					Nota nota2 = new Nota(2, 0);
					Nota nota1 = new Nota(1, 0);
					
					caixa.add(nota200);
					caixa.add(nota100);
					caixa.add(nota50);
					caixa.add(nota20);
					caixa.add(nota10);
					caixa.add(nota5);
					caixa.add(nota2);
					caixa.add(nota1);
				}
				
				public static void popularEstoque() {
					Produto produtoUm = new Produto("Batata", 5, 120);
					Produto produtoDois = new Produto("Pão", 2, 100);
					Produto produtoTres = new Produto("Ovo", 1, 240);
					Produto produtoQuatro = new Produto("Tomate", 3.25, 60);
					Produto produtoCinco = new Produto("Laranja", 2.50, 150);
					
					estoque.add(produtoUm);
					estoque.add(produtoDois);
					estoque.add(produtoTres);
					estoque.add(produtoQuatro);
					estoque.add(produtoCinco);
				}
				
				public static void mostrarMenu() {
					int escolha = -1;
					do {
					System.out.println("============================== MENU ==============================");
					System.out.println("1 - Cadastrar Produto");
					System.out.println("2 - Verificar Estoque");
					System.out.println("3 - Realizar Venda");
					System.out.println("4 - Exibir Saldo e Cédulas Disponíveis");
					System.out.println("0 - Sair");
					
					escolha = scan.nextInt();
					scan.nextLine();
					validarEscolha(escolha);
					}while(escolha!=0);
				}
				
				public static void validarEscolha(int escolha) {
					switch (escolha) {
					case 1:
						cadastrarProduto();
						break;
					case 2:
						listarEstoque();
						break;
					case 3:
						realizarVenda();
						break;
					case 4:
						mostrarCaixa();
						break;
					case 0:
						System.out.println("Obrigado pela preferência!");
						break;
					default:
						break;
				}
			}
			
			public static void cadastrarProduto() {
				Produto produto = new Produto();
				System.out.println("Insira o nome do produto:");
				produto.setNome(scan.nextLine());
				System.out.println("Insira o valor do produto:");
				produto.setPreco(scan.nextDouble());
				System.out.println("Insira a quantidade do produto:");
				produto.setEstoque(scan.nextInt());
				
				scan.nextLine();
				
				boolean existe=false;
				for(int i=0; i<estoque.size(); i++) {
					if(estoque.get(i).getNome().equals(produto.getNome())) {
						estoque.get(i).setEstoque(estoque.get(i).getEstoque() + produto.getEstoque());
						System.out.printf("Produto %s já existe e custa R$%.2f. Estoque atualizado!\n", estoque.get(i).getNome(), estoque.get(i).getPreco());
						existe=true;
						break;
					}
				}
				if(!existe) {
					estoque.add(produto);
					System.out.println("Produto cadastrado com sucesso!");
				}
			}
			
			public static void listarEstoque() {
				System.out.println("Estoque:");
				for (int i = 0; i < estoque.size(); i++) {
					System.out.printf("Espaço %d - ", i+1);
					estoque.get(i).mostrarEstoque();
				}
				System.out.printf("\n");
			}
			
			public static void realizarVenda() {
				adicionarCarrinho();
				mostrarCarrinho();
				realizarPagamento();
				calcularTroco();
			}
			
			public static void mostrarCaixa() {
				System.out.println("Caixa:");
				for (int i = 0; i < caixa.size(); i++) {
					System.out.printf("Nota: ");
					caixa.get(i).mostrarNotas();
				}
				System.out.printf("\n");
			}
			
			public static void adicionarCarrinho() {
				int resposta=0;
				int quantidade=0;
				do {
				System.out.printf("\n");
				listarEstoque();
				System.out.printf("Selecione o número do produto que deseja adicionar ao carrinho:\n");
				int escolha = scan.nextInt();
				if (escolha > estoque.size()) {
					System.out.println("ERRO: Item não existe!");
					return;
				}
				
				Produto produtoEstoque = estoque.get(escolha-1);
				System.out.println("Selecione a quantidade que deseja comprar:");
				quantidade = scan.nextInt();
				
				if(quantidade>produtoEstoque.getEstoque()) {
					System.out.println("ERRO: Quantidade indisponível no estoque!");
					return;
				}
				
				produtoEstoque.setEstoque(produtoEstoque.getEstoque()-quantidade);
				Produto produtoCarrinho = new Produto(produtoEstoque.getNome(), produtoEstoque.getPreco(), quantidade);
				
				//------------------------------------------------------------------------------//
				
				boolean existe=false;
				for(int i=0; i<carrinho.size(); i++) {
					if(carrinho.get(i).getNome().equals(produtoEstoque.getNome())) {
						carrinho.get(i).setEstoque(carrinho.get(i).getEstoque() + quantidade);
						existe=true;
						break;
					}
				}
				if(!existe) {
					carrinho.add(produtoCarrinho);
				}
					
				//------------------------------------------------------------------------------//
				
				System.out.print("\nProduto adicionado ao carrinho!\n");
				
				do {
				System.out.println("Deseja continuar comprando?");
				System.out.println("(1) - SIM    ==   (2) - NÃO");
				resposta = scan.nextInt();
				if(resposta!=1 && resposta!=2) {
					System.out.printf("\nERRO: Selecione uma opção válida!\n");
				}
				}while(resposta!=1 && resposta!=2);
				}while(resposta!=2);
			}
			
			public static void mostrarCarrinho() {
				System.out.println("Carrinho:");
				for (int i = 0; i < carrinho.size(); i++) {
					System.out.printf("Espaço %d - ", i+1);
					carrinho.get(i).mostrarEstoque();
				}
				double soma=somarCarrinho();
				System.out.printf("Valor total no carrinho: R$%.2f\n", soma);
				System.out.printf("\n");
			}
			
			public static void realizarPagamento() {
				int resposta=0;
				int escolha=0;
				int quantidade=0;
				double somaCarrinho = somarCarrinho();
				double somaCarteira = somarCarteira();
				double troco = 0;
				do {
				do {
				do {
				System.out.println("Selecione as notas que deseja usar no pagamento!");
				mostrarCarteira();
				escolha=scan.nextInt();
				if(escolha>carteira.size()) {
					System.out.println("ERRO: Nota Inexistente!");
				}
				}while(escolha > carteira.size());
				
				Nota nota = carteira.get(escolha-1);
				do {
				System.out.println("Quantas notas deseja usar?");
				quantidade = scan.nextInt();
				}while(quantidade<0);
				
				nota.setQuantidade(nota.getQuantidade()+quantidade);
				Nota notaCarteira = new Nota(nota.getValor(), nota.getQuantidade());
				
				for(int i=0; i<carteira.size(); i++) {
					if(carteira.get(i).getValor()==(notaCarteira.getValor())) {
						carteira.get(i).setQuantidade(carteira.get(i).getQuantidade());
						carteira.get(i).setTotal(carteira.get(i).getValor(), carteira.get(i).getQuantidade());
						break;
					}
				}
				mostrarCarteira();
				do {
				System.out.println("Deseja adicionar mais notas?");
				System.out.println("(1) - SIM    ==   (2) - NÃO");
				resposta=scan.nextInt();
				if(resposta!=1 && resposta!=2) {
					System.out.printf("\nERRO: Selecione uma opção válida!\n");
				}
				}while(resposta!=1 && resposta!=2);
				
				somaCarteira = somarCarteira();
				}while(resposta!=2);
				if(somaCarteira<somaCarrinho) {
					System.out.println("Valor Insuficiente: Tente Novamente!");
				}
				}while(somaCarteira<somaCarrinho);
				
			}
			
			public static double somarCarrinho() {
				double soma=0;
				for (int i = 0; i < carrinho.size(); i++) {
					soma+=carrinho.get(i).getPreco()*carrinho.get(i).getEstoque();
				}
				return soma;
			}
			
			public static void mostrarCarteira() {
				System.out.println("Carteira: ");
				for(int i=0; i < carteira.size();i++) {
					System.out.printf("%d - ",i+1);
					carteira.get(i).mostrarNotas();
				}
				double soma=somarCarteira();
				System.out.printf("Valor total na carteira é: R$%.2f\n", soma);
				System.out.printf("\n");
			}
			
			public static double somarCarteira() {
				double soma=0;
				for (int i = 0; i < carteira.size(); i++) {
					soma+=carteira.get(i).getTotal();
				}
				return soma;
			}
			
			public static void calcularTroco() {
				double troco=0;
				troco=somarCarteira() - somarCarrinho();
			}
			
		}
package fag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fag.objetos.Produto;

		public class Principal {
		
			static Scanner scan = new Scanner(System.in);
			static List<Produto> estoque = new ArrayList<>();
			static List<Produto> carrinho = new ArrayList<>();
			
			public static void main(String[] args) {
				popularEstoque();
				mostrarMenu();
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
				default:
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
				estoque.add(produto);
				System.out.println("Produto cadastrado com sucesso!");
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
				System.out.printf("\n");
			}
			
		}
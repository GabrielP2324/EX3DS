package ex3sala;

import arvoreBinaria.*;
import static javax.swing.JOptionPane.*;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Menu {
	ArvoreBinaria<BilheteUnico> tree = new ArvoreBinaria<BilheteUnico>();
	LinkedList<Usuario> list = new LinkedList<Usuario>();

	// I) O atributo tipoTarifa na classe Usu�rio dever� receber apenas os valores:
	// estudante (tarifa para estudante), professor (tarifa para professor) ou
	// normal (tarifa normal).
//	II)  O n�mero do bilhete �nico dever� ser gerado de forma aleat�ria e ser� um n�mero inteiro entre 100 
//	e 5000. N�o pode haver dois bilhetes com o mesmo n�mero. 
//	III) O m�todo carregarBilhete() dever� receber como par�metro o valor da recarga e dever� adicion�-lo 
//	ao saldo do bilhete previamente cadastrado. 
//	IV) O m�todo passarNaCatraca() dever� debitar o valor corresponde ao valorPassagem do saldo do 
//	bilhete. Tarifas do tipo estudante e professor pagam 50% do valor da passagem e, a tarifa normal, 
//	paga o valor cheio (sem descontos). 
	public void menuMain() {
		String temp = "";
		String[] options = { "Administrador", "Usu�rio", "Sair" };
		temp += "Quem � voc�? \n";
		int escolha = 0;
		do {
			escolha = showOptionDialog(getRootFrame(), temp, "Menu", YES_NO_CANCEL_OPTION, INFORMATION_MESSAGE, null,
					options, 2);
			switch (escolha) {
			case 0:
				menuAdm();
				break;
			case 1:
				menuUsuario();
				break;
			}
		} while (escolha != 2);
	}

	private void menuUsuario() {
		int choice = 0;
		String temp = "1. Consultar Saldo\n2. Carregar Bilhere\n3. Passar Catraca\n4. Sair";
		do {
			choice = Integer.parseInt(JOptionPane.showInputDialog(temp));
			switch (choice) {
			case 1:
				consulta();
				break;
			case 2:
				carrega();
				break;
			case 3:
				passar();
				break;
			}
		} while (choice != 4);

	}

	private void passar() {
		if (tree.getRaiz() != null) {
			String cpf = showInputDialog("Coloque o CPF do Usu�rio para passar na catraca");
			Usuario temp = new Usuario();
			temp.setCpf(cpf);
			BilheteUnico b = null;
			if (list.contains(temp)) {
				b = tree.buscaBilhete(temp);
				if(b.getSaldo() >= b.getValorPassagem()) {
					b.passarNaCatraca();
					showMessageDialog(getRootFrame(), "Passou na catraca com sucesso!\nSaldo Restante: " + String.format("%.2f",b.getSaldo()));
				}else {
					showMessageDialog(getRootFrame(), "Saldo indispon�vel.\n Saldo necess�rio para passar: "+ String.format("%.2f",b.getValorPassagem()));
				}
				
				return;
			} else {
				showMessageDialog(null, "N�o existe um bilhete �nico com este CPF");
			}
		} else {
			showMessageDialog(getRootFrame(), "Nenhum bilhete �nico cadastrado");
		}
	}

	private void carrega() {
		if (tree.getRaiz() != null) {
			String cpf = showInputDialog("Coloque o CPF para adicionar saldo");
			Usuario temp = new Usuario();
			temp.setCpf(cpf);
			BilheteUnico b = null;
			if (list.contains(temp)) {
				b = tree.buscaBilhete(temp);
				double s = Double.parseDouble(showInputDialog("Coloque a quantidade para carregar\nSaldo Dispon�vel: " + String.format("%.2f",b.getSaldo())));
				b.carregarBilhete(s);
				return;
			} else {
				showMessageDialog(null, "N�o existe um bilhete �nico com este CPF");
			}
		} else {
			showMessageDialog(getRootFrame(), "Nenhum bilhete �nico cadastrado");
		}
	}

	private void consulta() {
		if (tree.getRaiz() != null) {
			String cpf = showInputDialog("Coloque o CPF para procurar");
			Usuario temp = new Usuario();
			temp.setCpf(cpf);
			BilheteUnico b = null;
			if (list.contains(temp)) {
				b = tree.buscaBilhete(temp);
				showMessageDialog(getRootFrame(), b.toString());
				return;
			} else {
				showMessageDialog(null, "N�o existe um bilhete �nico com este CPF");
			}
		} else {
			showMessageDialog(getRootFrame(), "Nenhum bilhete �nico cadastrado");
		}
	}

	private void menuAdm() {
		int escolha = 0;
		String temp = "1. Cadastrar Bilhete\n2. Consultar Bilhete\n3. Listar Bilhetes\n4. Sair";
		do {
			try {
				escolha = Integer.parseInt(showInputDialog(getRootFrame(), temp));
				switch (escolha) {
				case 1:
					cadastrarBilhete();
					break;
				case 2:
					consultarBilhete();
					break;
				case 3:
					listarBilhete();
					break;
				}
			} catch (NumberFormatException e) {
				escolha = 0;
			}
		} while (escolha != 4);

	}

	private void consultarBilhete() {
		String cpf = showInputDialog("Coloque o CPF para procurar");
		Usuario temp = new Usuario();
		temp.setCpf(cpf);
		BilheteUnico b = null;
		if (tree.getRaiz() != null) {
			if (list.contains(temp)) {
				b = tree.buscaBilhete(temp);
				showMessageDialog(getRootFrame(), b.toString());
				return;
			} else {
				showMessageDialog(null, "N�o existe um bilhete �nico com este CPF");
			}
		}else {
			showMessageDialog(getRootFrame(), "Nenhum bilhete �nico cadastrado");
		}
	}

	private void listarBilhete() {
		if (tree.getRaiz() == null) {
			showMessageDialog(getRootFrame(), "Nenhum bilhete �nico encontrado");
		} else {
			String aux = tree.toString(tree.getRaiz());
			showMessageDialog(getRootFrame(), aux);
		}
	}

	private void cadastrarBilhete() {
		BilheteUnico temp = new BilheteUnico();
		Random rng = new Random();
		int n = rng.nextInt(100, 5001);

		if (tree.getRaiz() == null) {
			temp.setNumero(rng.nextInt(100, 5001));
			Usuario temp2 = new Usuario();
			String aux = showInputDialog("Coloque o CPF do Usu�rio a ser cadastrado");
			temp2.setCpf(aux);
			aux = showInputDialog("Coloque o nome do Usu�rio a ser cadastrado");
			temp2.setNome(aux);
			aux = showInputDialog("Coloque o tipo de tarifa do Usu�rio");
			if (!aux.equalsIgnoreCase("aluno") || !aux.equalsIgnoreCase("professor")) {
				aux = "normal";
				temp.setValorPassagem(4.40);
			} else {
				temp.setValorPassagem(2.20);
			}
			temp2.setTipoTarifa(aux);
			list.add(temp2);
			temp.setUsuario(temp2);
			String[] options = { "Sim", "N�o" };
			int choice = showOptionDialog(getRootFrame(), "Gostaria de adicionar um saldo para o novo bilhete �nico?",
					null, YES_NO_OPTION, INFORMATION_MESSAGE, null, options, rng);
			if (choice == 0) {
				double saldo = Double.parseDouble(showInputDialog("Quanto de saldo gostar�a de colocar?"));
				if (saldo > 0) {
					temp.setSaldo(saldo);
				} else {
					temp.setSaldo(0);
				}
			} else {
				temp.setSaldo(0);
			}

			tree.inserir(temp);

		} else {
			while (tree.busca(n)) {
				n = rng.nextInt(0, 5001);
			}
			temp.setNumero(n);
			Usuario temp2 = new Usuario();
			String aux = showInputDialog("Coloque o CPF do Usu�rio a ser cadastrado");
			temp2.setCpf(aux);
			if (list.contains(temp2)) {
				do {
					aux = showInputDialog("CPF j� cadastrado. Coloque um valor v�lido");
					temp2.setCpf(aux);
				} while (list.contains(temp2));
			}
			aux = showInputDialog("Coloque o nome do Usu�rio a ser cadastrado");
			temp2.setNome(aux);
			aux = showInputDialog("Coloque o tipo de tarifa do Usu�rio");
			if (!aux.equalsIgnoreCase("aluno") || !aux.equalsIgnoreCase("professor")) {
				aux = "normal";
				temp.setValorPassagem(4.40);
			} else {
				temp.setValorPassagem(2.20);
			}
			temp2.setTipoTarifa(aux);
			list.add(temp2);
			temp.setUsuario(temp2);
			String[] options = { "Sim", "N�o" };
			int choice = showOptionDialog(getRootFrame(), "Gostaria de adicionar um saldo para o novo bilhete �nico?",
					null, YES_NO_OPTION, INFORMATION_MESSAGE, null, options, rng);
			if (choice == 0) {
				double saldo = Double.parseDouble(showInputDialog("Quanto de saldo gostar�a de colocar?"));
				if (saldo > 0) {
					temp.setSaldo(saldo);
				} else {
					temp.setSaldo(0);
				}
			} else {
				temp.setSaldo(0);
			}

			tree.inserir(temp);
		}
	}
}

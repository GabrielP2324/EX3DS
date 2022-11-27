package arvoreBinaria;

import javax.swing.JOptionPane;

import ex3sala.BilheteUnico;
import ex3sala.Usuario;

public class ArvoreBinaria<T extends Comparable<T>> {
	private No<T> raiz;
	private ArvoreBinaria<T> arvoreEsquerda;
	private ArvoreBinaria<T> arvoreDireita;

	// método para inserir um elemento na árvore binária de busca
	public void inserir(T dado) {
		No<T> no = new No<T>(dado);
		inserirNo(no);
	}

	// método auxiliar recursivo para inserir um elemento na árvore binária de
	// busca
	private void inserirNo(No<T> no) {
		if (raiz == null) {
			raiz = no;
			JOptionPane.showMessageDialog(null, "Bilhete �nico cadastrado com sucesso");
		} else {
			if (no.getDado().compareTo(raiz.getDado()) < 0) {
				if (arvoreEsquerda == null) {
					arvoreEsquerda = new ArvoreBinaria<>();
				}
				arvoreEsquerda.inserirNo(no);
			} else {
				if (no.getDado().compareTo(raiz.getDado()) > 0) {
					if (arvoreDireita == null) {
						arvoreDireita = new ArvoreBinaria<>();
					}
					arvoreDireita.inserirNo(no);

				} else {

				}
			}
		}
	}

	public BilheteUnico buscaBilhete(Usuario u) {
		BilheteUnico b = new BilheteUnico();
		BilheteUnico temp = (BilheteUnico) raiz.getDado();

		b.setUsuario(u);
		try {
			try {
				if (raiz == null)
					return null;

				if (arvoreEsquerda != null) {
					if (b.getUsuario().equals(temp.getUsuario())) {
						return temp;
					} else {
						temp = arvoreEsquerda.buscaBilhete(u);
					}
				}

				if (b.getUsuario().equals(temp.getUsuario())) {
					return temp;
				}

				if (arvoreDireita != null) {
					if (b.getUsuario().equals(temp.getUsuario())) {
						return temp;
					} else {
						temp = arvoreDireita.buscaBilhete(u);
					}
				}
			} catch (NullPointerException e) {
				temp = null;
			}
		} catch (Exception e) {
			temp = null;
		}

		return temp;

	}

	public boolean busca(int numero) {
		boolean teste = true;
		try {
			BilheteUnico b = (BilheteUnico) raiz.getDado();
			BilheteUnico c = new BilheteUnico();
			c.setNumero(numero);
			int aux = b.compareTo(c);
			try {
				if (aux == 0) {
					return teste;
				} else if (aux >= 1) {
					teste = arvoreDireita.busca(numero);
				} else if (aux <= -1) {
					teste = arvoreEsquerda.busca(numero);
				}
			} catch (NullPointerException e) {
				teste = false;
			}
		} catch (Exception e) {
			teste = false;
		}
		return teste;
	}

	// método para percorrer a árvore binária no percurso pré-ordem
	public void preOrdem() {
		if (raiz == null)
			return;

		System.out.print(raiz.getDado() + "  ");

		if (arvoreEsquerda != null) {
			arvoreEsquerda.preOrdem();
		}

		if (arvoreDireita != null) {
			arvoreDireita.preOrdem();
		}
	}

	// método para percorrer a árvore binária no percurso em-ordem
	public void emOrdem() {
		if (raiz == null)
			return;

		if (arvoreEsquerda != null) {
			arvoreEsquerda.emOrdem();
		}

		System.out.println(raiz.getDado() + "  ");

		if (arvoreDireita != null) {
			arvoreDireita.emOrdem();
		}
	}

	// método para percorrer a árvore binária no percurso pós-ordem
	public void posOrdem() {
		if (raiz == null)
			return;

		if (arvoreEsquerda != null) {
			arvoreEsquerda.posOrdem();
		}

		if (arvoreDireita != null) {
			arvoreDireita.posOrdem();
		}

		System.out.println(raiz.getDado() + "  ");
	}

	// método para remover um elemento da árvore binária de busca
	public ArvoreBinaria<T> remover(T valor) {
		return removerNo(this, valor);
	}

	// método auxiliar recursivo para remover um elemento da árvore binária de
	// busca
	private ArvoreBinaria<T> removerNo(ArvoreBinaria<T> aux, T valor) {
		ArvoreBinaria<T> p, p2;
		if (aux.getRaiz().getDado().compareTo(valor) == 0) {
			if (aux.arvoreEsquerda == aux.arvoreDireita) {
				return null;
			} else if (aux.arvoreEsquerda == null) {
				return aux.arvoreDireita;
			} else if (aux.arvoreDireita == null) {
				return aux.arvoreEsquerda;
			} else {
				p2 = aux.arvoreDireita;
				p = aux.arvoreDireita;
				while (p.arvoreEsquerda != null) {
					p = p.arvoreEsquerda;
				}
				p.arvoreEsquerda = aux.arvoreEsquerda;
				return p2;
			}
		} else if (aux.getRaiz().getDado().compareTo(valor) < 0) {
			aux.arvoreDireita = removerNo(aux.arvoreDireita, valor);
		} else {
			aux.arvoreEsquerda = removerNo(aux.arvoreEsquerda, valor);
		}
		return aux;
	}

	// métodos get e set
	public No<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(No<T> raiz) {
		this.raiz = raiz;
	}

	public ArvoreBinaria<T> getArvoreEsquerda() {
		return arvoreEsquerda;
	}

	public void setArvoreEsquerda(ArvoreBinaria<T> arvoreEsquerda) {
		this.arvoreEsquerda = arvoreEsquerda;
	}

	public ArvoreBinaria<T> getArvoreDireita() {
		return arvoreDireita;
	}

	public void setArvoreDireita(ArvoreBinaria<T> arvoreDireita) {
		this.arvoreDireita = arvoreDireita;
	}

	public String toString(No<T> raiz) {
		String temp = "";
		if (raiz == null) {
			return "";
		}
		if (arvoreEsquerda != null) {
			temp += arvoreEsquerda.toString(arvoreEsquerda.getRaiz());
		}
		temp += raiz.toString() + "\n";
		if (arvoreDireita != null) {
			temp += arvoreDireita.toString(arvoreDireita.getRaiz());
		}
		return temp;
	}

}

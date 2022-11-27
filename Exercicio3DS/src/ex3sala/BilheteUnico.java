package ex3sala;

import javax.swing.JOptionPane;

public class BilheteUnico implements Comparable<BilheteUnico> {
	private int numero;
	private double saldo;
	private double valorPassagem;
	private Usuario usuario;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getValorPassagem() {
		return valorPassagem;
	}

	public void setValorPassagem(double valorPassagem) {
		this.valorPassagem = valorPassagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void carregarBilhete(double valor) {
		if (valor > 0) {
			setSaldo(getSaldo() + valor);
		}else {
			
		}
	}

	public void passarNaCatraca() {
		if(this.saldo>=this.valorPassagem) {
			this.saldo -= valorPassagem;
		}
		
	}

	@Override
	public boolean equals(Object obj) {
		BilheteUnico other = (BilheteUnico) obj;
		return numero == other.numero;
	}

	@Override
	public int compareTo(BilheteUnico o) {
		if (numero == o.numero && usuario.getCpf().equals(o.usuario.getCpf())) {
			return 0;
		} else if (numero > o.numero) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Bilhete Único numero= " + numero + " "+String.format(", saldo= %.2f ", saldo) +  usuario.toString();
	}
}

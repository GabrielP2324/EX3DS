package ex3sala;

import java.util.Objects;

public class Usuario implements Comparable<Usuario> {
	private String nome;
	private String cpf;
	private String tipoTarifa;

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

	public String getTipoTarifa() {
		return tipoTarifa;
	}

	public void setTipoTarifa(String tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

	@Override
	public boolean equals(Object obj) {
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public int compareTo(Usuario o) {
		return cpf.compareTo(o.cpf);
	}

	@Override
	public String toString() {
		return "nome= " + nome + ", cpf= " + cpf ;
	}
}

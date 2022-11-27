package arvoreBinaria;

public class No<T extends Comparable<T>> {
	private T dado;
	
	public No(T dado) {
		this.dado = dado;
	}
	
	public T getDado() {
		return dado;
	}
	
	public void setDado(T dado) {
		this.dado = dado;
	}

	@Override
	public String toString() {
		return dado.toString();
	}
	
}

package pacote.biblioteca;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class LivroMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();
	private List<Livro> livros;
	private String tituloBusca;

	@Inject
	private LivroDAO livroDAO;

	// Salvar novo livro
	public void salvar() {
		livroDAO.salvar(livro);
		livro = new Livro();
		carregarLivros();
	}

	// Remover livro
	public void remover(Livro livro) {
		livroDAO.remover(livro.getId());
		carregarLivros();
	}

	// Carregar todos livros
	public void carregarLivros() {
		livros = livroDAO.listar();
	}

	// Busca Livro parcial
	public void buscarTitulo() {
		if (tituloBusca != null && !tituloBusca.trim().isEmpty()) {
			livros = livroDAO.buscarTituloParcial(tituloBusca);
		} else {
			carregarLivros();
		}
	}

	// Getter p/ Enum usado no selectOneMenu
	public Genero[] getGeneros() {
		return Genero.values();
	}

	// Getters e Setters
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Livro> getLivros() {
		if (livros == null) {
			carregarLivros();
		}
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public String getTituloBusca() {
		return tituloBusca;
	}

	public void setTituloBusca(String tituloBusca) {
		this.tituloBusca = tituloBusca;
	}
}

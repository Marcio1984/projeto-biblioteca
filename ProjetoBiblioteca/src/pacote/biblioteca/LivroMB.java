package pacote.biblioteca;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	
	@PostConstruct
	public void init() {
	    System.out.println(">>> LivroMB inicializado. DAO: " + livroDAO);
	}

	// Salvar novo livro
	public void salvar() {
	    try {
	        livroDAO.salvar(livro);
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Livro salvo com sucesso."));
	    } catch (Exception e) {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar livro: " + e.getMessage(), null));
	        e.printStackTrace();
	    }
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
	
	public void debug() {
	    System.out.println(">>> DEBUG: LivroMB chamado. Dados atuais:");
	    System.out.println(">>> Título: " + livro.getTitulo());
	    System.out.println(">>> Autor: " + livro.getAutor());
	    System.out.println(">>> Editora: " + livro.getEditora());
	    System.out.println(">>> Gênero: " + livro.getGenero());
	    System.out.println(">>> Páginas: " + livro.getNumeroPaginas());
	}
	
}

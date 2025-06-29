package pacote.biblioteca;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LivroDAO {

	@PersistenceContext(unitName = "bibliotecaPU")
	private EntityManager gerenciadorEntidade;
	
	public void salvar(Livro livro) {
		if (livro.getId() == null) {
			gerenciadorEntidade.persist(livro);
		} else {
			gerenciadorEntidade.merge(livro);
		}
	}
	
	public List<Livro> buscarTituloParcial(String tituloParcial) {
		return gerenciadorEntidade.createQuery(
			"SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE :titulo", Livro.class)
			.setParameter("titulo", "%" + tituloParcial.toLowerCase() + "%")
			.getResultList();		
	}
	
	public List<Livro> listar(){
		return gerenciadorEntidade.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
	}
	
	public void remover(Long id) {
		Livro livroConsultado = gerenciadorEntidade.find(Livro.class,id);
		if (livroConsultado != null){
			gerenciadorEntidade.remove(livroConsultado);
		}
	}
}

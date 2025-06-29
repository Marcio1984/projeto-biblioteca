package pacote.biblioteca;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro {
	
		// Atributos da classe
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id; 
		private String titulo;
		private String autor;
		private String editora;
		@Enumerated(EnumType.STRING)
		private Genero genero;
		private int numeroPaginas;
		
		
		// Construtor Vazio
		public Livro() {	
	}
		public Livro(String titulo, String autor, String editora, Genero genero, int numeroPaginas) {
			super();
			this.titulo = titulo;
			this.autor = autor;
			this.editora = editora;
			this.genero = genero;
			this.numeroPaginas = numeroPaginas;
		}

		// Getters e Setters
		public Long getId() {
			return id;
		}
		
		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getAutor() {
			return autor;
		}

		public void setAutor(String autor) {
			this.autor = autor;
		}

		public String getEditora() {
			return editora;
		}

		public void setEditora(String editora) {
			this.editora = editora;
		}

		public Genero getGenero() {
			return genero;
		}

		public void setGenero(Genero genero) {
			this.genero = genero;
		}

		public int getNumeroPaginas() {
			return numeroPaginas;
		}

		public void setNumeroPaginas(int numeroPaginas) {
			this.numeroPaginas = numeroPaginas;
		}

}

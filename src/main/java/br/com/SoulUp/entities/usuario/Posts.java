package br.com.SoulUp.entities.usuario;

public class Posts {
        private int idPost;
        private String conteudo;
        private String cpfUsuario;
    private String nomeAutor;

    public Posts() {}

        public Posts(String conteudo, String cpfUsuario) {
            this.conteudo = conteudo;
            this.cpfUsuario = cpfUsuario;
        }

        public int getIdPost() {
            return idPost;
        }

        public void setIdPost(int idPost) {
            this.idPost = idPost;
        }

        public String getConteudo() {
            return conteudo;
        }

        public void setConteudo(String conteudo) {
            this.conteudo = conteudo;
        }

        public String getCpfUsuario() {
            return cpfUsuario;
        }

        public void setCpfUsuario(String cpfUsuario) {
            this.cpfUsuario = cpfUsuario;
        }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}


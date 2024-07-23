package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.api.produtos.modelo.PostModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.PostRepositorio;

@Service
public class PostServico {

    @Autowired
    private PostRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    // Método para listar todos os produtos
    public Iterable<PostModelo> listar() {
        return pr.findAll();
    }

    // Método para cadastrar ou alterar produto
    public ResponseEntity<?> cadastrarAlterar(PostModelo pm, String acao) {

        if (pm.getAutor().equals("")) {
            rm.setMensagem("O nome do autor é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getDataPublicacao() == null) {
            rm.setMensagem("A dataPublicacao é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<PostModelo>(pr.save(pm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<PostModelo>(pr.save(pm), HttpStatus.OK);
            }
        }
    }

    // Método para buscar um post por código
    public PostModelo buscarPorCodigo(Long codigo) {
        return pr.findById(codigo).orElse(null);
    }

    // Método para remover trade
    public ResponseEntity<RespostaModelo> remover(Long codigo) {
        pr.deleteById(codigo);
        rm.setMensagem("O autor foi removido");
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }
}
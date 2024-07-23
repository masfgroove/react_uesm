package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.modelo.UsuarioModelo;
import br.com.api.produtos.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio ur;

    @Autowired
    private RespostaModelo rm;

    // Método para listar todos os produtos
    public Iterable<UsuarioModelo> listar() {
        return ur.findAll();
    }

    // Método para cadastrar ou alterar produto
    public ResponseEntity<?> cadastrarAlterar(UsuarioModelo pm, String acao) {

        if (pm.getNome().equals("")) {
            rm.setMensagem("O nome do usuario é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getSenha().equals("")) {
            rm.setMensagem("O nome da senha é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<UsuarioModelo>(ur.save(pm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<UsuarioModelo>(ur.save(pm), HttpStatus.OK);
            }
        }
    }

    public UsuarioModelo buscarPorNomeESenha(String nome, String senha) {
        return ur.findByNomeAndSenha(nome, senha);
    }
    
    
    // Método para remover produto
    public ResponseEntity<RespostaModelo> remover(Long codigo) {

        ur.deleteById(codigo);

        rm.setMensagem("O produto foi removido");

        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }
}
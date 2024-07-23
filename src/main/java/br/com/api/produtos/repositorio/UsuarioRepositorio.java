package br.com.api.produtos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.produtos.modelo.UsuarioModelo;

@Repository
public interface UsuarioRepositorio extends CrudRepository<UsuarioModelo, Long> {
    UsuarioModelo findByNomeAndSenha(String nome, String senha);
    
}

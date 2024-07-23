package br.com.api.produtos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.produtos.modelo.PostModelo;

@Repository
public interface PostRepositorio extends CrudRepository<PostModelo, Long> {
    PostModelo findByCodigo(Long codigo);
}

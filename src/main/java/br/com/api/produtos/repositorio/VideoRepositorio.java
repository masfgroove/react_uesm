package br.com.api.produtos.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.produtos.modelo.PostModelo;
import br.com.api.produtos.modelo.VideoModelo;

@Repository
public interface VideoRepositorio extends CrudRepository<VideoModelo, Long> {
    PostModelo findByCodigo(Long codigo);
    List<VideoModelo> findByAno(int ano);
}

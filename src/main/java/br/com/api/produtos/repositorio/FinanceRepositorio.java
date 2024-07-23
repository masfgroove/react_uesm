package br.com.api.produtos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.produtos.modelo.FinanceModelo;

@Repository
public interface FinanceRepositorio extends CrudRepository<FinanceModelo, Long> {

}

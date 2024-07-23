package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.FinanceModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.FinanceRepositorio;

@Service
public class FinanceServico {

    @Autowired
    private FinanceRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    // Método para listar todos os produtos
    public Iterable<FinanceModelo> listar() {
        return pr.findAll();
    }

    // Método para cadastrar ou alterar produto
    public ResponseEntity<?> cadastrarAlterar(FinanceModelo pm, String acao) {

        if (pm.getNome().equals("")) {
            rm.setMensagem("O nome do trade é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getData() == null) {
            rm.setMensagem("A data é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<FinanceModelo>(pr.save(pm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<FinanceModelo>(pr.save(pm), HttpStatus.OK);
            }
        }
    }

    // Método para remover trade
    public ResponseEntity<RespostaModelo> remover(Long codigo) {
        pr.deleteById(codigo);
        rm.setMensagem("O trade foi removido");
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }
}
package br.com.api.produtos.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.api.produtos.modelo.VideoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.VideoRepositorio;

@Service
public class VideoServico {

    @Autowired
    private VideoRepositorio vr;

    @Autowired
    private RespostaModelo rm;

    // Método para listar todos os produtos
    public Iterable<VideoModelo> listar() {
        return vr.findAll();
    }

    // Método para cadastrar ou alterar produto
    public ResponseEntity<?> cadastrarAlterar(VideoModelo pm, String acao) {

        if (pm.getTitle().equals("")) {
            rm.setMensagem("O nome do autor é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else if (pm.getUrl() == null) {
            rm.setMensagem("A dataPublicacao é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                return new ResponseEntity<VideoModelo>(vr.save(pm), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<VideoModelo>(vr.save(pm), HttpStatus.OK);
            }
        }
    }

    // Método para buscar um post por código
    public VideoModelo buscarPorCodigo(Long codigo) {
        return vr.findById(codigo).orElse(null);
    }

    // Método para remover trade
    public ResponseEntity<RespostaModelo> remover(Long codigo) {
        vr.deleteById(codigo);
        rm.setMensagem("O autor foi removido");
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }


    public List<VideoModelo> listarVideosPorAno(int ano) {
        return vr.findByAno(ano);
    }
}
package br.com.api.produtos.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.produtos.modelo.FinanceModelo;
import br.com.api.produtos.modelo.PostModelo;
import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.modelo.UsuarioModelo;
import br.com.api.produtos.modelo.VideoModelo;
import br.com.api.produtos.servico.FinanceServico;
import br.com.api.produtos.servico.PostServico;
import br.com.api.produtos.servico.ProdutoServico;
import br.com.api.produtos.servico.UsuarioServico;
import br.com.api.produtos.servico.VideoServico;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoControle {

    @Autowired
    private ProdutoServico ps;

    
    @Autowired
    private UsuarioServico us;

    @Autowired
    private FinanceServico fs;

    @Autowired
    private PostServico pts;

    @Autowired
    private VideoServico vs;


    @DeleteMapping("/remover/{codigo}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo) {
        return ps.remover(codigo);
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody ProdutoModelo pm) {
        return ps.cadastrarAlterar(pm, "alterar");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoModelo pm) {
        return ps.cadastrarAlterar(pm, "cadastrar");
    }

    public String postMethodName(@RequestBody String entity) {

        return entity;
    }

    @GetMapping("/listar")

    public Iterable<ProdutoModelo> listar() {

        return ps.listar();
    }

    // tabela finance
    @DeleteMapping("/remover/finance/{codigo}")
    public ResponseEntity<RespostaModelo> removerFinance(@PathVariable long codigo) {
        return fs.remover(codigo);
    }

    @PutMapping("/alterar/finance")
    public ResponseEntity<?> alterarFinance(@RequestBody FinanceModelo fm) {
        return fs.cadastrarAlterar(fm, "alterar");
    }

    @PostMapping("/cadastrar/finance")
    public ResponseEntity<?> cadastrarFinance(@RequestBody FinanceModelo fm) {
        return fs.cadastrarAlterar(fm, "cadastrar");
    }

    @GetMapping("/listar/finance")

    public Iterable<FinanceModelo> listarFinance() {

        return fs.listar();
    }

    // Remover post
    @DeleteMapping("/remover/post/{codigo}")
    public ResponseEntity<RespostaModelo> removerPost(@PathVariable Long codigo) {
        return pts.remover(codigo);
    }

    // Alterar post
    @PutMapping("/alterar/post")
    public ResponseEntity<?> alterarPost(@RequestBody PostModelo pm) {
        return pts.cadastrarAlterar(pm, "alterar");
    }

    // Cadastrar post
    @PostMapping("/cadastrar/post")
    public ResponseEntity<?> cadastrarPost(@RequestBody PostModelo pm) {
        return pts.cadastrarAlterar(pm, "cadastrar");
    }

    // Listar todos os posts
    @GetMapping("/listar/posts")
    public Iterable<PostModelo> listarPosts() {
        return pts.listar();
    }

    @GetMapping("/listar/post/{codigo}")
    public ResponseEntity<?> buscarPostPorCodigo(@PathVariable Long codigo) {
        PostModelo post = pts.buscarPorCodigo(codigo);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            RespostaModelo resposta = new RespostaModelo();
            resposta.setMensagem("Post não encontrado para o código: " + codigo);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

//Video
    // tabela finance
    @DeleteMapping("/remover/video/{codigo}")
    public ResponseEntity<RespostaModelo> removerVideo(@PathVariable long codigo) {
        return vs.remover(codigo);
    }

    @PutMapping("/alterar/video")
    public ResponseEntity<?> alterarVideo(@RequestBody VideoModelo vm) {
        return vs.cadastrarAlterar(vm, "alterar");
    }

    @PostMapping("/cadastrar/video")
    public ResponseEntity<?> cadastrarVideo(@RequestBody VideoModelo vm) {
        return vs.cadastrarAlterar(vm, "cadastrar");
    }

    @GetMapping("/listar/video")

    public Iterable<VideoModelo> listarVideo() {

        return vs.listar();
    }

    @GetMapping("/listar/video/ano/{ano}")
    public ResponseEntity<List<VideoModelo>> listarVideosPorAno(@PathVariable int ano) {
        List<VideoModelo> videos = vs.listarVideosPorAno(ano);
        if (!videos.isEmpty()) {
            return ResponseEntity.ok(videos);
        } else {
            return ResponseEntity.noContent().build(); // Retorna 204 se não houver vídeos para o ano especificado
        }
    }
     
    //para usuario

    // tabela finance
    @DeleteMapping("/remover/usuario/{codigo}")
    public ResponseEntity<RespostaModelo> removerUsuario(@PathVariable long codigo) {
        return us.remover(codigo);
    }

    @PutMapping("/alterar/usuario")
    public ResponseEntity<?> alterarUsuario(@RequestBody UsuarioModelo fm) {
        return us.cadastrarAlterar(fm, "alterar");
    }

    @PostMapping("/cadastrar/usuario")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioModelo fm) {
        return us.cadastrarAlterar(fm, "cadastrar");
    }


    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody UsuarioModelo usuario) {
    System.out.println("Tentativa de login com nome: " + usuario.getNome() + " e senha: " + usuario.getSenha());
    UsuarioModelo usuarioEncontrado = us.buscarPorNomeESenha(usuario.getNome(), usuario.getSenha());

    if (usuarioEncontrado != null) {
        return ResponseEntity.ok(usuarioEncontrado);
    } else {
        RespostaModelo resposta = new RespostaModelo();
        resposta.setMensagem("Nome de usuário ou senha inválidos.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resposta);
    }
}



    @GetMapping("/listar/usuario")

    public Iterable<UsuarioModelo> listarUsuario() {

        return us.listar();
    }

    


    @GetMapping("/")
    public String rota() {
        return "API de produtos funcionando!";
    }

}

package tech.ada.java.demo.api.usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.ada.java.demo.api.exception.NaoEncontradoException;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final UsuarioJpaRepository repository;

    public UsuarioRestController(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/dummy")
    public Usuario dummyUsuario(){
        return new Usuario(UUID.randomUUID(), "Jordan", "jordan@gmail.com", LocalDate.now());
    }

    //CRUD
    @GetMapping
    public List<Usuario> listarTodos(){
        return this.repository.findAll();
    }

    @GetMapping("/{uuid}")
    public Usuario buscarPorUuid(@PathVariable UUID uuid){
        return this.repository.findByUuid(uuid).orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));

    }

    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody @Valid Usuario usuario){
        return this.repository.save(usuario);
    }

    @PostMapping("create-dummy")
    public Usuario createDummy(){
        Usuario dummy = new Usuario(UUID.randomUUID(), "Dummy", "dummy@gmail.com", LocalDate.now());
        return this.criarUsuario(dummy);
    }

    @PutMapping ("/{uuid}")
    public Usuario atualizarUsuario(@PathVariable UUID uuid, @RequestBody @Valid Usuario usuarioNovo){
        Usuario usuario = this.buscarPorUuid(uuid);
        usuarioNovo.setId(usuario.getId());
        return this.repository.save(usuarioNovo);
    }

    @Transactional
    @PatchMapping("/{uuid}/alterar-nome")
    public Usuario alterarNome (@PathVariable UUID uuid, @RequestBody Usuario usuarioAlterado){
        this.repository.updateNome(uuid, usuarioAlterado.getNome());
        return this.buscarPorUuid(uuid);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void deletarUsuario(@PathVariable UUID uuid){
        this.repository.deleteByUuid(uuid);
    }
}

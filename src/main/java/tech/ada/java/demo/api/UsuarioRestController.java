package tech.ada.java.demo.api;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final List<Usuario> usuarioList = new ArrayList<>();

    @GetMapping("/dummy")
    public Usuario dummyUsuario(){
        return new Usuario(UUID.randomUUID(), "Jordan", "jordan@gmail.com", LocalDate.now());
    }

    //CRUD
    @GetMapping
    public List<Usuario> listarTodos(){
        return this.usuarioList;
    }

    @GetMapping("/{uuid}")
    public Usuario buscarPorUuid(@PathVariable UUID uuid){
        return usuarioList.stream()
                .filter(usuario -> usuario.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow();
    }

    @PostMapping("/")
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        this.usuarioList.add(usuario);
        return usuario;
    }

    @PostMapping("create-dummy")
    public Usuario createDummy(){
        Usuario dummy = new Usuario(UUID.randomUUID(), "Dummy", "dummy@gmail.com", LocalDate.now());
        return this.criarUsuario(dummy);
    }

    @PutMapping ("/{id}")
    public Usuario atualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuarioNovo){
        Usuario usuario = this.buscarPorUuid(id);
        this.usuarioList.set(this.usuarioList.indexOf(usuario), usuarioNovo);
        return usuarioNovo;
    }

    @PatchMapping("/{uuid}/alterar-nome")
    public Usuario alterarNome (@PathVariable UUID uuid, @RequestBody Usuario usuarioAlterado){
        Usuario usuario = this.buscarPorUuid(uuid);
        usuario.setNome(usuarioAlterado.getNome());
        this.usuarioList.set(this.usuarioList.indexOf(usuario), usuarioAlterado);
        return usuarioAlterado;
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable UUID id){
        this.usuarioList.removeIf(usuario -> usuario.getUuid().equals(id));
    }

}

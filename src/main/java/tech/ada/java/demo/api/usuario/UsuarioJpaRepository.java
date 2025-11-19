package tech.ada.java.demo.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    @Modifying
    @Query("update Usuario u set u.nome = :nome where u.uuid = :uuid")//jpql
    void updateNome(@Param("uuid")UUID uuid, @Param("nome")String nome);

}

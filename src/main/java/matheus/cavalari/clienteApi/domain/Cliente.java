package matheus.cavalari.clienteApi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "matricula", unique = true)
    private String matricula;

    @Column(name = "comunidade")
    private String comunidade;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;
}

package br.com.improving.exemplo.bean.validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

// http://conteudo.improving.com.br/tdc
public class Candidato {

    @NotBlank
    private final String nomeCompleto;

    @Email
    private final String email;

    @Future
    private final LocalDateTime dataHoraProva;

    @NotEmpty
    private final Set<@NotNull @Size(max=30) String> skills;

    public Candidato(String nomeCompleto,
                     String email,
                     LocalDateTime dataHoraProva,
                     String... skills) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.dataHoraProva = dataHoraProva;
        this.skills = new LinkedHashSet<>(Arrays.asList(skills));
    }
}

package br.com.improving.exemplo.bean.validation;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

// http://conteudo.improving.com.br/tdc
public class Candidato {

    private final String nomeCompleto;


    private final String email;


    private final LocalDateTime dataHoraProva;


    private final Set<String> skills;

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

package br.com.improving.exemplo.bean.validation;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// http://conteudo.improving.com.br/tdc
public class CandidatoTest {
    private static Validator VALIDATOR;
    private static LocalDateTime DATA_HORA_PROVA = LocalDateTime.of(2017, 8, 31, 11, 25);

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void valido() {

        Candidato candidato = new Candidato(
                "Rodrigo Santos da Silva",
                "rodrigo@improving.com.br",
                DATA_HORA_PROVA,
                "Java", "Scala");

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertTrue(constraintViolations.isEmpty());
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void nomeNulo() {
        Candidato candidato = new Candidato(
                null,
                "rodrigo@improving.com.br",
                DATA_HORA_PROVA,
                "Java", "Scala");

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Candidato> constraintViolation = constraintViolations.iterator().next();
        constraintViolation.getPropertyPath().forEach(n -> assertEquals("nomeCompleto", n.getName()));
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void nomeVazio() {
        Candidato candidato = new Candidato(
                "",
                "rodrigo@improving.com.br",
                DATA_HORA_PROVA,
                "Java", "Scala");

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Candidato> constraintViolation = constraintViolations.iterator().next();
        constraintViolation.getPropertyPath().forEach(n -> assertEquals("nomeCompleto", n.getName()));
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void nomeComEspaco() {
        Candidato candidato = new Candidato(
                "                 ",
                "rodrigo@improving.com.br",
                DATA_HORA_PROVA,
                "Java", "Scala");

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Candidato> constraintViolation = constraintViolations.iterator().next();
        constraintViolation.getPropertyPath().forEach(n -> assertEquals("nomeCompleto", n.getName()));
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void emailInvalido() {
        Candidato candidato = new Candidato(
                "Rodrigo Santos da Silva",
                "rodrigo @improving.com.br",
                DATA_HORA_PROVA,
                "Java", "Scala");

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Candidato> constraintViolation = constraintViolations.iterator().next();
        constraintViolation.getPropertyPath().forEach(n -> assertEquals("email", n.getName()));
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void dataHoraProvaInvalido() {
        Candidato candidato = new Candidato(
                "Rodrigo Santos da Silva",
                "rodrigo@improving.com.br",
                LocalDateTime.now().minusDays(1),
                "Java", "Scala");

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Candidato> constraintViolation = constraintViolations.iterator().next();
        constraintViolation.getPropertyPath().forEach(n -> assertEquals("dataHoraProva", n.getName()));
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void dataHoraProvaAgora() {
        Candidato candidato = new Candidato(
                "Rodrigo Santos da Silva",
                "rodrigo@improving.com.br",
                LocalDateTime.now(),
                "Java", "Scala");

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Candidato> constraintViolation = constraintViolations.iterator().next();
        constraintViolation.getPropertyPath().forEach(n -> assertEquals("dataHoraProva", n.getName()));
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void skillsVazio() {
        Candidato candidato = new Candidato(
                "Rodrigo Santos da Silva",
                "rodrigo@improving.com.br",
                DATA_HORA_PROVA);

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Candidato> constraintViolation = constraintViolations.iterator().next();
        constraintViolation.getPropertyPath().forEach(n -> assertEquals("skills", n.getName()));
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void skillsNull() {
        Candidato candidato = new Candidato(
                "Rodrigo Santos da Silva",
                "rodrigo@improving.com.br",
                DATA_HORA_PROVA,
                (String)null);

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Candidato> constraintViolation = constraintViolations.iterator().next();
        Iterator<Path.Node> nodes = constraintViolation.getPropertyPath().iterator();
        assertEquals("skills", nodes.next().getName());
        assertTrue(nodes.next().isInIterable());
    }

    // http://conteudo.improving.com.br/tdc
    @Test
    public void skillsMuitoLongo() {
        Candidato candidato = new Candidato(
                "Rodrigo Santos da Silva",
                "rodrigo@improving.com.br",
                DATA_HORA_PROVA,
                "supercalifragilisticexpialidocious");

        Set<ConstraintViolation<Candidato>> constraintViolations =
                VALIDATOR.validate( candidato );

        assertEquals(1, constraintViolations.size());

        ConstraintViolation<Candidato> constraintViolation = constraintViolations.iterator().next();
        Iterator<Path.Node> nodes = constraintViolation.getPropertyPath().iterator();
        assertEquals("skills", nodes.next().getName());
        assertTrue(nodes.next().isInIterable());
    }


}

package main.services;

import main.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceTest {

    private PetService petService;
    private LerFormularioService lerFormularioServiceMock;

    @BeforeEach
    void setUp() throws Exception {
        petService = new PetService();

        // Criar mock manual sem Mockito
        lerFormularioServiceMock = new LerFormularioService() {
            private List<String> respostas;

            public void setRespostas(List<String> respostas) {
                this.respostas = respostas;
            }

            @Override
            public void lerFormulario() {
                // Mock implementation
            }

            @Override
            public List<String> getRespostas() {
                return this.respostas;
            }
        };

        // Injeta o mock usando reflection
        Field readField = PetService.class.getDeclaredField("read");
        readField.setAccessible(true);
        readField.set(petService, lerFormularioServiceMock);
    }

    private void configurarRespostas(List<String> respostas) {
        lerFormularioServiceMock.setRespostas(respostas);
    }

    @Test
    void testSalvarPetComDadosValidos() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3.5",
                "15.2",
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertDoesNotThrow(() -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComNomeInvalidoSemSobrenome() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex", // Nome sem sobrenome
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3.5",
                "15.2",
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertThrows(InvalidNameException.class, () -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComNomeInvalidoComCaracteresEspeciais() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex@ Silva", // Nome com caracteres especiais
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3.5",
                "15.2",
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertThrows(InvalidNameException.class, () -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComEnderecoInvalido() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Endereço@ Inválido#", // Endereço inválido
                "3.5",
                "15.2",
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertThrows(InvalidAddressException.class, () -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComPesoMaiorQue60() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3.5",
                "65.0", // Peso maior que 60kg
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertThrows(InvalidWeightException.class, () -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComPesoMenorQue05() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3.5",
                "0.3", // Peso menor que 0.5kg
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertThrows(InvalidWeightException.class, () -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComIdadeMaiorQue20() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "25.0", // Idade maior que 20 anos
                "15.2",
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertThrows(InvalidAgeException.class, () -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComIdadeMenorQue01() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "0.05", // Idade menor que 0.1 anos
                "15.2",
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertThrows(InvalidAgeException.class, () -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComRacaInvalidaComNumeros() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3.5",
                "15.2",
                "Labrador123" // Raça com números
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertThrows(InvalidRaceException.class, () -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComRacaInvalidaComCaracteresEspeciais() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3.5",
                "15.2",
                "Labrador@" // Raça com caracteres especiais
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertThrows(InvalidRaceException.class, () -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComCamposVazios() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "", // Nome vazio
                "cachorro",
                "M",
                "", // Endereço vazio
                "", // Idade vazia
                "", // Peso vazio
                ""  // Raça vazia
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertDoesNotThrow(() -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComVirgulaSubstituidaPorPonto() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3,5", // Idade com vírgula
                "15,2", // Peso com vírgula
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertDoesNotThrow(() -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComTipoGato() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Mimi Silva",
                "gato", // Tipo gato
                "F",
                "Rua dos Gatos, 456, Rio de Janeiro",
                "2.0",
                "4.5",
                "Siamês"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertDoesNotThrow(() -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComVariacoesTipoCachorro() {
        // Testa diferentes formas de escrever cachorro
        String[] variacoes = {"cao", "cão", "cachorro"};

        for (String tipo : variacoes) {
            // Arrange
            List<String> respostas = Arrays.asList(
                    "Rex Silva",
                    tipo,
                    "M",
                    "Rua das Flores, 123, São Paulo",
                    "3.5",
                    "15.2",
                    "Labrador"
            );
            configurarRespostas(respostas);

            // Act & Assert
            assertDoesNotThrow(() -> petService.salvarPet());
        }
    }

    @Test
    void testSalvarPetComGenerosDiferentes() {
        // Testa ambos os gêneros
        String[] generos = {"M", "F"};

        for (String genero : generos) {
            // Arrange
            List<String> respostas = Arrays.asList(
                    "Rex Silva",
                    "cachorro",
                    genero,
                    "Rua das Flores, 123, São Paulo",
                    "3.5",
                    "15.2",
                    "Labrador"
            );
            configurarRespostas(respostas);

            // Act & Assert
            assertDoesNotThrow(() -> petService.salvarPet());
        }
    }

    @Test
    void testSalvarPetComEnderecoValidoDiferentesFormatos() {
        // Testa diferentes formatos válidos de endereço
        String[] enderecos = {
                "Rua A, 123, São Paulo",
                "Av. Paulista, 1000, São Paulo",
                "Travessa das Flores, 45-A, Rio de Janeiro"
        };

        for (String endereco : enderecos) {
            // Arrange
            List<String> respostas = Arrays.asList(
                    "Rex Silva",
                    "cachorro",
                    "M",
                    endereco,
                    "3.5",
                    "15.2",
                    "Labrador"
            );
            configurarRespostas(respostas);

            // Act & Assert
            assertDoesNotThrow(() -> petService.salvarPet());
        }
    }

    @Test
    void testSalvarPetComRacaValidaComAcentos() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "Rex Silva",
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3.5",
                "15.2",
                "Pastor Alemão" // Raça com acento
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertDoesNotThrow(() -> petService.salvarPet());
    }

    @Test
    void testSalvarPetComNomeComAcentos() {
        // Arrange
        List<String> respostas = Arrays.asList(
                "João Silva", // Nome com acento
                "cachorro",
                "M",
                "Rua das Flores, 123, São Paulo",
                "3.5",
                "15.2",
                "Labrador"
        );
        configurarRespostas(respostas);

        // Act & Assert
        assertDoesNotThrow(() -> petService.salvarPet());
    }
}
# 🐾 Sistema de Cadastro de Pets (CLI)

Projeto desenvolvido em Java com foco em **boas práticas, POO e SOLID**, simulando um sistema real de cadastro e gerenciamento de pets via terminal.

---

## 🚀 Destaque do Projeto

Este projeto foi construído com foco em **qualidade de código e arquitetura**, indo além de um CRUD simples.

### 🔥 Principais diferenciais:

- Aplicação real dos **princípios SOLID**
- Arquitetura em camadas (**Service, Repository, Model**)
- **Formulário dinâmico** (extensível sem alterar código)
- Manipulação moderna de arquivos com `Files (NIO)`
- Código desacoplado e testável
- Evolução clara de código procedural → orientado a objetos

---

## 🧠 Conceitos aplicados

### ✔️ SOLID na prática

- **S (Single Responsibility)**  
  Cada classe tem uma única responsabilidade bem definida

- **O (Open/Closed)**  
  Suporte a novos campos sem alterar código existente

- **L (Liskov Substitution)**  
  `PetFileRepository` pode ser substituído por outro tipo de persistência

- **I (Interface Segregation)**  
  Interfaces específicas e enxutas

- **D (Dependency Inversion)**  
  Uso de abstrações (`PetRepository`) em vez de implementações diretas

---

## ⚙️ Tecnologias

- Java
- Java NIO (Files, Path)
- Programação Orientada a Objetos
- Manipulação de arquivos

---

## 📌 Funcionalidades

- Cadastro de pets via formulário externo
- Listagem de pets
- Busca com múltiplos critérios
- Atualização de dados
- Remoção de registros
- Sistema de perguntas dinâmico

---

## 🧩 Arquitetura

```
Service → Regras de negócio
Repository → Persistência (arquivos)
Model → Entidades do sistema
Utils → Regras auxiliares
```

---

## 📄 Exemplo de dado salvo

```
ELUNA SILVA
GATO
F
NÃO INFORMADO
NÃO INFORMADO
NÃO INFORMADO
NÃO INFORMADO
```

---

## ▶️ Como executar

```bash
git clone https://github.com/estevao081/desafioCadastro.git
cd desafioCadastro
javac Main.java
java Main
```

---

## 📈 Evolução técnica demonstrada

- Refatoração para baixo acoplamento
- Separação clara de responsabilidades
- Uso de composição em vez de dependências diretas
- Preparação para escalar (ex: banco de dados)

---

## 👨‍💻 Autor

Thiago Estevão

---

## 📌 Observação

Este projeto foi desenvolvido com foco em aprendizado estruturado, simulando práticas utilizadas em ambientes profissionais.

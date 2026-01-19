
# Loja Solidária - PPI

### Sistema de Controle de Estoque e Relatórios Mensais

## 📚 Prática Profissional Integrada (PPI)

* **Curso:** Análise e Desenvolvimento de Sistemas (ADS)
* **Instituição:** Instituto Federal Farroupilha (IFFar)
* **Atividade:** Prática Profissional Integrada (PPI)

## 🏪 Cliente

**Loja Solidária Irmã Dulce**

A Loja Solidária Irmã Dulce atua na doação de roupas, calçados e itens diversos para famílias em situação de vulnerabilidade social, promovendo inclusão social e apoio comunitário.

---

## 📝 Descrição do Sistema

Este sistema é um **protótipo funcional**, desenvolvido em **Java**, voltado ao controle de estoque e geração de relatórios mensais da Loja Solidária.

O sistema utiliza **arquivos CSV** para armazenamento dos dados e foi projetado para facilitar o registro de entradas e saídas de itens, bem como o acompanhamento do estoque ao longo do tempo.

---

## 🎯 Objetivos

* Controlar o estoque de itens doados
* Registrar movimentações de **entrada** e **saída**
* Gerar **relatórios mensais** organizados
* Facilitar o acompanhamento das atividades da Loja Solidária
* Aplicar na prática os conhecimentos do curso de ADS

---

## ⚙️ Funcionalidades Implementadas

### 📋 Menu Interativo (Console)

O sistema apresenta um menu em modo texto que permite ao usuário:

* Registrar **entrada de itens**
* Registrar **saída de itens**
* Visualizar o **estoque atual**
* Gerar **relatórios mensais**
* Registrar **observações**
* Encerrar o sistema

---

### 📦 Controle de Estoque

* Armazenamento dos dados em arquivo `estoque.csv`
* Controle de quantidade por categoria
* Categorias pré-definidas no sistema:

  * Masculinos
  * Femininos
  * Infantil
  * Calçados
  * Diversos

---

### 🔄 Registro de Movimentações

* Registro de **entradas e saídas** com:

  * Categoria
  * Quantidade
  * Data da movimentação
  * Atualização automática do estoque após cada operação

---

### 📊 Relatórios Mensais

O sistema gera relatórios mensais contendo:

* Total de **entradas** no período
* Total de **saídas** no período
* Situação atual do **estoque**
* **Observações** registradas pelo usuário
* Organização por mês e ano

---

### 📝 Observações

* Campo livre para anotações importantes
* Utilizado para registrar informações relevantes sobre o período analisado

---

## 🧪 Status do Projeto

🚧 **Protótipo funcional**

O sistema atende às funcionalidades básicas propostas na PPI, porém ainda pode ser expandido com melhorias futuras.

---

## 🛠️ Tecnologias Utilizadas

* **Java**
* **Programação Orientada a Objetos**
* **Arquivos CSV para persistência de dados**
* **Entrada de dados via Scanner**
* **Git e GitHub para versionamento**
* **Discord para reuniões**

---

## 📁 Estrutura do Projeto

```
├── Main.java        // Classe principal do sistema
├── Menu.java        // Interface de menu em modo texto
├── Estoque.java     // Regras de negócio e manipulação do estoque
├── estoque.csv      // Arquivo de dados do estoque
```

---

## 🚀 Como Executar

1. Certifique-se de ter o **Java JDK** instalado
2. Compile os arquivos:

   ```bash
   javac Main.java
   ```
3. Execute o sistema:

   ```bash
   java Main
   ```
4. Utilize o menu exibido no console para interagir com o sistema

---

## 👥 Equipe

Projeto desenvolvido por estudantes do **Curso de Análise e Desenvolvimento de Sistemas – IFFar Uruguaiana**, como parte da Prática Profissional Integrada (PPI).

---

## 🤝 Considerações Finais

Este projeto alia tecnologia e responsabilidade social, oferecendo uma solução simples e funcional para apoiar a organização da Loja Solidária Irmã Dulce, ao mesmo tempo em que promove a aprendizagem prática dos alunos envolvidos.

---

## 📌 Repositório

🔗 [https://github.com/mateus02916-cloud/LojaSolidariaPPI](https://github.com/mateus02916-cloud/LojaSolidariaPPI)


# ☕ Bean & Code Café — Sistema de Controle de Pedidos

Sistema desenvolvido em Java para gerenciamento de pedidos de uma cafeteria, aplicando os conceitos de Programação Orientada a Objetos: composição de classes, encapsulamento e arrays de objetos. A interface com o usuário é feita via caixas de diálogo com `JOptionPane`.

> Projeto acadêmico desenvolvido para a disciplina de **Object-Oriented Programming and Java Web Development** — FIAP  
> **Professor:** Dr. Antonio Marcos Selmini

---

## 📋 Funcionalidades

| Opção | Descrição |
|-------|-----------|
| 1 | Registrar novo pedido (valida duplicidade pelo número do pedido) |
| 2 | Adicionar item a um pedido em aberto (nome, valor e quantidade) |
| 3 | Finalizar pedido e exibir resumo com total |
| 4 | Listar todos os pedidos com status `ABERTO` |
| 5 | Exibir faturamento do dia (soma dos pedidos `FINALIZADOS`) |
| 6 | Encerrar o sistema |

---

## 🏗️ Estrutura do Projeto

```
Bean_Code_Cafe/
└── src/
    └── br/
        └── com/
            └── beanCafe/
                ├── cliente/
                │   └── Cliente.java
                ├── itemPedido/
                │   └── ItemPedido.java
                ├── pedido/
                │   └── Pedido.java
                ├── util/
                │   └── Util.java
                └── Main.java
```

---

## 🧩 Classes do Sistema

### `Cliente`
Representa o cliente vinculado ao pedido.

| Atributo | Tipo   |
|----------|--------|
| nome     | String |
| cpf      | String |

```java
public String getDados() {
    return nome + " - " + cpf;
}
```

---

### `ItemPedido`
Representa um produto adicionado ao pedido.

| Atributo      | Tipo   |
|---------------|--------|
| nomeProduto   | String |
| precoUnitario | double |
| quantidade    | int    |

```java
public double calcularTotal() {
    return this.precoUnitario * this.quantidade;
}
```

---

### `Pedido`
Representa um pedido de um cliente, com composição de `Cliente` e array de `ItemPedido`.

| Atributo     | Tipo          | Detalhe                        |
|--------------|---------------|--------------------------------|
| numeroPedido | int           | Identificador único            |
| cliente      | Cliente       | Composição                     |
| itens        | ItemPedido[]  | Capacidade máxima de 20 itens  |
| index        | int           | Contador de itens inseridos    |
| status       | String        | `"ABERTO"` ou `"FINALIZADO"`   |

Métodos relevantes:
- `adicionarItem(ItemPedido item)` — insere item no array, bloqueando se estiver cheio (`isFull()`)
- `calcularTotal()` — soma os subtotais de todos os itens
- `getDados()` — retorna número, cliente, lista de itens e subtotal formatados
- `isFull()` — verifica se o array de itens atingiu o limite de 20

---

### `Util`
Classe responsável pelo menu e por todas as operações do sistema.

| Atributo | Tipo     | Detalhe                               |
|----------|----------|---------------------------------------|
| pedidos  | Pedido[] | Array com capacidade para 100 pedidos |
| index    | int      | Contador de pedidos registrados       |

Operações internas:
- `buscarPedido(int id)` — percorre o array e retorna o `Pedido` correspondente ou `null`
- `registrar()` — valida duplicidade antes de criar e armazenar o pedido
- `adicionar()` — valida se o pedido existe e está em aberto antes de inserir item
- `finalizar()` — altera o status para `FINALIZADO` e exibe o resumo
- `listar()` — exibe pedidos com status `ABERTO`
- `faturamentoDia()` — soma totais dos pedidos `FINALIZADOS` com formatação `DecimalFormat`

---

## 🖥️ Interface

O sistema utiliza **`JOptionPane`** para toda a interação com o usuário — entradas via `showInputDialog` e saídas via `showMessageDialog` — sem uso de terminal.

---

## 🚀 Como Executar

**Pré-requisitos:**
- Java JDK 17 ou superior
- IntelliJ IDEA (recomendado)

**Passos:**

```bash
# Clone o repositório
git clone https://github.com/ArthurZapater/Bean_Code_Cafe.git
```

Abra o projeto no IntelliJ IDEA e execute a classe `Main.java` em `src/br/com/beanCafe/`.

---

## 💡 Conceitos de POO Aplicados

- **Composição** — `Pedido` contém um objeto `Cliente` e um array de `ItemPedido[]`
- **Encapsulamento** — todos os atributos são `private`, acessados via getters e setters
- **Arrays de objetos** — `Pedido[100]` na `Util` e `ItemPedido[20]` dentro de cada `Pedido`
- **Construtores parametrizados** — todos os objetos são inicializados com dados obrigatórios

---

## 🛠️ Tecnologias

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-007396?style=for-the-badge&logo=java&logoColor=white)

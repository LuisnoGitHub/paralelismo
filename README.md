https://github.com/LuisnoGitHub/paralelismo

Busca Paralela em Arquivos
Universidade do Extremo Sul Catarinense (UNESC)
Ciência da Computação
Computação Paralela
João Pedro Ronzani Dos Santos e Luís Eduardo Barrim Hadlich

Este projeto foi desenvolvido como parte de um trabalho para a disciplina de Computação Paralela. O objetivo é implementar diferentes métodos de busca em arquivos utilizando técnicas de paralelismo, além de um método sem paralelismo para comparação de desempenho. As buscas são realizadas em arquivos de texto localizados no diretório de recursos do projeto, e os resultados são exibidos diretamente no terminal.
Permite realizar busca em arquivos de texto, podendo escolher entre uma abordagem sequencial (sem paralelismo) ou com diferentes abordagens de paralelismo.

O projeto implementa três abordagens diferentes:
Fork/Join Framework - Uma técnica de paralelismo que divide a tarefa de busca em várias subtarefas menores e as executa em paralelo.
Multithreaded - Implementação com uso explícito de várias threads para realizar buscas concorrentes em diferentes arquivos.
Busca Sequencial (sem paralelismo) - Método de busca em um único thread para fins de comparação.

Como Utilizar
1. Configuração de Busca:
Para definir a palavra que será buscada, você deve modificar o valor da variável SEARCH_TERM na classe Configs.java.
Além disso, a variável MULTITASK também pode ser configurada. Quando MULTITASK for true, várias palavras serão buscadas, caso contrário, apenas a palavra definida em SEARCH_TERM será usada.

2. Escolher o Método de Busca:
No arquivo Main.java, você deve descomentar a linha correspondente ao método de busca desejado e comentar os outros.

3. Executar a Busca:
Para realizar a busca, rode o Main.java após configurar o termo de busca e escolher o método desejado.
O resultado da busca será exibido diretamente no terminal. Ele indicará em qual arquivo e em qual linha o termo foi encontrado.

4. Arquivos de Busca:
Os arquivos de texto usados na busca estão localizados no diretório resources/dataset_p/ ou resources/dataset_g/.

Requisitos
JDK 17+: O projeto foi desenvolvido utilizando o Java 17, portanto, é necessário ter o JDK 17 ou superior instalado.
Maven: O projeto utiliza Maven como ferramenta de gerenciamento de dependências e build. Certifique-se de ter o Maven instalado para compilar e rodar o projeto.

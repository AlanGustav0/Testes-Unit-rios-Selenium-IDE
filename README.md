# Critérios de Teste

#### Mantém Livros - REQ01. O sistema deve permitir o cadastro, consulta, edição para alteração e exclusão de livros. O sistema deve rejeitar entradas inválidas – o ISBN deve ter 4 caracteres numéricos, não deve permitir o cadastro de um ISBN já cadastrado, entradas válidas para autor e título devem ter entre 1 e 50 caracteres alfanuméricos. 

No primeiro caso de teste foi utilizada a técnica de caixa preta Classe de Equivalência (CE), levando em consideração o caminho feliz, ou seja, apenas as classes válidas.
![alt text](https://github.com/AlanGustav0/Testes-Unitarios-Selenium-IDE/blob/master/REQ01-CT01.JPG)

No segundo caso de teste foi utilizada a técnica de caixa preta Análise do Valor Limite (AVL) levando em consideração o intervalo de 1 a 50 caracteres, conforme o levantamento de requisitos.
![alt text](https://github.com/AlanGustav0/Testes-Unitarios-Selenium-IDE/blob/master/REQ01-CT02.JPG)

No terceiro caso de testes foi utilizada novamente a técnica de Classe de equivalência (CE), como se tratava da exclusão de um livro, a classe válida utilizada foi a de um livro já cadastrado para exclusão e a classe inválida um não não cadastrado ou nenhum livro cadastrado (Lista vazia).
![alt text](https://github.com/AlanGustav0/Testes-Unitarios-Selenium-IDE/blob/master/REQ01-CT03.JPG)

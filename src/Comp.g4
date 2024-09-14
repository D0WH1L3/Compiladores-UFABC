grammar Comp;

// Regra principal que define o programa
program : 'Start' body 'End';

// Corpo do programa pode ter múltiplas definições de variáveis, expressões, estruturas de controle
body : (definevar | assign | expr | if_statement | while_statement | for_statement | repeat_statement | dowhile_statement | print_statement | read_statement)* ;

// Definição de variáveis com tipos
definevar : 'define' type ID (',' ID)* PV;

// Definição dos tipos possíveis (numeros, booleano, texto)
type : 'numeros' | 'real' | 'booleano' | 'texto';

// Atribuição de valor a uma variável
assign : ID ATRIB expr PV;

// Expressão que pode ser um termo seguido de operadores e termos
expr : termo (OP termo)*;

// Estrutura condicional if-else
if_statement : 'if' '(' condition ')' bloco ('else' bloco)?;

// Estrutura de repetição while
while_statement : 'while' '(' condition ')' bloco;

// Estrutura de repetição for
for_statement : 'for' '(' assign ';' condition ';' assign ')' bloco;

// Estrutura de repetição repeat
repeat_statement : 'repeat' bloco 'until' '(' condition ')' PV;

// Estrutura de repetição do-while
dowhile_statement : 'do' bloco 'while' '(' condition ')' PV;

// Comando de leitura
read_statement : 'read' '(' ID ')' PV;

// Comando de impressão (sem quebra de linha)
print_statement : 'print' '(' (expr | TEXTO) ')' PV;

// Comando de impressão (com quebra de linha)
println_statement : 'println' '(' (expr | TEXTO) ')' PV;

// Condição, composta por operadores relacionais e termos
condition : expr (REL_OP expr)?;

// Bloco de código, que pode conter múltiplas expressões ou declarações
bloco : '{' body '}';

// Regra para termos, que podem ser identificadores, números, texto, booleanos ou expressões entre parênteses
termo : ID
      | NUM
      | TEXTO
      | BOOL
      | '(' expr ')';

// Operadores aritméticos
OP : '+' | '-' | '*' | '/';

// Operadores relacionais
REL_OP : '>' | '<' | '>=' | '<=' | '==' | '!=';

// Operador de atribuição (:=)
ATRIB : ':=';

// Booleanos (true e false)
BOOL : 'true' | 'false';

// Pontuação
VIRG : ',';
PV : ';';
DP : ':';

// Identificadores (letras seguidas por letras e números)
ID : [a-z] [a-zA-Z0-9]*;

// Números (inteiros e decimais)
NUM : [0-9]+ ('.' [0-9]+)?;

// Definição de texto (entre aspas duplas)
TEXTO : '"' (~["\r\n])* '"';

// Ignorar espaços em branco e novas linhas
WS : [ \t\n\r]+ -> skip;

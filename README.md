## Sobre
Projeto, feito por estudantes da Universidade Federal do ABC, para a disciplina de Compiladores (2024.2).

## Solução
### Ferramentas utilizadas
Compilador feito em Java, utilizando as bibliotecas org.antlr.v4.*.

### Como executar

#### Requisitos
É necessário ter um JDK configurado em sua máquina. Recomendamos JDK 17+.
Faça o download no site de sua preferência e acrescente o caminho do JDK na variável PATH do Windows.

#### Passos
1. Clone o projeto, usando "git clone" no Git Bash **OU** faça download do .zip (pelo Git Web) e extraia a pasta;

2. Com o projeto aberto em um Editor de Texto ou IDE de sua preferência, abra o terminal e execute os comandos abaixo:

```
cd src
java -jar antlr-4.13.2-complete.jar -Dlanguage=Java Comp.g4
```

Assim que o comando rodar, as classes CompLexer.java, CompParser e CompBaseListener.java serão geradas, assim como os arquivos Comp.interp, ComplLexer.interp, CompLexer.tokens e Comp.tokens.

3. Na sequência, execute o comando:
```
javac -cp ".;antlr-4.13.2-complete.jar" MainClass.java
```

4. Se ambos comandos executarem sem erros, você poderá executar o programa compilado, usando:
```
java -cp ".;antlr-4.13.2-complete.jar" MainClass
```


### Vídeo de apresentação
[Clique aqui para acessar o vídeo](https://youtu.be/Ttmtsddd7IY)

## Integrantes
- Ana Martins (11201722009)
- Vitor Malavasi (11202020929)
- Yasmin Santos (11202321255)




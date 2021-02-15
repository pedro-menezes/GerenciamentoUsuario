# Gerenciamento de Usuário - Desafio prático

- O Desenvolvimento do sistema foi feito todo em Java e foi utilizado a IDE NetBeans.
- Foi utilizado JDK 13.
- A interface gráfica do sistema foi feita utilizando Swing e foi toda criada através NetBeans GUI Builder. 
- Para a utilização do banco de dados foi utilizado o MySQL.
- Para a conexão entre o sistema e o banco de dados foi utilizada a biblioteca 'mysql-connector-java-8.0.22.jar' 

## Para executar o sistema:
- Antes de executar o sistema é necessário criar o banco de dados na máquina, o código que deve ser utilizado está na pasta principal do projeto: 'banco.sql'.
- Após isso é necessário alterar o usuário e a senha sql no arquivo 'ConnectionFactory.java' localizado em '/src/gerenciamentousuario/connection/ConnectionFactory.java', onde está escrito "root","root".
- É necessário indicar o caminho do biblioteca 'mysql-connector-java-8.0.22.jar', dependendo da ide que estiver sendo utilizada.
- Sendo feitos esses passos já é possível executar o sistema. Recomendo o uso do NetBeans devido a interface ser criada utillizando a IDE.

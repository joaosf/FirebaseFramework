## Firebase Framework

### Projeto

Este projeto tem como principal objetivo facilitar a comunicação em Java com o Database Realtime do Firebase, 
utilizando procedimentos simples e com poucas mudanças nos seus códigos habituais.

### Configuração

1. Adicione o Firebase ao seu projeto ( https://firebase.google.com/docs/android/setup?hl=pt-br )
2. Importe o arquvio .jar do Framework (https://goo.gl/c2xKYj)
3. Em seu objeto relacional adicione dois tipos de anotações:
    - @FrameworkAnnotation(tableName = "NomeDaTabela",keepSync = true/false) acima da declaração da classe
    - @Fields(name = "NomeDoCampo", key = true) acima da declaração da variável (lembrando que, ela precisa ser pública)
    - Lembrando que é necessário fazer o import ( import FirebaseFramework.Anotacoes.*; )
    - Para criar um Field autoincrement, coloque com o tipo inteiro e não atribua valor ao mesmo, ele irá gerar um autoincrement automático.
4. Configuração conluída

### Utilização

1. Adicionar o import da classe de operações do Framework ( import FirebaseFramework.Controladores.Operacoes; )
2. Manipular o objeto como preferir
3. Ao concluir a manipulação do objeto e desejar realizar alguma operação utilize Operacoes.TipoDeOperacao(Parametros); * Operações listadas abaixo! *

### Operações

1. Select(String tableName, Filtro filtro)
    - Requer: import FirebaseFramework.Modelos.Filtro;
2. Update(Object object)
3. InsertOrUpdate(Object object)
4. InsertOrUpdate(String child, Object object) 
    - Maneira simplificada, onde ele grava exatamente o objeto passado por parâmetro no child informado no parâmetro;
5. Delete(Object object)

### Sugestões de chamadas das operações

1. Select("NomeDeExemplo", new Filtro("NomeFiltro","ValorFiltro"))
2. Update(SeuObjetoRelacional)
3. InsertOrUpdate(SeuObjetoRelacional)
4. Delete(SeuObjetoRelacional)
# TEAM X-AE-A-12
Repositório Inicial do Tabalho 1 GCS 2020/1

##Componentes do time:
- Aloísio Bastian
- David Bertrand
- Gabriel Miola
- Guilherme Canto
- Hiago Luzardo
- Jorge Rosa
- Nicholas Silva
- Vanderson Nery

##Fluxo de trabalho
O fluxo de trabalho conterá 3 branches principais (master, hotfix, developer) e sub-branches para o desenvolvimento das features.

###Branches
- master: Responsável pela entrega final de versões;
- hotfix: Responsável pela correção emergencial de falhas;
- developer: Responsável pela unificação das features desenvolvidas;
- nicks-team: Responsável pela edição de documentações, não utilizada para desenvolvimento;

####Sub-branches
Cada sub-branch deve começar a partir da branch ***developer*** atualizada (última versão ao iniciar a sua sub-branch), seu nome deve **obrigatoriamente** ser **feat#nicknamefeature**, sendo o *nicknamefeature* compostor por "id-feature" conforme a lista de feature do backlog do projeto.
*Ex.: feat#001-addClassProduto*

####Mergers
Ao finalizar cada feature, deve ser solicitado um ***New pull request*** para a branch *developer*, que devera ser aceita por um integrante do time responsável.
A nomenclatura do merge será o nome da branch acrescida ao seu inicio a palavra "done-".
*Ex.: done-feat#001-addClassProduto*

###Comunicação
Toda a comunicação do time será feita por estes canais
- discord: https://discord.gg/zFjDB7V
- backlog: ...

##Projeto
O projeto consiste em desenvolver um sistema de registro de recebimentos e pagamentos de uma empresa. Funcionando como um *livro caixa*, local de armazenamento de registros contábeis das empresas ao longo do tempo.

###Arquitetura
O sistema é desenvolvido em linguagem Java e interface Desktop, utilizando Swing e XML.

####Ferramentas
As ferramentas e bibliotecas utilizadas são:
- IDE eclipse: ambiente de desenvolvimento versão 2020-03 disponível em https://www.eclipse.org/downloads/
- Swing: biblioteca nativa do JRE;
- XStream: biblioteca de manipulação e persistência em XML versão 1.4.12 disponível em https://x-stream.github.io/download.html

###Escopo
As definições do projeto são:

####Regras de negocio
O sistema deve funcionar como uma espécie de livro caixa, armazenando todas as contas da empresa;

####Requisitos
Os requisitos do sistema estão definidos a seguir.

#####Funcionais
- Não é necessário a implementação de persistência de dados, podendo mantar os dados na memória; *(deverá ter dados já preenchidos)*
- Não é necessário a implementação de tela de login, porém deverá ser possível identificar/alterar o usuário/operador que está usando o sistema no momento;
- Cada usuário/operador deverá ter o nome e as suas iniciais;
- O sistema deverá suportar a criação de novas contas;
- A conta deverá ter um identificador, uma data de criação (automática ao criar), o operador que a criou e o saldo deverá ser calculado;
- A conta deverá iniciar com saldo de R$ 0,00 e deverá manter uma relação de movimentações;
- A movimentação de conta deverá ter uma data, um número de documento, uma descrição e um valor monetário;
- Deverá ser somente possível adicionar novas movimentações, não podendo alterar/excluir;
- As movimentações deverão ser exibidas em ordem cronológica;
- Os valores positivos são receitas da empresa e os valores negativos são despesas;
- Valores negativos devem aparecer em vermelho (ou entre parênteses);
- Deverá ser possível trocar o operador utilizando o sistema a qualquer momento;
- Deverá ser possível cadastrar um novo operador;
- Deverá ser possível selecionar uma conta para trabalhar;
- Deverá ser possível consultar os movimentos da conta selecionada, os filtros são: todos os movimentos, movimentos por período (data inicial e final), movimentos por operador, movimentos por tipo (receita ou despesa);
- Deverá ser possível a transferência de fundos de uma conta para a outra, desde que a conta de origem tenha saldo positivo;
- A transferência de fundos deve gerar uma movimentação de saída na conta de origem e entrada na conta de destino;
- Deverá ser possível emitir um relatório geral que apresente cada conta em uma linha (número da conta, operador, data de criação), sua movimentação em uma tabela (data, operador, número do documento, descrição, valor) e, abaixo da tabela o saldo atual da conta, no final do relatório o saldo geral da empresa;# TEAM X-AE-A-12
Repositório Inicial do Tabalho 1 GCS 2020/1

Componentes do time:
- Aloisio Bastian
- David Bertrand
- Gabriel Miola
- Guilherme Canto
- Hiago Luzardo
- Jorge Rosa
- Nicholas Silva
- Vanderson Nery

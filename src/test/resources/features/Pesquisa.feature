#language: pt
#encoding: utf-8
@mobileApp
Funcionalidade: Pesquisa Wikipedia

  Esquema do Cenario: Pesquisar por BrowserStack
    Dado acesso o aplicativo no dispositivo <deviceId>
    Quando realizo o filtro
    Entao os resultados sao exibidos
    Exemplos:
      |   deviceId     |
      |       1        |
      |       2        |

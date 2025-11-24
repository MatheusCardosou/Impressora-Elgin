# Impressora-Elgin
Objetivo do projeto: é permitir que qualquer pessoa possa, configurar a impressora (tipo de conexão, modelo, porta etc.), abrir e fechar conexão, testar diferentes tipos de impressão, acionar funções específicas como gaveta e sinal sonoro, utilizar a DLL da Elgin sem complicações.

Tecnologias Utilizadas: Java 11+, JNA – Java Native Access, DLL da Elgin (E1_Impressora01.dll), Execução via console (terminal).

Estrutura Geral do Código

 Interface ImpressoraDLL
Esta interface representa os métodos da DLL da Elgin, Ela é carregada via:
ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load("caminho/para/sua/dll", ImpressoraDLL.class);
Cada método da interface corresponde a uma função nativa da impressora.

AbreConexaoImpressora
FechaConexaoImpressora
ImpressaoTexto
ImpressaoQRCode
ImpressaoCodigoBarras
ImprimeXMLSAT
ImprimeXMLCancelamentoSAT
AbreGaveta
AbreGavetaElgin
SinalSonoro
Corte
AvancaPapel

Lógica do Programa

O programa funciona com base em um loop infinito que exibe um menu, permite a seleção de opções e executa a função correspondente.
Principais variáveis globais:
tipo – tipo de conexão (USB, serial etc.)
modelo – nome da impressora
conexao – porta (USB/COM/IP)
parametro – geralmente “0”
conexaoAberta – controla se a impressora já está conectada

Funcionalidades do Menu

1  Configurar Conexão
Solicita ao usuário: Tipo da conexão, Modelo da impressora, Porta de comunicação, Parâmetro adicional(Geralmente sendo "0")

2️ Abrir Conexão
Chama a função da DLL para conectar com a impressora.

3️ Impressão de Texto
Permite digitar um texto no console e imprimir diretamente.

4️ Impressão QR Code
Imprime um QR Code.

5️ Impressão de Código de Barras
Executa um teste de codigo de barra.

6️ Impressão XML SAT
Lê e imprime o XML de um cupom SAT.

7️ Cancelamento SAT
Imprime o XML de cancelamento com assinatura do QRCode.

8️ Abrir Gaveta Elgin
Aciona diretamente a gaveta padrão da Elgin.

9️ Abrir Gaveta
Usa outra função de controle da gaveta.

10 Sinal Sonoro
Aciona o “bip” da impressora.

0️ Finalizar
Fecha a conexão com a impressora e encerra o programa.

 O uso de comando if e else, o uso de switch case, usado com base o que foi explicado em sala de aula e o uso das funções.

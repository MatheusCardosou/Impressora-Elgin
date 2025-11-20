import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;

public class main {

    // Interface que representa a DLL, usando JNA
    public interface ImpressoraDLL extends Library {

        // Caminho completo para a DLL
        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "C:\\Users\\cavalheiro_matheus\\Downloads\\Java-Aluno Graduacao\\Java-Aluno Graduacao\\E1_Impressora01.dll",
                ImpressoraDLL.class
        );


        private static String lerArquivoComoString(String path) throws IOException {
            FileInputStream fis = new FileInputStream(path);
            byte[] data = fis.readAllBytes();
            fis.close();
            return new String(data, StandardCharsets.UTF_8);
        }


        int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param);

        int FechaConexaoImpressora();

        int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho);

        int Corte(int avanco);

        int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);

        int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);

        int AvancaPapel(int linhas);

        int StatusImpressora(int param);

        int AbreGavetaElgin();

        int AbreGaveta(int pino, int ti, int tf);

        int SinalSonoro(int qtd, int tempoInicio, int tempoFim);

        int ModoPagina();

        int LimpaBufferModoPagina();

        int ImprimeModoPagina();

        int ModoPadrao();

        int PosicaoImpressaoHorizontal(int posicao);

        int PosicaoImpressaoVertical(int posicao);

        int ImprimeXMLSAT(String dados, int param);

        int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);
    }

    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;

    private static final Scanner sc = new Scanner(System.in);

    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine();
    }

    public static void configurarConexao() {

        Scanner sc = new Scanner(System.in);
        if (!conexaoAberta) {

        }
        System.out.println("Digite o tipo (ex: 1 - USB, 2- SERIAL)");
        tipo = sc.nextInt();

        System.out.println("Digite o modelo(ex: i7,i8,i9...)");
        modelo = sc.nextLine();
        sc.nextLine();

        System.out.println("Digite a conexão (ex: 1 - USB, 2- SERIAL)");
        conexao = sc.nextLine();

        System.out.println("Digite o parametro (ex: 0)");
        parametro = sc.nextInt();
    }

    public static void abrirConexao() {

        if (!conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso.");
            } else {
            System.out.println("Erro ao abrir conexão. Código de erro : " + retorno);
            }
        }  else {
            System.out.println("Conexão já está aberta.");
        }

    }

    public static void fecharConexao() {
        if (conexaoAberta){
            int ret = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
            if (ret ==0);
            System.out.println("Conexão Fechada");
        }
    }

    public static void impressaoTexto() {
        if (conexaoAberta){
            System.out.println("Digite o texto a ser impresso:");
            String dados = sc.nextLine();
            int ret = ImpressoraDLL.INSTANCE.ImpressaoTexto(dados, 1, 4,0);

            if(ret == 0){
                System.out.println("\n*************************************************\n" +
                        "************ Impressão OK ***************\n" +
                        "************************************************\n");
            }else{
                System.out.println("\n*************************************************\n" +
                        "************ Impressão OK. Retorno "+ret+"***************\n" +
                        "************************************************\n");
            }
        }else{
            System.out.println("\n*************************************************\n" +
                    "************ Abra a conexao primeiro ***************\n" +
                    "************************************************\n");
        }
    }
    public static void impressaoQRCode() {
        if (conexaoAberta){
            int ret = ImpressoraDLL.INSTANCE.ImpressaoQRCode("teste impressao",6,4);

            if(ret == 0){
                System.out.println("\n*************************************************\n" +
                        "************ Impressão OK ***************\n" +
                        "************************************************\n");
            }else{
                System.out.println("\n*************************************************\n" +
                        "************ Erro. Retorno "+ret+"***************\n" +
                        "************************************************\n");
            }
        }else{
            System.out.println("\n*************************************************\n" +
                    "************ Abra a conexao primeiro ***************\n" +
                    "************************************************\n");
        }
    }
    public static void impressaoCodigoBarras(){
        if (conexaoAberta){
            int ret =  ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8,"{A012345678912",100,2,3);

            if(ret == 0){
                System.out.println("\n*************************************************\n" +
                        "************ Impressão OK ***************\n" +
                        "************************************************\n");
            }else{
                System.out.println("\n*************************************************\n" +
                        "************ Erro. Retorno "+ret+"***************\n" +
                        "************************************************\n");
            }
        }else{
            System.out.println("\n*************************************************\n" +
                    "************ Abra a conexao primeiro ***************\n" +
                    "************************************************\n");
        }
    }

    public static void ImprimeXMLSAT(){
        if (conexaoAberta) {
            String dados = "path=C:\\Users\\cavalheiro_matheus\\Downloads\\Java-Aluno Graduacao\\Java-Aluno Graduacao\\XMLSAT.xml";
            int ret = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados, 0);

            if(ret == 0){
                System.out.println("\n*************************************************\n" +
                        "************ Impressão OK ***************\n" +
                        "************************************************\n");
            }else{
                System.out.println("\n*************************************************\n" +
                        "************ Erro. Retorno "+ret+"***************\n" +
                        "************************************************\n");
            }
        }else{
            System.out.println("\n*************************************************\n" +
                    "************ Abra a conexao primeiro ***************\n" +
                    "************************************************\n");
        }
    }
    public static void ImprimeXMLCancelamentoSAT(){

        if (conexaoAberta) {
            String dados = "path=C:\\Users\\cavalheiro_matheus\\Downloads\\Java-Aluno Graduacao\\Java-Aluno Graduacao\\CANC_SAT.xml";
            String assQRCode =(assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==");

            int ret = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(dados,assQRCode,0);


            if(ret == 0){
                System.out.println("\n*************************************************\n" +
                        "************ Impressão OK ***************\n" +
                        "************************************************\n");
            }else{
                System.out.println("\n*************************************************\n" +
                        "************ Erro. Retorno "+ret+"***************\n" +
                        "************************************************\n");
            }
        }else{
            System.out.println("\n*************************************************\n" +
                    "************ Abra a conexao primeiro ***************\n" +
                    "************************************************\n");
        }
    }



    public static void AbreGaveta(){
        if (conexaoAberta){
            int ret = ImpressoraDLL.INSTANCE.AbreGaveta(1,5,10);

            if(ret == 0){
                System.out.println("\n*************************************************\n" +
                        "************ ABRE GAVETA OK ***************\n" +
                        "************************************************\n");
            }else{
                System.out.println("\n*************************************************\n" +
                        "************ Erro. Retorno "+ret+"***************\n" +
                        "************************************************\n");
            }
        }else{
            System.out.println("\n*************************************************\n" +
                    "************ Abra a conexao primeiro ***************\n" +
                    "************************************************\n");
        }
    }

    public static void AbreGavetaElgin(){
        if (conexaoAberta){
            int ret = ImpressoraDLL.INSTANCE.AbreGavetaElgin();

            if(ret == 0){
                System.out.println("\n*************************************************\n" +
                        "************ ABRE GAVETA ELGIN OK ***************\n" +
                        "************************************************\n");
            }else{
                System.out.println("\n*************************************************\n" +
                        "************ Erro. Retorno "+ret+"***************\n" +
                        "************************************************\n");
            }
        }else{
            System.out.println("\n*************************************************\n" +
                    "************ Abra a conexao primeiro ***************\n" +
                    "************************************************\n");
        }
    }

    public static void SinalSonoro(){
        if (conexaoAberta){
            int ret = ImpressoraDLL.INSTANCE.SinalSonoro(4,5,5);

            if(ret == 0){
                System.out.println("\n*************************************************\n" +
                        "************ SINAL SONORO OK ***************\n" +
                        "************************************************\n");
            }else{
                System.out.println("\n*************************************************\n" +
                        "************ Erro. Retorno "+ret+"***************\n" +
                        "************************************************\n");
            }
        }else{
            System.out.println("\n*************************************************\n" +
                    "************ Abra a conexao primeiro ***************\n" +
                    "************************************************\n");
        }
    }




    public static void main(String[] args) {
        while (true) {
            System.out.println("\n*************************************************");
            System.out.println("**************** MENU IMPRESSORA *******************");
            System.out.println("*************************************************\n");

            System.out.println("1  - Configurar Conexao");
            System.out.println("2  - Abrir Conexao");
            System.out.println("3  - Impressao Texto");
            System.out.println("4  - Impressao QRCode");
            System.out.println("5  - Impressao Cod Barras");
            System.out.println("6  - Impressao XML SAT");
            System.out.println("7  - Impressao XML Canc SAT");
            System.out.println("8  - Abrir Gaveta Elgin");
            System.out.println("9  - Abrir Gaveta");
            System.out.println("10 - Sinal Sonoro");
            System.out.println("0  - Fechar Conexao e Sair");
            System.out.println("--------------------------------------");

            String escolha = capturarEntrada("\nDigite a opção desejada: ");

            if (escolha.equals("0")) {
                fecharConexao();
                break;
            }

            switch (escolha) {
                case "1":
                    configurarConexao();
                    break;

                case "2":
                    abrirConexao();
                    ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo,modelo,conexao,parametro);

                    break;
                case "3":
                    impressaoTexto();
                    ImpressoraDLL.INSTANCE.Corte(4);
                    break;

                case "4":
                    impressaoQRCode();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;

                case "5":
                    impressaoCodigoBarras();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;


                case "6":
                    ImprimeXMLSAT();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;


                case "7":
                    ImprimeXMLCancelamentoSAT();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;

                case "8":
                    AbreGavetaElgin();
                    break;

                case "9":
                    AbreGaveta();
                    break;


                case "10":
                    SinalSonoro();
                    break;
                default:
                    System.out.println("\n********************************************\n" +
                            "************ Opção Invalida ***************\n" +
                            "********************************************\n");


            }
        }

        sc.close();
    }}





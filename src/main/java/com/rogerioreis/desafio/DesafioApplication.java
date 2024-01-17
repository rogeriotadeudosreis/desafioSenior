package com.rogerioreis.desafio;

import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.model.*;
import com.rogerioreis.desafio.repositories.ClienteRepository;
import com.rogerioreis.desafio.repositories.ItemRepository;
import com.rogerioreis.desafio.repositories.ProdutoRepository;
import com.rogerioreis.desafio.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedidoService pedidoService;

    public static void main(String[] args) {


        // Estudo sobre a estrutura Map em Java
        Scanner sc = new Scanner(System.in);

        Map<String, Integer> votes = new LinkedHashMap<>();

//        System.out.println("Enter file full path: ");
//        String path = sc.nextLine();

//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//
//            String line = br.readLine();
//            while (line != null){
//                String[] fields = line.split(", ");
//                String name = fields[0];
//                int count = Integer.parseInt(fields[1]);
//
//                if (votes.containsKey(name)) {
//                    int votesSoFar = votes.get(name);
//                    votes.put(name,count + votesSoFar);
//                } else {
//                    votes.put(name,count);
//                }
//
//                line = br.readLine();
//            }
//
//            for (String key : votes.keySet()) {
//                System.out.println(key + ": " + votes.get(key));
//            }
//
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        } catch (ArrayIndexOutOfBoundsException e) {
//           e.printStackTrace();
//        }

        SpringApplication.run(DesafioApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

//        Product prod1 = new Product(null, "AR CONDICIONADO", "COD01", BigDecimal.valueOf(1500), null, null, null, EnumTipoProduto.PRODUTO);
//        Product prod2 = new Product(null, "INSTALAÇÃO AR CONDICIONADO", "COD02", BigDecimal.valueOf(150), null, null, null, EnumTipoProduto.SERVICO);
//        Product prod3 = new Product(null, "TV SONY BRAVIA", "COD03",BigDecimal.valueOf(3000), null, null, null, EnumTipoProduto.PRODUTO);
//        Product prod4 = new Product(null, "INSTALAÇÃO DE TV", "COD04", BigDecimal.valueOf(150), null, null, null, EnumTipoProduto.SERVICO);
//        Product prod5 = new Product(null, "CORTINA BLACKOUT", "COD05", BigDecimal.valueOf(1000), null, null, null, EnumTipoProduto.PRODUTO);
//        Product prod6 = new Product(null, "INSTALAÇÃO DE CORTINAS", "COD06", BigDecimal.valueOf(150), null, null, null, EnumTipoProduto.SERVICO);
//        produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6));

//        Client c1 = new Client(null, "CLIENTE 01", "cliente01@gmail.com");
//        Client c2 = new Client(null, "CLIENTE 02", "cliente02@gmail.com");
//        Client c3 = new Client(null, "CLIENTE 03", "cliente03@gmail.com");
//        clienteRepository.saveAll(Arrays.asList(c1, c2, c3));

//        ClientRecord clientRecord = new ClientRecord(null, "rogerio", "rogerio@gmail.com", null, null);


//        Item item01 = new Item(null, 1, BigDecimal.valueOf(1500), prod1, BigDecimal.valueOf(1500.0));
//        Item item02 = new Item(null, 1, BigDecimal.valueOf(150.0), prod2, BigDecimal.valueOf(1500.0));
//        List<Item> lista01 = new ArrayList<>();
//        lista01.add(item01);
//        lista01.add(item02);

//        Item item03 = new Item(null, 1, BigDecimal.valueOf(3000.0), prod3, BigDecimal.valueOf(3000.0));
//        Item item04 = new Item(null, 1, BigDecimal.valueOf(150), prod4, BigDecimal.valueOf(150.0));
//        List<Item> lista02 = new ArrayList<>();
//        lista02.add(item03);
//        lista02.add(item04);

//        Item item05 = new Item(null, 1, BigDecimal.valueOf(1000), prod5, BigDecimal.valueOf(1000));
//        Item item06 = new Item(null, 1, BigDecimal.valueOf(150.0), prod6, BigDecimal.valueOf(150.0));
//        List<Item> lista03 = new ArrayList<>();
//        lista03.add(item05);
//        lista03.add(item06);

//        itemRepository.saveAll(Arrays.asList(item01, item02, item03, item04, item05, item06));


//        Order ped1 = new Order(null, null, c1, lista01, BigDecimal.valueOf(100), EnumSituacaoPedido.ABERTO);
//        Order ped2 = new Order(null, null, c2, lista02, BigDecimal.valueOf(25), EnumSituacaoPedido.ABERTO);
//        Order ped3 = new Order(null, null, c3, lista03, BigDecimal.valueOf(30), EnumSituacaoPedido.ABERTO);
//        pedidoService.create(ped1);
//        pedidoService.create(ped2);
//        pedidoService.create(ped3);

//        System.out.println("testando a execução da aplicação");
//
//        int[] numberArray = {1,2,3,4,5} ;
//
//        for (int elemento : numberArray
//             ) {
//            System.out.print(elemento + " ");
//        }
//        System.out.println("=================================================================\n");
//
//        List<String> frutasList = new ArrayList<>();
//        frutasList.add("banana");
//        frutasList.add("maçã");
//        frutasList.add("laranja");
//
//        List<String> verdurasList = new ArrayList<>();
//        verdurasList.add("couve");
//        verdurasList.add("alface");
//        verdurasList.add("almeirão");
//
//        verdurasList.addAll(frutasList);
//
//        List<String> listaInicial = new ArrayList<>();
//        List<String> produtos = Arrays.asList("arroz", "feijão", "ovo");
//        listaInicial.addAll(produtos);
//
//        for (String produto : produtos) {
//            System.out.println(produto);
//        }
//        System.out.println("========================================================================================\n");

//        Sintaxe da estrutor do forEach
//        Primeiro o tipo de dado da lista
//        Segundo uma variável (nome semântico)
//        Terceiro o nome da lista que já fora declarado.
//        for (String frutaAndVerduras : verdurasList
//             ) {
//            System.out.println(frutaAndVerduras);
//        }
//        System.out.println("========================================================================================\n");

//        List<Client> clienteList = new ArrayList<>();
//
//        clienteList = Arrays.asList(c1,c2,c3);
//
//        for (Client cliente : clienteList) {
////            System.out.println(cliente.getNome());
//        }

//        List<String> originalList = new ArrayList<>();
//        originalList.add("Rogerio");
//
//        for (String original : originalList) {
////            System.out.println("original " + original);
//        }
//
//        List<String> copia = new ArrayList<>();
//        copia.addAll(originalList);
//
//        for (String objeto : copia) {
////            System.out.println("copia" + copia );
//        }

        // Criando uma lista de números vindo de um lugar qualquer
//        List<Long> numeros = new ArrayList<>();
//        numeros = Arrays.asList(5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L,3L,6L,9L,322101832664L, 322109985109L ,322113743912L,322131238418L,322131287621L,322131327780L,322141077344L,322141100699L,322142607029L,322142836214L);
//
//        // Filtrando os números pares
//        List<Long> numerosPares = numeros.stream()
//                .filter( numero -> ehPar(numero))
//                .collect(Collectors.toList());
//        // Imprimindo números pares
////        System.out.println("Numeros pares: " + numerosPares);
//
//        //Filtrando os números ímpares
//        List<Long> numerosImpares = numeros.stream()
//                .filter(numero -> ehImpar(numero))
//                .collect(Collectors.toList());
//        // Imprimindo números ímpares
//        System.out.println("números ímpares: " + numerosImpares);

        // Qualquer outra operação com map
//        List<Long> numerosTransform = numeros.stream()
//                .map(numero -> numero - numero)
//                .collect(Collectors.toList());
////        System.out.println("Números transformados com map: " + numerosTransform);
//
//        // Números primos
//        List<Long> numerosPrimos = numeros.stream()
//                .filter(numero -> ehPrimo(numero))
//                .collect(Collectors.toList());
////        System.out.println("números primos: " + numerosPrimos);

    }
    // método para encontar número par
//    private static boolean ehPar(Long numero){
//        if (numero % 2 == 0) {
//            return true;
//        }
//        return false;
//    }
//
//    // método para encontrar número ímpar
//    private static boolean ehImpar(Long numero){
//        if (numero % 2 != 0) {
//            return true;
//        }
//        return false;
//    }
//
//    //método para encontrar número primo
//    private static boolean ehPrimo(Long numero){
//        if (numero < 2) {
//            return false;
//        }
//        for (long i = 2; i <= Math.sqrt(numero); i++){
//            if (numero % i == 0){
//                return false;
//            }
//        }
//        return true;
//    }

}

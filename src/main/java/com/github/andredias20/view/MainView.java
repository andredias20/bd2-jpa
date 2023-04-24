package com.github.andredias20.view;

import com.github.andredias20.controller.ProdutoDAO;
import com.github.andredias20.model.Produto;

import java.io.IOException;
import java.util.Scanner;

public class MainView {

    private static final ProdutoDAO dao = ProdutoDAO.getInstance();
    private static final Scanner sc = new Scanner(System.in);

    public static void start(){

        boolean keep = true;

        while (keep){
            mensagem();
            sc.nextLine();
            switch (sc.nextInt()){
                case 1: listarTodos(); break;
                case 2: listarPorId(); break;
                case 3: adicionar(); break;
                case 4: modificar(); break;
                case 5: remover(); break;
                case 0:{
                    System.out.println("Até mais!");
                    keep = false;
                } break;
            }
        }
    }

    private static void mensagem(){
        System.out.println("--- MENU ---");
        System.out.println("1 - Listar Todos os Produtos");
        System.out.println("2 - Listar Produto por Id");
        System.out.println("3 - Cadastrar");
        System.out.println("4 - Alterar");
        System.out.println("5 - Remover");
        System.out.println("0 - Sair");
    }


    private static void listarTodos(){
        dao.findAll().forEach(System.out::println);
        pausa();
    }

    private static void listarPorId() {
        System.out.println("Digite qual o ID do item que deseja procurar");
        int id = sc.nextInt();
        System.out.println(dao.findById(id));
        pausa();
    }

    private static void adicionar(){
        System.out.println("Preencha os dados: (Cadastro produto)");
        Produto p1 = new Produto();

        System.out.println("Nome: ");
        sc.nextLine();
        String nome = sc.next();
        p1.setNome(nome);

        System.out.println("Preço: ");
        sc.nextLine();
        Double preco = sc.nextDouble();
        p1.setPreco(preco);

        System.out.println("Descricao: ");
        sc.nextLine();
        String descricao = sc.next();
        p1.setDescricao(descricao);

        System.out.println("Gravando..");
        dao.persist(p1);
        System.out.println("Concluido! ");

        pausa();
    }

    private static void modificar(){
        System.out.println("Qual ID do produto que deseja moficiar? ");

        int id = sc.nextInt();

        Produto p1 = dao.findById(id);
        if(p1 == null) return;

        System.out.println("Preencha os dados: (Moficação produto)");
        System.out.println("Dados originais"+p1);

        System.out.println("\nNome: ");
        sc.nextLine();
        String nome = sc.next();
        p1.setNome(nome);

        System.out.println("Preço: ");
        sc.nextLine();
        Double preco = sc.nextDouble();
        p1.setPreco(preco);

        System.out.println("Descricao: ");
        sc.nextLine();
        String descricao = sc.next();
        p1.setDescricao(descricao);

        System.out.println("Gravando..");
        dao.persist(p1);
        System.out.println("Concluido! ");

        pausa();
    }

    private static void remover(){

    }
    private static void pausa(){
        try {
            System.in.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.print("\n\n\n\n\n\n\n\n");
    }
}

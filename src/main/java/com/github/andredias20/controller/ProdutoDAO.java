package com.github.andredias20.controller;

import com.github.andredias20.model.Produto;
import com.github.andredias20.model.ProdutoNaoEncontradoException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
public class ProdutoDAO {

    private static ProdutoDAO instance;
    protected EntityManager entityManger;

    public ProdutoDAO() {
        entityManger = getEntityManager();
    }
    public static ProdutoDAO getInstance(){
        if(instance == null){
            instance = new ProdutoDAO();
        }
        return instance;
    }
    private EntityManager getEntityManager(){
        try{
            var entityManagerFactory = Persistence.createEntityManagerFactory("bd2");
            return entityManagerFactory.createEntityManager();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Produto> findAll(){
        return entityManger.createQuery("FROM " + Produto.class.getName(), Produto.class).getResultList();
    }
    public Produto findById(Integer id){
        return entityManger.find(Produto.class, id);
    }

    public void persist(Produto produto){
        try{
            entityManger.getTransaction().begin();
            entityManger.persist(produto);
            entityManger.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
            entityManger.getTransaction().rollback();
        }
    }
    public void merge(Produto produto){
        try{
            entityManger.getTransaction().begin();
            entityManger.merge(produto);
            entityManger.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
            entityManger.getTransaction().rollback();
        }
    }
    public void remove(Produto produto){
        try{
            entityManger.getTransaction().begin();
            entityManger.remove(produto);
            entityManger.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
            entityManger.getTransaction().rollback();
        }
    }

    public void removeById(Integer id){
        try{
            entityManger.getTransaction().begin();
            var prod = entityManger.find(Produto.class, id);
            System.out.println("Produto encontrado: "+prod);
            if(prod == null) throw new ProdutoNaoEncontradoException("Não foi possivel encontrar o produto informado");
            entityManger.remove(prod);
            entityManger.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
            entityManger.getTransaction().rollback();
        }
    }
}

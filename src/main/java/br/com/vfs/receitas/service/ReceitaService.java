package br.com.vfs.receitas.service;

import br.com.vfs.receitas.model.entity.Receita;

import java.util.List;

/**
 * Interface que define os metodos relacionados aos fluxos de receita
 */
public interface ReceitaService {

    /**
     * Metodo que retornar todas as receitas cadastras
     * @return List<Receita>
     */
    List<Receita> listarReceitas();

    /**
     * Metodo que ç cria uma nova receita
     * @param receita
     */
    void adicionarReceita(Receita receita);

    /**
     * metodo que altera uma receita ja cadastrada
     * @param id
     * @param receita
     */
    void alterarReceita(Integer id, Receita receita);

    /**
     * metodo que exclui uma receita cadastra
     * @param id
     */
    void excluirReceita(Integer id);

    /**
     * metodo que obtem uma receita cadastra
     * @param id
     */
    Receita obterReceita(Integer id);
}

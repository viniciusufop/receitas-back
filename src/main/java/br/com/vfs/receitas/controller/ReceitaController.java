package br.com.vfs.receitas.controller;

import br.com.vfs.receitas.model.entity.Receita;
import br.com.vfs.receitas.model.request.ReceitaRequest;
import br.com.vfs.receitas.model.response.ReceitaResponse;
import br.com.vfs.receitas.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController()
@RequestMapping("/v1/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<ReceitaResponse> findAll(){
        List<Receita> receitas = receitaService.listarReceitas();
        return new ResponseEntity(converterReceitasParaReceitasResponse(receitas), HttpStatus.OK);
    }

    private List<ReceitaResponse> converterReceitasParaReceitasResponse(List<Receita> receitas) {
        List<ReceitaResponse> receitaResponses = new ArrayList<ReceitaResponse>();
        if(receitas != null && !receitas.isEmpty()){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (Receita receita : receitas){
                ReceitaResponse receitaResponse = new ReceitaResponse();
                receitaResponse.setId(receita.getId());
                receitaResponse.setDescricao(receita.getDescricao());
                receitaResponse.setVencimento(dateFormat.format(receita.getVencimento()));
                receitaResponse.setValor(receita.getValor());
                receitaResponses.add(receitaResponse);
            }
        }
        return receitaResponses;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity insert(@RequestBody ReceitaRequest receitaRequest){
        Receita receita = new Receita();
        receita.setDescricao(receitaRequest.getDescricao());
        receita.setValor(receitaRequest.getValor());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            receita.setVencimento(dateFormat.parse(receitaRequest.getVencimento()));
        } catch (ParseException e) {
            receita.setVencimento(Calendar.getInstance().getTime());
        }
        receitaService.adicionarReceita(receita);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Receita receita){
        //TUDO alterar para enviar o id tbm
        receitaService.alterarReceita(receita);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        receitaService.excluirReceita(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

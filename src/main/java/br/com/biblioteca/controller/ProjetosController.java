package br.com.biblioteca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.model.RiscoProjeto;
import br.com.biblioteca.model.StatusProjeto;
import br.com.biblioteca.repository.Pessoas;
import br.com.biblioteca.repository.Projetos;
import br.com.biblioteca.service.CadastroProjetoService;
import br.com.biblioteca.service.TabelaPessoasProjeto;
import br.com.biblioteca.service.Exception.GerenteObrigatorioException;
import br.com.biblioteca.service.Exception.ImpossivelExcluirEntidadeException;
import br.com.biblioteca.service.Exception.MembrosProjetoObrigatorioException;

@Controller
@RequestMapping("/projetos")
public class ProjetosController {
	
	@Autowired
	private Pessoas pessoas;
	
	@Autowired
	private Projetos projetos;

	@Autowired
	private CadastroProjetoService cadastroProjetoService;
	
	@Autowired
	private TabelaPessoasProjeto tabelaPessoasProjeto;
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/projeto/PesquisaProjetos");		
		mv.addObject("projetos", projetos.findAll());
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Projeto projeto) {
		ModelAndView mv = new ModelAndView("projeto/CadastroProjeto");
		mv.addObject("statusProjeto", StatusProjeto.values());
		mv.addObject("riscosProjeto", RiscoProjeto.values());
		mv.addObject("pessoas", projeto.getPessoas());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Projeto projeto, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(projeto);
		}
		
		projeto.setPessoas(tabelaPessoasProjeto.getPessoas());
		
		try {			
			cadastroProjetoService.salvar(projeto);
		} catch (GerenteObrigatorioException e) {
			result.rejectValue("gerente.id", e.getMessage(), e.getMessage());
			return novo(projeto);
		} catch (MembrosProjetoObrigatorioException e) {
			result.rejectValue("pessoas", e.getMessage(), e.getMessage());
			return novo(projeto);
		}
		
		attributes.addFlashAttribute("mensagem", "Projeto salva com sucesso!");
		return new ModelAndView("redirect:/projetos/novo");
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		
		Projeto projeto = projetos.buscarComPessoas(id);
			
		if (projeto != null) {
			ModelAndView mv = novo(projeto);
			mv.addObject(projeto);
			return mv;
		}
		return new ModelAndView("404");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Projeto projeto) {
		try {
			cadastroProjetoService.excluir(projeto);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/pessoa")
	public ModelAndView adicionarPessoa(Long codigoPessoa) {
		tabelaPessoasProjeto.adicionarPessoa(pessoas.findOne(codigoPessoa));
		return mvTabelaPessoasProjeto();
	}
	
	@DeleteMapping("/pessoa/{codigoPessoa}")
	public ModelAndView excluirPessoa(@PathVariable("codigoPessoa") Pessoa pessoa){
		tabelaPessoasProjeto.excluirPessoa(pessoa);
		return mvTabelaPessoasProjeto();
	}
	
	private ModelAndView mvTabelaPessoasProjeto() {
		ModelAndView mv = new ModelAndView("pessoa/TabelaPessoasProjeto");
		mv.addObject("pessoas", tabelaPessoasProjeto.getPessoas());
		return mv;
	}
}

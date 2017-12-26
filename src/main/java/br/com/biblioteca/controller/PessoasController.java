package br.com.biblioteca.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.repository.Pessoas;
import br.com.biblioteca.service.CadastroPessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

	@Autowired
	private Pessoas pessoas;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@GetMapping("/novo")
	public ModelAndView novo(Pessoa pessoa) {
		ModelAndView mv = new ModelAndView("pessoa/CadastroPessoa");
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(pessoa);
		}
		
		cadastroPessoaService.salvar(pessoa);
		attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
		return new ModelAndView("redirect:/pessoas/novo");
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Pessoa> pesquisar(String nome) {
		validarTamanhoNome(nome);
		return pessoas.findByNomeContainingIgnoreCase(nome);
	}
	
	@RequestMapping(path = "/filtro-cadastro-projeto", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Pessoa> pesquisarFiltro(String campoPesquisa) {
		return pessoas.porCpfCnpjOuNome(campoPesquisa);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Pessoa pessoa) {
		pessoa = cadastroPessoaService.salvar(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
}

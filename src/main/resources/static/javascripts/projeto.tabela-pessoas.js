var Teste = Teste || {};

Teste.TabelaPessoas = (function () {
	
	function TabelaPessoas(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaPessoasContainer = $('.js-tabela-pessoas-container');
	}
	
	TabelaPessoas.prototype.iniciar = function() {
		this.autocomplete.on('pessoa-selecionada', onPessoaSelecionada.bind(this));
		$('.js-exclusao-pessoa').on('click', onExclusaoPessoa.bind(this));
	}
	
	function onPessoaSelecionada(evento, pessoa) {
		var resposta = $.ajax({
			url: 'pessoa',
			method: 'POST',
			data: {
				codigoPessoa: pessoa.id
			}
		});
		
		resposta.done(onPessoaAtualizadaNoServidor.bind(this));
	}
	
	function onPessoaAtualizadaNoServidor(html) {
		this.tabelaPessoasContainer.html(html);	
		$('.js-exclusao-pessoa').on('click', onExclusaoPessoa.bind(this));
	}
	
	function onExclusaoPessoa(evento) {
		var codigoPessoa = $(evento.target).data('codigo-pessoa');
		var resposta = $.ajax({
			url: 'pessoa/' + codigoPessoa,
			method: 'DELETE'
		});
		
		resposta.done(onPessoaAtualizadaNoServidor.bind(this));
	}
	
	return TabelaPessoas;
	
}());

$(function() {		
	var autocomplete = new Teste.Autocomplete($('.js-nome-cpf-input'), 'nome', 'pessoa-selecionada');
	autocomplete.iniciar();
	
	var tabelaPessoas = new Teste.TabelaPessoas(autocomplete);
	tabelaPessoas.iniciar();
});
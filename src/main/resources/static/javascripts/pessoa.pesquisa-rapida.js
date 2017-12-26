var Teste = Teste || {};

Teste.PesquisaRapidaPessoa = (function() {
	
	function PesquisaRapidaPessoa() {
		this.pesquisaRapidaPessoasModal = $('#pesquisaRapidaPessoas');
		this.nomeInput = $('#nomePessoaModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-pessoas-btn');
		this.pesquisaRapidaCancelarBtn = $('.js-pesquisa-rapida-pessoas-cancelar-btn');
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaPessoas');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-pessoa').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro-pessoa');
		this.closePesquisaRapidaBtn = $('.js-close-pesquisa-rapida');
	}
	
	PesquisaRapidaPessoa.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.closePesquisaRapidaBtn.on('click', onPesquisaRapidaCancelarClicado.bind(this));
		this.pesquisaRapidaCancelarBtn.on('click', onPesquisaRapidaCancelarClicado.bind(this));
		this.pesquisaRapidaPessoasModal.on('shown.bs.modal', onModalShow.bind(this));
	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaPessoasModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			},
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaRapidaCancelarClicado(event) {
		event.preventDefault();
		this.pesquisaRapidaPessoasModal.modal('hide');
	}
	
	function onPesquisaConcluida(resultado) {
		console.log(resultado);
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		this.mensagemErro.addClass('hidden');
		
		var tabelaPessoaPesquisaRapida = new Teste.TabelaPessoaPesquisaRapida(this.pesquisaRapidaPessoasModal);
		tabelaPessoaPesquisaRapida.iniciar();
	}
	
	function onErroPesquisa(evento) {
		console.log(evento);
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaPessoa;
	
}());

Teste.TabelaPessoaPesquisaRapida = (function() {
	
	function TabelaPessoaPesquisaRapida(modal) {
		this.modalPessoa = modal;
		this.pessoa = $('.js-pessoa-pesquisa-rapida');
	}
	
	TabelaPessoaPesquisaRapida.prototype.iniciar = function() {
		this.pessoa.on('click', onPessoaSelecionado.bind(this));
	}
	
	function onPessoaSelecionado(evento) {
		this.modalPessoa.modal('hide');
		
		var pessoaSelecionado = $(evento.currentTarget);
		$('#codigoGerente').val(pessoaSelecionado.data('codigo'));
		$('#nomeGerente').val(pessoaSelecionado.data('nome'));
		$('#cpfGerente').val(pessoaSelecionado.data('cpf'));
	}
	
	return TabelaPessoaPesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaPessoa = new Teste.PesquisaRapidaPessoa();
	pesquisaRapidaPessoa.iniciar();
});
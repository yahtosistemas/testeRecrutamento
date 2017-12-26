var Teste = Teste || {};

Teste.Autocomplete = (function() {
	
	function Autocomplete(nomeInput, campoShowTabela, registroSelecionado) {
		this.nomeInput = nomeInput;
		this.campoShowTabela = campoShowTabela;
		this.registroSelecionado = registroSelecionado;
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
				url: function(campoPesquisa) {
					return this.nomeInput.data('url') + '?campoPesquisa=' + campoPesquisa;
				}.bind(this),
				getValue: this.campoShowTabela,
				minCharNumber: 3,
				requestDelay: 300,
				adjustWidth: false,
				ajaxSettings: {
					contentType: 'application/json'
				},
				list: {
					onChooseEvent: onRegistroSelecionado.bind(this)
				}
		}
		
		this.nomeInput.easyAutocomplete(options);
	}
	
	function onRegistroSelecionado() {
		this.emitter.trigger(this.registroSelecionado, this.nomeInput.getSelectedItemData());
		this.nomeInput.val('');
		this.nomeInput.focus();
	}
	
	return Autocomplete;
	
}());
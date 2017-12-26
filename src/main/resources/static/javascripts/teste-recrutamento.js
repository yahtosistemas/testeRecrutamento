var Teste = Teste || {};

Teste.MaskMoney = (function() {
	
	function MaskMoney() {
		this.decimal = $('.js-decimal');
	}
	
	MaskMoney.prototype.enable = function() {
		this.decimal.maskNumber({ decimal: ',', thousands: '.' });
	}
	
	return MaskMoney;
	
}());

Teste.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			todayHighlight: true,
			autoclose: true
		});
	}
	
	return MaskDate;
	
}());

$(function() {
	var maskMoney = new Teste.MaskMoney();
	maskMoney.enable();
	
	var maskDate = new Teste.MaskDate();
	maskDate.enable();
});
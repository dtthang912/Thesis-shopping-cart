//eshop-----------------------//
$(document).ready(function(){

	$(function() {
		$('#gallery a').lightBox();
	});
	
	$('.subMenu > a').click(function(e)
	{
		e.preventDefault();
		var subMenu = $(this).siblings('ul');
		var li = $(this).parents('li');
		var subMenus = $('#sidebar li.subMenu ul');
		var subMenus_parents = $('#sidebar li.subMenu');
		if(li.hasClass('open'))
		{
			if(($(window).width() > 768) || ($(window).width() < 479)) {
				subMenu.slideUp();
			} else {
				subMenu.fadeOut(250);
			}
			li.removeClass('open');
		} else 
		{
			if(($(window).width() > 768) || ($(window).width() < 479)) {
				subMenus.slideUp();			
				subMenu.slideDown();
			} else {
				subMenus.fadeOut(250);			
				subMenu.fadeIn(250);
			}
			subMenus_parents.removeClass('open');		
			li.addClass('open');	
		}
	});
	var ul = $('#sidebar > ul');
	$('#sidebar > a').click(function(e)
	{
		e.preventDefault();
		var sidebar = $('#sidebar');
		if(sidebar.hasClass('open'))
		{
			sidebar.removeClass('open');
			ul.slideUp(250);
		} else 
		{
			sidebar.addClass('open');
			ul.slideDown(250);
		}
	});
	
	// eshop-----------------------//
	$(document).ready(function(){

		$(function() {
			$('#gallery a').lightBox();
		});
		
		$('.subMenu > a').click(function(e)
		{
			e.preventDefault();
			var subMenu = $(this).siblings('ul');
			var li = $(this).parents('li');
			var subMenus = $('#sidebar li.subMenu ul');
			var subMenus_parents = $('#sidebar li.subMenu');
			if(li.hasClass('open'))
			{
				if(($(window).width() > 768) || ($(window).width() < 479)) {
					subMenu.slideUp();
				} else {
					subMenu.fadeOut(250);
				}
				li.removeClass('open');
			} else 
			{
				if(($(window).width() > 768) || ($(window).width() < 479)) {
					subMenus.slideUp();			
					subMenu.slideDown();
				} else {
					subMenus.fadeOut(250);			
					subMenu.fadeIn(250);
				}
				subMenus_parents.removeClass('open');		
				li.addClass('open');	
			}
		});
		var ul = $('#sidebar > ul');
		$('#sidebar > a').click(function(e)
		{
			e.preventDefault();
			var sidebar = $('#sidebar');
			if(sidebar.hasClass('open'))
			{
				sidebar.removeClass('open');
				ul.slideUp(250);
			} else 
			{
				sidebar.addClass('open');
				ul.slideDown(250);
			}
		});
		
		$('.add-to-cart-btn').click(function(){
			event.preventDefault(); 
			var successAlert = $(this).data('alert');
			console.log(successAlert);
			$.ajax('http://localhost:8080/DoAn_Store/addToCart',{
				success: function(response){
					var totalPrice = 0;
					var successAlert = $('.add-to-cart-btn').data('alert');
					$('.no-in-cart').text(response.length);
					$.each(response, function(index, lineItem){
						totalPrice += lineItem.price * lineItem.quantity;
					});
					$('.total-price').text(totalPrice.toFixed(1) + ' đ');
					alert(successAlert);
				},
				data: {'productId' : $(this).data('productid'),
					   'quantity'  : $('.product-quantity').val()
					  }
			});
		});
		
		$('.order-btn').click(function(){
			var successAlert = $(this).data('alert');
			alert(successAlert);
		});

	});


});

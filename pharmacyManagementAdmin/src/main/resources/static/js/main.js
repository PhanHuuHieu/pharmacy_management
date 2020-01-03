jQuery(document).ready(function($){
	productGroup();
	loadName();
	// get Event when search with name
	$('#searchNameIndex').keypress(function(event) {
		 if (event.keyCode == 13 || event.which == 13) {
			var searchNameIndex = $('#searchNameIndex').val();
			$.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: /*window.location.pathname*/"/searchWithName",
	        data: {
	        	searchNameIndex: searchNameIndex
            },
	        success: function (json) {
	        	window.location.pathname = 'listProduct';
	        },
	        error: function (e) {
	        	alert('er');
	        }
	    });
		  }
		});
	//if you change this breakpoint in the style.css file (or _layout.scss if you use SASS), don't forget to update this value as well
	var MqL = 1170;
	//move nav element position according to window width
	moveNavigation();
	$(window).on('resize', function(){
		(!window.requestAnimationFrame) ? setTimeout(moveNavigation, 300) : window.requestAnimationFrame(moveNavigation);
	});

	//mobile - open lateral menu clicking on the menu icon
	$('.cd-nav-trigger').on('click', function(event){
		event.preventDefault();
		if( $('.cd-main-content').hasClass('nav-is-visible') ) {
			closeNav();
			$('.cd-overlay').removeClass('is-visible');
		} else {
			$(this).addClass('nav-is-visible');
			$('.cd-main-header').addClass('nav-is-visible');
			$('.cd-main-content').addClass('nav-is-visible').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
				$('body').addClass('overflow-hidden');
			});
			toggleSearch('close');
			$('.cd-overlay').addClass('is-visible');
		}
	});

	//open search form
	$('.cd-search-trigger').on('click', function(event){
		event.preventDefault();
		toggleSearch();
		closeNav();
	});

	
	
	


	

	//submenu items - go back link
	$('.go-back').on('click', function(){
		$(this).parent('ul').addClass('is-hidden').parent('.has-children').parent('ul').removeClass('moves-out');
	});

	function closeNav() {
		$('.cd-nav-trigger').removeClass('nav-is-visible');
		$('.cd-main-header').removeClass('nav-is-visible');
		$('.cd-primary-nav').removeClass('nav-is-visible');
		$('.has-children ul').addClass('is-hidden');
		$('.has-children a').removeClass('selected');
		$('.moves-out').removeClass('moves-out');
		$('.cd-main-content').removeClass('nav-is-visible').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
			$('body').removeClass('overflow-hidden');
		});
	}

	function toggleSearch(type) {
		if(type=="close") {
			//close serach 
			$('.cd-search').removeClass('is-visible');
			$('.cd-search-trigger').removeClass('search-is-visible');
			$('.cd-overlay').removeClass('search-is-visible');
		} else {
			//toggle search visibility
			$('.cd-search').toggleClass('is-visible');
			$('.cd-search-trigger').toggleClass('search-is-visible');
			$('.cd-overlay').toggleClass('search-is-visible');
			if($(window).width() > MqL && $('.cd-search').hasClass('is-visible')) $('.cd-search').find('input[type="search"]').focus();
			($('.cd-search').hasClass('is-visible')) ? $('.cd-overlay').addClass('is-visible') : $('.cd-overlay').removeClass('is-visible') ;
		}
	}

	function checkWindowWidth() {
		//check window width (scrollbar included)
		var e = window, 
            a = 'inner';
        if (!('innerWidth' in window )) {
            a = 'client';
            e = document.documentElement || document.body;
        }
        if ( e[ a+'Width' ] >= MqL ) {
			return true;
		} else {
			return false;
		}
	}

	function moveNavigation(){
		var navigation = $('.cd-nav');
  		var desktop = checkWindowWidth();
        if ( desktop ) {
			navigation.detach();
			navigation.insertBefore('.cd-header-buttons');
		} else {
			navigation.detach();
			navigation.insertAfter('.cd-main-content');
		}
	}
	
});
function purcharse()
{
	var id = parseInt($("#idPro").val(), 10);
	addCart(id,1);
	window.location.pathname = 'cart';
}
function addCart(id,status)
{
	if(id === 0) {
		var idPro = $('#idPro').val();
		id = parseInt(idPro, 10);
	}
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: /*window.location.pathname*/"/addCart",
        data: {
            id: JSON.stringify(id),
            status: JSON.stringify(status)
        },
        success: function (json) {
        	//location.reload();
        	//alert(json[0].total_money);
        	if(json === ""&&status===1)
        	{
        		window.location.pathname = 'login';
        	}
        	else
        	{
        		$("#cart_total").text(json[0].total_money + " đ");
        	}
        	
        },
        error: function (e) {
        	alert('er');
        }
    });
}
$(document).ready(function () {
	addCart(-1,0);
});
function productGroup() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: /*window.location.pathname*/"/getProductGroup",
        dataType: 'json',
        success: function (data) {
        	var hi = '';
        	for(var i=0;i<data.tProductGroup.length;i++)
	        {
        		hi+='<li><a onclick="getDetailProductGroup('+data.tProductGroup[i].id+')" href="#" class="active">'+data.tProductGroup[i].product_group_name+'</a></li>';
        	}
        	
        	$('#listProductGroup').html(hi);
        },
        error: function (e) {
        	alert('er');
        }
    });
}
function getDetailProductGroup(id) {
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: /*window.location.pathname*/"/getDataListProduct",
        data: {
            id: JSON.stringify(id)
        },
        success: function (json) {
        	location.reload();
        	window.location.pathname = 'listProduct';
        },
        error: function (e) {
        	alert('er');
        }
    });
}
function loadName() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: /*window.location.pathname*/"/loadName",
        success: function (json) {
        	//location.reload();
        	$('#userNameLogin').text(json);
        	$('#cct').text(json);
        },
        error: function (e) {
        	alert('er');
        }
    });
}

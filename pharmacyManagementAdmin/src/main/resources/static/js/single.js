            var htmll = 'k';
            $(document).ready(function () {
				//fire_ajax_submit();
            	
			});
            
			function getDetailProduct(idProduct) {
				
			    $.ajax({
			        type: "GET",
			        contentfire_ajax_submitType: "application/json",
			        url: /*window.location.pathname*/"/getDetailProduct",
			        data: {
 		                idProduct: JSON.stringify(idProduct)
 		            },
			        dataType: 'json',
			        success: function (data) {
			        	htmll += data.tProduct[0][0];
			        	displayProduct(htmll);
			        	window.location = 'detail/';
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
			function displayProduct(htmll)
			{
				$('#hihihi').html(htmll);
			}
            $(document).ready(function () {
            	productCart();
            	productGroup();
			});
            function check(id) {
            	var amount = $("#amountProduct"+id).val();
            	if(amount==="0")
            	{
            		$.ajax({
				        type: "GET",
				        contentType: "application/json",
				        url: /*window.location.pathname*/"/deleteCart",
				        data: {
			                id: JSON.stringify(id)
			            },
				        success: function (json) {
				        	location.reload();
				        },
				        error: function (e) {
				        	alert('er');
				        }
				    });
            	}
            }
			function productCart() {
			    $.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getDataCart",
			        dataType: 'json',
			        success: function (data) {
			        	var hi = '';
			        	for(var i=0;i<data.tOrder.length;i++)
				        {
			        		hi+='<div class="cart-header wow fadeInUp animated" data-wow-delay=".5s">'
			    			hi+='<div class="alert-close1"></div>'
			    			hi+='<div class="cart-sec simpleCart_shelfItem">'
			    			hi+='<div class="cart-item cyc">'
			    			hi+='<a href="detail/'+data.tOrder[i].idPro+'"><img src="'+data.tOrder[i].picture+'" class="img-thumbnail" alt=""></a>'
			    			hi+='</div>'
			    			hi+='<div class="cart-item-info">'
			    			hi+='<h4><a href="detail/'+data.tOrder[i].idPro+'"> '+data.tOrder[i].name+' </a><span>'+data.tOrder[i].note+'</span></h4>'
			    			hi+='<ul class="qty">'
			    			hi+='<li><p>'+data.tOrder[i].color+'</p></li>'
			    			hi+='<li><p>'+data.tOrder[i].weight+'</p></li>'
			    			hi+='</ul>'
			    			hi+='<div class="delivery">'
			    			hi+='<label>Số tiền : '+data.tOrder[i].price_sell+' đ</label>'
			    			hi+='<span><input onchange="check('+data.tOrder[i].id+')" type="number" id="amountProduct'+data.tOrder[i].id+'" class="item_quantity_cart" min="0" value="1"></span>';
			    			hi+='<div class="clearfix"></div>'
			    			hi+='</div>'
			    			hi+='</div>'
			    			hi+='<div class="clearfix"></div>'
			    			hi+='</div>'
			    			hi+='</div>'
			        	}
			        	
			        	$('#listCart').html(hi);
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
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
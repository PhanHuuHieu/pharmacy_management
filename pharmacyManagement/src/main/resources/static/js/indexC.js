            $(document).ready(function () {
				fire_ajax_submit();
				
			});
			function fire_ajax_submit() {
			    $.ajax({
			        type: "GET",
			        contentfire_ajax_submitType: "application/json",
			        url: /*window.location.pathname*/"/getIndex",
			        dataType: 'json',
			        success: function (data) {
			        	var hi = '';
			        	for(var i=0;i<data.tProduct.length;i++)
				        {
				        	    hi+= '<div style="margin-top:15px;" class="col-lg-3 col-md-4 col-sm-6 col-6 top_brand_left">'
								hi+='<div class="w3ls_w3l_banner_left">'
								hi+='<div class="hover14 column" >'
								hi+='<div class="agile_top_brand_left_grid w3l_agile_top_brand_left_grid" >'
								hi+='<div class="agile_top_brand_left_grid1">'
								hi+='<figure>'
								hi+='<div class="snipcart-item block">'
								hi+='<div class="snipcart-thumb" >'
								hi+='<a href="detail/'+data.tProduct[i].id+'"><img src="'+data.tProduct[i].picture+'" alt=" " class="img-responsive" /></a>'
								hi+='<span><b>'+data.tProduct[i].name+'</b></span>'
								hi+='<p>'+data.tProduct[i].note+'</p>'
								hi+='<h4>'+data.tProduct[i].price_sell+' '+'đ'+'</h4>'
								hi+='</div>'
								hi+='<div class="snipcart-details">'
								hi+='<form action="#" method="post">'
								hi+='<fieldset>'
								hi+='<input type="submit" name="submit" value="Add to cart" class="button" />'
								hi+='</fieldset>'
								hi+='</form>'
								hi+='</div>'
								hi+='</div>'
								hi+='</figure>'
								hi+='</div>'
								hi+='</div>'
								hi+='</div>'
								hi+='</div>'
								hi+='</div>'
								
			        	}
			        	
			        	$('#hihi').html(hi);
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
			
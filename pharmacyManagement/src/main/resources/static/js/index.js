            $(document).ready(function () {
            	productNew();
            	productPopular();
            	var x = window.location;
            	//alert(.substr(27,30));
            	if(x.href === 'http://localhost:8080/index#_=_' || x.href === 'http://localhost:8080/index#') {
            		
            		window.location = 'http://localhost:8080/index';
            	}
			});
			function productNew() {
			    $.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getIndex",
			        dataType: 'json',
			        success: function (data) {
			        	var hi = '';
			        	for(var i=0;i<data.tProduct.length;i++)
				        {
			        		hi+='<div style="margin-right:15px;" class="col-md-3 new-grid simpleCart_shelfItem wow flipInY animated" data-wow-delay=".5s">';
							hi+='<div class="new-top">';
							hi+='<a href="detail/'+data.tProduct[i].id+'"><img src="'+data.tProduct[i].picture+'" class="img-responsive" alt=""/></a>';
							hi+='<div class="new-text">';
							hi+='<ul>';
							hi+='<li><a class="item_add" onclick="addCart('+data.tProduct[i].id+',1)" href="#">Thêm giỏ hàng</a></li>';
							hi+='<li><a href="detail/'+data.tProduct[i].id+'">Xem chi tiết</a></li>';
							hi+='</ul>';
							hi+='</div>';
							hi+='</div>';
							hi+='<div class="new-bottom">';
							hi+='<h5 style="height:90px;"><a class="name" href="detail/'+data.tProduct[i].id+'">'+data.tProduct[i].name+'</a></h5>';
							hi+='<div class="ofr">';
							hi+='<p><span class="item_price">'+data.tProduct[i].price_sell+' đ</span></p>';
							hi+='</div>';
							hi+='</div>';
						    hi+='</div>';
			        	}
			        	
			        	$('#hihi').html(hi);
			        	
						
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
			function productPopular() {
			    $.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getProductIndex",
			        dataType: 'json',
			        success: function (data) {
			        	var hi = '';
			        	for(var i=0;i<data.tProduct.length;i++)
				        {
			        		hi+='<div style="margin-right:15px;" class="col-md-3 gallery-grid wow flipInY animated" data-wow-delay=".5s">';
							hi+='<a href="detail/'+data.tProduct[i].id+'"><img src="'+data.tProduct[i].picture+'" class="img-responsive" alt=""/></a>';
							hi+='<div class="gallery-text simpleCart_shelfItem">';
							hi+='<h5 style="height:105px;"><a class="name" href="detail/'+data.tProduct[i].id+'">'+data.tProduct[i].name+'</a></h5>';
							hi+='<p><span class="item_price">'+data.tProduct[i].price_sell+' đ</span></p>';
							hi+='<h4>'+data.tProduct[i].note+'</h4>';
							hi+='<ul>';
							hi+='<li><a href="detail/'+data.tProduct[i].id+'" ><span class="glyphicon glyphicon glyphicon-eye-open" aria-hidden="true"></span></a></li>';
							// if have comment, pick it here
							hi+='<li><a class="item_add" href="#" onclick="addCart('+data.tProduct[i].id+',1)"><span class="glyphicon glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a></li>';
							hi+='</ul>';
							hi+='</div>';
						    hi+='</div>';
			        	}
			        	
			        	$('#productIndex').html(hi);
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
			
			
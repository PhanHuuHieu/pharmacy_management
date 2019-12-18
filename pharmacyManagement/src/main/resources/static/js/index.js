            $(document).ready(function () {
            	productNew();
            	productPopular();
            	productGroup();
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
							hi+='<li><a class="item_add" onclick="addCart('+data.tProduct[i].id+')" href="#">Thêm giỏ hàng</a></li>';
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
							hi+='<li><a href="#"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></a></li>';
							hi+='<li><a class="item_add" href="#" onclick="addCart('+data.tProduct[i].id+')"><span class="glyphicon glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a></li>';
							hi+='<li><a href="#"><span class="glyphicon glyphicon glyphicon-heart-empty" aria-hidden="true"></span></a></li>';
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
			        contenType: "application/json",
			        url: /*window.location.pathname*/"/getDataListProduct",
			        data: {
 		                id: JSON.stringify(id)
 		            },
			        dataType: 'json',
			        success: function (data) {
			        	window.location.pathname = 'listProduct';
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
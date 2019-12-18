            $(document).ready(function () {
				//fire_ajax_submit();
            	getDetailProductGroupx();
			});
//            document.getElementById('color').onclick = function(e){
//                if (this.checked){
//                    alert(this.value);
//                }
//                else{
//                    alert("Bạn vừa bỏ thích freetuts.net");
//                }
//            };
			function getDetailProductGroupx() {
			    $.ajax({
			        type: "GET",
			        contentfire_ajax_submitType: "application/json",
			        url: /*window.location.pathname*/"/getDataListProductx",
			        dataType: 'json',
			        success: function (data) {
			        	var hi = '';
			        	for(var i=0;i<data.tProduct.length;i++)
				        {
			        		hi+='<div style="margin-right:15px;" class="product-grids simpleCart_shelfItem wow fadeInUp animated" data-wow-delay=".5s">';
							hi+='<div class="new-top">';
							hi+='<a href="detail/'+data.tProduct[i].id+'"><img src="'+data.tProduct[i].picture+'" class="img-responsive" alt=""/></a>';
							hi+='<div class="new-text">';
							hi+='<ul>';
							hi+='<li><a href="detail/'+data.tProduct[i].id+'">Xem chi tiết</a></li>';
							//hi+='<li><input type="number" class="item_quantity" min="1" value="1"></li>';
							hi+='<li><a class="item_add" href="">Thêm vào giỏ</a></li>';
							hi+='</ul>';
							hi+='</div>';
							hi+='</div>';
							hi+='<div class="new-bottom">';
							hi+='<h5 style="height:110px;"><a class="name" href="detail/'+data.tProduct[i].id+'">'+data.tProduct[i].name+' </a></h5>';
							hi+='<div class="rating">';
							hi+='<span class="on">☆</span>';
							hi+='<span class="on">☆</span>';
							hi+='<span class="on">☆</span>';
							hi+='<span class="on">☆</span>';
							hi+='<span>☆</span>';
							hi+='</div>';
							hi+='<div class="ofr">';
							hi+='<p><span class="item_price">'+data.tProduct[i].price_sell+'</span></p>';
							hi+='</div>';
							hi+='</div>';
						    hi+='</div>';
			        	}
			        	
			        	$('#listProduct').html(hi);
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
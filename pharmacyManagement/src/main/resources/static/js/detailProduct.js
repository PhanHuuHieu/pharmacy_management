            $(document).ready(function () {
            	productNew();
			});
			function productNew() {
			    $.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getListRelated",
			        dataType: 'json',
			        success: function (data) {
			        	var hi = '';
			        	hi+='<div class="row">';
			        	hi+='<div class="well">';
			        	hi+='<div id="myCarousel" class="carousel slide">';
						hi+='<div class="carousel-inner">';
						hi+= '<div class="item active">';
			        	hi+= '<div class="row">';
			        	hi+= '<div class="item">';
			        	hi+= '<div class="row">';
						var count = 0;
			        	for(var i=0;i<data.tProduct.length;i++)
				        {
			        		
//			        		if(i%4==0)
//			        		{
//			        			hi = '<div class="item">';
//				        	    hi+= '<div class="row">';
//			        		}
			        		if(count===4)
					        {
					        	hi+='</div>';
					        	hi+='</div>';
					        	count=-1;
					        }
			        		if(count===-1)
					        {
					        	hi+= '<div class="item">';
					        	hi+= '<div class="row">';
					        	count++;
					        }
			        		hi+='<div class="col-md-3">'; 
					        hi+='<div class="thumbnail"><img src="'+data.tProduct[i].picture+'" alt="Sản phẩm 1">'; 
					        hi+='<div class="caption">'; 
					        hi+='<h3>Sản phẩm 1</h3>'; 
					        hi+='<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod temporhi</p>'; 
					        hi+='<p><a href="http://hocwebgiare.com/" class="btn btn-primary" role="button">200.000 đ</a><a href="http://hocwebgiare.com/" class="btn btn-default" role="button">Đặt hàng</a>';
					        hi+='</p>'; 
					        hi+='</div>'; 
					        hi+='</div>'; 
					        hi+='</div>';
					        count++;
//					        if(i%3==0 && i!=0)
//					        {
//					        	hi+='</div>';
//					        	hi+='</div>';
//					        }
			        	}
			        	hi+='</div>';
			        	hi+='</div>';
			        	hi+='</div>';
			        	hi+='<a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="fa fa-chevron-left fa-2x"></i></a> <a class="right carousel-control" href="#myCarousel" data-slide="next"><i class="fa fa-chevron-right fa-2x"></i></a>'; 
					    hi+='<ol class="carousel-indicators">'; 
					    hi+='<li data-target="#myCarousel" data-slide-to="0" class=""></li>';
					    hi+='<li data-target="#myCarousel" data-slide-to="1" class=""></li>'; 
					    hi+='<li data-target="#myCarousel" data-slide-to="2" class="active"></li>';
					    hi+='</ol>'; 
					    hi+='</div>';
					    hi+='</div>';
					    hi+='</div>';
			        	$('#ccc').html(hi);
			        	
						
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
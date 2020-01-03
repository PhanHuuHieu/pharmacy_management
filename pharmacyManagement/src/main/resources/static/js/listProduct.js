            $(document).ready(function () {
            	getPageProduct(1,1);
			});
            function searchWithName()
            {
            	getPageProduct(1,1);
            }
			function loadData(data)
			{
				var hi = '';
	        	for(var i=0;i<data.pages.pageList.length;i++)
		        {
	        		hi+='<div style="margin-right:15px;" class="product-grids simpleCart_shelfItem wow fadeInUp animated" data-wow-delay=".5s">';
					hi+='<div class="new-top">';
					hi+='<a href="detail/'+data.pages.pageList[i].id+'"><img src="'+data.pages.pageList[i].picture+'" class="img-responsive" alt=""/></a>';
					hi+='<div class="new-text">';
					hi+='<ul>';
					hi+='<li><a href="detail/'+data.pages.pageList[i].id+'">Xem chi tiết</a></li>';
					//hi+='<li><input type="number" class="item_quantity" min="1" value="1"></li>';
					hi+='<li><a class="item_add" onclick="addCart('+data.pages.pageList[i].id+',1)" href="#">Thêm vào giỏ</a></li>';
					hi+='</ul>';
					hi+='</div>';
					hi+='</div>';
					hi+='<div class="new-bottom">';
					hi+='<h5 style="height:110px;"><a class="name" href="detail/'+data.pages.pageList[i].id+'">'+data.pages.pageList[i].name+' </a></h5>';
					hi+='<div class="rating">';
					hi+='<span class="on">☆</span>';
					hi+='<span class="on">☆</span>';
					hi+='<span class="on">☆</span>';
					hi+='<span class="on">☆</span>';
					hi+='<span>☆</span>';
					hi+='</div>';
					hi+='<div class="ofr">';
					hi+='<p><span class="item_price">'+data.pages.pageList[i].price_sell+' đ</span></p>';
					hi+='</div>';
					hi+='</div>';
				    hi+='</div>';
	        	}
	        	
	        	$('#listProduct').html(hi);
	        	var he = '';
	        	he+='<ul class="pagination">';
	        	he+='<li';
	        	if(data.currentIndex === 1)
	        	{
	        		he+=' class="page-item disabled">';
	        	}
	        	else
	        	{
	        		he+=' class="page-item">';
	        	}
	        	he+='<a class="page-link" onclick="getPageProduct(1,0)">First</a>';
				he+='</li>';
				he+='<li';
				if(data.currentIndex === 1)
	        	{
	        		he+=' class="page-item disabled">';
	        	}
	        	else
	        	{
	        		he+=' class="page-item">';
	        	}
				var pre = data.currentIndex - 1;
				he+='<a class="page-link" aria-label="Previous" onclick="getPageProduct('+pre+',0)"';
				he+=' title="Go to previous page"><span aria-hidden="true">«</span>';
				he+='<span class="sr-only">Previous</span></a>';
			    he+='</li>';
			    ////////////////////////
			    for(var i = data.beginIndex;i<=data.endIndex;i++) {
			    	if(i == data.currentIndex) {
			    		he+='<li class="page-item active"><a class="page-link" onclick="getPageProduct('+i+',0)" href="#"><span>'+i+'</span></li>';
			    	}
			    	else {
			    		he+='<li class="page-item"><a class="page-link" onclick="getPageProduct('+i+',0)" href="#"><span>'+i+'</span></li>';
			    	}
			    }
			    ////////////////////////
			    he+='<li';
			    if(data.currentIndex === data.totalPageCount)
			    {
			    	he+=' class="page-item disabled">';
			    }
			    else
			    {
			    	he+=' class="page-item">';
			    }
				he+='<a class="page-link" aria-label="Next"';
				var next = data.currentIndex + 1;
				he+='onclick="getPageProduct('+next+',0)" title="Go to next page"><span';
				he+=' aria-hidden="true">»</span> <span class="sr-only">Next</span></a>';
			    he+='</li>';
			    he+='<li';
			    if(data.currentIndex === data.totalPageCount)
			    {
			    	he+=' class="page-item disabled">';
			    }
			    else
			    {
			    	he+=' class="page-item">';
			    }
				he+='<a class="page-link" onclick="getPageProduct('+data.totalPageCount+',0)">Last</a>';
			    he+='</li>';
			    he+='</ul>';
				$('#kaka').html(he);
				window.scrollTo({ top: 0, behavior: 'smooth' });
			}
			function getPageProduct(page,status,keySearch)
			{
				var checkboxUnit = document.getElementsByName('searchUnit');
                var resultUnit = "";
                var checkboxSup = document.getElementsByName('searchSup');
                var resultSup = "";
                 
                // Lặp qua từng checkbox để lấy giá trị
                for (var i = 0; i < checkboxSup.length; i++){
                    if (checkboxSup[i].checked === true){
                    	resultSup += checkboxSup[i].value + ',';
                    }
                }
             // Lặp qua từng checkbox để lấy giá trị
                for (var i = 0; i < checkboxUnit.length; i++){
                    if (checkboxUnit[i].checked === true){
                    	resultUnit += checkboxUnit[i].value + ',';
                    }
                }
                var nameSearch = $('#searchName').val();
                
				$.ajax({
			        type: "GET",
			        contentfire_ajax_submitType: "application/json",
			        url: /*window.location.pathname*/"/paginationListProduct",
			        data: {
 		               page: JSON.stringify(page),
 		               resultUnit: resultUnit,
 		               resultSup: resultSup,
 		               nameSearch: nameSearch,
 		               status: status
 		            },
			        dataType: 'json',
			        success: function (data) {
			        	loadData(data);
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
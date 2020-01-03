            $(document).ready(function () {
            	setLatAndLon();
            	productCart();
            	productGroup();
            	var name = $("#namedeli").val();
            	var phone_number = $("#phone_number").val();
            	var address_detail = $("#address_detail").val();
            	if(name === "" || phone_number === "")
            	{
            		$("#error_message").text("Vui nhập đầy đủ thông tin địa chỉ");
            	}
            	if(address_detail === "")
            	{
            		let geolocation = navigator.geolocation;
            		let options = {
            				  enableHighAccuracy: true,
            				  timeout: 5000,
            				  maximumAge: 0
            				};
            		geolocation.getCurrentPosition(onGeoSuccess, onGeoError, options);
            		function onGeoSuccess(position) {
            			let url = 'https://us1.locationiq.com/v1/reverse.php?key=ba6dafbdb2ad02&lat='+position.coords.latitude+'&lon='+position.coords.longitude+'&format=json';
                    	fetch(url)
                    	.then(res => res.json())
                    	.then((out) => {
                    	  $("#address_detail").val(out.display_name);
//	              		  var uluru = {lat: position.coords.latitude, lng: position.coords.longitude};
//	        			  var map = new google.maps.Map(
//	        			      document.getElementById('map'), {zoom: 16, center: uluru});
//	        			  var marker = new google.maps.Marker({position: uluru, map: map});
                    	  var hi = '';
                    	  hi+='<iframe width="100%" height="400" id="gmap_canvas" src="https://maps.google.com/maps?q='+out.display_name+'&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0">';
	          	    		hi+='</iframe>';
	          	    		hi+='<a href="https://periodic-table-of-elements.net"></a>';
	          	    		$('#mapx').html(hi);
                    	})
                    	.catch(err => { throw err });
                    	////////////
            			
            		}
            		function onGeoError(error) {
        			  let detailError;

        			  if(error.code === error.PERMISSION_DENIED) {
        			    detailError = "User denied the request for Geolocation.";
        			  }
        			  else if(error.code === error.POSITION_UNAVAILABLE) {
        			    detailError = "Location information is unavailable.";
        			  }
        			  else if(error.code === error.TIMEOUT) {
        			    detailError = "The request to get user location timed out."
        			  }
        			  else if(error.code === error.UNKNOWN_ERROR) {
        			    detailError = "An unknown error occurred."
        			  }

        			  $("#error").innerHTML = `Error: ${detailError}`;
        			}
            	}
            	$('#barcode').keypress(function(event) {
            	  if (event.keyCode == 13 || event.which == 13) {
      				var barcode = $('#barcode').val();
      				$.ajax({
    			        type: "GET",
    			        contentType: "application/json",
    			        url: /*window.location.pathname*/"/getCustomerWithBarcode",
    			        data: {
    			        	barcode: barcode
    		            },
    			        success: function (json) {
    			        	if(json.length > 0) {
    			        		document.getElementById("checkPoint").checked = true;
    			        	}
    			        },
    			        error: function (e) {
    			        	alert('er');
    			        }
    			    });
      			  }
      			});
			});
            function barcodeClick() {
            	 $("#barcode").focus();
            }
            function displayWhenKeyUp() {
            	var address = $('#address_detail').val();
            	if(address != '') {
            		$("#error_message").text("");
            	}
//            	let url = 'https://us1.locationiq.com/v1/search.php?key=ba6dafbdb2ad02&q='+address+'&format=json';
//            	fetch(url)
//            	.then(res => res.json())
//            	.then((out) => {
//            	  var getLat = parseFloat(out[0].lat);
//      			  var getLon = parseFloat(out[0].lon);
//            	  var uluru = {lat: getLat, lng: getLon};
//      			  var map = new google.maps.Map(
//      			      document.getElementById('map'), {zoom: 16, center: uluru});
//      			  var marker = new google.maps.Marker({position: uluru, map: map});
//            	})
//            	.catch(err => { throw err });
            	var hi = '';
            	hi+='<iframe width="100%" height="400" id="gmap_canvas" src="https://maps.google.com/maps?q='+address+'&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0">';
	    		hi+='</iframe>';
	    		hi+='<a href="https://periodic-table-of-elements.net"></a>';
            	
	    		$('#mapx').html(hi);
            }
            function setLatAndLon() {
            	var address = $('#address_detail').val();
//            	let url = 'https://us1.locationiq.com/v1/search.php?key=ba6dafbdb2ad02&q='+address+'&format=json';
//            	fetch(url)
//            	.then(res => res.json())
//            	.then((out) => {
//            	  var getLat = parseFloat(out[0].lat);
//      			  var getLon = parseFloat(out[0].lon);
//            	  var uluru = {lat: getLat, lng: getLon};
//      			  var map = new google.maps.Map(
//      			      document.getElementById('map'), {zoom: 16, center: uluru});
//      			  var marker = new google.maps.Marker({position: uluru, map: map});
//            	})
//            	.catch(err => { throw err });
            	var hi = '';
            	hi+='<iframe width="100%" height="400" id="gmap_canvas" src="https://maps.google.com/maps?q='+address+'&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0">';
	    		hi+='</iframe>';
	    		hi+='<a href="https://periodic-table-of-elements.net"></a>';
	    		$('#mapx').html(hi);
            }
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
            	else
            	{
            		var integer = parseInt(amount, 10);
            		var idPro = parseInt($("#idPro").val(), 10);
            		$.ajax({
				        type: "GET",
				        contentType: "application/json",
				        url: /*window.location.pathname*/"/updateCart",
				        data: {
			                id: JSON.stringify(id),
			                amount: amount,
			                idPro: idPro
			            },
				        success: function (json) {
				        	//location.reload();
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
			        	if(data.tOrder === null)
			        	{
			        		window.location.pathname = 'login';
			        	}
			        	else
			        	{
			        		var hi = '';
				        	for(var i=0;i<data.tOrder.length;i++)
					        {
				        		hi+='<div class="cart-header wow fadeInUp animated" data-wow-delay=".5s">'
				    			hi+='<div class="alert-close1"></div>'
				    			hi+='<div class="cart-sec simpleCart_shelfItem">'
				    			hi+='<div class="cart-item cyc">'
				    			hi+='<a href="detail/'+data.tOrder[i].idPro+'"><img src="'+data.tOrder[i].picture+'" class="img-thumbnail" alt=""></a>'
				    			hi+='<input type="hidden" id="idPro" value="'+data.tOrder[i].idPro+'">'
				    			hi+='</div>'
				    			hi+='<div class="cart-item-info">'
				    			hi+='<h4><a href="detail/'+data.tOrder[i].idPro+'"> '+data.tOrder[i].name+' </a><span>'+data.tOrder[i].note+'</span></h4>'
				    			hi+='<ul class="qty">'
				    			hi+='<li><p>'+data.tOrder[i].color+'</p></li>'
				    			hi+='<li><p>'+data.tOrder[i].weight+'</p></li>'
				    			hi+='</ul>'
				    			hi+='<div class="delivery">'
				    			hi+='<label>Số tiền : '+data.tOrder[i].price_sell+' đ</label>'
				    			hi+='<span><input onchange="check('+data.tOrder[i].id+')" type="number" id="amountProduct'+data.tOrder[i].id+'" class="item_quantity_cart" min="0" value="'+data.tOrder[i].amount+'"></span>';
				    			hi+='<div class="clearfix"></div>'
				    			hi+='</div>'
				    			hi+='</div>'
				    			hi+='<div class="clearfix"></div>'
				    			hi+='</div>'
				    			hi+='</div>'
				        	}
				        	
				        	$('#listCart').html(hi);
				        	if (data.tOrder.length === 0)
				        	{
				        		document.getElementById("btnPurchase").style.display='none';
				        	}
			        	}
			        	
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
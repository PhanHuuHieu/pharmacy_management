            $(document).ready(function () {
            	
					fire_ajax_submit();
					$("#formOrderSearch").submit(function (event) {
				        event.preventDefault();
				        fire_ajax_submit();
				    });
			});
            function setLatAndLon() {
            	var address = $('#address').val();
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
            function validateOrder() {
            	if($('#date_start').val() === ''){
            		$("#btnSb").prop( "disabled", true );
            		$("#error").text("Ngày đặt đang trống!");
            	} else if($('#date_delivery').val() === ''){
            		$("#btnSb").prop( "disabled", true );
            		$("#error").text("Ngày vận chuyển đang trống!");
            	} else if($('#date_pay').val() === ''){
            		$("#btnSb").prop( "disabled", true );
            		$("#error").text("Ngày chờ thanh toán đang trống!");
            	} else if($('#date_end').val() === ''){
            		$("#btnSb").prop( "disabled", true );
            		$("#error").text("Ngày giao hàng đang trống!");
            	} else {
            		$("#btnSb").prop( "disabled", false );
            		$("#error").text("");
            	}
            }
            function initUpdate(idOrder)
            {
            	setLatAndLon();
            	 $.ajax({
 			        type: "GET",
 			        contentType: "application/json",
 			        url: /*window.location.pathname*/"/detailOrderAdmin",
 			        data: {
 			        	idOrder: JSON.stringify(idOrder)
 		            },
 			        success: function (json) {
 			        	$('#id').val(json[0].id);
 			        	$('#nameProducts').val(json[0].nameProducts);
 			        	$('#nameCustomer').val(json[0].nameCustomer);
 			        	$('#address').val(json[0].address);
 			        	if(json[0].payment === '1') {
 			        		$('#payment').val('Thanh toán với Paypal');
 			        	}
 			        	else {
 			        		$('#payment').val('Thanh toán khi nhận hàng');
 			        	}
 			        	
 			        	$('#status').val(json[0].status);
 			        	$('#date_start').val(json[0].date_start);
 			        	$('#date_pay').val(json[0].date_pay);
 			        	$('#date_delivery').val(json[0].date_delivery);
 			        	$('#date_end').val(json[0].date_end);
 			        },
 			        error: function (e) {
 			        	alert('er');
 			        }
 			    });
            }
			function fire_ajax_submit() {
				var employeeId = $("#fk_employee_id").val();
				var customerId = $("#fk_customer_id").val();
				var dateOrder = $("#dateOrder").val();
				
			    $.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getListOrder",
			        data: {
			        	employeeId:employeeId,
			        	customerId:customerId,
			        	dateOrder:dateOrder
			        },
			        dataType: 'json',
			        success: function (data) {
			        	$("#tableProduct").dataTable({
			        		"scrollX": 200,
			        		"ordering": true,
			        		"info":     false,
			        		"bDestroy": true,
			        		"bProcessing": true,
			        		"aaData": data,
			        		 "aoColumns": [
			        	            {
			                            "sName": "id",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[0];
			                            	return '<a href="'+html+'" data-toggle="modal" data-target="#modalDetailOrder" onclick="initUpdate('+html+')">'+html+'</a>';
			                                //return html;
			                            }
			                        },
			                        {
			                            "sName": "nameCus",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[1];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "status",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[2];
			                            	if (html === '0'){
			                            		return 'Chờ xác nhận';
			                            	} else if (html === '1'){
			                            		return 'Chờ thanh toán';
			                            	} else if (html === '2'){
			                            		return 'Giao hàng';
			                            	} 
			                            	return 'Nhận hàng';
			                            }
			                        },
			                        {
			                            "sName": "totalMoney",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[3];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "date",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[4];
			                            	return html;
			                            }
			                        }
//			                        {
//			                            "sName": "status",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[5];
//			                            	if (html === '0'){
//			                            		return 'Chờ xác nhận';
//			                            	} else if (html === '1'){
//			                            		return 'Chờ thanh toán';
//			                            	} else if (html === '2'){
//			                            		return 'Giao hàng';
//			                            	} 
//			                            	return 'Nhận hàng';
//			                            }
//			                        }
			        	        ]
			        	});
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
			
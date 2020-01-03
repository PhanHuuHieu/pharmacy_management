//            $(document).ready(function () {
//            	
//					fire_ajax_submit();
//					$("#formOrderSearch").submit(function (event) {
//				        event.preventDefault();
//				        fire_ajax_submit();
//				    });
//			});
//            function setLatAndLon() {
//            	var address = $('#address').val();
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
//            	
//            }
//            function validateOrder() {
//            	if($('#date_start').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Ngày đặt đang trống!");
//            	} else if($('#date_delivery').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Ngày vận chuyển đang trống!");
//            	} else if($('#date_pay').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Ngày chờ thanh toán đang trống!");
//            	} else if($('#date_end').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Ngày giao hàng đang trống!");
//            	} else {
//            		$("#btnSb").prop( "disabled", false );
//            		$("#error").text("");
//            	}
//            }
//            function initUpdate(idOrder)
//            {
//            	setLatAndLon();
//            	 $.ajax({
// 			        type: "GET",
// 			        contentType: "application/json",
// 			        url: /*window.location.pathname*/"/detailOrderAdmin",
// 			        data: {
// 			        	idOrder: JSON.stringify(idOrder)
// 		            },
// 			        success: function (json) {
// 			        	$('#id').val(json[0].id);
// 			        	$('#nameProducts').val(json[0].nameProducts);
// 			        	$('#nameCustomer').val(json[0].nameCustomer);
// 			        	$('#address').val(json[0].address);
// 			        	if(json[0].payment === '1') {
// 			        		$('#payment').val('Thanh toán với Paypal');
// 			        	}
// 			        	else {
// 			        		$('#payment').val('Thanh toán khi nhận hàng');
// 			        	}
// 			        	
// 			        	$('#status').val(json[0].status);
// 			        	$('#date_start').val(json[0].date_start);
// 			        	$('#date_pay').val(json[0].date_pay);
// 			        	$('#date_delivery').val(json[0].date_delivery);
// 			        	$('#date_end').val(json[0].date_end);
// 			        },
// 			        error: function (e) {
// 			        	alert('er');
// 			        }
// 			    });
//            }
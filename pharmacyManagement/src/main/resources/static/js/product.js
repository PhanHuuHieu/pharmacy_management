//            $(document).ready(function () {
//            	getApi();
//					fire_ajax_submit();
//					$("#formProductSearch").submit(function (event) {
//				        event.preventDefault();
//				        fire_ajax_submit();
//				        refresh();
//				    });
//					$("img#picture").hide();
//					$("img#picture1").hide();
//					$("img#picture2").hide();
//					
//			});
//            function refresh()
//            {
//            	$('#formProduct').trigger("reset");
//            	$("#btnSb").prop( "disabled", true );
//            }
//            function validateProduct() {
//            	if($('#name').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Tên sản phẩm đang trống!");
//            	} else if($('#date_manufature').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Ngày sản xuất đang trống!");
//            	} else if($('#date_expirate').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Ngày hết hạn đang trống!");
//            	} else if($('#price_orginal').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Giá gốc đang trống!");
//            	} else if($('#price_sell').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Giá bán đang trống!");
//            	} else if($('#weight').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Khối lượng đang trống!");
//            	} else if($('#color').val() === ''){
//            		$("#btnSb").prop( "disabled", true );
//            		$("#error").text("Màu sắc đang trống!");
//            	} else {
//            		$("#btnSb").prop( "disabled", false );
//            		$("#error").text("");
//            	}
//            }
//            function x()
//            {
//            	var name = $('#picture2l').val()/*.substr(12,$('#picture2l').val().length);*/
//            	$.ajax({
//			        type: "GET",
//			        contentType: "application/json",
//			        url: /*window.location.pathname*/"/loadImage",
//			        data: {
//		                name: name
//		            },
//			        success: function (json) {
//			        },
//			        error: function (e) {
//			        	alert('er');
//			        }
//			    });
//            }
//            function check(obj)
//            {
//            	var html=obj.value;
//            	var select='<select class="form-control" name="month" id="month">';
//                if(html==="1")
//                {
//                	select+='<option value="-1">--Chọn theo đặc điểm--</option>';
//                	select+='<option value="1">01</option>';
//                	select+='<option value="2">02</option>';
//                	select+='<option value="3">03</option>';
//                	select+='<option value="4">04</option>';
//                	select+='<option value="5">05</option>';
//                	select+='<option value="6">06</option>';
//                	select+='<option value="7">07</option>';
//                	select+='<option value="8">08</option>';
//                	select+='<option value="9">09</option>';
//                	select+='<option value="10">10</option>';
//                	select+='<option value="11">11</option>';
//                	select+='<option value="12">12</option>';
//                }
//                else if(html==="2")
//                {
//                	select+='<option value="qu0">--Chọn theo đặc điểm--</option>';
//                	select+='<option value="qu1">Qúy 01 (1->3)</option>';
//                	select+='<option value="qu2">Qúy 02 (4->6)</option>';
//                	select+='<option value="qu3">Qúy 03 (7->9)</option>';
//                	select+='<option value="qu4">Qúy 04 (10->12)</option>';
//                }
//                else if(html==="3")
//                {
//                	select+='<option value="1000">--Chọn theo đặc điểm--</option>';
//                	select+='<option value="2016">2016</option>';
//                	select+='<option value="2017">2017</option>';
//                	select+='<option value="2018">2018</option>';
//                	select+='<option value="2019">2019</option>';
//                }
//                select+='</select>';
//                $('#displaySelect').html(select);
//            }
//            function deleteProduct(idProduct)
//            {
//	           	 $.ajax({
//				        type: "GET",
//				        contentType: "application/json",
//				        url: /*window.location.pathname*/"/deleteProduct",
//				        data: {
//			                idProduct: JSON.stringify(idProduct)
//			            },
//				        success: function (json) {
//				        	alert('Xóa dữ liệu thành công');
//				        	location.reload();
//				        },
//				        error: function (e) {
//				        	alert('er');
//				        }
//				    });
//            }
//            function getApi()
//            {
//            	var idProduct = [
//            		{"label" : "https:\\\\cdn.jiohealth.com\\pharmacy\\product\\P01510H\\P01510H_1_s.jpg" }, {"label" : "https:\\\\cdn.jiohealth.com\\pharmacy\\product\\P00886C\\P00886C_1_s.jpg" }, {"label" : "https:\\\\cdn.jiohealth.com\\pharmacy\\product\\P00885C\\P00885C_1_s.jpg" }, {"label" : "https:\\\\cdn.jiohealth.com\\pharmacy\\product\\P01265H\\P01265H_1_s.jpg" }, {"label" : "https:\\\\cdn.jiohealth.com\\pharmacy\\product\\P00616H\\P00616H_1_s.jpg" }, {"label" : "https:\\\\cdn.jiohealth.com\\pharmacy\\product\\P00252H\\P00252H_1_s.jpg" }, {"label" : "https:\\\\cdn.jiohealth.com\\pharmacy\\product\\P00460H\\P00460H_1_s.jpg" }, {"label" : "https:\\\\cdn.jiohealth.com\\pharmacy\\product\\P00156H\\P00156H_1_s.jpg" }
//            	];
//            	 $.ajax({
// 			        type: "GET",
// 			        contentType: "application/json",
// 			        url: /*window.location.pathname*/"/api",
// 			        data: {
// 		                idPr: JSON.stringify(idProduct)
// 		            },
// 			        success: function (json) {
// 			        },
// 			        error: function (e) {
// 			        	alert('er');
// 			        }
// 			    });
//            }
//            function initUpdate(idProduct)
//            {
//            	 $.ajax({
// 			        type: "GET",
// 			        contentType: "application/json",
// 			        url: /*window.location.pathname*/"/displayProduct",
// 			        data: {
// 		                idProduct: JSON.stringify(idProduct)
// 		            },
// 			        success: function (json) {
// 			        	$("img#picture").show();
// 						$("img#picture1").show();
// 						$("img#picture2").show();
// 						
// 			        	$('#name').val(json[0][0]);
// 			        	$('#price_orginal').val(json[0][1]);
// 			        	$('#price_sell').val(json[0][2]);
// 			        	$('#weight').val(json[0][3]);
// 			        	$('#color').val(json[0][4]);
// 			        	$('#date_manufature').val(json[0][5]);
// 			        	$('#date_expirate').val(json[0][6]);
// 			        	$('#fk_product_type_id').val(json[0][7]).change();
// 			        	$('#fk_product_group_id').val(json[0][8]).change();
// 			        	$('#fk_supplier_id').val(json[0][9]).change();
// 			        	$('#fk_unit_id').val(json[0][10]).change();
// 			        	$('#note').val(json[0][11]);
// 			        	$('#id').val(json[0][12]);
// 			        	$('img#picture').attr({
// 			               'src': json[0][13],
// 			               'alt': 'Học Web Chuẩn'
// 			            });
// 			        	$('img#picture1').attr({
//  			               'src': json[0][15],
//  			               'alt': 'Học Web Chuẩn'
//  			            });
// 			        	$('img#picture2').attr({
//  			               'src': json[0][16],
//  			               'alt': 'Học Web Chuẩn'
//  			            });
// 			        	//$('#picture')[0].files[0]=json[0][13];
//// 			        	const input = document.querySelector("input[type=file]");
//// 			        	input.value = "fooo.jpg";
// 			           //$('input:file').val("x.png");
// 			        	//$('#picture').val(json[0][13]).change();
// 			        	
// 			        },
// 			        error: function (e) {
// 			        	alert('er');
// 			        }
// 			    });
//            }
//			function fire_ajax_submit() {
////				var search = {}
////			    search["name"] = $("#nameSearch").val();
////				search["fk_product_group_id"] = $("#fk_product_group_id_search").val();
////				search["price_orginal"] = $("#price_orginal_search").val();
////				search["price_sell"] = $("#price_sell_search").val();
//				//var search = {}
//			    var nameSearch = $("#nameSearch").val();
//				var productGroup = $("#fk_product_group_id_search").val();
//				var price_orginal = $("#price_orginal_search").val();
//				var price_sell = $("#price_sell_search").val();
//			    $.ajax({
//			        type: "GET",
//			        contentType: "application/json",
//			        url: /*window.location.pathname*/"/getListProduct",
//			        data: {
//			        	nameSearch: nameSearch,
//			        	productGroup: productGroup,
//			        	price_orginal: price_orginal,
//			        	price_sell: price_sell
// 		            },
//			        //data:JSON.stringify(nameSearch),
//			        dataType: 'json',
//			        success: function (data) {
//			        	$("#tableProduct").dataTable({
//			        		"scrollX": 200,
//			        		"ordering": true,
//			        		"info":     false,
//			        		"bDestroy": true,
//			        		"bProcessing": true,
//			        		"aaData": data,
//			        		 "aoColumns": [
//			        	            {
//			                            "sName": "id",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[0];
//			                                return '<a href="'+html+'" data-toggle="modal" data-target="#addProductModal" onclick="initUpdate('+html+')">'+html+'</a>';
//			                            }
//			                        },
//			                        {
//			                            "sName": "name",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[1];
//			                            	return html;
//			                            }
//			                        },
//			                        {
//			                            "sName": "price_orginal",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[2];
//			                            	return html;
//			                            }
//			                        },
//			                        {
//			                            "sName": "price_sell",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[3];
//			                            	return html;
//			                            }
//			                        },
//			                        {
//			                            "sName": "weight",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[4];
//			                            	return html;
//			                            }
//			                        },
//			                        {
//			                            "sName": "color",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[5];
//			                            	return html;
//			                            }
//			                        },
//			                        {
//			                            "sName": "fk_product_type_id",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[6];
//			                            	return html;
//			                            }
//			                        },
//			                        {
//			                            "sName": "fk_product_type_id",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[7];
//			                            	return html;
//			                            }
//			                        },
//			                        {
//			                            "sName": "picture",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[8];
//			                            	return '<img id="imageProduct" src="'+html+'" />';
//			                            }
//			                        },
//			                        {
//			                            "sName": "",
//			                            "fnRender": function (obj)                              
//			                            {
//			                            	var html=obj.aData[9];
//			                            	return '<a onclick="deleteProduct('+html+')" th:href="#">Xóa</a>';
//			                            	//return '<button class="btn btn-primary" onclick="window.location.href=/deleteProduct?id={id}(id='+html+')">Xóa</button>'
//			                            }
//			                        }
//			        	        ]
//			        	});
//			        	$.fn.dataTable.ext.errMode = 'none';   
////			        	var table = document.getElementById('table_data');
////
////			        	var rowLength = table.rows.length;
////
////			        	for(var i=0; i<rowLength; i+=1){
////			        		$('#thu').remove(); 
////			        	}
////			        	 
////			        	for(var i=0;i<data.length;i++)
////			        	{
////			        		var html = '<tr id="thu">';
////			        		 html += '<th scope="row"><a href="'+data[i][0]+'" onclick="initUpdate('+data[i][0]+')" data-toggle="modal" data-target="#addProductModal">'+data[i][0]+'</a></th>';
////			        	     html += '<td>'+data[i][1]+'</td>';
////			        	     html += '<td>'+data[i][2]+'</td>';
////			        	     html += '<td>'+data[i][3]+'</td>';
////			        	     html += '<td>'+data[i][4]+'</td>';
////			        	     html += '<td>'+data[i][5]+'</td>';
////			        	     html += '<td>'+data[i][6]+'</td>';
////			        	     html += '<td>'+data[i][7]+'</td>';
////			        	     html += '<td><img id="imageProduct" src="'+data[i][8]+'" /></td></tr>';
////			        	     $('#table_data').prepend(html);
////			        	}
//			        },
//			        error: function (e) {
//			        	alert('er');
//			        }
//			    });
//			}
//			
            $(document).ready(function () {
            	loadSupplier();
					$("#formSupplierSearch").submit(function (event) {
				        event.preventDefault();
				        loadSupplier();
				        refresh();
				    });
			});
            function refresh()
            {
            	$('#formSupplier').trigger("reset");
            	$("#btnSb").prop( "disabled", true );
            }
            function validateSupplier() {
            	if($('#name').val() === ''){
            		$("#btnSb").prop( "disabled", true );
            		$("#error").text("Tên nhà cung cấp đang trống!");
            	} else if($('#phoneNumber').val() === ''){
            		$("#btnSb").prop( "disabled", true );
            		$("#error").text("Số điện thoại đang trống!");
            	} else if($('#address').val() === ''){
            		$("#btnSb").prop( "disabled", true );
            		$("#error").text("Địa chỉ nhà cung cấp đang trống!");
            	} else if($('#phoneNumber').val() != ''){
            		if($("#phoneNumber").val().length < 10 || $("#phoneNumber").val().length > 11)
     				{
             			$("#btnSb").prop( "disabled", true );
     					$("#error").text("Bạn nhập không đúng định dạng số điện thoại!");
     				}
             		else {
             			$("#btnSb").prop( "disabled", false );
                 		$("#error").text("");
             		}
            	} else {
            		$("#btnSb").prop( "disabled", false );
            		$("#error").text("");
            	}
            }
            function deleteSupplier(idSupplier)
            {
	           	 $.ajax({
				        type: "GET",
				        contentType: "application/json",
				        url: /*window.location.pathname*/"/deleteSupplier",
				        data: {
				        	idSupplier: JSON.stringify(idSupplier)
			            },
				        success: function (json) {
				        	alert('Xóa dữ liệu thành công');
				        	location.reload();
				        },
				        error: function (e) {
				        	alert('er');
				        }
				    });
            }
            function initUpdate(idSupplier)
            {
            	 $.ajax({
 			        type: "GET",
 			        contentType: "application/json",
 			        url: /*window.location.pathname*/"/displaySupplier",
 			        data: {
 			        	idSupplier: JSON.stringify(idSupplier)
 		            },
 			        success: function (json) {
 			        	$('#id').val(json[0][0]);
 			        	$('#name').val(json[0][1]);
 			        	$('#phoneNumber').val(json[0][2]);
 			        	$('#address').val(json[0][3]);
 			        },
 			        error: function (e) {
 			        	alert('er');
 			        }
 			    });
            }
			function loadSupplier() {
			    var idSearch = $("#idSearch").val();
				var nameSearch = $("#nameSearch").val();
				var phoneNumberSearch = $("#phoneNumberSearch").val();
			    $.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getListSupplier",
			        data: {
			        	idSearch: idSearch,
			        	nameSearch: nameSearch,
			        	phoneNumberSearch: phoneNumberSearch
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
			                                return '<a href="'+html+'" data-toggle="modal" data-target="#addProductModal" onclick="initUpdate('+html+')">'+html+'</a>';
			                            }
			                        },
			                        {
			                            "sName": "name",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[1];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "phoneNumber",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[2];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "address",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[3];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[4];
			                            	return '<a onclick="deleteSupplier('+html+')" th:href="#">Xóa</a>';
			                            	//return '<button class="btn btn-primary" onclick="window.location.href=/deleteProduct?id={id}(id='+html+')">Xóa</button>'
			                            }
			                        }
			        	        ]
			        	});
			        	$.fn.dataTable.ext.errMode = 'none'; 
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
			
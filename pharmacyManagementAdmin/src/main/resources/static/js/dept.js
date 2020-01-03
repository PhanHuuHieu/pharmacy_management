            $(document).ready(function () {
					fire_ajax_submit();
					$("#formDeptSearch").submit(function (event) {
				        event.preventDefault();
				        fire_ajax_submit();
				        refresh();
				    });
			});
            function refresh()
            {
            	$('#formDept').trigger("reset");
            	$("#btnSave").prop( "disabled", true );
            }
            function validateDept() {
            	if($('#dept_pay').val() === ''){
            		$("#btnSave").prop( "disabled", true );
            		$("#error").text("Số tiền nợ đang trống!");
            	} else if($('#dept_paied').val() === ''){
            		$("#btnSave").prop( "disabled", true );
            		$("#error").text("Số tiền đã trả đang trống!");
            	} else if($('#date_start').val() === ''){
            		$("#btnSave").prop( "disabled", true );
            		$("#error").text("Ngày bắt đầu đang trống!");
            	} else if($('#date_end').val() === ''){
            		$("#btnSave").prop( "disabled", true );
            		$("#error").text("Ngày kết thúc đang trống!");
            	} else if($('#limit').val() === ''){
            		$("#btnSave").prop( "disabled", true );
            		$("#error").text("Hạn mức đang trống!");
            	} else {
            		$("#btnSave").prop( "disabled", false );
            		$("#error").text("");
            	}
            }
            function deleteDept(id)
            {
	           	 $.ajax({
				        type: "GET",
				        contentType: "application/json",
				        url: /*window.location.pathname*/"/deleteDept",
				        data: {
			                id: JSON.stringify(id)
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
            function initUpdate(id)
            {
            	 $.ajax({
 			        type: "GET",
 			        contentType: "application/json",
 			        url: /*window.location.pathname*/"/getDept",
 			        data: {
 		                id: JSON.stringify(id)
 		            },
 			        success: function (json) {
 			        	$("#btnSave").attr("disabled", false);
 			        	$("#messageError").text('');
 			        	$('#id_dept').val(json[0][0]);
 			        	$('#name_supplier').val(json[0][1]);
 			        	$('#dept_pay').val(json[0][2]);
 			        	$('#dept_paied').val(json[0][3]);
 			        	$('#date_start').val(json[0][4]);
 			        	$('#date_end').val(json[0][5]);
 			        	$('#limit').val(json[0][6]);
 			        	//$('#over_limit').attr('checked');
 			        	if(json[0][7]==='1')
 			        	{
 			        		document.getElementById("over_limit").checked = true;
 			        	}
 			        	else
 			        	{
 			        		document.getElementById("over_limit").checked = false;
 			        	}
 			        	document.getElementById("over_limit").value = json[0][7];
 			        	//$('#over_limit').val(json[0][7]);
 			        	$('#term').val(json[0][8]);
 			        	$('#note').val(json[0][9]);
 			        	
 			        },
 			        error: function (e) {
 			        	alert('er');
 			        }
 			    });
            }
            function check()
            {
            	var money_own = parseInt($('#dept_pay').val(), 10);
            	var limit = parseInt($('#limit').val(), 10);
            	if(money_own > limit)
            	{
            		$("#btnSave").attr("disabled", true);
            		$("#messageError").text('Số nợ vượt quá hạn mức cho phép!');
            	}
            	else
            	{
            		$("#btnSave").attr("disabled", false);
            		$("#messageError").text('');
            	}
            }
            function checkOwn()
            {
            	var money_own = $('#dept_pay').val();
            	var limit = $('#limit').val();
            	if(money_own > limit)
            	{
            		$("#btnSave").attr("disabled", true);
            		$("#messageError").text('Số nợ vượt quá hạn mức cho phép!');
            	}
            	else
            	{
            		$("#btnSave").attr("disabled", false);
            		$("#messageError").text('');
            	}
            }
			function fire_ajax_submit() {
//				var search = {}
//			    search["name_supplier_dept"] = $("#name_supplier_dept").val();
//				search["date_start_dept"] = $("#date_start_dept").val();
//				search["date_end_dept"] = $("#date_end_dept").val();
				var nameSupplier = $("#name_supplier_dept").val();
				var dateStart = $("#date_start_dept").val();
				var dateEnd = $("#date_end_dept").val();
			    $.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getListDept",
			        data: {
			        	nameSupplier:nameSupplier,
			        	dateStart:dateStart,
			        	dateEnd:dateEnd
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
			                            	var today = new Date();
			                            	var dd = String(today.getDate()).padStart(2, '0');
			                            	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
			                            	var yyyy = today.getFullYear();
			                            	today = yyyy + '-' + mm + '-' + dd;
			                            	var html=obj.aData[0];
			                            	if(obj.aData[5]===today)
			                            	{
			                            		alert('Đơn nợ: '+html+', NCC: '+obj.aData[1]+' Đến hạn thanh toán.')
				                            	return '<a style="color:red; font-weight: bold;" href="'+html+'" data-toggle="modal" data-target="#addProductModal" onclick="initUpdate('+html+')">'+html+'</a>';
			                            		
			                            	}
			                            	return '<a href="'+html+'" data-toggle="modal" data-target="#addProductModal" onclick="initUpdate('+html+')">'+html+'</a>';
			                            	
			                            }
			                        },
			        	            {
			                            "sName": "supplier_name",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[1];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "money_own",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[2];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "money_paided",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[3];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "date_start",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[4];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "date_end",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[5];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "limit",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[6];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "over_limit",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[7];
			                            	if(html==='1')
			                            	{
			                            		return '<input type="checkbox" value="'+html+'" checked>';
			                            	}
			                            	return '<input type="checkbox" value="'+html+'" >';
			                            }
			                        },
			                        {
			                            "sName": "a",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[8];
			                            	return '<a onclick="deleteDept('+html+')" th:href="#">Xóa</a>';
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
			
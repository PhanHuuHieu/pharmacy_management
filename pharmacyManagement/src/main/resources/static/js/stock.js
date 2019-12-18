            $(document).ready(function () {
					fire_ajax_submit();
					$("#formStockSearch").submit(function (event) {
				        event.preventDefault();
				        fire_ajax_submit();
				        refresh();
				    });
			});
            function refresh()
            {
            	$('#formStock').trigger("reset");
            }
            function deleteProduct(id)
            {
	           	 $.ajax({
				        type: "GET",
				        contentType: "application/json",
				        url: /*window.location.pathname*/"/deleteStock",
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
 			        url: /*window.location.pathname*/"/getStock",
 			        data: {
 		                id: JSON.stringify(id)
 		            },
 			        success: function (json) {
 			        	$('#type_form').val(json[0][0]);
 			        	$('#datePost').val(json[0][1]);
 			        	$('#stock_name').val(json[0][2]);
 			        	$('#type').val(json[0][3]);
 			        	$('#fk_product_id').val(json[0][4]);
 			        	$('#fk_employee_id').val(json[0][5]);
 			        	$('#statusx').val(json[0][6]);
 			        	$('#note').val(json[0][7]);
 			        	$('#idStock').val(json[0][8]);
 			        },
 			        error: function (e) {
 			        	alert('er');
 			        }
 			    });
            }
			function fire_ajax_submit() {
				var search = {}
			    search["id"] = $("#id").val();
				search["date"] = $("#date").val();
				search["status"] = $("#status").val();
				
			    $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getListStock",
			        data: JSON.stringify(search),
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
			                            "sName": "type_form",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[0];
			                            	if(html==='1')
			                            	{
			                            		return '<div style="color:red;">Nhập</div>';
			                            	} 
			                            	return '<div style="color:red;">Xuất</div>';
			                            }
			                        },
			                        {
			                            "sName": "date",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[1];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "id",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[2];
			                            	return '<a href="'+html+'" data-toggle="modal" data-target="#addProductModal" onclick="initUpdate('+html+')">'+html+'</a>';
			                            }
			                        },
			                        {
			                            "sName": "stock_name",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[3];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "type",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[4];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "fk_order_id",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[5];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "status",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[6];
			                            	if(html==='1')
			                            	{
			                            		return '<div style="color:green;">Mới</div>';
			                            	} else if(html==='2')
			                            	{
			                            		return '<div style="color:green;">Đang xử lý</div>';
			                            	} else if(html==='3')
			                            	{
			                            		return '<div style="color:green;">Hoàn thành</div>';
			                            	} else if(html==='4')
			                            	{
			                            		return '<div style="color:green;">Hủy bỏ</div>';
			                            	} 
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[7];
			                            	return '<a onclick="deleteProduct('+html+')" th:href="#">Xóa</a>';
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
			
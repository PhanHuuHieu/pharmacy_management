            $(document).ready(function () {
					fire_ajax_submit();
					$("#formOrderSearch").submit(function (event) {
				        event.preventDefault();
				        fire_ajax_submit();
				    });
			});
			function fire_ajax_submit() {
				var search = {}
			    search["idEmployee"] = $("#fk_employee_id").val();
				search["idCustomer"] = $("#fk_customer_id").val();
				search["dateOrder"] = $("#dateOrder").val();
				
			    $.ajax({
			        type: "POST",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getListOrder",
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
			                            "sName": "id",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[0];
			                                return html;
			                            }
			                        },
			                        {
			                            "sName": "nameCustomer",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[1];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "nameEmployee",
			                            "fnRender": function (obj)                              
			                            {
			                            	var html=obj.aData[2];
			                            	return html;
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
			        	        ]
			        	});
//			        	var table = document.getElementById('table_data');
//
//			        	var rowLength = table.rows.length;
//
//			        	for(var i=0; i<rowLength; i+=1){
//			        		$('#thu').remove(); 
//			        	}
//			        	 
//			        	for(var i=0;i<data.length;i++)
//			        	{
//			        		var html = '<tr id="thu">';
//			        		 html += '<th scope="row"><a href="'+data[i][0]+'" onclick="initUpdate('+data[i][0]+')" data-toggle="modal" data-target="#addProductModal">'+data[i][0]+'</a></th>';
//			        	     html += '<td>'+data[i][1]+'</td>';
//			        	     html += '<td>'+data[i][2]+'</td>';
//			        	     html += '<td>'+data[i][3]+'</td>';
//			        	     html += '<td>'+data[i][4]+'</td>';
//			        	     html += '<td>'+data[i][5]+'</td>';
//			        	     html += '<td>'+data[i][6]+'</td>';
//			        	     html += '<td>'+data[i][7]+'</td>';
//			        	     html += '<td><img id="imageProduct" src="'+data[i][8]+'" /></td></tr>';
//			        	     $('#table_data').prepend(html);
//			        	}
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
			
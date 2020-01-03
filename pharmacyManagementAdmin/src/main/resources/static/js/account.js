            $(document).ready(function () {
					fire_ajax_submit();
					$("#formAccountSearch").submit(function (event) {
				        event.preventDefault();
				        fire_ajax_submit();
				    });
			});
            function blockAccount(idAccount) {
            	//var check = $('#checkBlock').val();
            	var checkBlock = document.getElementById('checkBlock'+idAccount);
            	var check = '';
            	if(checkBlock.checked===true)
            	{
            		check = '0';
            	}
            	else {
            		check = '1';
            	}
            	$.ajax({
 			        type: "GET",
 			        contentType: "application/json",
 			        url: "/blockAccount",
 			        data: {
 			        	idAccount: JSON.stringify(idAccount),
 			        	check: check  
 		            },
 			        success: function (json) {
 			        	location.reload();
 			        },
 			        error: function (e) {
 			        	alert('er');
 			        }
 			    });
            }
            function deleteAccount(idAccount) {
            	$.ajax({
 			        type: "GET",
 			        contentType: "application/json",
 			        url: "/deleteAccount",
 			        data: {
 			        	idAccount: JSON.stringify(idAccount)
 		            },
 			        success: function (json) {
 			        	location.reload();
 			        },
 			        error: function (e) {
 			        	alert('er');
 			        }
 			    });
            }
			function fire_ajax_submit() {
				var username = $("#usernameSearch").val();
				var email = $("#emailSearch").val();
				var phone_number = $("#phoneNumberSearch").val();
				
			    $.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getListAccount",
			        data: {
			        	username : username,
			        	email : email,
			        	phone_number : phone_number
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
			                            	//var html=obj.aData.id;
			                            	var html=obj.aData[0];
			                                return html;
			                            }
			                        },
			                        {
			                            "sName": "username",
			                            "fnRender": function (obj)                              
			                            {
			                            	//var html=obj.aData.username;
			                            	var html=obj.aData[1];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "email",
			                            "fnRender": function (obj)                              
			                            {
			                            	//var html=obj.aData.email;
			                            	var html=obj.aData[2];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "phone_number",
			                            "fnRender": function (obj)                              
			                            {
			                            	//var html=obj.aData.phoneNumber;
			                            	var html=obj.aData[3];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "create_date",
			                            "fnRender": function (obj)                              
			                            {
			                            	//var html=obj.aData.createDate;
			                            	var html=obj.aData[4];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "point",
			                            "fnRender": function (obj)                              
			                            {
			                            	//var html=obj.aData.createDate;
			                            	var html=obj.aData[5];
			                            	return html;
			                            }
			                        },
			                        {
			                            "sName": "status",
			                            "fnRender": function (obj)                              
			                            {
			                            	//var html=obj.aData.status;
			                            	var html=obj.aData[6];
			                            	if(html==='0')
			                            	{
			                            		return '<i style="color:red;">Khóa</i>'
			                            	}
			                            	return '<i style="color:green;">Hoạt động</i>';
			                            }
			                        },
			                        {
			                            "sName": "id1",
			                            "fnRender": function (obj)                              
			                            {
			                            	//var html=obj.aData.id1;
			                            	var html=obj.aData[7];
			                            	if(html==='0')
			                            	{
			                            		return '<input type="checkbox" id="checkBlock'+obj.aData[8]+'" checked onchange="blockAccount('+obj.aData[8]+')" >';
			                            	}
			                            	return '<input type="checkbox" id="checkBlock'+obj.aData[8]+'" onchange="blockAccount('+obj.aData[8]+')" >';
			                            	//return '<button class="btn btn-primary" onclick="window.location.href=/deleteProduct?id={id}(id='+html+')">Xóa</button>'
			                            }
			                        },
			                        {
			                            "sName": "id2",
			                            "fnRender": function (obj)                              
			                            {
			                            	//var html=obj.aData.id2;
			                            	var html=obj.aData[8];
			                            	return '<a onclick="deleteAccount('+html+')" href="#">Xóa</a>';
			                            	//return '<button class="btn btn-primary" onclick="window.location.href=/deleteProduct?id={id}(id='+html+')">Xóa</button>'
			                            }
			                        }
			        	        ]
			        	});
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
			
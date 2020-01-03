            $(document).ready(function () {
            	$("#btnSubmit").prop( "disabled", true );
			});
           
			function validateRegister() {
				var password = $("#password").val();
				var re_password = $("#re_password").val();
				var phone_number = $("#phone_number").val();
				var email = $("#email").val(); 
				var user_name = $("#user_name").val(); 
				if(user_name === '') {
					$("#error").text("Bạn chưa nhập tên đăng nhập!");
					$("#btnSubmit").prop( "disabled", true );
				} else if(email === '') {
					$("#error").text("Bạn chưa nhập nhập email!");
					$("#btnSubmit").prop( "disabled", true );
				} else if(phone_number === '') {
					$("#error").text("Bạn chưa nhập số điện thoại!");
					$("#btnSubmit").prop( "disabled", true );
				} else if(password === '') {
					$("#error").text("Bạn chưa nhập mật khẩu!");
					$("#btnSubmit").prop( "disabled", true );
				} else if(password != re_password) {
					$("#error").text("Mật khẩu nhập lại không đúng!");
					$("#btnSubmit").prop( "disabled", true );
				} else {
					$("#error").text("");
					$("#btnSubmit").prop( "disabled", false );
				}
				
				if(user_name != '') {
					var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; 
				    if (!filter.test(email)) { 
				    	$("#error").text("Bạn nhập không đúng định dạng email!");
				    	$("#btnSubmit").prop( "disabled", true );
				    }
				}
			    if(password.length < 9 && phone_number != '')
				{
					$("#error").text("Password cần lớn hơn 8 kí tự!");
					$("#btnSubmit").prop( "disabled", true );
				}
			    if(email != '') {
			    	 if($("#phone_number").val().length < 10 || $("#phone_number").val().length > 11)
						{
							$("#error").text("Bạn nhập không đúng định dạng số điện thoại!");
							$("#btnSubmit").prop( "disabled", true );
						}
			    }
			   
			}
			function validateEmail() {
				 var email = $("#email").val(); 
				    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; 
				    if (!filter.test(email)) { 
				    	$("#error").text("Bạn nhập không đúng định dạng email!");
				    }
				    else{ 
				    	$("#error").text("");
				    } 
			}
			function validatePassword() {
				var password = $("#password").val();
				var re_password = $("#re_password").val();
				if(password.length < 9)
				{
					$("#error").text("Password cần lớn hơn 8 kí tự!");
				}
				else
				{
					$("#error").text("");
				}
			}
			function validateRePassword() {
				var password = $("#password").val();
				var re_password = $("#re_password").val();
				if(password != re_password)
				{
					$("#error").text("Mật khẩu nhập lại không đúng!");
				}
				else
				{
					$("#error").text("");
					$("#btnSubmit").prop( "disabled", false );
				}
			}
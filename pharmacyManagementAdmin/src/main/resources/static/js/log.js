            $(document).ready(function () {
					fire_ajax_submit();
					$("#formLogSearch").submit(function (event) {
				        event.preventDefault();
				        fire_ajax_submit();
				    });
			});
			function fire_ajax_submit() {
			    var idEmployee = -1;
				var dateTime = '';
			    $.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: /*window.location.pathname*/"/getDataLog",
			        data: {
			        	idEmployee: idEmployee,
			        	dateTime: dateTime
 		            },
			        dataType: 'json',
			        success: function (data) {
			        	var hi = '';
			        	for(var i=0;i<data.length;i++)
				        {
			        		hi+='<div class="inbox-row widget-shadow" id="accordion" role="tablist" aria-multiselectable="true">';
			        		hi+='<div class="mail"><img src="/images/2.jpg" alt=""/></div>';
			        		hi+='<div class="mail mail-name"> <h6>'+data[i].name+'</h6></div>';
			        		hi+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">';
			        		hi+='<div class="mail"><p>'+data[i].content+'</p></div>';
			        		hi+='</a>';
			        		hi+='<div class="mail-right"><p>'+data[i].dateTime+'</p></div>';
			        		hi+='<div class="clearfix"> </div>';	
			        		hi+='</div>';
			        	}
			        	$('#log').html(hi);
			        },
			        error: function (e) {
			        	alert('er');
			        }
			    });
			}
			
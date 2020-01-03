            $(document).ready(function () {
            	
			});
            function check(obj)
            {
            	var html=obj.value;
            	var select='<select class="form-control" name="month" id="month">';
                if(html==="1")
                {
                	select+='<option value="-1">--Chọn theo đặc điểm--</option>';
                	select+='<option value="1">01</option>';
                	select+='<option value="2">02</option>';
                	select+='<option value="3">03</option>';
                	select+='<option value="4">04</option>';
                	select+='<option value="5">05</option>';
                	select+='<option value="6">06</option>';
                	select+='<option value="7">07</option>';
                	select+='<option value="8">08</option>';
                	select+='<option value="9">09</option>';
                	select+='<option value="10">10</option>';
                	select+='<option value="11">11</option>';
                	select+='<option value="12">12</option>';
                }
                else if(html==="2")
                {
                	select+='<option value="qu0">--Chọn theo đặc điểm--</option>';
                	select+='<option value="qu1">Qúy 01 (1->3)</option>';
                	select+='<option value="qu2">Qúy 02 (4->6)</option>';
                	select+='<option value="qu3">Qúy 03 (7->9)</option>';
                	select+='<option value="qu4">Qúy 04 (10->12)</option>';
                }
                else if(html==="3")
                {
                	select+='<option value="1000">--Chọn theo đặc điểm--</option>';
                	select+='<option value="2016">2016</option>';
                	select+='<option value="2017">2017</option>';
                	select+='<option value="2018">2018</option>';
                	select+='<option value="2019">2019</option>';
                	select+='<option value="2019">2020</option>';
                }
                select+='</select>';
                $('#displaySelect').html(select);
            }
	$.validator.setDefaults({
	    submitHandler: function(form) {
	    	form.submit();
	    }
	});
	$().ready(function() {
	    $("#commentForm").validate();
	    $("#shopPrice,#discount").change(function(){
	        var before = $("#shopPrice").val();
	        var discount = $("#discount").val();
	        if(before.trim() != "" && discount.trim() !=""){
	           if(!isNaN(before)){
	             var after = before / discount *10;
	             $("#marketPrice").val(after.toFixed(2));
	           }
	        }
	      });

      $("#shopPrice").blur(function(){
	         var a = new Number($(this).val());
	         $(this).val(a.toFixed(2));
	   });
  		$("#goodsName").change(function(){
  			var url ="validateGoodsLimitName.do";
  			var name =$(this).val();
  			var id = $("#id").val();
  			$.ajax({
  				url:url,
  				data:{goodsName : name,id : id},
  				type:"post",
  				dateType:"json",
  				success:function(data){
  					data=eval("("+data+")");
  					if(data.msg=="yes"){
  						$("#errorMessage").empty();
  						$("#errorMessage").append("<font style='color:green'>ok</font>");
  					}
  					if(data.msg=="no"){
  						$("#errorMessage").empty();;
  						$("#errorMessage").append("<font style='color:red'>Not ok</font>");
  						$("#goodsName").val(name);
  					}
  				}
  			});
  		});
	});
	
	
    j = 1; 
    i = 1;
    $(document).ready(function(){  
        $("#btn_add1").click(function(){  
            document.getElementById("newUpload1").innerHTML+='<div id="div_'+j
            +'"><input  name="carousel_'+
            j+'" type="file" required /><input type="button" value="删除"  onclick="del1('+
            		j+')"/></div>';  
              j = j + 1;  
        });  
        $("#btn_add2").click(function(){  
        	document.getElementById("newUpload2").innerHTML+='<div id="div_'+i
        	+'"><input  name="detail_'+
        	i+'" type="file" required /><input type="button" value="删除"  onclick="del2('+
        	i+')"/></div>';  
        	i = i + 1;  
        });  
    });  
  
    function del1(o){  
         document.getElementById("newUpload1").removeChild(document.getElementById("div_"+o));  
    }  
    function del2(o){  
    	document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));  
    }  

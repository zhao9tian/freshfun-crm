$(function(){
	$("#query").click(function(){
		$("#queryForm").append('<input type="hidden" name="currentPage" value="'+1+'"/>');
		$("#queryForm").submit();
	});
});
$.validator.setDefaults({
    submitHandler: function(form) {
    	form.submit();
    }
});
$().ready(function() {
    $("#queryForm").validate();
});
function gotoPage(currentPage){  
	$("#queryForm").append('<input type="hidden" name="currentPage" value="'+currentPage+'"/>');
	$("#queryForm").submit();  
};
	


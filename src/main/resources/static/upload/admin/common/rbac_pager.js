function toPage(type){
	var currentPage = $("#currentPage").val();
	var totalPage = $("#totalPage").val();
	switch(type){
		case "home":
			$("#currentPage").val("1");
			break;
		case "up":
			if(parseInt(currentPage)==1){
				alert("没有上一页");
				return false;
			}
			
			$("#currentPage").val(parseInt(currentPage)-1);
			break;
		case "down":
			if(parseInt(currentPage)==parseInt(totalPage)){
				alert("没有下一页");
				return false;
			}
			$("#currentPage").val(parseInt(currentPage)+1);
			break;
		case "end":
			$("#currentPage").val(totalPage);
			break;
		default:
			$("#currentPage").val(type);
			break;
	}
	$("#searchForm").submit();
}

function toDelete(basic_id){
    if(window.confirm("您确定要删除吗？该操作会级联删除整条数据！")){
        $.post("sys/basicinfo/delete/"+basic_id,{_method:'delete'},function(data){
            if(data.flag=='success'){
                alert("删除成功！");
                window.location.href = window.location.href;
            }else{
                alert("删除失败！");
            }
        },"json");
    }
}
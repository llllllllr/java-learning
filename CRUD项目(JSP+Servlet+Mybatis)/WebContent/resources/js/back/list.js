/**
 * 调用后台批量删除方法
 * 
 * $id选择器
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("action",basePath + "/Auto/DeleteBenchServlet");
	$("#mainForm").submit();
}


function insertOne(basePath){
	$("#mainForm").attr("action",basePath + "/Auto/InsertOServlet");
	$("#mainForm").submit();
}

function updateCommand(basePath){
	
	$("#mainForm").attr("action",basePath+"/Auto/UpdateJServlet");
	$("#mainForm").submit();
}
/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}
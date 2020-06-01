//绑定表单提交事件 新增招聘信息
function addcompany(all) {
    $.ajax({
        type:"POST",
        url:"/Position/insertPosition",
        data:all,
        success: function(result) {
            console.log(result.resultCode);
            if (result.resultCode == 0) {
                alert(result.message);
                window.location.href="recruitment.html";
            }else if (result.resultCode == -1){
                alert(result.message);
            }
        }
    });
}

$(".addPosition").click(function () {
    $("#userId").val(sessionStorage.getItem("userId"));
    $("#company_id").val(sessionStorage.getItem("company_id"));
    var all=$(".cen").serializeArray();
    selectpayment(all);
    return false;
});


/*支付 查询余额是否足*/
function selectpayment(all) {
    $.ajax({
        type:"POST",
        url:"/Position/selectpayment",
        data:all,
        success: function(result) {
            console.log(result.resultCode);
            if (result.resultCode == 0) {
               f();
            }else if (result.resultCode == -1){
                alert(result.message);
            }
        }
    });
}
function f() {
    $("#userId").val(sessionStorage.getItem("userId"));
    $("#company_id").val(sessionStorage.getItem("company_id"));
    var all=$(".cen").serializeArray();
    addcompany(all);
}
//显示公司名字
function showcompany() {
    console.log(sessionStorage.getItem("company_name"));
    $("#company_name").val(sessionStorage.getItem("company_name"));
}
showcompany()
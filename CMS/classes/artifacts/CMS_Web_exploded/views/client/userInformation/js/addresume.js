//绑定表单提交事件 添加简历
function addresume(all) {
    console.log(all);
    $.ajax({
        type:"POST",
        url:"/information/insertInformation",
        data:all,
        "success": function(result) {
            console.log(result.resultCode);
            if (result.resultCode == 0) {
                alert(result.message);
                window.location.href="resume.html";
            } else if (result.resultCode == -1) {
                alert(result.message);
            }
        }
    });
}
//点击事件
$(".addcompany").click(function () {
    $("#userId").val(sessionStorage.getItem("userId"));
    var all=$("#myform").serializeArray();
    addresume(all);

    return false;
});


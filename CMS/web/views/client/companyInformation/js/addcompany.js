//绑定表单提交事件 新增公司
$(".addcompany").click(function () {
    var img=document.getElementById("file").value;
    if(img!=null){
        // 创建表单数据对象
        var obj = new FormData();
        // 获取文件框中的数据
        var file= document.getElementById("file").files[0];
        // 将文件数据添加至表单数据对象中
        obj.append("file", file);
        console.log("请求参数---》》》" + obj);
        upload1(obj);
    }
    $("#userId").val(sessionStorage.getItem("userId"));
    var all=$(".cen").serializeArray();
    addcompany(all);
    return false;
});
function  upload1(obj) {
    $.ajax({
        type:"POST",
        url:"/Company/upload",
        data:obj,
        contentType : false,
        processData : false,
        async:false,
        mimeType : 'multipart/form-data',
        success: function(result) {
            alert("头像上传成功");
        }
    });
}
function addcompany(all) {
    console.log(all);
    $.ajax({
        type:"POST",
        url:"/Company/insertCompany",
        data:all,
        success: function(result) {
            console.log(result.resultCode);
            if (result.resultCode == 0) {
                alert(result.message);
                window.location.href="companyPersonal.html";
            } else if (result.resultCode == -1) {
                alert(result.message);
            }
        }
    });
}
/*$(".up").click(function () {
    // 创建表单数据对象
    var obj = new FormData();
    // 获取文件框中的数据
    var file= document.getElementById("file").files[0];
    // 将文件数据添加至表单数据对象中
    obj.append("file", file);
    console.log("请求参数---》》》" + obj);
    upload1(obj);
    return false;
});*/




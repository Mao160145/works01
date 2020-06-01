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

//修改公司信息（显示）
function companyInfo() {
    var user_id=sessionStorage.getItem("userId");
    find_company(user_id);
}
function find_company(user_id) {
    $.ajax({
        type: "get",
        url: "/Company/selectCompany?user_id="+user_id,
        async: true,
        "success": function (result) {
            if (result.resultCode == 0) {
                sessionStorage.setItem("Companyid",result.data[0].company_id)
                $("#company_name").val(result.data[0].company_name);
                console.log(result.data[0].company_name);
                $("#company_alias").val(result.data[0].company_alias);
                console.log(result.data[0].company_alias);
                $("#company_address").val(result.data[0].company_address);
                var Type = ["input[title='个人独资']", "input[title='合伙公司']", "input[title='有限责任公司']", "input[title='股份制公司']", "input[title='集团公司']", "input[title='个人制公司']","input[title='其他']", ];
                $(Type[result.data[0].companyType_id]).prop("selected", true);
                $("#companyType_id").val(result.data[0].companyType_id);
                $("#industry").val(result.data[0].industry);
                $("#company_scale").val(result.data[0].company_scale);
                $("#company_introduce").val(result.data[0].company_introduce);
            }
        }
    });
}
 //修改公司信息
$(".updateCompanyid").click(function () {
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
    $("#Companyid").val(sessionStorage.getItem("Companyid"));
    //获取form表单的所有值serializeArray()
    var all=$("#cen").serializeArray();
    registered(all);
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
//修改公司信息
function registered(all) {
    console.log(all);
    $.ajax({
        type:"POST",
        url:"/Company/updateCompany",
        data:all,
        success: function(result) {
            console.log(result.resultCode);
            if(result.resultCode==0){
                alert(result.message);
                parent.location.reload();
            }else if(result.resultCode==-1){
                alert(result.message);
            }else if(result.resultCode==-2){
                alert(result.message);
            }
        }
    });
}

companyInfo();

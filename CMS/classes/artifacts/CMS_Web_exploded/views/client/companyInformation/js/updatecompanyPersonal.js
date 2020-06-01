//修改头像
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
function  upload1(obj) {
    $.ajax({
        type:"POST",
        url:"/index/upload",
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


//根据id查找登录公司账号信息
$(function() {
    function find_currentUser_info(user_id) {
        $.ajax({
            type: "get",
            url: "/index/selectUserAll?user_id="+user_id,
            async: true,
            "success": function(result) {
                if(result.resultCode == 0) {
                    sessionStorage.setItem("version",result.data[0].version);
                    build_currentUser_info(result);
                }
            }
        });
    }

    //显示公司个人信息
    function build_currentUser_info(result) {
        var data = result.data;
        $("input[name='user_name']").val(result.data[0].user_name);
        $("input[name='user_account']").val(result.data[0].user_account);
        $("input[name='login_password']").val(result.data[0].login_password);
        $("input[name='user_phone']").val(result.data[0].user_phone);
        $("input[name='user_age']").val(result.data[0].user_age);
        var sex = ["input[title='保密']", "input[title='男']", "input[title='女']"];
        $(sex[result.data[0].user_sex]).prop("checked", true);
        $("input[name='user_email']").val(result.data[0].user_email);
    }
    function fimd() {
        var user_id=sessionStorage.getItem("userId");
        find_currentUser_info(user_id);
    }
    fimd();

    $(".updateuser").click(function () {
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
        $("#version").val(sessionStorage.getItem("version"));
        var all=$("#cen").serializeArray();
        registered(all);
        return false;
    });

//绑定表单提交事件 修改公司个人信息
    function registered(all) {
        console.log(all);
        $.ajax({
            type:"POST",
            url:"/index/updateUser",
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
});

$(function () {
    var edit=document.getElementById("edit");
    var user_id1=sessionStorage.getItem("userId");
    var Information_id;
//判断用户是否登录


function tx(Information_id) {
    //查询登陆用户的信息将头像 用户名显示在导航栏
    var user_id=sessionStorage.getItem("userId");
    $.ajax({
        type: "get",
        url: "/index/selectUserAll?user_id="+Information_id,
        async: true,
        "success": function (result) {
            if (result.resultCode ==0){
                $(".userName").text(result.data[0].user_name);
                if(result.data[0].user_url!=null){
                    $(".headImg").attr("src", result.data[0].user_url);
                }else{
                    $(".headImg").attr("src", "/images/tx.jpg");
                }
            }
        }
    });
}

/*将时间搓传位日期格式*/
 function changeDateFormat(cellval) {
    var dateVal = cellval + "";
    if (cellval != null) {
        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        /* var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
         var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
         var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();*/
        return date.getFullYear() + "-" + month + "-" + currentDate;
    }
}
//显示简历信息

function find_resumeInfo(user_id) {
    $.ajax({
        type: "get",
        url: "/information/selectInformation?user_id="+user_id,
        async: true,
        "success": function (result) {
            if (result.resultCode == 0) {
                Information_id=result.data[0].user_id;
                $("#name").text(result.data[0].name);
                $("#expected_career").text(result.data[0].expected_career);
                $("#education").text(result.data[0].education);
                var sex = ["保密", "男", "女"];
                $("#sex").text(sex[result.data[0].sex]);
                $("#date_birth").text(changeDateFormat(result.data[0].date_birth));
                var marital_status = ["未婚", "已婚", "离婚"];
                $("#marital_status").text(marital_status[result.data[0].marital_status]);
                $("#Certificate_picture").text(result.data[0].Certificate_picture);
                $("#brief_introduction").text(result.data[0].brief_introduction);
                var phone = "";
                if (result.data[0].phone != null) {
                    phone = result.data[0].phone;
                }
                $("#phone").text(phone);
            //    email
                var emil = "";
                if (result.data[0].emil != null) {
                    emil = result.data[0].emil;
                }
                $("#emil").text(emil);
            }
            if (Information_id==user_id1){
                edit.style.display = "block";
            }else {
                edit.style.display = "none";
            }
            tx(Information_id);
        }

    });
}

function resumeInfo() {
    var user_id=sessionStorage.getItem("userId");
    find_resumeInfo(user_id);
}
        resumeInfo() ;


$(function() {
            layui.use(['form', 'laydate', 'layer', 'util'], function () {
                var form = layui.form;
                var laydate = layui.laydate;
                var layer = layui.layer;
                var util = layui.util;
                form.render();
                //重新加载date空间(出生日期)
                BaseUtil.ajaxSetup();
                function resumeInfo2(user_id) {
            find_resumeInfo(user_id);
        }
        var user_id = BaseUtil.GetQueryString("user_id");
        resumeInfo2(user_id) ;
    });
});
});
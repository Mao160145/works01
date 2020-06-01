//显示用户个人信息
function find_userInfo(user_id) {
    $.ajax({
        type: "get",
        url: "/index/selectUserAll?user_id="+user_id,
        async: true,
        "success": function (result) {
            if (result.resultCode == 0) {
                $("#user_name").text(result.data[0].user_name);
                $("#user_account").text(result.data[0].user_account);
                $("#login_password").text(result.data[0].login_password);
                var user_phone = "";
                if (result.data[0].user_phone != null) {
                    user_phone = result.data[0].user_phone;
                }
                if(result.data[0].user_url!=null){
                    $(".headImg").attr("src", result.data[0].user_url);
                }else{
                    $(".headImg").attr("src", "/images/tx.jpg");
                }
                $("#user_phone").text(user_phone);
                $("#user_age").text(result.data[0].user_age);
                var user_sex = ["保密", "男", "女"];
                $("#user_sex").text(user_sex[result.data[0].user_sex]);
            }
        }
    });
}
   function userInfo() {
   var user_id=sessionStorage.getItem("userId");
      find_userInfo(user_id);
}
    userInfo() ;

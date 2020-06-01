/*用户登录*/
 function login(account,passWord) {
        $.ajax({
            url:"/index/login",
            data:{
                "user_account":account,
                 "login_password":passWord
            },
            dataType:"json",
            type:"post",
            success:function(result){
                if(result.resultCode==0){
                    sessionStorage.setItem("userId",result.data.user_id);
                    sessionStorage.setItem("userName",result.data.user_name);
                    sessionStorage.setItem("headImg",result.data.user_url);
                    sessionStorage.setItem("userstyle",result.data.style);
                    window.location.href="/views/client/index.html";
                }else if(result.resultCode==-1){
                    alert(result.message);
                }else if(result.resultCode==-2){
                    alert(result.message);
                }
            }
        })
    }
    $(".login").click(function () {
        var account=$("#account").val();
        var passWord=$("#passWord").val();
        login(account,passWord);
        return false;
    });

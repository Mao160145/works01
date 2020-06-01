
function updateName() {
    //查询登陆用户的信息将头像 用户名显示在导航栏
    var user_id=sessionStorage.getItem("userId");
    $.ajax({
        type: "get",
        url: "/index/selectUserAll?user_id="+user_id,
        async: true,
        "success": function (result) {
            if (result.resultCode ==0){
                $(".userName").text(result.data[0].user_name);
                  if(result.data[0].user_url!=null){
                      $(".headImg").attr("src", result.data[0].user_url);
                  }else{
                      $(".headImg").attr("src", "/images/tx.jpg");
                  }
            }else{


            }
        }
    });

}
    //注销登录
    $("#outlogin").click(function () {
        if(window.confirm('你确定退出？')){
            //清除缓存
            sessionStorage.clear();
            //刷新页面
            window.location.reload();
            return true;
        }else{
            return false;
        }

    });



function loginshow(){
            //cookies中获得存入的姓名
            var a=sessionStorage.getItem("userName");
            //cookies中获得存入的用户类型
            var b=sessionStorage.getItem("userstyle");
            var div = document.getElementById("notlogin");
            var div1 = document.getElementById("inlogin");
            var li = document.getElementById("company");
            var li1 = document.getElementById("user");
            var li2=document.getElementById("insertcompany");
            var li3=document.getElementById("insert");
                //判断用户是否登录
                 if (a==null){
                     div.style.display = "block";
                     div1.style.display = "none";
                 }else{
                     div.style.display = "none";
                     div1.style.display = "block";
                     //判断用户的类型
                     if(b==0){
                         li.style.display = "none";
                         li1.style.display = "block";

                         li2.style.display = "none";
                         li3.style.display = "block";
                     }else if(b==1){
                         li.style.display = "block";
                         li1.style.display = "none";

                         li2.style.display = "block";
                         li3.style.display = "none";
                     }
                 }
      }


      //根据用户id查询公司信息用于判断是否有添加公司
function find_company(user_id) {
    $.ajax({
        type: "get",
        url: "/Company/selectCompany?user_id="+user_id,
        async: true,
        "success": function (result) {
            if (result.resultCode ==-1){
                var aC = document.getElementById("insertCom");
                aC.href="/views/client/companyInformation/addcompany.html";
            }else{
                $("#insertCom").addClass("insertCom");

            }
        }
    });
}

function companyInfo() {
    var user_id=sessionStorage.getItem("userId");
    find_company(user_id);

}


//限制一个用户只能添加一份简历
$(document).on("click",".insertCom",function () {
    alert("你已有公司！去编辑吧");
});

//根据用户id查询简历信息用于判断是否有添加简历
function find_insertInformation(user_id) {
    $.ajax({
        type: "get",
        url: "/information/selectInformation?user_id="+user_id,
        async: true,
        "success": function (result) {
            if (result.resultCode ==-1){
                var aC = document.getElementById("insertInformation");
                aC.href="/views/client/userInformation/addresume.html";
            }else{
                $("#insertInformation").addClass("insertInformation");
            }
        }
    });
}

function insertInformationInfo() {
    var user_id=sessionStorage.getItem("userId");
    find_insertInformation(user_id);

}


//限制一个用户只能添加一个公司
$(document).on("click",".insertInformation",function () {
    alert("你已有简历！去编辑吧");
});


insertInformationInfo()
companyInfo()
loginshow();
updateName();
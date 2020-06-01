/*
//上传头像
$(".up").click(function () {
    // 创建表单数据对象
    var obj = new FormData();
    // 获取文件框中的数据
    var file= document.getElementById("file").files[0];
    // 将文件数据添加至表单数据对象中
    obj.append("file", file);
    console.log("请求参数---》》》" + obj);
    upload1(obj);
    return false;
});
function  upload1(obj) {
    $.ajax({
        type:"POST",
        url:"/Company/upload",
        data:obj,
        contentType : false,
        processData : false,
        mimeType : 'multipart/form-data',
        success: function(result) {
            alert("头像上传成功");
        }
    });
}
*///显示公司用户信息
function userInfo() {
    var user_id=sessionStorage.getItem("userId");
    find_userInfo(user_id);
}
function find_userInfo(user_id) {
    $.ajax({
        type: "get",
        url: "/index/selectUserAll?user_id="+user_id,
        async: true,
        "success": function (result) {
            if (result.resultCode == 0){
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

//显示公司信息
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
                $("#headImg").attr("src",result.data[0].company_logo);
                $("#company_name").text(result.data[0].company_name);
                sessionStorage.setItem("company_name",result.data[0].company_name);
                sessionStorage.setItem("company_id",result.data[0].company_id)
                $("#company_alias").text(result.data[0].company_alias);
                $("#company_address").text(result.data[0].company_address);
                var Type = ["个人独资", "合伙公司", "有限责任公司", "股份制公司", "集团公司","个人制公司","其他"];
                $("#companyType_id").text(Type[result.data[0].companyType_id]);
                $("#industry").text(result.data[0].industry);
                $("#company_scale").text(result.data[0].company_scale);
                $("#company_balance").text(result.data[0].company_balance);
            }
        }
    });
}

$(".updatecompany").click(function () {
     var c=sessionStorage.getItem("company_id");
    if(c==null){
        alert("您还没有添加公司，去添加吧！")
    }else{
     window.location.href='updatecompany.html';
    }

})

companyInfo();
userInfo() ;

$(function() {
    layui.use(['form', 'laypage', 'util', 'layer'], function () {
        var form = layui.form;
        form.render();
        var laypage = layui.laypage;
        var util = layui.util;
        var layer = layui.layer;
        var tot;//总条数
        var tot2;
  //充值余额
        $(".chong").click(function () {
            layer.open({
                title: '余额充值'
                ,content: ' <form id="cen2">\n' +
                    '<input type="hidden" name="user_id" id="user_id">' +
                    '\t\t\t\t<input type="hidden" name="company_id" id="company_id">\n' +
                    '\t\t\t\t<input type="text" name="company_balance" id="company_balance">\n' +
                    '\t\t\t\t<button class="layui-btn layui-btn-sm addpayment" >提交</button>\n' +
                    '\t\t\t</form>'

            });
        });
        $(document).on("click",".addpayment",function () {
            $("#user_id").val(sessionStorage.getItem("userId"));
            $("#company_id").val(sessionStorage.getItem("company_id"));
            var all=$("#cen2").serializeArray();
            addpayment(all);
            return false;
        })
     function addpayment(all) {
         $.ajax({
             type: "post",
             url: "/Position/addpayment",
             async: false,
             data: all,
             "success": function (result) {
                 if (result.resultCode == 0) {
                     layer.msg(result.message, {
                         icon: 2,
                         time: 1000
                     });
                     window.location.reload();
                 } else {
                     layer.msg(result.message, {
                         icon: 2,
                         time: 1000
                     });
                 }
             }
         });
     }
         //分页显示应聘信息
        function UserinformationAllByPage(company_id,page,rows) {
            $.ajax({
                type: "post",
                url: "/formation/selectUserinformationAllByPage",
                async: false,
                data: {
                    "page": page,
                    "maxSize": rows,
                    "company_id":company_id
                },
                "success": function (result) {
                    if (result.resultCode == 0) {
                        tot2 = result.data.total;//输出分页
                        console.log(tot2);
                        build_information(result);//输出方法

                    } else {
                        layer.msg("请求失败，联系管理员", {
                            icon: 2,
                            time: 1000
                        });

                    }
                }
            });
        }

        function build_information(result) {
            console.log(11);
            var userList=result.data.list;
            $(".table_tbody2").empty();
            $.each(userList, function (index, item) {
                var tr = $("<tr></tr>");
                var td1 = $("<td></td>").addClass("childrenBox").append("<input type='checkbox' position_id='" + item.userInformation_id + "' lay-skin='primary'>");
                var td2 = $("<td></td>").append(item.user_name);
                var td3 = $("<td></td>").append(item.position_name);

               /* var zt=["审核通过","审核未通过","审核中"];
                var td9 = $("<td></td>").append(zt[item.state]);*/
                var td10=$("<td></td>").append("<button  class='layui-btn layui-btn-sm information' user_id='"+item.user_id+"'>查看简历</button>")
                    .append("<button class='layui-btn layui-btn-sm layui-btn-danger deleteinformation' userInformation_id='"+item.userInformation_id+"'>删除</button>");
                tr.append(td1).append(td2).append(td3).append(td10).appendTo(".table_tbody2");

            });
            form.render();
        }
        $(document).on("click",".information",function(){
            var user_id=$(this).attr("user_id");
            //点击修改用户全屏
            var index = layer.open({
                type: 2,
                title: false,
                shadeClose:true,
                shade:0.8,
                content: '../../client/userInformation/resume.html?user_id='+user_id,
            });
            layer.full(index);
        });

        //分页显示招聘信息
        function find_Position_list(user_id,page,rows) {
            $.ajax({
                type: "post",
                url: "/Position/selectPositionIdAllByPage",
                async: false,
                data: {
                    "page": page,
                    "maxSize": rows,
                    "user_id":user_id

                },
                "success": function (result) {
                    if (result.resultCode == 0) {
                        tot = result.data.total;//输出分页
                        console.log(tot);
                        build_sysUser_list(result);//输出方法

                    } else {
                        layer.msg("请求失败，联系管理员", {
                            icon: 2,
                            time: 1000
                        });

                    }
                }
            });
        }

        function build_sysUser_list(result) {
            var userList=result.data.list;
            $(".table_tbody1").empty();
            $.each(userList, function (index, item) {
                var tr = $("<tr></tr>");
                var td1 = $("<td></td>").addClass("childrenBox").append("<input type='checkbox' position_id='" + item.position_id + "' lay-skin='primary'>");
                var td2 = $("<td></td>").append(item.company_name);
                var td3 = $("<td></td>").append(item.position_name);
                var td4 = $("<td></td>").append(item.position_type);
                var td5 = $("<td></td>").append(item.position_people);
                var td6 = $("<td></td>").append(item.salary);
                var td7 = $("<td></td>").append(item.education);
                var td8 = $("<td></td>").append(item.position_introduce);
                var zt=["审核通过","审核未通过","审核中"];
                var td9 = $("<td></td>").append(zt[item.state]);
                var td10=$("<td></td>").append("<button  class='layui-btn layui-btn-sm updatePosition' position_id='"+item.position_id+"'>编辑</button>")
                    .append("<button class='layui-btn layui-btn-sm layui-btn-danger deletePosition' position_id='"+item.position_id+"'>删除</button>");
                tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9).append(td10).appendTo(".table_tbody1")

            });
            form.render();
        }
        $(document).on("click",".updatePosition",function(){
            var position_id=$(this).attr("position_id");
            //点击修改用户全屏
            var index = layer.open({
                type: 2,
                title: false,
                shadeClose:true,
                shade:0.8,
                content: '../../client/recruitmentInformation/updaterecruitment.html?position_id='+position_id,
            });
            layer.full(index);
        });


        //删除招聘信息
        $(document).on("click",".deletePosition",function(){
            var position_id=$(this).attr("position_id");
            layer.confirm("您确定删除吗?",function(){
                delete_Position(position_id);
            });
        });
        function delete_Position(position_id){
            $.ajax({
                url:"/Position/deletePosition",
                async:false,
                data:{"ids":position_id},

                "success":function(result){
                    if(result.resultCode==0){
                        layer.msg(result.message,{
                            icon:1,
                            time:1000
                        });
                        window.location.reload();
                    }else{
                        layer.msg(result.message,{
                            icon:1,
                            time:1000
                        });
                    }
                }
            });
        }

        //全选
        form.on('checkbox(parentBox)',function(data){
            if(data.elem.checked){
                $(".childrenBox input[type='checkbox']").prop("checked",true);

            }else{
                $(".childrenBox input[type='checkbox']").prop("checked",false);
            }
            form.render();
        });

        //反选
        form.on('checkbox',function(data){
            var length=0;	//checkbox总个数
            var checkLength=0;//被选中的总个数
            $(".childrenBox input[type='checkbox']").each(function(){
                length++;

                if($(this).is(":checked")){
                    checkLength++;
                }
            });
            if(checkLength==length){
                $(".parentBox").prop("checked",true);
            }else{
                $(".parentBox").prop("checked",false);
            }
            form.render();
        });
        //执行分页显示应聘信息的方法
        var company_id=sessionStorage.getItem("company_id");
        UserinformationAllByPage(company_id,1,5);
        var user_id=sessionStorage.getItem("userId");
        find_Position_list(user_id,1,5);
        //分页
        console.log(tot);
        laypage.render({
            elem: "table_page",//绑定页面元素节点的id(只能是id)
            count: tot,//数据总数，从服务器中读取中
            limit:5,
            //分页的样式
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump: function (obj, first) {//当前分页执行成功后的回调函数
                //obj包含了当前分页的所有参数
                //obj.curr,obj.limit，当前页面所容纳的最大条数
                var user_id=sessionStorage.getItem("userId");
                if (!first) {
                    console.log(obj.count);
                    find_Position_list(user_id,obj.curr, obj.limit);

                }
            }
        });
        laypage.render({
            elem: "table_page1",//绑定页面元素节点的id(只能是id)
            count: tot2,//数据总数，从服务器中读取中
            limit:5,
            //分页的样式
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump: function (obj, first) {//当前分页执行成功后的回调函数
                //obj包含了当前分页的所有参数
                //obj.curr,obj.limit，当前页面所容纳的最大条数
                var company_id=sessionStorage.getItem("company_id");
                if (!first) {
                    console.log(obj.count);
                    UserinformationAllByPage(company_id,obj.curr, obj.limit);
                }
            }
        });
    });
});
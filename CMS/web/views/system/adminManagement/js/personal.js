$(function() {
    layui.use(['table', 'util','laypage','layer'], function() {
        var table = layui.table;
        var util = layui.util;

        function find_userInfo(admin_id) {
            $.ajax({
                type: "get",
                url: "/admin/selectAdminAll?admin_id="+admin_id,
                async: true,
                "success": function (result) {
                    if (result.resultCode == 0) {
                        $("#name").text(result.data[0].admin_name);
                        $("#account").text(result.data[0].admin_account);
                        var sex = ["保密", "男", "女"];
                        $("#sex").text(sex[result.data[0].admin_sex]);
                        // $("#birthday").text(result.data[0].admin_birthday);
                        $("#birthday").text(util.toDateString(result.data[0].admin_birthday,"yyyy-MM-dd"));
                        var phone = "";
                        if (result.data[0].admin_phone != null) {
                            phone = result.data[0].admin_phone;
                        }
                        $("#phone").text(phone);
                        var hiredate = "";
                        if (result.data[0].admin_hiredate != null) {
                            hiredate = result.data[0].admin_hiredate;
                        }
                        $("#hiredate").text(util.toDateString(result.data[0].admin_hiredate,"yyyy-MM-dd"))
                        $("#img").attr("src",result.data[0].admin_url);
                    }
                }
            });

        }

    function adminInfo() {
          var admin_id=sessionStorage.getItem("admin_id")

        find_userInfo(admin_id);
    }
        adminInfo();

        var laypage = layui.laypage;
        var layer=layui.layer;
        var total; //总条数
        //定义获取当前用户登录日志信息的函数
        function finf_log_list(page, rows) {
            var index;
            $.ajax({
                type:"get",
                url:"/Log/selectLoginLogPage",
                async:false,
                data:{
                    "page": page,
                    "maxSize": rows
                },
                "beforeSend": function() {
                    index = layer.load(3, {
                        shade: [0.5, "black"]
                    })
                },
                "success": function(result) {
                    if(result.resultCode == 0) {
                        layer.close(index);
                        build_log_list(result);
                        total = result.data.total;
                    } else {
                        layer.msg("服务器错误，请联系管理员", {
                            icon: 2,
                            time: 1000
                        });
                    }
                },
                "error": function() {
                    layer.msg("服务器错误，请联系管理员", {
                        icon: 2,
                        time: 1000
                    });
                }
            });

        }
        //定义渲染登录日志的函数
        function build_log_list(result) {
            var logList = result.data.list;
            $(".table_tbody").empty();
            //循环遍历日志数组，在页面上显示

        // <tr>
        //     <td id="user_id">1</td>
        //         <td id="user_name">blue</td>
        //         <td id="login_time">2018/9/21 15:51:30</td>
        //
        //     </tr>

            $.each(logList,function (index,item) {
               var tr = $("<tr></tr>");
               var td1 = $("<td></td>").text(item.user_id);
               var td2 = $("<td></td>").text(item.user_name);
               var td3 = $("<td></td>").text(util.toDateString(item.login_time, "yyyy-MM-dd HH:mm:ss"));
               tr.append(td1).append(td2).append(td3).appendTo(".table_tbody")
            });
        }
        finf_log_list(1,10);
        laypage.render({
            elem: "table_page", //绑定页面元素节点的id，只能是id
            count: total, //数据总数，从服务中读取的数据中
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump: function(obj, first) { //当前分页执行成功后的回调函数，obj包含了当前分页的所有参数，比如：
                //obj.curr,当前页码，obj.limit,当前页所容纳的最大条数
                if(!first) {
                    finf_log_list(obj.curr, obj.limit);
                }

            }
        });


    });

})
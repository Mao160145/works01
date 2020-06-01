
$(function() {
    layui.use(['form', 'laypage', 'util', 'layer'], function () {
        var form = layui.form;
        form.render();
        var laypage = layui.laypage;
        var util = layui.util;
        var layer = layui.layer;
        var total;//总条数

       //搜索公司
        $(".sousuo2").click(function () {
            var likeName=document.getElementById("sousuoinput").value;
            console.log(likeName);
            sou(1,3,likeName);
            laypage.render({
                elem: "table_page",//绑定页面元素节点的id(只能是id)
                count: total,//数据总数，从服务器中读取中
                theme: '#FFB800',
                limit:3,
                //分页的样式
                layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                jump: function (obj, first) {//当前分页执行成功后的回调函数
                    //obj包含了当前分页的所有参数
                    //obj.curr,obj.limit，当前页面所容纳的最大条数
                    if (!first) {
                        find_sysUser_list(obj.curr, obj.limit,likeName);
                    }
                }
            });

            return false;
        })
        function sou(page, rows,likeName) {
            $.ajax({
                type: "post",
                url: "/Company/selectCompanyAllByPage",
                async: false,
                data: {
                    "page": page,
                    "maxSize": rows,
                    "likeName":likeName
                },
                "success": function (result) {
                    if (result.resultCode == 0) {
                        build_sysUser_list(result);//输出方法
                        total = result.data.total;//输出分页
                    } else {
                        layer.msg("请求失败，联系管理员", {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            });
        }

         //分页显示公司
        function find_sysUser_list(page, rows) {
            $.ajax({
                type: "post",
                url: "/Company/selectCompanyAllByPage",
                async: false,
                data: {
                    "page": page,
                    "maxSize": rows
                },
                "success": function (result) {
                    if (result.resultCode == 0) {
                        build_sysUser_list(result);//输出方法
                        total = result.data.total;//输出分页
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
            var userList = result.data.list;
            $(".new").empty();
            $.each(userList, function (index, item) {
                //存放id的目的是为了删除，或者修改单个记录的时候找到那条数据
                var tr=$("<div class=\"a\"></div>");
                var tr1=$("<img src="+item.company_logo+" /><br />");
                var tr2=$("<p class=\"company_name\">"+item.company_name+"</p>");
                var tr3=$("<button class=\"btn btn-link selet\" data-toggle=\"modal\" company_id='"+item.company_id+"' data-target=\"#myModal\">查看详情</button>");
                tr.append(tr1).append(tr2).append(tr3).appendTo(".new");
            });
            form.render();
        }
        //调用获取时获取用户列表的方法
        find_sysUser_list(1, 5);
        //分页
        laypage.render({
            elem: "table_page",//绑定页面元素节点的id(只能是id)
            count: total,//数据总数，从服务器中读取中
            theme: '#FFB800',
            limit:5,
            //分页的样式
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump: function (obj, first) {//当前分页执行成功后的回调函数
                //obj包含了当前分页的所有参数
                //obj.curr,obj.limit，当前页面所容纳的最大条数
                if (!first) {
                    find_sysUser_list(obj.curr, obj.limit);
                }
            }
        });
    });
});

//查看公司详细信息
$(document).on("click",".selet",function(){
    var company_id=$(this).attr("company_id");
    find_company(company_id);

});
//显示公司信息
function find_company(company_id) {
    $.ajax({
        type: "get",
        url: "/Company/selectCompany?company_id="+company_id,
        async: true,
        "success": function (result) {
            if (result.resultCode == 0) {
                $("#company_name").text(result.data[0].company_name);
                $("#company_name").text(result.data[0].company_name);
                $("#company_alias").text(result.data[0].company_alias);
                $("#company_address").text(result.data[0].company_address);
                $("#companyType_id").text(result.data[0].companyType_id);
                $("#industry").text(result.data[0].industry);
                $("#company_scale").text(result.data[0].company_scale);
                $("#company_introduce").text(result.data[0].company_introduce);
            }
        }
    });
}

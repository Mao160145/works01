//查询菜单
function selectCmsmenu(){
        $.ajax({
            type: "get",
            url: "/Menu/selectCmsmenu",
            async: true,
            "success": function (result) {
                var userList=result.data;
                $(".menu").empty();
                $.each(userList, function(index,item) {
                    if(item.code<99){
                        var menu1=$("<div class=\"aui-content-menu \"></div>");
                        var menu3=$("<div  class=\"aui-content-menu-dow aui-ds "+item.code+" \"></div>");
                        var menu2=$("<div class=\"aui-content-menu-head\">\n" +
                            "\t\t\t\t\t\t\t<div class=\"aui-content-menu-head-list\">\n" +
                            "\t\t\t\t\t\t\t\t<h2>"+item.menu_name+"</h2>\n" +
                            "\t\t\t\t\t\t\t\t<i class=\"aui-content-menu-head-list-arrow\"></i>\n" +
                            "\t\t\t\t\t\t\t</div>\n" +
                            "\t\t\t\t\t\t</div>");
                        menu1.append(menu2).append(menu3).appendTo(".menu");
                    }
                });
                $.each(userList, function(index,item) {
                    if(item.code<999&&item.code>100){
                        var menu4=$("<dl></dl>");
                        var menu5=$("<dt>\n" +
                            "<span>"+item.menu_name+"</span>\n" +
                            "</dt>");
                        var menu6=$("<dd class="+item.code+"></dd>");
                       var arr = (item.code+'').split('');
                        menu4.append(menu5).append(menu6).appendTo("."+arr[0]+"");
                    }
                });
                $.each(userList, function(index,item) {
                    if(item.code<9999&&item.code>1000){
                        var a=$("\t<a href=\"#\" class=\"aui-a-curr cousuo\">"+item.menu_name+"</a>");
                       var b=parseInt(item.code/10)
                        a.appendTo("."+b+"");
                    }
                });
            }
        });
}
function hov(){
         $(document).on("mouseover",".aui-content-main .aui-content-menu",function() {
             $(this).addClass('active').find('s').hide();
             $(this).find('.aui-content-menu-dow').show();
         });
        $(document).on("mouseout",".aui-content-main .aui-content-menu",function() {
             $(this).removeClass('active').find('s').show();
             $(this).find('.aui-content-menu-dow').hide();
       });
     }
hov();
selectCmsmenu();

$(function() {
    layui.use(['form', 'laypage', 'util', 'layer'], function () {
        var form = layui.form;
        form.render();
        var laypage = layui.laypage;
        var util = layui.util;
        var layer = layui.layer;
        var total;//总条数

        function find_sysUser_list(page, rows,likeName) {
            $.ajax({
                type: "post",
                url: "/Position/selectPositionstateAllByPage",
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

        $(".sousuo3").click(function () {
            var likeName=$(this).html();
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
        });
      /*  BaseUtil.ajaxSetup();
        var sousuo1 = BaseUtil.GetQueryString("sousuo1");
        console.log(sousuo1);
        sou(1,3,sousuo1);

*/
        $(".sousuo2").click(function () {
            var likeName=document.getElementById("sousuoinput").value;
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
        });

        $(document).on("click",".cousuo",function () {
           var likeName=$(this).html();
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
                url: "/Position/selectPositionstateAllByPage",
                async: false,
                data: {
                    "page": page,
                    "maxSize": rows,
                    "likeName":likeName
                },
                "success": function (result) {
                    if (result.resultCode == 0) {
                        total = result.data.total;//输出分页
                        console.log(result.data.list);
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
            var userList = result.data.list;
            $(".zp").empty();
            $.each(userList, function (index, item) {
                //存放id的目的是为了删除，或者修改单个记录的时候找到那条数据
                var tr=$("<div class=\"zpdiv\"></div>");
                var tr1=$(" <div class=\"zpxx\"></div>");
                var tr2=$("<a href=\"#\" class=\"zpa\">"+item.position_name+"</a>");
                var tr3=$("<p class=\"zpp\">"+item.company_name+"</p>");
                var tr4=$("<p class=\"zpp\">招聘人数:<em>"+item.position_people+"</em></p>");
                var tr5=$("<p class=\"glyphicon glyphicon-map-marker zpdz\">"+item.work_address+"</p>");
                var tr6=$("<div class=\"zpxz\"></div>");
                var tr7=$("<span class=\"xz\">"+item.salary+"元/月</span><br/>");
                var education="";
                if(item.education!=null){
                    education=item.education
                }
                var tr8=$("<span class=\"xl\">最低学历:<em>"+education+"</em></span>");
                var tr9=$("<button class=\"btn btn-warning btn-xs zpbutton\" position_id='"+item.position_id+"' company_id='"+item.company_id+"'  position_name='"+item.position_name+"'>立即应聘</button>");
                tr1.append(tr2).append(tr3).append(tr4).append(tr5);
                tr6.append(tr7).append(tr8).append(tr9);
                tr.append(tr1).append(tr6).appendTo(".zp");
            });
            form.render();
        }

        $(document).on("click",".zpbutton",function () {
             var user_id=sessionStorage.getItem("userId");
             var user_name=sessionStorage.getItem("userName");
             var position_id=$(this).attr("position_id");
             var position_name=$(this).attr("position_name");
             var company_id=$(this).attr("company_id");
            userinformation(user_id,user_name,position_id,position_name,company_id);
        });
        function userinformation(user_id,user_name,position_id,position_name,company_id){
            $.ajax({
                type: "post",
                url: "/formation/Userinformation",
                async: false,
                data: {
                    "user_id": user_id,
                    "user_name": user_name,
                    "position_id":position_id,
                    "position_name":position_name,
                    "company_id":company_id
                },
                "success": function (result) {
                    if (result.resultCode == 0) {
                        layer.msg(result.message, {
                            icon: 1,
                            time: 1000
                        });
                    } else {
                        layer.msg("请求失败，联系管理员", {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            });
        }
        //调用获取时获取用户列表的方法
        find_sysUser_list(1, 3);
        //分页
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
                    find_sysUser_list(obj.curr, obj.limit);
                }
            }
        });
    });
});
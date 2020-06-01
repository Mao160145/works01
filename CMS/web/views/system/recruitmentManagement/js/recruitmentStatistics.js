$(function() {
    layui.use(['form', 'laypage', 'util', 'layer'], function () {
        var form = layui.form;
        form.render();
        var laypage = layui.laypage;
        var util = layui.util;
        var layer = layui.layer;
        var total;//总条数
        var tota2;
        var tota3;
        var menuId;
        var codeb;
        var menuName;

  //添加分类
        $(".insertMenu").click(function () {
            layer.open({
                title: '在线调试'
                ,content: ' <form id="cen2">\n' +
                    '' +
                    '\t\t\t\t<input type="text" name="code" id="code">\n' +
                    '\t\t\t\t<input type="text" name="menu_name" id="menu_name">\n' +
                    '\t\t\t\t<button class="layui-btn layui-btn-sm insertque" lay-submit lay-filter="updatecompany">提交</button>\n' +
                    '\t\t\t</form>'

            });
        });

        $(document).on("click",".insertque",function () {
            var all=$("#cen2").serializeArray();
            insert_menu(all);
            return false;
        })


        function insert_menu(all) {
            $.ajax({
                type: "post",
                url: "/Menu/insertCmsmenu",
                async: true,
                data: all,
                "success": function(result) {
                    if(result.resultCode == 0) {
                        layer.msg(result.message, {
                            icon: 1,
                            time: 1000
                        });
                        //获取当前弹出层，通过父页面获取当前frame框的索引即窗口名称
                        //var index=parent.layer.getFrameIndex(window.name);
                        window.location.reload(); //刷新父页面
                        //parent.layer.close(index);//关闭弹出层
                    }
                }
            });
        }

        //修改分类
        $(document).on("click",".updatecompany",function () {
            var menu_id=$(this).attr("menu_id");
            find_companyInfo_bycompanyId(menu_id);
            function find_companyInfo_bycompanyId(menu_id) {
                $.ajax({
                    type: "post",
                    url: "/Menu/selectCmsmenu",
                    data: {
                        "menu_id": menu_id
                    },
                    async: false,
                    "success": function(result) {

                        if(result.resultCode == 0) {
                            build_companyInfo(result);

                        }
                    }
                });
            }
            function build_companyInfo(result) {
                var data = result.data[0];

                menuId =(data.menu_id);
                codeb=(data.code);
                console.log(codeb);
                menuName=(data.menu_name);

            }
            layer.open({
                title: '在线调试'
                ,content: ' <form id="cen1">\n' +
                    '\t\t\t\t<input type="hidden"  name="menu_id" id="menu_id" />\n' +
                    '\t\t\t\t<input type="text" name="code" id="code">\n' +
                    '\t\t\t\t<input type="text" name="menu_name" id="menu_name">\n' +
                    '\t\t\t\t<button class="layui-btn layui-btn-sm que" lay-submit lay-filter="updatecompany">提交</button>\n' +
                    '\t\t\t</form>',
                "success":function(result){

                    $("input[name='menu_id']").val(menuId);
                    $("input[name='code']").val(codeb);
                    $("input[name='menu_name']").val(menuName);
                }
            });
        })

          $(document).on("click",".que",function () {
              var all=$("#cen1").serializeArray();
              update_menu(all);
             return false;
          })


        function update_menu(all) {
            $.ajax({
                type: "post",
                url: "/Menu/updateCmsmenu",
                async: true,
                data: all,
                "success": function(result) {
                    if(result.resultCode == 0) {
                        layer.msg(result.message, {
                            icon: 1,
                            time: 1000
                        });
                        //获取当前弹出层，通过父页面获取当前frame框的索引即窗口名称
                        //var index=parent.layer.getFrameIndex(window.name);
                        window.location.reload(); //刷新父页面
                        //parent.layer.close(index);//关闭弹出层
                    }
                }
            });
        }


        $(".sousuo").click(function () {
            var likeName=document.getElementById("likeName").value;
            console.log(likeName);
            find_syscompany_list(1,10,likeName);
            find_syscompany_list2(1,10,likeName);
            find_syscompany_list3(1,10,likeName);
            //一级分页
            laypage.render({
                elem:"table_page",//绑定页面元素节点的id(只能是id)
                count:total,//数据总数，从服务器中读取中
                //分页的样式
                layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                jump:function(obj,first){//当前分页执行成功后的回调函数
                    //obj包含了当前分页的所有参数
                    //obj.curr,obj.limit，当前页面所容纳的最大条数
                    if(!first){
                        find_syscompany_list(obj.curr,obj.limit,likeName);
                    }
                }
            });
            //二级分页
            laypage.render({
                elem:"table_page2",//绑定页面元素节点的id(只能是id)
                count:tota2,//数据总数，从服务器中读取中
                //分页的样式
                layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                jump:function(obj,first){//当前分页执行成功后的回调函数
                    //obj包含了当前分页的所有参数
                    //obj.curr,obj.limit，当前页面所容纳的最大条数
                    if(!first){
                        find_syscompany_list2(obj.curr,obj.limit,likeName);
                    }
                }
            });

            //三级分页
            laypage.render({
                elem:"table_page3",//绑定页面元素节点的id(只能是id)
                count:tota3,//数据总数，从服务器中读取中
                //分页的样式
                layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                jump:function(obj,first){//当前分页执行成功后的回调函数
                    //obj包含了当前分页的所有参数
                    //obj.curr,obj.limit，当前页面所容纳的最大条数
                    if(!first){
                        find_syscompany_list3(obj.curr,obj.limit,likeName);
                    }
                }
            });
            return false;
        })
        //分页查找一级分类
        function find_syscompany_list(page,rows,likeName){
            $.ajax({
                type:"post",
                url:"/Menu/selectmenuAllByPage",
                async:false,
                data:{
                    "page":page,
                    "maxSize":rows,
                    "likeName":likeName
                },
                "success":function(result){
                    if(result.resultCode==0){
                        build_syscompany_list(result);//输出方法
                        total=result.data.total;//输出分页
                    }else{
                        layer.msg("请求失败，联系管理员",{
                            icon:2,
                            time:1000
                        });

                    }
                }
            });
        }

        function build_syscompany_list(result){
            var companyList=result.data.list;
            $(".table_tbody").empty();
            $.each(companyList, function(index,item) {
                //存放id的目的是为了删除，或者修改单个记录的时候找到那条数据
                var tr=$("<tr></tr>");
                var td1=$("<td></td>").addClass("childrenBox").append("<input type='checkbox' menu_id='"+item.menu_id+"' lay-skin='primary'>");
                var td2=$("<td></td>").append(item.menu_id);
                var td3=$("<td></td>").append(item.code);
                var td4=$("<td></td>").append(item.menu_name);


                var td10=$("<td></td>").append("<button class='layui-btn layui-btn-sm updatecompany' menu_id='"+item.menu_id+"'>编辑</button>");
                tr.append(td1).append(td2).append(td3).append(td4).append(td10).appendTo(".table_tbody")
            });
            form.render();
        }
        find_syscompany_list(1,10);
        //分页
        laypage.render({
            elem:"table_page",//绑定页面元素节点的id(只能是id)
            count:total,//数据总数，从服务器中读取中
            //分页的样式
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump:function(obj,first){//当前分页执行成功后的回调函数
                //obj包含了当前分页的所有参数
                //obj.curr,obj.limit，当前页面所容纳的最大条数
                if(!first){
                    find_syscompany_list(obj.curr,obj.limit);
                }
            }
        });

      //分页查找二级分页
        function find_syscompany_list2(page,rows,likeName){
            console.log(likeName);
            $.ajax({
                type:"post",
                url:"/Menu/selectmenuAllByPage2",
                async:false,
                data:{
                    "page":page,
                    "maxSize":rows,
                    "likeName2":likeName
                },
                "success":function(result){
                    if(result.resultCode==0){
                        build_syscompany_list2(result);//输出方法
                        tota2=result.data.total;//输出分页
                    }else{
                        layer.msg("请求失败，联系管理员",{
                            icon:2,
                            time:1000
                        });

                    }
                }
            });
        }

        function build_syscompany_list2(result){
            var companyList=result.data.list;
            $(".table_tbody2").empty();
            $.each(companyList, function(index,item) {
                //存放id的目的是为了删除，或者修改单个记录的时候找到那条数据
                var tr=$("<tr></tr>");
                var td1=$("<td></td>").addClass("childrenBox").append("<input type='checkbox' menu_id='"+item.menu_id+"' lay-skin='primary'>");
                var td2=$("<td></td>").append(item.menu_id);
                var td3=$("<td></td>").append(item.code);
                var td4=$("<td></td>").append(item.menu_name);


                var td10=$("<td></td>").append("<button class='layui-btn layui-btn-sm updatecompany' menu_id='"+item.menu_id+"'>编辑</button>");
                tr.append(td1).append(td2).append(td3).append(td4).append(td10).appendTo(".table_tbody2")
            });
            form.render();
        }

        //调用获取时获取公司列表的方法
        find_syscompany_list2(1,10);

        laypage.render({
            elem:"table_page2",//绑定页面元素节点的id(只能是id)
            count:tota2,//数据总数，从服务器中读取中
            //分页的样式
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump:function(obj,first){//当前分页执行成功后的回调函数
                //obj包含了当前分页的所有参数
                //obj.curr,obj.limit，当前页面所容纳的最大条数
                if(!first){
                    find_syscompany_list2(obj.curr,obj.limit);
                }
            }
        });
        //分页查找三级级分页
        function find_syscompany_list3(page,rows,likeName){
            $.ajax({
                type:"post",
                url:"/Menu/selectmenuAllByPage3",
                async:false,
                data:{
                    "page":page,
                    "maxSize":rows,
                    "likeName3":likeName
                },
                "success":function(result){
                    if(result.resultCode==0){
                        build_syscompany_list3(result);//输出方法
                        tota3=result.data.total;//输出分页
                    }else{
                        layer.msg("请求失败，联系管理员",{
                            icon:2,
                            time:1000
                        });

                    }
                }
            });
        }

        function build_syscompany_list3(result){
            var companyList=result.data.list;
            $(".table_tbody3").empty();
            $.each(companyList, function(index,item) {
                //存放id的目的是为了删除，或者修改单个记录的时候找到那条数据
                var tr=$("<tr></tr>");
                var td1=$("<td></td>").addClass("childrenBox").append("<input type='checkbox' menu_id='"+item.menu_id+"' lay-skin='primary'>");
                var td2=$("<td></td>").append(item.menu_id);
                var td3=$("<td></td>").append(item.code);
                var td4=$("<td></td>").append(item.menu_name);


                var td10=$("<td></td>").append("<button class='layui-btn layui-btn-sm updatecompany' menu_id='"+item.menu_id+"'>编辑</button>");
                tr.append(td1).append(td2).append(td3).append(td4).append(td10).appendTo(".table_tbody3")
            });
            form.render();
        }

        //调用获取时获取公司列表的方法
        find_syscompany_list3(1,10);

        laypage.render({
            elem:"table_page3",//绑定页面元素节点的id(只能是id)
            count:tota3,//数据总数，从服务器中读取中
            //分页的样式
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump:function(obj,first){//当前分页执行成功后的回调函数
                //obj包含了当前分页的所有参数
                //obj.curr,obj.limit，当前页面所容纳的最大条数
                if(!first){
                    find_syscompany_list3(obj.curr,obj.limit);
                }
            }
        });


        //批量删除
        $(".batchDeleteCompany").click(function(){
            var ids="";//要删除的用户id
            var check=0;//记录删除的条数
            $(".childrenBox input[type='checkbox']").each(function(){
                //如果当前的checkbox被选中，则将companyId的属性值存放到ids的变量中
                if($(this).is(":checked")){
                    ids+=$(this).attr("menu_id")+",";
                    check++;

                }
            });
            //若选中次数不超过一次，给出错误提示
            if(check>0){
                layer.confirm("你确定要删除"+check+"条记录吗？",function(){
                    //将最后一位的逗号给删除
                    delete_companyId(ids.substring(0,ids.length-1));
                });
            }else{
                layer.msg("不能少于0条记录",{
                    icon:2,
                    time:1000
                })
            }
            function delete_companyId(menu_id){
                $.ajax({
                    url:"/Menu/deleteCmsmenu",
                    async:false,
                    data:{"ids":menu_id},

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
        });
    });
});
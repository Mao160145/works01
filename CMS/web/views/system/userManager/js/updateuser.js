$(function() {
    layui.use(['form', 'laydate', 'layer', 'util'], function() {
        var form = layui.form;
        var laydate = layui.laydate;
        var layer = layui.layer;
        var util = layui.util;
        form.render();
        //重新加载date空间(出生日期)
        laydate.render({
            elem: "#birthday", //绑定文本框的id(text)
            //type:"datetime"//时间类型,年月日，时分秒
        });
      var version = null;
        console.log(userId);
        BaseUtil.ajaxSetup();
        var userId = BaseUtil.GetQueryString("userId");

        //调用查询后台角色列表的函数
        find_userInfo_byUserId(userId);
        //通过用户Id查询用户详情
        function find_userInfo_byUserId(userId) {

            $.ajax({
                type: "post",
                url: "/index/selectUserAll",
                data: {
                    "user_id": userId
                },
                async: false,
                "success": function(result) {
                    if(result.resultCode == 0) {
                        build_userInfo(result);
                        version = result.data[0].version;

                    }
                }
            });
        }

        //显示编辑按钮信息
        function build_userInfo(result) {
            var data = result.data[0];
            console.log(data);
            if(data.user_url != null) {
                $("#showImg").attr("src", data.user_url);
            }

            $("input[name='user_name']").val(data.user_name);
            $("input[name='user_account']").val(data.user_account);
            $("input[name='user_phone']").val(data.user_phone);
            var sex = ["input[title='保密']", "input[title='男']", "input[title='女']"];
            $(sex[data.user_sex]).prop("checked", true);
            $("input[name='user_age']").val(data.user_age);

            form.render();
        }

        //将角色列表中选中的添加name，以便传值到后台
        $(".updateUser").click(function () {
            $("#token").val(token);
            var user_Id = parseInt(userId);
            $("#userId").val(user_Id);
            $("#version").val(version);
        });

        form.on("submit(updateUser)", function(data) {
            console.log(data.field);
            var params = data.field;
            update_user(params);
            return false; //防止表单跳转
        });

        //修改用户的ajax
        function update_user(params) {
            $.ajax({
                type: "post",
                url: "/index/updateUser",
                data: params,
                async: true,
                "success": function(result) {
                    if (result.resultCode == 0) {
                        layer.msg(result.message, {
                            icon: 1,
                            time: 1000
                        });
                        //获取当前弹出层，通过父页面获取当前frame框的索引即窗口名称
                        //var index=parent.layer.getFrameIndex(window.name);
                        parent.location.reload(); //刷新父页面
                        //parent.layer.close(index);//关闭弹出层
                    }
                }
            });
        }
    });
});
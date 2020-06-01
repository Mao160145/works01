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

        BaseUtil.ajaxSetup();
        var companyId = BaseUtil.GetQueryString("companyId");

        //调用查询后台角色列表的函数
        find_companyInfo_bycompanyId(companyId);
        //通过用户Id查询公司详情
        function find_companyInfo_bycompanyId(companyId) {
            $.ajax({
                type: "post",
                url: "/Company/selectCompany",
                data: {
                    "company_id": companyId
                },
                async: false,
                "success": function(result) {
                    if(result.resultCode == 0) {
                        build_companyInfo(result);

                    }
                }
            });
        }

        //显示编辑按钮信息
        function build_companyInfo(result) {
            var data = result.data[0];
            console.log(data);
            if(data.company_logo != null) {
                $("#showImg").attr("src", data.company_logo);
            }
            $("input[name='company_name']").val(data.company_name);
            $("input[name='company_alias']").val(data.company_alias);
            $("input[name='company_address']").val(data.company_address);
            var companyType_id = ["input[title='个人独资']", "input[title='合伙公司']", "input[title='有限责任公司']",
                "input[title='股份制公司']", "input[title='集团公司']", "input[title='其他']", "input[title='其他']"];
            $(companyType_id[data.companyType_id]).prop("checked", true);
            $("input[name='industry']").val(data.industry);
            $("input[name='company_scale']").val(data.company_scale);
            $("input[name='company_balance']").val(data.company_balance);
            $("input[name='company_introduce']").val(data.company_introduce);
        }

        //将角色列表中选中的添加name，以便传值到后台
        $(".updatecompany").click(function() {
            $("#token").val(token);
            var company_id = parseInt(companyId);
            $("#companyId").val(companyId);


        });

        form.on("submit(updatecompany)", function(data) {
            console.log(data.field);
            var params = data.field;
            update_company(params);
            return false; //防止表单跳转
        });

        function update_company(params) {
            $.ajax({
                type: "post",
                url: "/Company/updateCompany",
                async: true,
                data: params,
                "success": function(result) {
                    if(result.resultCode == 0) {
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
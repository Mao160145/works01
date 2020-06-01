$(function(){
	layui.use(['form', 'element','layer'], function() {
		var form = layui.form;
		form.render();
		$(".layui-tab-item iframe").height($(".layui-side-scroll").height() - 110);
		
		var element = layui.element;
		$(document).on("click", ".li_item", function() {

			var dataId = $(this).data("id");
			if($("#ifm_" + dataId).length > 0) {
				element.tabChange("demo", "lid_" + dataId);
			} else {
				var title = $(this).text();
				var url = $(this).data("url");
				element.tabAdd("demo", {
					"title": title,
					"content": "<iframe src='" + url + "' id='ifm_" + dataId + "'></iframe>",
					id: "lid_" + dataId
					});
				element.tabChange("demo", "lid_" + dataId);
				$(".layui-tab-item iframe").height($(".layui-side-scroll").height() - 110);
			}
		});
           //退出登录
        $(".outLogin").click(function () {
            if(window.confirm('你确定退出？')){
                sessionStorage.clear();
                location.href="login.html";
                return true;
            }else{
                return false;
            }

        });
//		//显示头像和用户名
//


		function updateName(admin_id) {
                $.ajax({
                    type: "get",
                    url: "/admin/selectAdminAll?admin_id="+admin_id,
                    async: true,
                    "success": function (result) {
                        if (result.resultCode == 0) {
                            $(".admin_name").text(result.data[0].admin_name);
                            if(result.data[0].admin_url != null &&result.data[0].admin_url != "") {
                                $(".admin_url").attr("src", result.data[0].admin_url);
                            }else{
                                $(".admin_url").attr("src", "/upload/20181226131233.jpg");
							}
                        }
                    }
                });
		}
       var admin_id=sessionStorage.getItem("admin_id");
		updateName(admin_id);

	
	//查找我的菜单函数
	function find_myMenu(){
		var index;
		$.ajax({
			type:"get",
			url:"../../base/data/index/findMyMenu.json",
			async:true,
			"beforeSend":function(){
				//幕布
			index=layer.load(1,{
					shade:[0.5,'black']
					
				});
			},
			"success":function(result){
				if(result.resultCode==0){
					layer.close(index);
					build_MyMenu(result);
				}else{
					layer.msg("服务器崩溃，请稍后重试",{
						icon:2,
						time:1000
					});
				}
			},		
			"error":function(){				
					layer.msg("服务器崩溃，请稍后重试",{
						icon:2,
						time:1000
					});
				
			}
		});	
	}
	
	//显示我的菜单函数
	function build_MyMenu(result){
			var firstMenu=result.data.menu_1;//获取所有的一级菜单
			var secondMenu=result.data.menu_2;//获取所有的二级菜单
				//遍历并生成一级菜单
				$.each(firstMenu, function(index,item) {
					var li=$("<li></li>").addClass("layui-nav-item layui-nav-itemed")
					var a=$("<a  href='javascript:;' data-id='"+item.code+"' data-url='"+item.url+"'><i class='fa fa-cogs' aria-hidden='true'></i>&nbsp;"+item.name+"</a>");				
					var dl=$("<dl class='layui-nav-child'></dl>")
					var firstMenuCode=item.code;//用来比较的父级id的值
					//遍历并生成二级菜单
					$.each(secondMenu, function(index,item) {
						//如果当前菜单对象的父亲，等于一级菜单的code则生成二级菜单
						if(firstMenuCode==item.fk_parent_id){
						var dd_a=$("<a class='li_item' data-id='"+item.code+"' data-url='"+item.url+"'  ><i class='"+item.icon+"' aria-hidden='true'></i>&nbsp;"+item.name+"</a>");
						var	dd=$("<dd></dd>")						
						dd.append(dd_a);
						dl.append(dd);
						}					
					});
				li.append(a);
				li.append(dl);
				//将添加好的菜单一次性添加到页面的ul上去
				$(".menu_ul").append(li);
			});
			//下拉
			element.render();
		}
	find_myMenu();
	});	 
	 
});	
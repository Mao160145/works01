layui.use('form', function(){
	var form = layui.form;
	form.render();		

	function login(account,passWord){

		$.ajax({
			type:"post",//前端往后台发送的请求
			url:"/admin/login",//请求的接口路径
			async:true,//表明ajax是一个同步请求false还是一个异步请求true
			data:{
				"admin_account":account,
				"admin_password":passWord
			},
			//ajax执行之前才会回调函数
			"beforeSend":function(){
				layer.load(1,{
					shade:[0.5,'black']//0.5透明度的黑色背景
				});
			},
			//ajax执行成功才会回调函数
			"success":function(result){
				//result为ajax响应的数据
				console.log(result);
				if(result.resultCode==0){
					sessionStorage.setItem("admin_id",result.data.admin_id);
					sessionStorage.setItem("admin_name",result.data.admin_name);
					sessionStorage.setItem("admin_url",result.data.admin_url);
					location.href="index.html";
					//return false;
				}else{
					layer.msg("登录失败",{
						icon:2,
						time:1000
					});
				}
			},
			"error":function(){
				layer.confirm("服务器崩溃了，请重新启动页面",{
					icon:2
				})
			}
		});

	}

	form.on('submit(login)',function(){
		var account=$("#account").val();
		var passWord=$("#passWord").val();
        login(account,passWord);
        return false;//阻止表单跳转，如果需要表单跳转，去掉这段即可
		// //加密字符串
		// var rsa_password = rsaSecurity(passWord);
		// login(account,rsa_password);
		// login(account,passWord);
		// console.log(account);console.log(rsa_password);console.log(passWord);
		// if($("#remember").is(":checked")){
		// 	localStorage.setItem("account",account);
		// 	localStorage.setItem("passWord",passWord);
		// }else{
		// 	localStorage.setItem("account","");
		// 	localStorage.setItem("passWord","");
		//
		// }

	});
	
	function remembers(){
		if(sessionStorage.getItem("account")!=""&&sessionStorage.getItem("passWord")!=""){
			$("#account").val(sessionStorage.getItem("account"));
			$("#passWord").val(sessionStorage.getItem("passWord"));
			$("#remember").prop("checked",true);
		}
		form.render();
	}
	remembers();
	
	//密码加密
//	function rsaSecurity(password){
//		var rsa_str;
//		$.ajax({
//			type:"get",
//			url:"http://localhost:8080/rsa/generateRSAJsKey",
//			async:false,
//			"success":function(result){
//				//从服务端获取加密模板
//				var modulus = result.split(";")[0];
//				//获取公钥指数
//				var public_exponent = result.split(";")[1];
//				//公钥指数  加密模
//				var public_key = new RSAUtils.getKeyPair(public_exponent,"",modulus)
//				//对登录password颠倒
//				var rsa_pass = password.split("").reverse().join("");
//				//获取到加密字符串  参数（公钥，颠倒后的密码）
//				rsa_str = RSAUtils.encryptedString(public_key,rsa_pass);
//				console.log(rsa_str);
//			}
//		});
//		return rsa_str;
//	}
	
	//添加验证码
	function addUnlock(){
		//定义一个英文字母的数组
		var engLish=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
		//定义一个字母
		var attr=[];
		//定义最终的验证码的值
		var code="";
		for(var i=0;i<2;i++){
			var ranNum = Math.ceil(Math.random() * 25); 
			if(attr.indexOf(ranNum)==-1){
				attr.push(ranNum);				 	
			}else{
				i--;
			}	 	
		}
			
		//code+=engLish[ranNum];
		for (var i=0;i<2;i++) {
			code+=engLish[attr[i]];
		
		}
			
		$(".captcha_gbws_wrap").remove();
		$(".login").buttonCaptcha({
			codeWord:code,//匹配验证码
			codeZone:code,
			captchaHeader:"请正确的将字符移动到框内", //问题上面的验证码
			captchaUnlocked:"通过验证" //文本时验证码解锁的头
		});
		$(".captcha_gbws_wrap").appendTo("#verifyGroup");
	}
	addUnlock();
		
});	
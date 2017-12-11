	var U= location.href;
  	var startU=U.indexOf("act=");
  	var endU=U.length;
    var Uact = U.substr(startU+4,endU);
    console.log(Uact);
    switch(Uact){
    	case "myorder":
    		$(".user_content_r_order").css("display","block");
    		break;
    	case "myaddress":
    		$(".user_content_r_address").css("display","block");
    		break;
    	case "mycollect":
    		$(".user_content_r_order").css("display","block");
    		break;
    	case "user_profile":
    		$(".user_content_r_stn").css("display","block");
    		break;
    	case "personal_data":
    		$(".user_content_r_person").css("display","block");
    		break;
    	default:
    		$(".user_content_r_stn").css("display","block");
    		break;
    }
// 获取session
$.ajax({
	type:"get",
	url:"http://localhost:8090/user/getSession.do",
	async:true,
	dataType:"json",
	success:function(data){
		console.log(data);
		Uid = data.uId;
		// 填入信息 ——> 个人资料
		personinfo(data);
		// 填入信息 ——> 账户概况
		AccountProfile(data)
		// 个人资料 ——> 修改密码
		$(".edit_pwd").click(function(){
			$(".user_change_box").css("display","block");
			$(".change_password").css("display","block");
			revisepassword(data.password);
		});
		// 个人资料——> 修改用户名
		$(".edit_UN").click(function(){
			$(".user_change_box").css("display","block");
			$(".change_userName").css("display","block");
			$("#username_form label")[0].innerHTML = "原用户名："+data.username;
			reviseuserName(data.uId);
		})
		// 个人资料 ——》修改个人头像
		$(".img_p").on("click",function(){
			$(".user_change_pic_box").css("display","block");
		})
		$(".avatar_save").on("click",function(){
			changeHeadImg();
		})
		// 个人资料 ——> 保存修改
		savePersonalData(data);
	}
});
//  将信息填入个人资料
function personinfo(data){
	$(".user-name").html(data.username);
	$(".phoneNumber").html(data.phone);
	$(".img_p img").attr("src",data.headImgUrl);
	console.log(data.sex)
	switch(data.sex){
		case "secret":
			$("input[value='secret']").prop("checked",true);
			break;
		case "man":
			$("input[value='man']").prop("checked",true);
			break;
		case "woman":
			$("input[value='woman']").prop("checked",true);
			break;
	}
}
// 修改个人头像
function changeHeadImg(){
    var fd = new FormData();
    fd.append("files", $("#Imgfiles").get(0).files[0]);
    $.ajax({
		type:"post",
		url:"http://localhost:8090/user/upload.do",
		async:true,
		dataType:"json",
		data:fd,
		processData:false,
		contentType: false,
		success:function(data){
			console.log(data)
			if (data) {
				window.location.href="http://139.199.11.183:8080/Seven_Two/resource/views/sevenAddtwo/html/user.html?act=personal_data";
			}
		}
	});
}

/* --------------------------点击账户方式-------------------------------*/
// $("#user_stn").click(function(){
// 	$(".user_content_r_tab_list").css("display","none");
// 	$(".user_content_r_stn").css("display","block");
// })
// 账户概况 ——> 编辑个人资料
// $(".user_editData").on("click",function(){
// 	$(".user_content_r_stn").css("display","none");
// 	$(".user_content_r_person").css("display","block");
// })
$(".user_change_pic_closeBtn").click(function(){
	$(".user_change_pic_box").css("display","none");
})
//  账户概况 ——》查看全部订单
$(".look_Allorder").on("click",function(){
	$(".user_content_r_stn").css("display","none");
	$(".user_content_r_order").css("display","block");
})
/*------------------------------点击个人资料------------------------------*/ 
// $("#user_preson").click(function(){
// 	$(".user_content_r_tab_list").css("display","none");
// 	$(".user_content_r_person").css("display","block");
// })

// 账户概况
function AccountProfile(data){
	$(".loadIP").html("登录IP："+data.addressIp);
	$(".user_banner_tit").html("欢迎,"+data.username);
	$(".lastLoadDate").html("最后登录："+data.loginDate)
}
/*******************点击修改密码***********/
// 修改密码
function revisepassword(pwd){
	// 旧密码
	$("input[name='old_password']").blur(function(){
		if ($("input[name='old_password']").val()!=pwd) {
			alert("密码不正确,请重新输入密码");
		}
	})
	$("input[name='new_password']").blur(function(){
		// 前台判断修改后的密码长度，长度正确才能触发提交事件
		if(($("input[name='new_password']").val().length< 6 && $("input[name='new_password']").val().length>0) || $("input[name='new_password']").val().length>20){
			alert("您的密码长度不正确，应在6-20之间");
		}else{
			$("#sub").on("click",function(){
				if ($("input[name='old_password']").val() == pwd && $("input[name='new_password']").val()==$ ("input[name='confirm_password']").val()) {
					console.log($("input[name='old_password']").val())
					console.log($("input[name='new_password']").val())
					$.ajax({
						type:"get",
						url:"http://139.199.11.183:8080/Seven_Two/user/updatePassword.do",
						async:true,
						dataType:"json",
						data:{
							oldPassword:$("input[name='old_password']").val(),
							password:$("input[name='new_password']").val()
						},
						success:function(data){
								console.log(data)
							if (data) {
								window.location.href="http://139.199.11.183:8080/Seven_Two/resource/views/sevenAddtwo/html/user.html";
							}
						}
					})
				}else{
					if ($("input[name='new_password']").val()!=$ ("input[name='confirm_password']").val()) {
						alert("两次输入的密码不相符")
					}
				}
			})
		}
		
	});
	
	
}
// 个人资料 ——> 保存修改
function savePersonalData(data){
	$("#button2").on("click",function(){
		var options1 = $("#year option:selected");
		var options2 = $("#month option:selected");
		var options3 = $("#day option:selected");
		var years =options1.val();
		var months =options2.val();
		var days = options3.val();
		var birthDate = '';
		// 生日
		BirthDate = years+"-"+months+"-"+days;
		// 真实姓名
		var Name=$("#real_name").val();
		// 性别
		var Sex = '';
		for(var i=0;i<$("input[name='gender']").length;i++){
			if ($("input[name='gender']")[i].checked) {
				Sex=$("input[name='gender']")[i].value;
			}
		}
		var QQ = $("input[name='im_qq']").val();
		$.ajax({
			type:"post",
			url:"http://139.199.11.183:8080/Seven_Two/user/updateUserInfo.do",
			async:true,
			dataType:"json",
			data:{
				uId:data.uId,
				username:data.username,
				name:Name,
				sex:Sex,
				birthDate:BirthDate,
				qq:QQ
			},
			success:function(data){
				console.log(data);
				if (data) {
					window.location.href="http://139.199.11.183:8080/Seven_Two/resource/views/sevenAddtwo/html/user.html";
				}
			}
		})
	})
}

// 个人资料 ——> 修改用户名
function reviseuserName(uId){
	$("#new_username").blur(function(){
		$.ajax({
			type:"post",
			url:"http://139.199.11.183:8080/Seven_Two/user/findUserByName.do",
			async:true,
			dataType:"json",
			data:{username:$("#new_username").val()},
			success:function(data){
				if (!data) {
					alert("改名字已使用，请重新输入");
					$("#new_username").val("");			
				}else{
					$("#submit").on("click",function(){
						$.ajax({
							type:"get",
							url:"http://139.199.11.183:8080/Seven_Two/user/updateUserName.do",
							async:true,
							dataType:"json",
							data:{
								username:$("#new_username").val(),
								uId:uId
							},
							success:function(data){
								console.log(data);
								if (data) {
									window.location.href="http://139.199.11.183:8080/Seven_Two/resource/views/sevenAddtwo/html/user.html";
								}
							}
						})
					})
				}
			}
		})
	})
}
/***********************修改用户名****************/
 
// $(".close_layer").click(function(){
// 	$(".pop").css("display","none");
// 	$(".user_change_box").css("display","none");
// 	$("input").val("");
// })

<%@ include file="../common/IncludeTop.jsp"%>

${sessionScope.messageAccount}

<style>
	.okTips{
		color : green;
	}
	.errorTips {
		color : red;
	}
</style>
<script>
	var xhr;
	function checkUsername() {
		var username = document.getElementById("username").value;
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = f1;
		function f1() {
			if (xhr.readyState === 4)
				if (xhr.status === 200) {
					var tips = document.getElementById("usernameTips");
					let responseInfo = xhr.responseText;
					if (responseInfo === "Exist") {
						tips.className = "errorTips";
						tips.innerText = "Invalid";
					} else if (responseInfo === "Not Exist") {
						tips.className = "okTips";
						tips.innerText = "Available";
					}

				}
		}
		xhr.open("GET", "usernameExist?username=" + username, true);
		xhr.send(null);
	}

	function checkPasswordSame() {
		var pw1 = document.getElementById("password").value;
		var pw2 = document.getElementById("repeatedPassword").value;

		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = f1;
		function f1() {
			if (xhr.readyState === 4)
				if (xhr.status === 200) {
					var tips = document.getElementById("repeatedPasswordTips");
					let responseInfo = xhr.responseText;
					if (responseInfo === "Not Same") {
						tips.className = "errorTips";
						tips.innerText = "Not Same";
					} else if (responseInfo === "Same") {
						tips.className = "okTips";
						tips.innerText = "Same";
					}

				}
		}
		xhr.open("GET", "passwordSame?pw1=" + pw1 + "&pw2=" + pw2, true);
		xhr.send(null);
	}
</script>
<div id="Catalog">
	<form action="newAccount" method="post">
		<h3>User Information</h3>

		<table>
			<tr>
				<td>User ID:</td>
				<td>
					<input id="username" type="text" name="username" onblur="checkUsername();"/>
					<span id="usernameTips"></span>
				</td>
			</tr>
			<tr>
				<td>New password:</td>
				<td><input id="password" type="text" name="password" /></td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td>
					<input id="repeatedPassword" type="text" name="repeatedPassword" onblur="checkPasswordSame()"/>
					<span id="repeatedPasswordTips"></span>
				</td>

			</tr>
			<tr>
				<td>VerificationCode:</td>
				<td>
					<input type="text" name="vCode" size="5" maxlength="4"/>
					<a href="newAccount"><img border="0" src="verificationCode" name="checkcode"></a>
				</td>
			</tr>
		</table>

		<%@ include file="../account/IncludeAccountFields.jsp"%>
		<input type="submit" name="newAccount" value="Save Account Information" />
	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
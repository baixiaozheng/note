<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Certification System&nbsp;-&nbsp;Login</title>
	</head>
	<body>
		<form action="/auth/doLogin" method="post">
			<input type="hidden" name="action" value="login" />
			<input type="hidden" name="gotoURL" value="${param.gotoURL }" />
			<input type="hidden" name="setCookieURL" value="${param.setCookieURL }" />
			<table>
				<tr>
					<td>
						username:
					</td>
					<td>
						<input type="text" name="username" />
					</td>
				</tr>
				<tr>
					<td>
						password:
					</td>
					<td>
						<input type="password" name="password" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<input type="submit" value="Login" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
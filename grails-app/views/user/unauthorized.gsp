<!DOCTYPE html>
<html>
<head>
    <title>Unauthorized Access</title>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
</head>
<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>
<ul class="errors">
    <li>You are not authorized to access this area</li>
</ul>
</body>
</html>

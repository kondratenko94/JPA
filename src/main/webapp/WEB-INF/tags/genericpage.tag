<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <sec:authentication var="principal" property="principal" />

        <link rel="stylesheet" href="/resources/css/main.css" type="text/css">
        <link href="/resources/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>

<body>

    <script>
        var id = '${principal.id}' != '' ? ${principal.id} : 0;
        var email = '${principal.email}';
        var firstName = '${principal.firstName}';
        var lastName = '${principal.lastName}';
        var role = '${principal.role}';

        var user = new User(id, firstName, lastName, email, role);
    </script>

    <div class="container">

        <div class="row">
            <div class="col-lg-6" style="text-align: left; margin-top: 10px">
                <a href="/project/">Проекты</a>
            </div>

            <div class="col-lg-6" style="text-align: right; margin-top: 10px">
                <span>${principal.firstName} ${principal.lastName} (${principal.email}) <a href="/logout">Выйти</a></span>
            </div>

        </div>

        <div class="row">
            <div class="col-lg-12">
                <div id="body">
                    <jsp:doBody/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div>
                    <p style="color: #586069; font-size: 12px; text-align: center">© 2018 TaskTracker</p>
                </div>
            </div>
        </div>
    </div>


</body>
</html>
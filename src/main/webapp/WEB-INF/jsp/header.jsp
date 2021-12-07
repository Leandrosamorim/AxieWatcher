<%-- 
    Document   : header
    Created on : 4 de dez. de 2021, 16:15:16
    Author     : 55229
--%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
        crossorigin="anonymous">

    <script 
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" 
    crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">


    <style>
        body {
            width: 95vw;
            margin: 10px auto;
        }
        .topnav{
            background-color: #333;
            overflow: hidden;
        }
        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }
        .topnav a img{
            max-height: 53px;
            max-width: 72px;
            transform: scale(1);
        }

        /* Change the color of links on hover */
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        /* Add a color to the active/current link */
        .topnav a.active {
            background-color: #3e7292;
            color: white;
        }
        .topnav #user{
            background-color: white;
            color: black !important;
        }
    </style>

    <title>Header</title>
</head>

<body>
    <div class="topnav">
        <a class="active" href="/">Home</a>
        <a href="/recent">Recently Listed</a>
         <c:choose>
            <c:when test="${user != null}">
                <a href="/fav">My favorites</a>
            </c:when>
            <c:otherwise>
                <a href="/register">Register</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${user != null}">
                <a><img src="https://leandrobucket.s3.sa-east-1.amazonaws.com/${user.id}" style=""/></a>
                <a id="user">${user.username}</a>
                <a href="/auth/signout">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="/auth/signin">Login</a>
            </c:otherwise>
        </c:choose>
    </div>
</body>

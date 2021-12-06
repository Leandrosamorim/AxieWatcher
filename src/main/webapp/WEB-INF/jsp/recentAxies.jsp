<%-- 
    Document   : recentAxies
    Created on : 4 de dez. de 2021, 13:04:54
    Author     : 55229
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Recently Listed Axies</title>
            <style>
                body  {
                    font-weight: 500 !important;
                    color: white !important;
                    background-color: #242735 !important;

                }
                img{
                    max-width: 64px; transform: scale(1.9);
                }
                table{
                    color: #FFFFFF !important;
                }


            </style>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <script>
                function saveA(id) {
                    window.addEventListener('DOMContentLoaded', function () {
                        callFUnction()
                    });
                    var id = "#" + id;
                    $.post("/fav", $(id).serialize());
                }

                function sendRequestform(id, image, name, class1, price) {
                    console.log("teste");
                    $.ajax({
                        type: 'post',
                        url: 'fav',
                        data: {"id": id, "image": image, "name": name, "class1": class1, "price": price}, //append any other elements if you want to send
                        success: function (data) {
                            console.log(JSON.strigify(data));
                            $('#formid').html(data);
                            $('body').append(data);
                        }
                    });
                }
            </script>
        </head>
        <body>
            <%@include file="header.jsp" %>
            <h1>Recently Listed</h1>
            <c:if test="${axies.size() == 0}">
                <div class="alert alert-danger" role="alert">
                    No axies listed recently.
                </div>
            </c:if>
            <table class="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Img</th>
                        <th>Name</th>
                        <th>Class</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="axie" items="${axies}">
                        <tr>
                            <td>${axie.getId()}</td>
                            <td><img src="${axie.getImage()}"></td>
                            <td>${axie.getName()}</td>
                            <td>${axie.getClass1()}</td>
                            <td>${axie.getPrice()}</td>
                            <c:if test="${user != null}">
                                <td>
                                    <form id="${axie.getId()}" method="POST" th:action="@{/fav}" target="_blank"> 
                                        <input type="hidden" value="${axie.getId()}" name="id"/>
                                        <input type="hidden" value="${axie.getImage()}" name="image"/>
                                        <input type="hidden" value="${axie.getName()}" name="name"/>
                                        <input type="hidden" value="todo" name="price"/>
                                        <input type="hidden" value="${axie.getClass1()}" name="class1"/>
                                        <input type="hidden" value="${axie.getOwner()}" name="owner"/>
                                        <input type="hidden" value="${axie.getBreedCount()}" name="breedCount"/>

                                        <!-- ADD ALL THE OTHER FIELDS THAT ARE PART OF THE OBJECT -->
                                        <input type="button" class="bi bi-heart" onclick="saveA(${axie.getId()}) "/>
                                    </form>
                                    
                                </td>

                            </c:if>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </body>

    </html>
</f:view>

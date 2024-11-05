<%@ page import="top.soft.bookonline.entity.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书详情页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <style type="text/css">
        h2, h3 {
            color: rgb(73, 73, 73);
        }

        #search {
            height: 80px;
            background-color: rgb(246, 246, 241);
            display: flex;
            align-items: center;
            padding-left: 8%;
            margin-bottom: 10px;
        }

        .search-input {
            flex: 0 0 40%;
            height: 35px;
            background-color: #fff;
            border: none;
            border-radius: 3px;
            margin-left: 50px;
        }

        .search-btn {
            width: 35px;
            height: 35px;
            background-color: rgb(155, 154, 143);
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .search-btn img {
            width: 50%;
            height: 50%;
        }

        .col-4 img {
            margin: 10px 5px 20px 5px;
            width: 80%;
        }

        hr {
            width: 90%;
            color: #eee;
            margin-top: 10px;
        }

        .col-6 {
            height: 400px;
            padding-right: 10px;
        }

        .col-6 img {
            width: 100%;
            height: 100%;
            border-radius: 20px;
        }
    </style>
</head>
<body>
    <%
    Book book = (Book) request.getAttribute("book");
    pageContext.setAttribute("book", book);
%>
<div id="top">
    <jsp:include page="top.jsp"/>
</div>
<div id="search">
    <h2>读书时刻</h2>
    <input type="text" placeholder="书名、作者、ISBN" class="search-input">
    <div class="search-btn">
        <img src="${pageContext.request.contextPath}/images/search.png">
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-8">
            <p style="font-weight: bold;font-size: 22px;color: #232525">${book.name}</p>
            <hr>
            <div class="row">
                <div class="col-4">
                    <img src="${book.cover}" alt="">
                </div>
                <div class="col-6">
                    <p style="color: #12266c">${book.author}</p>
                </div>
                <div class="col-6">
                    <p style="color: #dd4646">${book.price}</p>
                </div>
                <div class="col-6">
                    <p style="color: #ca4646">${book.content}</p>
                </div>
            </div>
        </div>
        <div class="col-4">
            <h3>热门标签</h3>
            <hr>
            <img src="${pageContext.request.contextPath}/images/right.png">
        </div>
    </div>
</div>

<header>
    <nav>
        <div>
            <a href="./">Home</a>
            <a href="./account">Account</a>
        </div>
        <div>
            <%
                if (session.getAttribute("auth") != null) {
            %>
            <a href="./singout">Singout</a>
            <%  } else { %>
            <a href="./singin">Singin</a>
            <%
                }
            %>
        </div>
    </nav>
</header>
